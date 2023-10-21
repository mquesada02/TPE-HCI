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
  import { useExerciseStore } from '@/stores/exerciseStore';
  import { useRoutineStore } from '@/stores/routineStore';
 

  onBeforeMount(() => {
    const userStore = useUserStore();
    const exerciseStore = useExerciseStore();
    const routineStore = useRoutineStore();
    userStore.initialize();
    //exerciseStore.initialize();


  })
</script>
