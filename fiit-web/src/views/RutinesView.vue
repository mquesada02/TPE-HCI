<template>
    <v-app id="rutines">
      <v-main color="background">
        <v-container>
          <FavsRec v-if="1===2" title="Recientes" :imgs="recientes"/>

          <v-btn color="secondary" to=" /">Crear nueva rutina +</v-btn>
          <Myfavs :items="recientes"/>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script setup>
      import FavsRec from '@/components/FavsRec.vue';
      import Myfavs from '@/components/MyFavs.vue';
      const recientes = [
    { src: 'abdominales bicicleta.webp', title: 'Abdominales en bicicleta' },
    { src: 'abductores.jpg', title: 'Abductores' },
    { src: 'dominadas.jpg', title: 'Dominadas' },
    { src: 'elevaciones laterales.jpeg', title: 'Elevaciones laterales' },
    { src: 'estiramiento abductores.jpg', title: 'Estiramiento de abductores' },
    { src: 'abdominales bicicleta.webp', title: 'Abdominales en bicicleta' },
    { src: 'abductores.jpg', title: 'Abductores' },
    { src: 'dominadas.jpg', title: 'Dominadas' },
    { src: 'elevaciones laterales.jpeg', title: 'Elevaciones laterales' },
    { src: 'estiramiento abductores.jpg', title: 'Estiramiento de abductores' }
  ];
  </script>

