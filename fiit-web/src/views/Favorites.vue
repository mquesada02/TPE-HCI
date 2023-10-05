<template>
  <v-app id="Favorites">
    <v-main color="background">
      <v-container>
        <FavsRec title="Recientes" :imgs="recientes"/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
    import FavsRec from '@/components/FavsRec.vue';
    const recientes = [
  { src: 'abdominales bicicleta.webp', title: 'Abdominales en bicicleta' },
    { src: 'abductores.jpg', title: 'Abductores' },
    { src: 'dominadas.jpg', title: 'Dominadas' },
    { src: 'elevaciones laterales.jpeg', title: 'Elevaciones laterales' },
    { src: 'estiramiento abductores.jpg', title: 'Estiramiento de abductores' }
  ];
</script>

