<template>
  <v-app>
    <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
      <v-container fill-height class="loginContainer">
        <LogIn />
      </v-container>
    </v-img>
  </v-app>
</template>

<script setup>
import LogIn from '@/components/LogIn.vue';
</script>

<style scoped>
.loginContainer {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 50%;
  left: 0;
  right: 0;
  transform: translateY(-50%);
}
</style>

<!-- fijar el cuadro al medio, emprolijar el cuadro  -->