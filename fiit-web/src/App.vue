<template>
  <v-app>
    <v-main>
      <Navbar />
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script setup>
  import Home from '@/views/Home.vue'
  import InicioSesion from '@/views/InicioSesion.vue'
  import Search from '@/views/Search.vue'
  import Register from '@/views/Register.vue'
  import Navbar from './components/Navbar.vue';
  import Favs from '@/views/Favorites.vue'
</script>
