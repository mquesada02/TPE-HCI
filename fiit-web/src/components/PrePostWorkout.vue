<template>
   <div class="text-right">
            <v-icon icon="mdi-information" size="x-large" @click="dialog = true" color="white"></v-icon>
        <v-dialog
            v-model="dialog"
            width="auto"
        >
        <v-card>
            <v-card-text>
              Aunque recomendamos altamente que usted lo haga, no es necesario agregar ejercicios en esta sección.
              Además cada ejercicio creado necesita tener marcado al cantidad de segundos o las repeticiones.
            </v-card-text>
            <v-card-actions>
            <v-btn color="primary" block @click="dialog = false">Cerrar</v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </div>
    <div class="pt-3">
      <Cicle :title="props.title" :type="true" :arrayPos="props.arrayPos"/>
    </div>
</template> 

<script>
  export default {
    data () {
      return {
        dialog: false,
      }
    },
  }
</script>

<script setup>
    import Cicle from '@/components/Cicle.vue';
    const props = defineProps(['title', 'arrayPos']);
</script>
