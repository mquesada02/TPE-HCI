<template>
    <div v-if="userStore.isLoggedIn">
        <v-card color="background">
            <v-tabs grow v-model="tab">
                <v-tab value="one">Descripción
                    <div v-if="!descCheckbox">
                    </div>
                    <div v-else>
                        <v-icon icon="mdi-checkbox-marked" />
                    </div>
                </v-tab>
                <v-tab value="two">Entrada en calor
                    <div v-if="!warmupCheckbox">
                    </div>
                    <div v-else>
                        <v-icon icon="mdi-checkbox-marked" />
                    </div>
                </v-tab>
                <v-tab value="three">Ejercitación
                    <div v-if="!exerciceCheckbox">
                    </div>
                    <div v-else>
                        <v-icon icon="mdi-checkbox-marked" />
                    </div>
                </v-tab>
                <v-tab value="four">Enfriamiento
                    <div v-if="!cooldownCheckbox">
                    </div>
                    <div v-else>
                        <v-icon icon="mdi-checkbox-marked" />
                    </div>
                </v-tab>
            </v-tabs>
            <v-card-text>
                <v-window v-model="tab">
                    <v-window-item value="one">
                        <Desc></Desc>
                    </v-window-item>
                    <v-window-item value="two">
                        <v-row class="top d-flex justify-center mt-5">
                            <v-col class="d-flex align-center pl-5 pb-5">
                                <h1>Ciclo Inicial</h1>
                            </v-col>
                            <v-col class="d-flex justify-end">
                                <v-responsive maxWidth="300">
                                    <v-text-field density="compact" v-model="startingNumberOfSeries" outlined dense
                                        :rules="seriesRules">
                                        <template v-slot:prepend>
                                            <span class="size">Series:</span>
                                        </template>
                                    </v-text-field>
                                </v-responsive>
                            </v-col>
                        </v-row>
                        <v-row v-for="(exercise, index) in firstCycleExercises"
                            class="pt-10 d-flex align-center justify-center text-center">
                            <AddExCard :index="index" :exercise="exercise" :key="exercise" />
                        </v-row>
                        <v-row class="pa-10 d-flex align-center justify-center">
                            <v-btn color="primary" @click="addToStarterCycle()">Añadir ejercicios</v-btn>
                            <v-overlay class="align-center justify-center" location-strategy="static"
                                v-model:model-value="overlay">
                                <v-card width="80vw" height="100vh">
                                    <ExerciseCycleSearch :cycleIndex="0" />
                                </v-card>
                            </v-overlay>
                        </v-row>
                    </v-window-item>
                    <v-window-item value="three"> <!-- ejercitación -->
                        <v-card-text class="text-center">
                            <v-btn variant="text" @click="length++, exerciseTab = length">
                                Finalizar y añadir ciclo
                            </v-btn>
                            <v-divider class="mx-4" vertical></v-divider>
                            <v-btn :disabled="!length" variant="text" @click="tab = 3">
                                Finalizar ejercitación
                            </v-btn>
                        </v-card-text>
                        <v-tabs v-model="exerciseTab" bg-color="surface">
                            <v-tab v-for="n in length" :key="n" :value="n">
                                Ciclo {{ n }}
                            </v-tab>
                        </v-tabs>
                        <v-card-text>
                            <v-window v-model="exerciseTab">
                                <v-window-item v-for="n in length" :key="n" :value="n">
                                    <v-row class="top d-flex justify-center mt-5">
                                        <v-col class="d-flex align-center pl-5 pb-5">
                                            <h1>Ciclo {{ n }}</h1>
                                        </v-col>
                                        <v-col class="d-flex justify-end">
                                            <v-responsive maxWidth="300">
                                                <v-text-field type="number" density="compact"
                                                    v-model="exercisesSeries[n - 1].value" outlined dense
                                                    :rules="seriesRules">
                                                    <template v-slot:prepend>
                                                        <span class="size">Series:</span>
                                                    </template>
                                                </v-text-field>
                                            </v-responsive>
                                        </v-col>
                                    </v-row>
                                    <v-row v-for="(exercise, index) in exerciseExercises[n-1]"
                                        class="pt-10 d-flex align-center justify-center text-center">
                                        <AddExCard :index="index" :exercise="exercise" :key="exercise" />
                                    </v-row>
                                    <v-row class="pa-10 d-flex align-center justify-center">
                                        <v-btn :disabled="n != length" color="primary" @click="addToStarterCycle()">Añadir ejercicios</v-btn>
                                        <v-overlay class="align-center justify-center" location-strategy="static"
                                            v-model:model-value="overlay">
                                            <v-card width="80vw" height="100vh">
                                                <ExerciseCycleSearch :cycleIndex="n+1" />
                                            </v-card>
                                        </v-overlay>
                                    </v-row>
                                </v-window-item>
                            </v-window>
                        </v-card-text>
                    </v-window-item>
                    <v-window-item value="four">
                        <v-row class="top d-flex justify-center mt-5">
                            <v-col class="d-flex align-center pl-5 pb-5">
                                <h1>Ciclo Final</h1>
                            </v-col>
                            <v-col class="d-flex justify-end">
                                <v-responsive maxWidth="300">
                                    <v-text-field density="compact" v-model="finalNumberOfSeries" outlined dense
                                        :rules="seriesRules">
                                        <template v-slot:prepend>
                                            <span class="size">Series:</span>
                                        </template>
                                    </v-text-field>
                                </v-responsive>
                            </v-col>
                        </v-row>
                        <v-row v-for="(exercise, index) in lastCycleExercises"
                            class="pt-10 d-flex align-center justify-center text-center">
                            <AddExCard :index="index" :exercise="exercise" :key="exercise" />
                        </v-row>
                        <v-row class="pa-10 d-flex align-center justify-center">
                            <v-btn color="primary" @click="addToStarterCycle()">Añadir ejercicios</v-btn>
                            <v-overlay class="align-center justify-center" location-strategy="static"
                                v-model:model-value="overlay">
                                <v-card width="80vw" height="100vh">
                                    <ExerciseCycleSearch :cycleIndex="1" />
                                </v-card>
                            </v-overlay>
                        </v-row>
                    </v-window-item>
                </v-window>
            </v-card-text>
        </v-card>
        <div class="text-right mr-5 pb-10 finalizarBtn">
            <v-btn :disabled="canCreate" :loading="onProcess" color="secondary" size="x-large" @click="createRoutine()">
                Finalizar rutina
            </v-btn>
        </div>
        <AlertSnackbar />
    </div>
    <div v-else>
        {{ router.push('/login') }}
    </div>
