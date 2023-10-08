<template>
  <v-app>
  <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
    <v-container fill-height>
      <v-row align="center" justify="center">
        <v-col cols="12">
          <LogIn />
        </v-col>
      </v-row>
    </v-container>
    </v-img>
  </v-app>
</template>

<script setup>
    import LogIn from '@/components/LogIn.vue';
</script>

<!-- fijar el cuadro al medio, emprolijar el cuadro  -->