<template>
    <div v-if="userStore.isLoggedIn">
        <v-app id="searchScreen">
        <SearchBar text="Buscar rutinas públicas..." :withFilters="true" />
        <v-container>
        <MyRout :items="filteredRoutines" :text="texto" :key="finished"/>
    </v-container>
    </v-app>
    </div>
    <div v-else>
      {{ router.push('/login') }}
    </div>
</template>

<style scoped>
    .custom-class {
        background: rgb(var(--v-theme-background));
    }
</style>

<script setup>
    import SearchBar from '@/components/SearchBar.vue';
    import MyRout from '@/components/RoutineIter.vue';
    import router from '@/router';
    import { onBeforeMount, ref } from 'vue';
    import { useRoutineStore } from '@/stores/routineStore';
import { provide } from 'vue';
import { watch } from 'vue';

import { useUserStore } from '@/stores/userStore';
    const userStore = useUserStore();

    const texto="No se encontraron resultados para tu búsqueda"
    const myroutines = ref([]);
    const filteredRoutines = ref([]);
    const query = ref('')

    const finished = ref(false)

    provide('query', query)


    const toReceive = ref(null);
    provide('filters', toReceive);

    onBeforeMount( async () => {
      await fetchRoutines();
  })

  function queryRoutines(query) {
    filteredRoutines.value = myroutines.value;
    filteredRoutines.value = filteredRoutines.value.filter((routine) => {
      return routine.name.toLowerCase().includes(query.toLowerCase());
    })
    finished.value = ! finished.value;
  }

  function queryFilterRoutines(object) {
    filteredRoutines.value = myroutines.value;
    console.log(object);
    filteredRoutines.value = filteredRoutines.value.filter((routine) => {
      console.log(routine)
      return (object.muscles.length == 0 || routine.muscles.some(elem => object.muscles.includes(elem))) && (object.material.length == 0 || routine.material.some(elem => object.material.includes(elem)) || routine.material[0] == 'Sin material') && (object.goal.length == 0 || routine.goal.some(elem => object.goal.includes(elem))) && (object.difficulty.length == 0 || object.difficulty.includes(routine.difficulty));  
    })
    finished.value = ! finished.value;

  }

  async function fetchRoutines(){
      const routineStore = useRoutineStore();
      const routines = ref(null)
      let i = 0;
      routines.value = await routineStore.getRoutinesByPage(i);
      routines.value.content.forEach((elem) => {
        myroutines.value.push({name: elem.name, src: elem.metadata.img, title: elem.name, id: elem.id, muscles: elem.metadata.muscles, material: elem.metadata.materials, goal: elem.metadata.goals, difficulty: elem.difficulty})
      })
      
      while (!routines.value.isLastPage) {
        routines.value = await routineStore.getRoutinesByPage(++i);
        routines.value.content.forEach((elem) => {
          myroutines.value.push({name: elem.name, src: elem.metadata.img, title: elem.name, id: elem.id, muscles: elem.metadata.muscles, material: elem.metadata.materials, goal: elem.metadata.goals, difficulty: elem.difficulty})
        })
      }
      filteredRoutines.value = myroutines.value;
      finished.value = !finished.value;
  }

  watch(query, (value, oldValue) => {
    queryRoutines(value)
  })

  watch(toReceive, (value,oldValue) => {
    if (value != null) {
      queryFilterRoutines(value)
    }
  })

</script>