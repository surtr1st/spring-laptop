const about = document.getElementById('about')
const contact = document.getElementById('contact')

about.addEventListener('click', () => {
    window.scrollBy({
        top: 1500,
        left: 0,
        behavior: "smooth"
    })
})

contact.addEventListener('click', () => {
    window.scrollBy({
        top: 3000,
        left: 0,
        behavior: "smooth"
    })
})