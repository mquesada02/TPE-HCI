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
               <PrePostWorkout title="Inicial" :ejercicios="[]"></PrePostWorkout>
            </v-window-item>
            <v-window-item value="three">
                <Workout></Workout>
            </v-window-item>
            <v-window-item value="four">
                <PrePostWorkout title="Final" :ejercicios="[1,2]"></PrePostWorkout>
            </v-window-item>
        </v-window>
      </v-card-text>
    </v-card>
    <div class="text-right mr-5 pb-10 finalizarBtn">
        <v-btn :disabled="canEnd" :loading="onProcess" color="secondary" size="x-large" @click="createRoutine()">
            Finalizar rutina
        </v-btn>
    </div>
    <AlertSnackbar />
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
    import { provide, ref } from 'vue';
    import { computed } from 'vue';
    import { useRoutineStore } from '@/stores/routineStore';
    import AlertSnackbar from '@/components/AlertSnackbar.vue'

    const routineStore = useRoutineStore();

    /* Descripción */

    const routineName = ref('');
    const routineImg = ref('');
    const routineDescription = ref('');
    provide('routineName', routineName);
    provide('routineImg', routineImg);
    provide('routineDescription', routineDescription);

    /* MÚSCULOS checkboxes*/
    const muscles = ref([]);
    provide('muscles', muscles);

    /* INTENSIDAD checkboxes */
    const dificultad = ref('Media')
    provide('dificultad', dificultad);

    /* OBJECTIVO checkboxes */
    const goals = ref([]); 
    provide('goals', goals);

    /* EQUIPAMIENTO checkboxes */
    const materials = ref([])
    provide('materials', materials);

    /* ESTADO radiogroup */
    const estado = ref(false);
    provide('estado', estado);

    const canEnd = computed(() => {
        /* AGREGAR LO DE LOS CICLOS */
        return !(muscles.value.length && goals.value.length && materials.value.length);
    })  

    /* Fin descripción */

    /* Ciclos */

    /* Finalizar rutina */

    const snackbar = ref(false)
    const text = ref('');
    provide('snackbar', snackbar);
    provide('text', text);

    const onProcess = ref(false);

    async function createRoutine() {
        onProcess.value = true;
        /* primero creamos la rutina */
        try {
            const res = await routineStore.newRoutine(routineName.value, routineDescription.value, estado.value, dificultad.value, muscles.value, goals.value, materials.value, routineImg.value);
        } catch(error) {
            text.value = error.description;
            snackbar.value = true;
        } finally {
            onProcess.value = false;
        }
        
        /* añadimos los ciclos y agregamos los ejercicios */

        /* le agregamos la imagen a la rutina */

        /* router push */

    }


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