</template>

<script>
export default {
    data: () => ({
        length: 1,
        tab: null,
        exerciseTab: null,
    }),
    computed: {
        seriesRules() {
            return [
                (v) => !!v || 'La cantidad de series es requerida',
                (v) => v > 0 || 'La cantidad de series debe ser un número mayor a 0',
            ];
        },
        cycleRules() {
            return [
                (v) => !!v || 'La cantidad de ciclos es requerida',
                (v) => v > 0 || 'La cantidad de ciclos debe ser un número mayor a 0',
                (v) => v <= 20 || 'La cantidad de ciclos debe ser un número menor o igual a 20',
            ]
        }
    }
}
</script>

<style scoped>
.top {
    background: rgb(var(--v-theme-surface));
}
</style>

<script setup>
import Desc from '@/components/Description.vue';
import Workout from '@/components/Workout.vue';
import PrePostWorkout from '@/components/PrePostWorkout.vue';
import AlertSnackbar from '@/components/AlertSnackbar.vue'
import router from '@/router';
import { provide, ref } from 'vue';
import { computed } from 'vue';
import { useRoutineStore } from '@/stores/routineStore';
import { CycleInfo, Cycle } from '@/api/routine';
import { useUserStore } from '@/stores/userStore';
import { watch } from 'vue';
import ExerciseCycleSearch from '@/components/ExerciseCycleSearch.vue';
const userStore = useUserStore();
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


const startingNumberOfSeries = ref('1');
const finalNumberOfSeries = ref('1');

/* Fin descripción */

/* Ciclos */

const ciclos = ref([]); //array de ciclos

ciclos.value.push(new Cycle(new CycleInfo('Entrada en calor', 'entrada en calor', 'warmup', 1), []));
ciclos.value.push(new Cycle(new CycleInfo('Enfriamiento', 'enfriamiento', 'cooldown', 1), []));

const numberOfCycles = ref('1');
const exercisesSeries = []
const exerciseExercises = []
const overlays = []

for (let i = 0; i < 20; i++) {
    exercisesSeries.push(ref('1'));
    ciclos.value.push(new Cycle(new CycleInfo('Ciclo de ejercitación ' + i, 'ejercitacion', 'exercise', 1), []))
    exerciseExercises.push(ciclos.value[i + 2].exercisesArray);
}

