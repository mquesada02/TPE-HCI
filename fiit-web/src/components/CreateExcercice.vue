<template>
  <div v-if="from">
    <v-row class="ml-8 pt-5">
        <v-btn
          color="secondary"
          @click="dialog = true"
        >
        <v-icon icon="mdi-chevron-left" size="x-large"></v-icon>
        </v-btn>
        <v-dialog
            v-model="dialog"
            width="auto"
        >
        <v-card>
            <v-card-actions>
            <v-btn color="primary" block @click="dialog = false" to="/addRoutine">Si usted vuelve a la creación de rutina perdera el progreso de este ejercicio</v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </v-row>
  </div>  
  <div v-else>
    <v-row class="ml-8 pt-5">
        <v-btn
          color="secondary"
          @click="dialog = true"
        >
        <v-icon icon="mdi-chevron-left" size="x-large"></v-icon>
        </v-btn>
        <v-dialog
            v-model="dialog"
            width="auto"
        >
        <v-card>
            <v-card-actions>
            <v-btn color="primary" block @click="dialog = false" to="/myExercices">Si usted vuelve al buscador de ejercicios perdera el progreso de este ejercicio</v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </v-row>
  </div>
<h1 class="mt-8 mb-4 ml-8">Crear Ejercicio</h1>
<v-row>
<v-col cols="6">
    <v-sheet color="secondary" width="600" height="125" class="mt-4 mb-2 mx-auto" rounded="lg">
     <v-textarea
            v-model:model-value="nombre"
            label="Nombre ejercicio:"
            class="text-field-center ml-4 mr-4"
            :rules="nombreRules"
            rows="4">
        </v-textarea>
    </v-sheet>
    <v-sheet color="secondary" width="600" height="175" class="mt-4 mb-2 mx-auto" rounded="lg">
     <v-textarea
            v-model:model-value="musculos"
            label="Músculos que trabaja:"
            class="text-field-center ml-4 mr-4"
            :rules="musculosRules"
            rows="6">
        </v-textarea>
    </v-sheet>
<v-container width="200">
    <v-file-input
  label="Subir foto"
  v-model="foto"
  class="mt-8 mx-auto"
  :rules="fotoRules">
</v-file-input>

<v-image
  v-if="foto"
  :src="foto"
  height="200px">
</v-image>
</v-container>
</v-col>
<v-col cols="6">
    <v-sheet color="secondary" width="600" height="314" class="mt-4 mb-2 mx-auto" rounded="lg">
     <v-textarea
            v-model:model-value="descripcion"
            label="Descripción:"
            class="text-field-center ml-4 mr-4"
            :rules="descripcionRules"
            rows="12"
            >
            
        </v-textarea>
    </v-sheet>
<v-container width="200">
    <v-file-input
  label="Subir video"
  accept="video/*"
  v-model="video"
  class=" mt-8 mx-auto"
  >
</v-file-input>

<v-video
  v-if="video"
  :src="video"
  controls>
</v-video>
</v-container>
<v-container class="text-right">
 <v-btn color="secondary" :loading="loading"  type="submit"  class="text-center mr-12 mb-8">Crear Ejercicio</v-btn>
 </v-container>
</v-col>
</v-row>
</template>

FROM tiene q ser una variable definida por la api, sera true cuando uno entre al CreateExercice desde el crearRutina
y sera false cuando uno entre desde el buscador de rutinas

<script>
export default {
  computed: {
    nombreRules() {
      return [
        (v) => !!v || 'Este campo es requerido',
        (v) => (v && v.length <= 100) || 'Máximo 100 caracteres',
      ];
    },
    musculosRules() {
      return [
        (v) => !!v || 'Este campo es requerido',
        (v) => (v && v.length <= 300) || 'Máximo 300 caracteres',
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
data () {
      return {
        dialog: false,
      }
    },
};
</script>
