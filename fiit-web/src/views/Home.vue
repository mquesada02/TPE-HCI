<template>
    <v-app id="home">
      <Navbar />
      <v-main class="bg-background">
        <v-container>
          <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
            <p class="text-h1 font-weight-bold text-center drop-shadow-lg">
              CONCENTRACIÓN
              ADAPTACIÓN
              ENTRENAMIENTO
            </p>
          </v-img>
        </v-container>
        <v-container>
          <SmallCarousel :imgs="imgs"/>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
<style scoped>
    .custom-class {
        background: rgb(var(--v-theme-background));
    }
</style>

<script setup>
  import Navbar from '@/components/Navbar.vue';
  import FullCarousel from '@/components/FullCarousel.vue';
  import SmallCarousel from '@/components/SmallCarousel.vue';

  const imgs = ['homepage-bg.png','homepage-bg.png','homepage-bg.png','homepage-bg.png','homepage-bg.png','homepage-bg.png','homepage-bg.png','homepage-bg.png','homepage-bg.png'];
</script>

<script>
  export default {
    data: () => ({ drawer: null }),
  }
</script>