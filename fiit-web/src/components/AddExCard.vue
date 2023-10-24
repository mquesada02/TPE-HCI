<template>
  <v-container fluid color="secondary">
    <v-row justify="center">
        <v-col class="d-flex justify-end">
            <ExCard :title="title" :img="img"/>
        </v-col>
        <v-col class="d-flex text-left align-center">
          <div>
            <div class="mb-5">
              <h2 v-if="reps">Repeticiones {{ reps }}</h2> 

            </div>
            <div>
              <h2 v-if="duration">Segundos: {{ duration }}</h2> 

            </div>
          </div>
        </v-col>
    </v-row>
  </v-container>
</template>

LA CONST notDescanso TIENE Q IGUALARSE A LA VARIABLE DE API Q DIFERENCIA ENTRE EJERCICIO Y DESCANSO  --------------------------------------------------------------------------------------------------------

<script setup>
    import { ExerciseApi } from '@/api/exercise';
import ExCard from '@/components/ExcersiveCard.vue'
import { inject, ref } from 'vue';
    const notDescanso = true;

    const props = defineProps(['exercise', 'index'])
    const exercise = props.exercise;
    const title = exercise.name
    const img = exercise.img
    const reps = ref('5');
    const duration = ref('');
    reps.value = exercise.repetitions;
    duration.value = exercise.duration;


</script>

<style scoped>
.centered-text {
  font-size: 24px;
  text-align: center;
}
</style>
