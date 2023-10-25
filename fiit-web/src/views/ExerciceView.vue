<template>
  <v-app>
    <v-main color="background">
      <v-container>
        <v-btn
          color="secondary"
          @click="goBack()"
          >
          <v-icon icon="mdi-chevron-left" size="x-large"></v-icon>
        </v-btn>
        <Exercice/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
  import Exercice from '@/components/Exercice.vue';
  import { useExerciseStore } from '@/stores/exerciseStore';
  import { provide } from 'vue';
  import { onBeforeMount, ref } from 'vue';
  import router from '@/router';

  const id = ref('')
  const nombre = ref('')
  const descripcion = ref('')
  const videoUrl = ref('')
  const img = ref('')

  provide('id', id)
  provide('nombre', nombre)
  provide('descripcion', descripcion)
  provide('videoUrl', videoUrl)
  provide('img', img)

  function goBack(){
    router.go(-1);
  }

  onBeforeMount(async () => {
        const exerciseStore = useExerciseStore();
        const params = new URLSearchParams(location.search);
        id.value = params.get("id");
        const exercise = await exerciseStore.getExerciseFromApi(id.value);
        const exerciseImg = await exerciseStore.exerciseImage(id.value)
        const exerciseVideo = await exerciseStore.exerciseVideo(id.value)

        nombre.value = exercise.name
        descripcion.value = exercise.detail 
        img.value = exerciseImg   
        videoUrl.value = exerciseVideo 
        console.log(videoUrl.value)
    })
</script>