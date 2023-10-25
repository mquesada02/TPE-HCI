<template>
  <v-app id="home">
    <v-main color="background">
          <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
            <v-row v-if="notLogin" class="ml-15">
              <v-col>
                <p class="text-h3 text-xl-h1 text-lg-h1 text-md-h1 text-sm-h2 font-weight-bold text-center drop-shadow-lg">
                  CONCENTRACIÓN<br/>
                  ADAPTACIÓN<br/>
                  ENTRENAMIENTO<br/>
                </p>
              </v-col>
              <v-col>
                <p class="text-sm-h3 text-center font-weight-bold drop-shadow-lg pb-15">
                  No dejes que tus objetivos esperen  
                </p>
                <p class="text-md-h3 drop-shadow-lg text-center">
                  Con nosotros podes crear y almacenar tus propios ejercicios<br/>
                  Personalizá, entrená y triunfá.
                 
                </p>
                <div class="text-center mr-10 mt-12">
                  <v-btn color="secondary" size="x-large" to="/register">
                    Registrate
                  </v-btn>
                </div>
              </v-col>
            </v-row>
            <p v-else class="text-h3 text-xl-h1 text-lg-h1 text-md-h1 text-sm-h2 font-weight-bold text-center drop-shadow-lg">
              CONCENTRACIÓN<br/>
              ADAPTACIÓN<br/>
              ENTRENAMIENTO<br/>
            </p>
          </v-img>
      <v-container v-if="userStore.isLoggedIn">
        <SmallCarousel title="Destacadas" :imgs="destacadas" text="No hay rutinas disponibles"/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
  import SmallCarousel from '@/components/SmallCarousel.vue';
  import { useRoutineStore } from '@/stores/routineStore';
  import { useUserStore } from '@/stores/userStore';
  import { onBeforeMount } from 'vue';
  import { ref } from 'vue'
  const destacadas = ref([])
  const userStore = useUserStore();
  const notLogin = ref(!userStore.isLoggedIn);
  onBeforeMount( async () => {
    const routineStore = useRoutineStore();
    const routines = await routineStore.retrieveRoutines();
    routines.content.forEach((elem) => {
      destacadas.value.push({src: elem.metadata.img, title: elem.name, id: elem.id})
    })
  })
</script>

