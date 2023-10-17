<template>
    <v-app id="rutines">
      <v-main color="background">
        <v-container class="pt-15">
          <v-row align="center" justify="center">
            <v-btn  size="x-large" variant="outlined" color="secondary" to=" /">Crear nueva rutina <v-icon icon="mdi-plus" size="x-large"></v-icon></v-btn>
          </v-row>
          <h1 class="pt-5">Mis rutinas:</h1>
          <MyRout :items="recientes"/>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script setup>
      import MyRout from '@/components/RoutineIter.vue';
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