/* watch(exercisesSeries[0], () => {
    console.log(exercisesSeries[0].value);
})
 */


provide('ciclos', ciclos);

/* Finalizar rutina */

const snackbar = ref(false)
const text = ref('');
provide('snackbar', snackbar);
provide('text', text);

const onProcess = ref(false);
const res = ref(null);

const overlay = ref(false);
provide('overlay', overlay);

function addToStarterCycle() {
    overlay.value = true;
}

import AddExCard from '@/components/AddExCard.vue';

const firstCycleExercises = ciclos.value[0].exercisesArray
const lastCycleExercises = ciclos.value[1].exercisesArray

const currentOrder = ref(1);

async function createRoutine() {
    onProcess.value = true;
    /* primero creamos la rutina */

    res.value = await routineStore.newRoutine(routineName.value, routineDescription.value, estado.value, dificultad.value, muscles.value, goals.value, materials.value, routineImg.value);
 

    /* añadimos los ciclos y agregamos los ejercicios */
    // Ciclo Inicial
    try {
        ciclos.value[0].cycleInfo.setSeries(parseInt(startingNumberOfSeries.value));
        ciclos.value[0].cycleInfo.setOrder(currentOrder.value);
        currentOrder.value++;
        const response = await routineStore.addCycle(res.value.id, ciclos.value[0].cycleInfo);
        const cycleId = response.id;
        ciclos.value[0].exercisesArray.forEach(async (exercise, index) => {
            await routineStore.addExerciseToCycle(cycleId, exercise.id, { order: currentOrder.value, duration: parseInt(exercise.duration) || 0, repetitions: parseInt(exercise.repetitions) || 0 })
        })
    } catch (error) {
        console.log(error);
        text.value = error.description;
        snackbar.value = true;
    } finally {
        onProcess.value = false;
    }

    // Ciclo Final
    try {
        ciclos.value[1].cycleInfo.setSeries(parseInt(finalNumberOfSeries.value));
        ciclos.value[1].cycleInfo.setOrder(currentOrder.value);
        currentOrder.value++;
        const response = await routineStore.addCycle(res.value.id, ciclos.value[1].cycleInfo);
        const cycleId = response.id;
        ciclos.value[1].exercisesArray.forEach(async (exercise, index) => {
            await routineStore.addExerciseToCycle(cycleId, exercise.id, { order: index + 1, duration: parseInt(exercise.duration) || 0, repetitions: parseInt(exercise.repetitions) || 0 })
        })
    } catch (error) {
        console.log(error);
        text.value = error.description;
        snackbar.value = true;
    } finally {
        onProcess.value = false;
    }

    // Ciclos de ejercitación
    for (let i = 2; i < ciclos.value.length && exerciseExercises[i-2].length !== 0; i++) {
        try {
            ciclos.value[i].cycleInfo.setSeries(parseInt(exercisesSeries[i-2].value));
            ciclos.value[i].cycleInfo.setOrder(currentOrder.value);
            currentOrder.value++;
            const response = await routineStore.addCycle(res.value.id, ciclos.value[i].cycleInfo);
            const cycleId = response.id;
            ciclos.value[i].exercisesArray.forEach(async (exercise, index) => {
                await routineStore.addExerciseToCycle(cycleId, exercise.id, { order: index + 1, duration: parseInt(exercise.duration) || 0, repetitions: parseInt(exercise.repetitions) || 0 })
            })
        } catch (error) {
            console.log(error);
            text.value = error.description;
            snackbar.value = true;
        } finally {
            onProcess.value = false;
        }
    }


    /* router push */
    await router.push('/myRoutines');
    router.go();

}


const descCheckbox = computed(() => {
    return (muscles.value.length && goals.value.length && materials.value.length && routineName.value && routineDescription.value)
})

const warmupCheckbox = computed(() => {
    return (parseInt(startingNumberOfSeries.value) >= 1 && firstCycleExercises.length);
})

const exerciceCheckbox = computed(() => {
    return false;
})

const cooldownCheckbox = computed(() => {
    return (parseInt(finalNumberOfSeries.value) >= 1 && lastCycleExercises.length);
})

const canCreate = computed(() => {
    return !(descCheckbox.value && warmupCheckbox.value && cooldownCheckbox.value);
})

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