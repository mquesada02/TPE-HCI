<template>
    <v-card color="background">
      <v-tabs grow v-model="tab">
        <v-tab value="one">Descripción 
            <div v-if="false">
                <v-icon icon="mdi-checkbox-marked"/>
            </div>
            <div v-else>
                <v-icon icon="mdi-checkbox-marked" color="completed"/>
            </div>
        </v-tab>
        <v-tab value="two">Entrada en calor
            <div v-if="true">
                <v-icon icon="mdi-checkbox-marked"/>
            </div>
            <div v-else>
                <v-icon icon="mdi-checkbox-marked" color="completed"/>
            </div>
        </v-tab>
        <v-tab value="three">Ejercitación
            <div v-if="true">
                <v-icon icon="mdi-checkbox-marked"/>
            </div>
            <div v-else>
                <v-icon icon="mdi-checkbox-marked" color="completed"/>
            </div>
        </v-tab>
        <v-tab value="four">Enfriamiento
            <div v-if="true">
                <v-icon icon="mdi-checkbox-marked"/>
            </div>
            <div v-else>
                <v-icon icon="mdi-checkbox-marked" color="completed"/>
            </div>
        </v-tab>
      </v-tabs>
      <v-card-text>
        <v-window v-model="tab">
            <v-window-item value="one">
                <Desc></Desc>
            </v-window-item>
            <v-window-item value="two">
               <PrePostWorkout :title="Inicial" :ejercicios="[]"></PrePostWorkout>
            </v-window-item>
            <v-window-item value="three">
                <Workout></Workout>
            </v-window-item>
            <v-window-item value="four">
                <PrePostWorkout :title="Final" :ejercicios="[1,2]"></PrePostWorkout>
            </v-window-item>
        </v-window>
      </v-card-text>
    </v-card>
    <div v-if="true" class="text-right mr-5 pb-10 finalizarBtn">
        <v-btn color="secondary" size="x-large" to="/myRoutines">
            Finalizar rutina
        </v-btn>
    </div>
  </template>

faltan cuatro variabls boolean, q se crean a partir de la api
seran true si ya se completo la info de la tab
ESTAS CUATRO VARIABLES SE USAN PARA LOS V-IF DE LOS V-ICON (deciden su color)
Y PARA Q APAREZCA O NO EL BUTTON DE CREAR RUTINA

EL BOTON FINALIZA RUTINA TIENE Q ACCIONAR CON LA API PARA GUARDAR LA PROPIA RUTINA

<script>
export default {
data: () => ({
    tab: null,
}),
}
</script>

<script setup>
import Desc from '@/components/Description.vue';
import Workout from '@/components/Workout.vue';
import PrePostWorkout from '@/components/PrePostWorkout.vue';
</script>

<style scoped>
.finalizarBtn {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 95%;
  left: 10;
  right: 0;
  transform: translateY(-50%);
}
</style>