<template>
  <h1 class="mt-8 mb-4 ml-8">Crear Ejercicio</h1>
  <v-card color="black">
    <v-row>
    <v-col>
        <v-text-field v-model:model-value="nombre" label="Nombre ejercicio:" class="text-field-center ml-4 mr-4"
          :rules="nombreRules">
        </v-text-field>
        <v-textarea v-model:model-value="descripcion" label="Descripción:" class="text-field-center ml-4 mr-4"
          :rules="descripcionRules">
        </v-textarea>
    </v-col> 
    <v-col>
      <v-container width="50%">
        <v-text-field label="Ingresar el URL de la imagen" v-model="foto" class="mx-auto" :rules="fotoRules">
        </v-text-field>
        <v-img v-if="foto" :src="foto" height="200px">
        </v-img>
      </v-container>
      <v-container width="50%">
        <v-text-field label="Ingresar el URL del video" accept="video/*" v-model="video" class="mx-auto">
        </v-text-field>
        <video v-if="video" :src="video" controls>
        </video>
      </v-container>
    </v-col>
    <v-container class="text-right">
        <div v-if="from">
          <v-btn color="secondary" :loading="isLoading" @click="createAndAddToCycle()" type="submit" class="text-center mr-12 mb-8">Crear y añadir a
            ciclo</v-btn>
        </div>
        <div v-else>
          <v-btn color="secondary" :loading="isLoading" @click="createExercise()" type="submit" class="text-center mr-12 mb-8">Crear</v-btn>
        </div>
      </v-container>
  </v-row>
  </v-card>
  
</template>



<script setup>
  import { ref } from 'vue';
  import { useExerciseStore } from '@/stores/exerciseStore';
  import { inject } from 'vue';
import router from '@/router';
import { provide } from 'vue';

  const exerciseStore = useExerciseStore();

  const props = defineProps(['from'])
  const from = props.from;

  const nombre = ref('');
  const descripcion = ref('');
  const foto = ref('');
  const video = ref('');

  const isLoading = ref(false);

  const exercises = inject('exercises');

  const text = ref('')
  const snackbar = ref(false)

  provide('text', text)
  provide('snackbar', snackbar)

  async function createExercise() {
    isLoading.value = true;
    const id = ref(null);
    try {
            id.value = await exerciseStore.createExercise(nombre.value, descripcion.value, "exercise", foto.value, video.value);
            router.go()
        } catch(error) {
            text.value = error.description;
            snackbar.value = true;
        } finally {
            isLoading.value = false;
        }
  }

  async function createAndAddToCycle(){
    isLoading.value = true;
    const id = ref(null);
    try {
            id.value = await exerciseStore.createExercise(nombre.value, descripcion.value, "exercise", foto.value, video.value);
        } catch(error) {
            text.value = error.description;
            snackbar.value = true;
        } finally {
            isLoading.value = false;
        }
    exercises.push(id);
  }

</script>


<script>
export default {
  computed: {
    nombreRules() {
      return [
        (v) => !!v || 'Este campo es requerido',
        (v) => (v && v.length <= 100) || 'Máximo 100 caracteres',
      ];
    },
    descripcionRules() {
      return [
        (v) => !!v || 'Este campo es requerido',
        (v) => (v && v.length <= 500) || 'Máximo 500 caracteres',
      ];
    },
  },
  computed: {
    fotoRules() {
      return [(v) => !!v || 'Subir una foto es obligatorio'];
    },
  },
  data() {
    return {
      dialog: false,
    }
  },
};
</script>
