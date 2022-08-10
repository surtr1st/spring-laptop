const { createApp } = Vue
import App from './App.js'
import Dashboard from "./components/Dashboard.js"
import Product from './components/Product.js'
import Account from './components/Account.js'

const routes = [
    {
        path: '/',
        component: Dashboard
    },
    {
        path: '/dashboard',
        component: Dashboard
    },
    {
        path: '/products',
        component: Product
    },
    {
        path: '/accounts',
        component: Account
    }
]

const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes
})


createApp(App)
    .use(router)
    .mount('#app')