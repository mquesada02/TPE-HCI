<template>
    <div class="text-right">
            <v-icon icon="mdi-information" size="x-large" @click="dialog = true" color="white"></v-icon>
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
        <ciclo v-for="index in numCiclos" :key="index" type="false" :ejercicios="[]"/>
      </v-container>
    </v-app>
</template>

EL V-FOR VA A RECORRER TANTO CICLOS (cantidad=numCiclos) COMO UN ARRAY DE ARRAYS(de ejercicios), YA Q PARA CADA CICLO HAY UN ARRAY(de ejercicios)
osea donde dice [1, 2, 3] deberia haber un array de arrays(de ejercicios) q tenga como indice a index
AL INSTANCIAR CADA CICLO SE LE ENVIA SU ARRAY (como props) Q SIEMPRE COMIENZA VACIOS


<script setup>
  import ciclo from '@/components/Cicle.vue'
  import {ref, computed} from 'vue'
  const numCiclos = ref(1)
  const dialog = ref(false)
</script>


<style scoped>
    .size {
    font-size: 25px;
    }
</style>