<template>
  <v-app id="home">
    <Navbar />
    <v-main color="background">
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
        <SmallCarousel title="Destacadas" :imgs="destacadas"/>
        <SmallCarousel title="Recientes" :imgs="recientes"/>
        <SmallCarousel title="Recomendaciones para tí" :imgs="recomendaciones"/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
  import Navbar from '@/components/Navbar.vue';
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
  const recomendaciones = [
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

<script>
  export default {
    data: () => ({ drawer: null }),
  }
</script>