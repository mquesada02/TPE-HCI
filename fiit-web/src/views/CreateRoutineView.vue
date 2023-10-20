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
               <PrePostWorkout :title="Inicial"></PrePostWorkout>
            </v-window-item>
            <v-window-item value="three">
                <Workout></Workout>
            </v-window-item>
            <v-window-item value="four">
                <PrePostWorkout :title="Final"></PrePostWorkout>
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
import Workout from '@/components/Workout.vue';
import PrePostWorkout from '@/components/PrePostWorkout.vue';
/*
faltan cuatro variabls boolean, q se crean a partir de la api
seran true si ya se completo la info de la pestaña
*/
</script>