<template>
  <v-app>
  <Navbar />
  <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
    <v-container fill-height>
      <v-row align="center" justify="center">
        <v-col cols="12">
          <Registrarse />
        </v-col>
      </v-row>
    </v-container>
    </v-img>
  </v-app>
</template>

<script setup>
    import LogIn from '@/components/LogIn.vue';
    import Navbar from '@/components/Registrarse.vue';
</script>