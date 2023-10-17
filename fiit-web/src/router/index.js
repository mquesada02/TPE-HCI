import { createRouter, createWebHistory } from 'vue-router'

// Views
import HomeView from '@/views/HomeView.vue'
import SearchView from '@/views/SearchView.vue'
import FavouritesView from '@/views/FavoritesView.vue'
import ProfileView from '@/views/ProfileView.vue'

const routes = [
    { 
    path: '/', 
    name: 'home', 
    component: HomeView 
    },
    { 
    path: '/login', 
    name:'login', 
    component: () => import('@/views/LoginView.vue')
    },
    { 
    path: '/register', 
    name:'register', 
    component: () => import('@/views/Register.vue')
    },
    { 
    path: '/search', 
    name:'search', 
    component: SearchView 
    },
    { 
    path: '/favourites', 
    name:'favourites', 
    component: FavouritesView 
    },
    { 
    path: '/profile', 
    name:'profile', 
    component: ProfileView 
    },
    { 
    path: '/:pathMatch(.*)*',
    name: 'notfound',
    component: () => import('@/views/NotFoundView.vue') //pagina poco frecuente -> optimizacion (no es necesario tenerla desde un principio)
    }
  ]

const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHistory(), //import.meta.env.BASE_URL como parametro??
    routes, // short for `routes: routes`
})

export default router