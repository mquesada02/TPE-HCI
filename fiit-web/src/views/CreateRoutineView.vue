<template>
    <v-card color="background">
      <v-tabs grow
        v-model="tab"
      >
        <v-tab value="one">Descriptción</v-tab>
        <v-tab value="two">Entrada en calor</v-tab>
        <v-tab value="three">Ejercitación</v-tab>
        <v-tab value="four">Enfriamiento</v-tab>
      </v-tabs>
      <v-card-text>
        <v-window v-model="tab">
            <v-window-item value="one">
                <Desc></Desc>
            </v-window-item>
            <v-window-item value="two">
                Two
            </v-window-item>
    
            <v-window-item value="three">
                Three
            </v-window-item>
            <v-window-item value="four">
                Four
            </v-window-item>
        </v-window>
      </v-card-text>
    </v-card>
  </template>

<script>
export default {
data: () => ({
    tab: null,
}),
}
</script>
<script setup>
import Desc from '@/components/Description.vue';
</script>