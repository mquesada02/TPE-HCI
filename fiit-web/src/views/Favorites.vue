<template>
  <v-app id="Favorites">
    <v-main color="background">
      <v-container>
            <p class="text-h1 font-weight-bold text-center drop-shadow-lg">
             ANDA 
             BIEN 
             ????            
            </p>
        <FavsRec title="Recientes" :imgs="recientes"/>
        <h1> y esto? </h1>
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
    { src: 'estiramiento abductores.jpg', title: 'Estiramiento de abductores' },
    { src: 'fondo triceps.webp', title: 'Fondo de tríceps' },
    { src: 'peso muerto.jpg', title: 'Peso muerto' },
    { src: 'sentadillas.jpg', title: 'Sentadillas' },
    { src: 'zancadas.webp', title: 'Zancadas' }
  ];
</script>

