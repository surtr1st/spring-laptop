package com.ps17931.controller;

import com.ps17931.domain.Cart;
import com.ps17931.domain.Product;
import com.ps17931.domain.Receipt;
import com.ps17931.service.CartService;
import com.ps17931.service.ProductService;
import com.ps17931.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("cart")
public class CartController {

    private final CartService service;
    private final ProductService productService;
    private final ReceiptService receiptService;
    private final HttpServletRequest request;

    @Autowired
    private CartController(CartService service, HttpServletRequest request, ProductService productService, ReceiptService receiptService) {
        this.service = service;
        this.request = request;
        this.productService = productService;
        this.receiptService = receiptService;
    }

    @ModelAttribute("carts")
    public List<Cart> carts() {
        return service.retrieveCart(request.getRemoteUser());
    }
    private Product getProduct(int id) {
        return productService.findById(id);
    }

    private Cart retrieveProduct(String id) {
        return service.findById(id);
    }

    @RequestMapping()
    public String view(Model model) {
        model.addAttribute("hasItem", carts().size());
        return "cart/view";
    }

    @RequestMapping("/add/{id}")
    public String addCart(@PathVariable("id") int id) {
        Optional<Cart> items = service.findByProdId(id);

        if (items.isEmpty()) {
            Cart cart = new Cart();
            cart.setName(getProduct(id).getName());
            cart.setPrice(getProduct(id).getPrice());
            cart.setQuantity(getProduct(id).getQuantity());
            cart.setImage(getProduct(id).getImage());
            cart.setUsername(request.getRemoteUser());
            cart.setProduct(getProduct(id));
            service.addCart(cart);
        }

        if(items.isPresent()) {
            items.get().setQuantity(items.get().getQuantity() + 1);
            items.get().setPrice(items.get().getQuantity() * items.get().getPrice());
            return "forward:/cart";
        }

        return "redirect:/cart";
    }

    @PostMapping("/update/{cart-id}/inc")
    public String updateByIncrease(@PathVariable("cart-id") String cartId, @RequestParam("quantity") int quantity) {
        quantity++;
        double price = retrieveProduct(cartId).getProduct().getPrice();
        service.updateQuantity(quantity, cartId);
        service.updatePrice(price * quantity, cartId);
        return "redirect:/cart";
    }

    @PostMapping("/update/{cart-id}/dec")
    public String updateByDecrease(@PathVariable("cart-id") String cartId, @RequestParam("quantity") int quantity) {
        quantity--;
        double price = retrieveProduct(cartId).getProduct().getPrice();
        if (quantity == 0)
            removeCart(cartId);
        service.updateQuantity(quantity, cartId);
        service.updatePrice(price * quantity, cartId);
        return "redirect:/cart";
    }

    @RequestMapping("/remove/{cart-id}")
    public String removeCart(@PathVariable("cart-id") String id) {
        service.deleteCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkOutCart(Model model) {
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        if(address.isBlank() || phoneNumber.isBlank()) {
            model.addAttribute("msg", "Please enter your address and phone number for contact!");
            return "forward:/cart";
        }

        Date date = new Date();
        String createdDate = new SimpleDateFormat("dd/MM/yyyy").format(date);

        service.findAll().forEach((cart) -> {
            Receipt info = new Receipt();
            info.setImage(cart.getImage());
            info.setName(cart.getName());
            info.setPrice(cart.getPrice());
            info.setQuantity(cart.getQuantity());
            info.setUsername(cart.getUsername());
            info.setAddress(address);
            info.setPhoneNumber(phoneNumber);
            info.setCheckoutDate(createdDate);
            receiptService.saveReceipt(info);
        });
        service.deleteAll();

        return "redirect:/cart";
    }
}
