const { createApp } = Vue
import App from './App.js'
import Info from './components/Info.js'
import Password from './components/Password.js'
import BoughtItem from "./components/BoughtItem.js"

const routes = [
    {
        path: '/',
        component: Info
    },
    {
        path: '/info',
        component: Info
    },
    {
        path: '/password',
        component: Password
    },
    {
        path: '/bought-item',
        component: BoughtItem
    }
]

const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes
})


createApp(App)
    .use(router)
    .mount('#app')