import { createRouter, createWebHistory } from 'vue-router'

// Views
import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import SearchView from '@/views/SearchView.vue'
import FavouritesView from '@/views/FavoritesView.vue'
import ProfileView from '@/views/ProfileView.vue'
import NotFoundView from '@/views/NotFoundView.vue'
import VerifyEmailView from '@/views/VerifyEmailView.vue'

const routes = [
    { 
    path: '/', 
    name: 'home', 
    component: HomeView 
    },
    { 
    path: '/login', 
    name:'login', 
    component: LoginView 
    },
    { 
    path: '/register', 
    name:'register', 
    component: RegisterView 
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
    path: '/verify',
    name: 'verify',
    component: VerifyEmailView
    },
    { 
    path: '/:pathMatch(.*)*',
    name: 'notfound',
    component: NotFoundView 
    }
  ]

const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHistory(), //import.meta.env.BASE_URL como parametro??
    routes, // short for `routes: routes`
})

export default router