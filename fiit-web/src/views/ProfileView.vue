<template>
  <v-app>
    <ProfileCard />
  </v-app>
</template>

<script setup>

  import Profile from '@/components/Profile.vue';
import ProfileCard from '@/components/ProfileCard.vue';
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
