<template>
  <v-app>
  <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
    <v-container fill-height>
      <v-row align="center" justify="center">
        <v-col cols="12">
          <Register />
        </v-col>
      </v-row>
    </v-container>
    </v-img>
  </v-app>
</template>

<script setup>
    import Register from '@/components/Register.vue';
</script>