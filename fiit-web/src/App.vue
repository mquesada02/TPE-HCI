<template>
  <v-app>
    <v-main>
      <Navbar />
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script setup>
  import { onBeforeMount } from 'vue';
  import Navbar from '@/components/Navbar.vue';
  import { useUserStore } from '@/stores/userStore';
  import { useExerciseStore } from './stores/exerciseStore';

  onBeforeMount(() => {
    const userStore = useUserStore();
    const exerciseStore = useExerciseStore();
    userStore.initialize();
    //exerciseStore.initialize();


  })
</script>
