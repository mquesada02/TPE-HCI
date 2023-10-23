<template>
    <div v-if="userStore.isLoggedIn">
        <v-app id="searchScreen">
        <SearchBar />
        <v-container>
        <MyRout :items="myroutines" :text="texto" :key="myroutines"/>
    </v-container>
    </v-app>
    </div>
    <div v-else>
        <NotLogIn/>
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
    import NotLogIn from '@/views/NotLogInView.vue';
    import { onBeforeMount, ref } from 'vue';
    import { useRoutineStore } from '@/stores/routineStore';
import { provide } from 'vue';
import { watch } from 'vue';

import { useUserStore } from '@/stores/userStore';
    const userStore = useUserStore();

    const texto="No se encontraron resultados para tu búsqueda"
    const myroutines = ref([]);
    const query = ref('')

    provide('query', query)

    onBeforeMount( async () => {
      await fetchRoutines('');
  })

  async function fetchRoutines(query){
      console.log(myroutines)
      const routineStore = useRoutineStore();
      const routines = ref(null)
      let i = 0;
      routines.value = await routineStore.filterRoutinesByPage(i, query);
      routines.value.content.forEach((elem) => {
        myroutines.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
      })
      
      while (!routines.value.isLastPage) {
        routines.value = await routineStore.filterRoutinesByPage(++i, query);
        routines.value.content.forEach((elem) => {
          myroutines.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
        })
      }
      console.log(myroutines.value.length)
  }

  watch(query, async (value, oldValue) => {
    await fetchRoutines(value)
  })

</script>