<template>
    <v-sheet
      class="mx-auto"
      elevation="8"
      max-width="100%"
    >
    <p class="pl-3 pt-3 text-h4 font-weight-medium">Destacadas</p>
      <v-slide-group
        v-model="model"
        class="pa-4"
        selected-class="bg-success"
        show-arrows
      >
        <v-slide-group-item
          v-for="img in imgs"
          :key="img"
          v-slot="{ isSelected, toggle, selectedClass }"
        >
          <v-card
            color="bg-secondary"
            :class="['ma-4', selectedClass]"
            height="100"
            width="200"
            @click="toggle"
          >
            <v-img :src="img" cover/>
          </v-card>
        </v-slide-group-item>
      </v-slide-group>
    </v-sheet>
</template>

<style scoped>
    .custom-class {
        background: rgb(var(--v-theme-secondary));
    }
</style>

<script setup>
    import { defineProps } from 'vue';
    const props = defineProps(['imgs']);
    const imgs = props.imgs.map((img) => new URL('../assets/img/' + img, import.meta.url).href);
</script>