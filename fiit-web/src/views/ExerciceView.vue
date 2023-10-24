<template>
  <v-app>
    <v-main color="background">
      <v-container>
      <Exercice :nombre="nombre" :descripcion="descripcion" :img="img" :video="videoUrl"/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
  import Exercice from '@/components/Exercice.vue';
  import { useExerciseStore } from '@/stores/exerciseStore';
  import { onBeforeMount, ref } from 'vue';

  const id = ref('')
  const nombre = ref('')
  const descripcion = ref('')
  const videoUrl = ref('')
  const img = ref('')

  onBeforeMount(async () => {
        const exerciseStore = useExerciseStore();
        const params = new URLSearchParams(location.search);
        id.value = params.get("id");
        console.log(id.value)
        const exercise = await exerciseStore.getExerciseFromApi(id.value);
        console.log(exercise)
        const exerciseImg = await exerciseStore.exerciseImage(id.value)
        console.log(exerciseImg)
        const exerciseVideo = ref('')
        exerciseVideo.value = await exerciseStore.exerciseVideo(id.value)
        console.log(exerciseVideo.value)

        nombre.value = exercise.name
        descripcion.value = exercise.detail 
        img.value = exerciseImg   
        videoUrl.value = exerciseVideo.value 

    })
</script>