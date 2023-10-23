<template>
  <div class="text-right">
    <v-menu
      v-model="menu"
      :close-on-content-click="false"
      location="end"
    >
      <template v-slot:activator="{ props }">
        <v-btn
          color="secondary"
          v-bind="props"
        >
        Filtrar
        </v-btn>
      </template>

      <v-card class="card">
        <div class="checkbox-list">
          Músculo
          <v-divider></v-divider>
          <v-checkbox label="Abdominales" value="Abdominales" v-model="musculos"></v-checkbox>
          <v-checkbox label="Biceps" value="Biceps" v-model="musculos"></v-checkbox>
          <v-checkbox label="Cuádriceps" value="Cuádriceps" v-model="musculos"></v-checkbox>
          <v-checkbox label="Espalda" value="Espalda" v-model="musculos"></v-checkbox>
          <v-checkbox label="Glúteos" value="Glúteos" v-model="musculos"></v-checkbox>
          <v-checkbox label="Gemelos" value="Gemelos" v-model="musculos"></v-checkbox>
          <v-checkbox label="Isquiotibiales" value="Isquiotibiales" v-model="musculos"></v-checkbox>
          <v-checkbox label="Hombro" value="Hombro" v-model="musculos"></v-checkbox>
          <v-checkbox label="Pectoral" value="Pectoral" v-model="musculos"></v-checkbox>
          <v-checkbox label="Triceps" value="Triceps" v-model="musculos"></v-checkbox>
        </div>

        <div class="checkbox-list">
          Intensidad
          <v-divider></v-divider>
          <v-checkbox label="Baja" value="Baja"></v-checkbox>
          <v-checkbox label="Media" value="Media"></v-checkbox>
          <v-checkbox label="Alta" value="Alta"></v-checkbox>
        </div>

        <div class="checkbox-list">
          Objetivo
          <v-divider></v-divider>
          <v-checkbox label="Fuerza" value="Fuerza"></v-checkbox>
          <v-checkbox label="Bajar de peso" value="Bajar de peso"></v-checkbox>
          <v-checkbox label="Flexibilidad" value="Flexibilidad"></v-checkbox>
          <v-checkbox label="Ganar músculo" value="Ganar músculo"></v-checkbox>
        </div>

        <div class="checkbox-list">
          Equipamiento
          <v-divider></v-divider>
          <v-checkbox label="Sin material" value="Sin material"></v-checkbox>
          <v-checkbox label="Máquinas" value="Máquinas"></v-checkbox>
          <v-checkbox label="Pesas" value="Pesas"></v-checkbox>
          <v-checkbox label="Banda elástica" value="Banda elástica"></v-checkbox>
          <v-checkbox label="Soga" value="Glúteos"></v-checkbox>
        </div>
        <v-card-actions>
          <v-spacer />
            <v-btn
            color="secondary"
            @click="menu = false"
            icon="mdi-check"
            vertical-align="bottom"
            class="bottom-right-button"
          >
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-menu>
  </div>
</template>

<style scoped>
  .card{
    min-width: 500px;
    display: flex;
    justify-content: space-between;
    vertical-align: top;
  }
  .checkbox-list{
    width:300;
    padding: 15px;
  }
  .bottom-right-button {
  position: fixed;
  bottom: 20px; 
  right: 20px; 
}
  v-checkbox{
    margin: 0px;
    padding: 0px;
    height: 12px;
  }
</style>

<script>
import { onDeactivated } from 'vue';

  export default {
    data: () => ({
        fav: true,
        menu: false,
        message: false,
        hints: true,
    }),
    components: { onDeactivated }
}
</script>

<script setup>
  import { ref } from 'vue';

  const musculos = ref([]);
</script>