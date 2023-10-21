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
              Aunque recomendamos altamente que usted lo haga, no es necesario agregar ejercicios en esta sección.
              Además cada ejercicio creado necesita tener marcado al cantidad de segundos o las repeticiones.
            </v-card-text>
            <v-card-actions>
            <v-btn color="primary" block @click="dialog = false">Cerrar</v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </div>
    <div class="pt-5">
      <Cicle :title="props.title" type="true" :ejercicios="ejerciciosS"/>
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
    const props = defineProps(['title', 'ejercicios']);
    const ejerciciosS = props.ejercicios
</script>
