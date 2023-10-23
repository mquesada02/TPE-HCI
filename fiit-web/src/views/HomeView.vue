<template>
  <v-app id="home">
    <v-main color="background">
          <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
            <p class="text-h3 text-xl-h1 text-lg-h1 text-md-h1 text-sm-h2 font-weight-bold text-center drop-shadow-lg">
              CONCENTRACIÓN<br/>
              ADAPTACIÓN<br/>
              ENTRENAMIENTO<br/>
            </p>
          </v-img>
      <v-container v-if="userStore.isLoggedIn">
        <SmallCarousel title="Destacadas" :imgs="destacadas" text="No hay rutinas disponibles"/>
        <SmallCarousel title="Recientes" :imgs="recientes"/>
        <SmallCarousel title="Recomendaciones para tí" :imgs="recomendaciones"/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
  import SmallCarousel from '@/components/SmallCarousel.vue';
  import { useRoutineStore } from '@/stores/routineStore';
  import { useUserStore } from '@/stores/userStore';
  import { onBeforeMount } from 'vue';
  import { ref } from 'vue'
  const destacadas = ref([])
  const userStore = useUserStore();
  onBeforeMount( async () => {
    const routineStore = useRoutineStore();
    const routines = await routineStore.retrieveRoutines();
    routines.content.forEach((elem) => {
      destacadas.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
    })
  })
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

