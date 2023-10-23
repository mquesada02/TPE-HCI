<template>
  <div v-if="userStore.isLoggedIn">
    <v-app id="Favorites">
      <v-main color="background">
        <v-container>
          <h1 class="pt-5">Mis favoritos:</h1>
          <Myfavs :items="myfavs" :text="texto"/>
        </v-container>
      </v-main>
    </v-app>
  </div>
  <div v-else>
    {{ router.push('/login') }}
  </div>
</template>


<script setup>
    import FavsRec from '@/components/Recently.vue';
    import Myfavs from '@/components/RoutineIter.vue';
    import NotLogIn from '@/views/NotLogInView.vue';
    import { useRoutineStore } from '@/stores/routineStore';
    import { onBeforeMount, ref } from 'vue';
    import { useUserStore } from '@/stores/userStore';
    import router from '@/router';
    const userStore = useUserStore();
    const texto = "No has agregado ninguna rutina a favoritos"
    const myfavs = ref([])

    onBeforeMount(async () => {
      const routineStore = useRoutineStore();
      const favourites = await routineStore.retrieveFavourites();
        favourites.forEach((elem) => {
          myfavs.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
        })
    })
</script>