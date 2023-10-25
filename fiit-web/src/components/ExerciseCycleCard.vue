<template>
    <v-card color="secondary" class="ma-4" width="200" @click="activateOverlay()">
        <v-card height="75%">
            <v-img :src="img" cover />
        </v-card>
        <v-card-text class="pa-2 font-weight-medium text-center" height="25%"> {{ cardTitle }}</v-card-text>
    </v-card>
    <v-dialog max-width="25vw" height="50vh" persistent v-model:model-value="overlayReps">
        <v-card>
            <v-row class="mt-5">
                <v-col v-if="!isRest" class="d-flex justify-end">
                    <v-checkbox-btn class="ml-5" v-model="enabledReps"></v-checkbox-btn>
                    <v-text-field type="number" class="mr-5" :disabled="!enabledReps" hide-details label="Repeticiones:" style="width: 125px;"
                        v-model="reps"></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col class="d-flex justify-end">
                    <v-checkbox-btn class="ml-5" v-model="enabledSecs"></v-checkbox-btn>
                    <v-text-field type="number" class="mr-5" :disabled="!enabledSecs" hide-details label="Segundos:" style="width: 125px;"
                        v-model="secs"></v-text-field>
                </v-col>
            </v-row>
            <v-btn color="surface" class="mt-5 ml-15 mr-15" @click="overlayReps=false">Volver</v-btn>
            <v-btn color="primary" class="mt-5 ml-15 mr-15 mb-5" @click="confirm()">Confirmar</v-btn>
        </v-card>
    </v-dialog>
</template>

<script setup>
import { ExerciseApi } from '@/api/exercise';
import { onBeforeMount } from 'vue';
import { inject, ref } from 'vue';

const props = defineProps(['img', 'title', 'id', 'cycleIndex']);
const cycleIndex = props.cycleIndex;
const img = props.img;
const cardTitle = props.title;
const id = props.id;
const ciclos = inject('ciclos')
const overlay = inject('overlay')
const overlayReps = ref(false);

const enabledReps = ref(false);
const enabledSecs = ref(false);

const reps = ref('');
const secs = ref('');

const exercise = ref(null);
const isRest = ref(false);

onBeforeMount(async () => {
    const res = await ExerciseApi.getExercise(id);
    exercise.value = res;
    if (res.type == 'rest')
        isRest.value = true;
    else
        isRest.value = false;
})

function activateOverlay() {
    
    overlayReps.value = true;
}

function confirm() {
    if ((reps.value && enabledReps.value)|| (secs.value && enabledSecs.value)) { 
        overlayReps.value = false
        addToCycle();
    }
}

const isRepeated = ref(false);

async function addToCycle() {
    const res = await ExerciseApi.getExercise(id);
    const img = await ExerciseApi.getExerciseImage(id);

    ciclos.value[cycleIndex].exercisesArray.forEach((element) => {
        if (element.id == id)
            isRepeated.value = true;
    })
    if (!isRepeated.value)
        ciclos.value[cycleIndex].exercisesArray.push({ ...exercise.value, img: img?.url || 'https://www.thermaxglobal.com/wp-content/uploads/2020/05/image-not-found.jpg', duration: secs.value, repetitions: reps.value });
    overlay.value = false;
}

</script>