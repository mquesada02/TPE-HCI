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
          <v-checkbox label="Abdominales" values="Abdominales"></v-checkbox>
          <v-checkbox label="Biceps" values="Biceps"></v-checkbox>
          <v-checkbox label="Cuádriceps" values="Cuádriceps"></v-checkbox>
          <v-checkbox label="Espalda" values="Espalda"></v-checkbox>
          <v-checkbox label="Glúteos" values="Glúteos"></v-checkbox>
          <v-checkbox label="Gemelos" values="Gemelos"></v-checkbox>
          <v-checkbox label="Isquiotibiales" values="Isquiotibiales"></v-checkbox>
          <v-checkbox label="Hombro" values="Hombro"></v-checkbox>
          <v-checkbox label="Pectoral" values="Pectoral"></v-checkbox>
          <v-checkbox label="Triceps" values="Triceps"></v-checkbox>
        </div>

        <div class="checkbox-list">
          Intensidad
          <v-divider></v-divider>
          <v-checkbox label="Baja" values="Baja"></v-checkbox>
          <v-checkbox label="Media" values="Media"></v-checkbox>
          <v-checkbox label="Alta" values="Alta"></v-checkbox>
        </div>

        <div class="checkbox-list">
          Objetivo
          <v-divider></v-divider>
          <v-checkbox label="Fuerza" values="Fuerza"></v-checkbox>
          <v-checkbox label="Bajar de peso" values="Bajar de peso"></v-checkbox>
          <v-checkbox label="Flexibilidad" values="Flexibilidad"></v-checkbox>
          <v-checkbox label="Ganar músculo" values="Ganar músculo"></v-checkbox>
        </div>

        <div class="checkbox-list">
          Equipamiento
          <v-divider></v-divider>
          <v-checkbox label="Sin material" values="Sin material"></v-checkbox>
          <v-checkbox label="Máquinas" values="Máquinas"></v-checkbox>
          <v-checkbox label="Pesas" values="Pesas"></v-checkbox>
          <v-checkbox label="Banda elástica" values="Banda elástica"></v-checkbox>
          <v-checkbox label="Soga" values="Glúteos"></v-checkbox>
        </div>

        <v-card-actions>
          <v-spacer />
          <v-btn
            color="secondary"
            @click="menu = false"
            icon="mdi-check"
            vertical-align="bottom"
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

  v-checkbox{
    margin: 0px;
    padding: 0px;
    height: 12px;
  }

  .checkbox-list{
    width:300;
    padding: 15px;
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