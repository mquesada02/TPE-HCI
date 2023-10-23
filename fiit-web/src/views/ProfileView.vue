<template>
  <v-app>
    <v-row class="container">
      <ProfileCard />
      <ProfileRoutines />
    </v-row>
  </v-app>
</template>

<script setup>

import ProfileCard from '@/components/ProfileCard.vue';
import ProfileRoutines from '@/components/ProfileRoutines.vue';

</script>

<style scoped>
  .container{
    padding:20px;
    padding-bottom: 30px;
  }
</style>