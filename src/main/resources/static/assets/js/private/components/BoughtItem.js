const REST_URL = 'http://localhost:8080/rest'

export default {
    name: 'BoughtItem',
    data() {
        return {
            src: '../../images/products/',
            receipts: {}
        }
    },
    created() {
        fetch(`${REST_URL}/receipt/list`)
            .then(async res => {
                this.receipts = await res.json()
            })
            .catch(e => console.log(e))
    },
    template: /*html*/
    `
    <div class="row col-xl-10 col-lg-10">
		<table class="table table-hover bg-white rounded rounded-5">
			<thead class="bg-primary text-white text-center">
				<tr>
					<th>Id</th>
					<th>Product</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Address Sent</th>
					<th>Checkout Date</th>
					<th>Image</th>
				</tr>
			</thead>
			<tbody class="text-center align-middle overflow-auto">
			    <tr v-for="(rec, index) in receipts" :key="index">
			        <td>{{ rec.rid }}</td>
                    <td>{{ rec.name }}</td>
                    <td>{{ rec.price }}</td>
                    <td>{{ rec.quantity }}</td>
                    <td>{{ rec.address }}</td>
                    <td>{{ rec.checkoutDate }}</td>
                    <td>
                        <img :src="src + rec.image" class="img-fluid" style="max-width: 5rem;">
                    </td>
                </tr> 
			</tbody>
        </table>
    </div>
    `
}