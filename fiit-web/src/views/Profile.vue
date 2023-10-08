<template>
  <v-app>
    <v-container fill-height>
      <v-row align="center" justify="center">
        <v-col cols="4">
        <container >
          <Perfil />
          </container>
        </v-col>
        <v-col cols="2">
        </v-col>
        <v-col cols="6">
        <container >
           <SmallCarousel title="Mis rutinas:" :imgs="destacadas"/>
        <SmallCarousel title="Favoritas:" :imgs="recientes"/>
        </container>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script setup>

    import Perfil from '@/components/Perfil.vue';
    import FullCarousel from '@/components/FullCarousel.vue';
  import SmallCarousel from '@/components/SmallCarousel.vue';

 const destacadas = [
    { src: 'abdominales bicicleta.webp', title: 'Abdominales en bicicleta' },
    { src: 'abductores.jpg', title: 'Abductores' },
    { src: 'dominadas.jpg', title: 'Dominadas' },
    { src: 'elevaciones laterales.jpeg', title: 'Elevaciones laterales' },
    { src: 'estiramiento abductores.jpg', title: 'Estiramiento de abductores' },
    { src: 'fondo triceps.webp', title: 'Fondo de tríceps' },
    { src: 'peso muerto.jpg', title: 'Peso muerto' },
    { src: 'sentadillas.jpg', title: 'Sentadillas' },
    { src: 'zancadas.webp', title: 'Zancadas' }
  ];
  const recientes = [
  { src: 'abdominales bicicleta.webp', title: 'Abdominales en bicicleta' },
    { src: 'abductores.jpg', title: 'Abductores' },
    { src: 'dominadas.jpg', title: 'Dominadas' },
    { src: 'elevaciones laterales.jpeg', title: 'Elevaciones laterales' },
    { src: 'estiramiento abductores.jpg', title: 'Estiramiento de abductores' },
    { src: 'fondo triceps.webp', title: 'Fondo de tríceps' },
    { src: 'peso muerto.jpg', title: 'Peso muerto' },
    { src: 'sentadillas.jpg', title: 'Sentadillas' },
    { src: 'zancadas.webp', title: 'Zancadas' }
  ];
  </script>
