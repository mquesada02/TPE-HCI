<template>
  <v-app>
  <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
    <v-container fill-height class="registerContainer">
      <Register />
    </v-container>
    </v-img>
  </v-app>
</template>

<script setup>
    import Register from '@/components/Register.vue';
</script>

<style scoped>
  .registerContainer {
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    top: 50%;
    left: 0;
    right: 0;
    transform: translateY(-45%);
  }
</style>
