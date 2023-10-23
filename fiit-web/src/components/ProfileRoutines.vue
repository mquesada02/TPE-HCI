<template>
    <v-sheet width="50%" color="background" height="80vh" class="align-start justify-space-evenly flex-column mt-10">
        <SmallCarousel title="Mis rutinas" :imgs="myroutines" text="No has creado ninguna rutina"/>
        <SmallCarousel title="Mis favoritos" :imgs="myfavs" text="No has agregado ninguna rutina a favoritos"/>
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