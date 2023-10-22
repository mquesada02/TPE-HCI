<template>
    <div class="text-right">
        <v-icon icon="mdi-information" size="x-large" @click="dialog = true" color="white"></v-icon>

        <v-dialog v-model="dialog" width="auto">
            <v-card>
                <v-card-text>
                    Para completar esta sección se tiene que haber seleccionado minimo un elemento en Músculo, Intensidad,
                    Objetivo, Estado y Equipameinto.
                    Además se debe haber completado el nombre y una breve descirpción.
                </v-card-text>
                <v-card-actions>
                    <v-btn color="primary" block @click="dialog = false">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
    <v-form>
        <v-row>
            <v-col>
                <v-text-field class="pt-11" hide-details label="Nombre de la rutina:" single-line
                    v-model:model-value="routineName" :maxlength="30"></v-text-field>
            </v-col>
            <v-col>
                <v-container>
                    <v-text-field label="URL de la imagen de la rutina" v-model:model-value="routineImg" class="mt-8 mx-auto">
                    </v-text-field>
                    <v-img v-if="routineImg" :src="routineImg" height="200px">
                    </v-img>
                </v-container>
            </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-textarea counter label="Descripción..." maxlength="200" single-line
            v-model:model-value="routineDescription"></v-textarea>
    </v-form>
    <v-card class="card pt-3">
        <v-row>
            <v-col>
                <div class="checkbox-list">
                    <h1 style="font-size: 25px;" class="pb-2 ml-2">Músculo:</h1>
                    <v-divider></v-divider>
                    <template v-for="muscle in routineStore.getMuscles()">
                        <v-checkbox v-model="muscles" :label="muscle" :value="muscle"></v-checkbox>
                    </template>
                </div>
            </v-col>
            <v-col>
                <div class="checkbox-list">
                    <h1 style="font-size: 25px;" class="pb-2">Intensidad:</h1>
                    <v-divider></v-divider>
                    <v-radio-group v-for="intensity in routineStore.getIntensity()" v-model="dificultad" class="pt-4">
                        <v-radio :label="intensity" :value="intensity"></v-radio>
                    </v-radio-group>
                </div>
                <div class="checkbox-list pt-0">
                    <h1 style="font-size: 25px;" class="pb-2">Objetivo:</h1>
                    <v-divider></v-divider>
                    <template v-for="goal in routineStore.getGoal()">
                        <v-checkbox v-model="goals" :label="goal" :value="goal"></v-checkbox>
                    </template>
                </div>
            </v-col>
            <v-col>
                <div class="checkbox-list">
                    <h1 style="font-size: 25px;" class="pb-2">Equipamiento:</h1>
                    <v-divider></v-divider>
                    <template v-for="material in routineStore.getMaterial()">
                        <v-checkbox v-model="materials" :label="material" :value="material"></v-checkbox>
                    </template>
                </div>
                <div>
                    <h1 style="font-size: 25px" class="pb-2">Estado:</h1>
                    <v-divider></v-divider>
                    <v-radio-group v-model="estado" class="pt-4">
                        <v-radio label="Privada" :value="false"></v-radio>
                        <v-radio label="Pública (podrá ser vista por cualquier persona con una cuenta en fiit-web)"
                            :value="true"></v-radio>
                    </v-radio-group>
                </div>
            </v-col>
        </v-row>
    </v-card>
</template>

<script setup>
    import { useRoutineStore } from '@/stores/routineStore';
    import { inject } from 'vue';

    const routineStore = useRoutineStore();

    const routineName = inject('routineName');
    const routineImg = inject('routineImg');
    const routineDescription = inject('routineDescription');

    /* MÚSCULOS checkboxes*/
    const muscles = inject('muscles');

    /* INTENSIDAD checkboxes */
    const dificultad = inject('dificultad');

    /* OBJECTIVO checkboxes */
    const goals = inject('goals');

    /* EQUIPAMIENTO checkboxes */
    const materials = inject('materials');

    /* ESTADO radiogroup */
    const estado = inject('estado');
</script>

<script>
export default {
    data() {
        return {
            dialog: false,
        }
    },
}
</script>

<style scoped>
.nextBtn {
    align-items: right;
    justify-content: right;
    position: fixed;
    top: 93%;
    right: 3%;
    transform: translateY(-50%);
}
.textarea {
    height: 50px;
}
</style>