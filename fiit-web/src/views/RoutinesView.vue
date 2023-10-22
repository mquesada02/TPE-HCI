<template>
    <v-app id="rutines">
      <v-main color="background">
        <v-tabs grow >
            <v-tab  to="/myRoutines" text="Mis rutinas" />
            <v-tab  to="/myExercices" text="Mis ejercicios"/>
        </v-tabs>
        <v-container class="pt-15">
          <v-row align="center" justify="center">
            <v-btn  size="x-large" variant="outlined" color="secondary" to="/addRoutine">Crear nueva rutina <v-icon icon="mdi-plus" size="x-large"></v-icon></v-btn>
          </v-row>
          <h1 class="pt-5">Mis rutinas:</h1>
          <MyRout :items="myroutines"/>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script setup>
      import MyRout from '@/components/RoutineIter.vue';
      import { onBeforeMount, ref } from 'vue';

      import { useUserStore } from '@/stores/userStore';

      const myroutines = ref([])

      onBeforeMount( async () => {
        const userStore = useUserStore();
        const routines = await userStore.getCurrentRoutines();
            
        routines.content.forEach((elem) => {
          myroutines.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
        })
      })
  </script>

