/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'

// Plugins
import { registerPlugins } from '@/plugins'
import { createRouter, createWebHashHistory } from 'vue-router'
import  Home  from '@/views/Home.vue'
import InicioSesion from '@/views/InicioSesion.vue'
import Register from '@/views/Register.vue'
import Search from '@/views/Search.vue'

const routes = [
    { path: '/', component: Home },
    { path: '/login', component: InicioSesion },
    { path: '/register', component: Register},
    { path: '/search', component: Search}
  ]
  

const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

const app = createApp(App)

registerPlugins(app)

app.use(router)

app.mount('#app')
