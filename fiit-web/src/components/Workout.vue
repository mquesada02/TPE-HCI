<template>
    <div class="text-right">
        <v-btn
            color="secondary"
            @click="dialog = true"
            >
            <v-icon icon="mdi-information" size="x-large"></v-icon>
        </v-btn>
        <v-dialog
            v-model="dialog"
            width="auto"
        >
        <v-card>
            <v-card-text>
            Para completar esta sección se tiene que añadir minimo un ejercició, el nombre del ciclo y la cantidad de series. 
            Además cada ejercicio necesita tener marcado al cantidad de segundos o las repeticiones.
            </v-card-text>
            <v-card-actions>
            <v-btn color="primary" block @click="dialog = false">Cerrar</v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </div>
    <v-app>
      <v-container fluid>
        <div>
          <label for="numCiclos" class="size">Número de ciclos: </label>
          <input
            type="number"
            id="numCiclos"
            class="size"
            v-model="numCiclos"
            @input="updateCiclos"
          />
        </div>
        <ciclo v-for="index in numCiclos" :key="index" type="false" />
      </v-container>
    </v-app>
</template>

<script setup>
  import addEx from '@/components/AddExCard.vue'
  import ciclo from '@/components/Cicle.vue'
  import {ref} from 'vue'
  const numCiclos = ref(1)
  const cicloTitles = ref([])
</script>

<script>
  export default {
    data () {
      return {
        dialog: false,
      }
    },
  }
</script>

<style scoped>
    .size {
    font-size: 25px;
    }
</style>