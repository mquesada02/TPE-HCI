<template>
  <v-app id="Favorites">
    <v-main color="background">
      <v-container>
        <FavsRec title="Recientes" :imgs="recientes"/>
        <h1 class="pt-5">Mis favoritos:</h1>
        <Myfavs :items="recientes2" :text="texto"/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
    import FavsRec from '@/components/Recently.vue';
    import Myfavs from '@/components/RoutineIter.vue';
    const texto = "No has agregado ninguna rutina a favoritos"
    const recientes = [
    { src: 'abdominales bicicleta.webp', title: 'Abdominales en bicicleta' },
    { src: 'abductores.jpg', title: 'Abductores' },
    { src: 'dominadas.jpg', title: 'Dominadas' },
    { src: 'elevaciones laterales.jpeg', title: 'Elevaciones laterales' }
  ];
  const recientes2 = [
    ];
</script>