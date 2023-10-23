<template>
    <v-sheet width="60vw" color="background" rounded="xl" height="80vh" class="d-flex align-center justify-space-evenly flex-column mt-15" >
        <br/>    
        <SmallCarousel title="Mis rutinas" :imgs="myroutines"/>
            <SmallCarousel title="Mis favoritos" :imgs="myfavs"/>
    </v-sheet>
</template>

<script setup>
    import { useUserStore } from '@/stores/userStore';
    import { useRoutineStore } from '@/stores/routineStore';
    import SmallCarousel from './SmallCarousel.vue';
    import { ref, onBeforeMount } from 'vue';
    const myroutines = ref([]) 
    const myfavs = ref([])
    onBeforeMount( async () => {
      const userStore = useUserStore();
      const routineStore = useRoutineStore();
      const routines = await userStore.getCurrentRoutines();
          
      routines.content.forEach((elem) => {
        myroutines.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
      })
      const favourites = await routineStore.retrieveFavourites();
      favourites.forEach((elem) => {
        myfavs.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
      })
    })
</script>