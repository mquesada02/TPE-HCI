<template>
  <div v-if="userStore.isLoggedIn">
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
          <MyRout :items="myroutines" :text="texto"/>
        </v-container>
      </v-main>
    </v-app>
  </div>
  <div v-else>
    {{ router.push('/login') }}
  </div>
</template>
  
<script setup>
  import router from '@/router';
  import MyRout from '@/components/RoutineIter.vue';
  import { onBeforeMount, ref } from 'vue';
  import { useUserStore } from '@/stores/userStore';
  const userStore = useUserStore();
  const texto = "No has creado ninguna rutina"
  const myroutines = ref([])
  onBeforeMount( async () => {
    const userStore = useUserStore();
    const routines = ref(null)
    let i = 0;
    routines.value = await userStore.getCurrentRoutinesByPage(i);
    routines.value.content.forEach((elem) => {
      myroutines.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
    })
    
    while (!routines.value.isLastPage) {
      routines.value = await userStore.getCurrentRoutinesByPage(++i);
      routines.value.content.forEach((elem) => {
        myroutines.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
      })
    }
    
    
  })
</script>

