<template>
    <v-app id="home">
      <Navbar />
      <v-main class="bg-background">
        <v-container>
          <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
            <p class="text-h1 font-weight-bold text-center drop-shadow-lg">
              CONCENTRACIÓN
              ADAPTACIÓN
              ENTRENAMIENTO
            </p>>
          </v-img>
        </v-container>
        <v-container>
          <v-row>
            <template v-for="n in 4" :key="n">
              <v-col
                class="mt-2"
                cols="12"
              >
                <strong>Category {{ n }}</strong>
              </v-col>
  
              <v-col
                v-for="j in 6"
                :key="`${n}${j}`"
                cols="6"
                md="2"
              >
                <v-sheet height="150"></v-sheet>
              </v-col>
            </template>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
<style scoped>
    .custom-class {
        background: rgb(var(--v-theme-background));
    }
</style>

<script setup>
  import { ref } from 'vue'
  import Navbar from '@/components/Navbar.vue';

  const drawer = ref(null)
</script>

<script>
  export default {
    data: () => ({ drawer: null }),
  }
</script>