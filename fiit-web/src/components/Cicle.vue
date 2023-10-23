<template>
    <v-app>
        <v-app-bar app class="pb-4 pt-4">
            <div v-if="type" class="ml-3">
                <v-toolbar-title class="size">
                    Ciclo {{ title }}
                </v-toolbar-title>
            </div>
            <div v-else>
                <div class="pt-6 ml-2">
                    <v-text-field width="300">
                        <template v-slot:prepend>
                            <span class="size">Ciclo:</span>
                        </template>
                    </v-text-field>
                </div>
            </div>
            <v-spacer></v-spacer>
            <div class="pt-6 mr-4">
                <v-text-field v-model="numberOfSeries" outlined dense :rules="seriesRules">
                    <template v-slot:prepend>
                        <span class="size">Series:</span>
                    </template>
                </v-text-field>
            </div>
        </v-app-bar>
        
        <div class="exerciseComponents pt-15" v-for="item in exercises"> 
            <v-row >
                <div>
                    <AadEx />
                </div>
                <div class="pt-11">
                        <v-btn>Borrar ejercicio</v-btn>
                    <div class="pt-15">
                        <v-btn>Editar ejercicio</v-btn>
                    </div>
                </div>
            </v-row>
        </div>
        <v-row justify="center" align="center">
            <div class="size text-center mr-5">Añadir ejercicio</div>
            <v-btn color="secondary" @click="dialog = true">
                <v-icon icon="mdi-plus" size="x-large" />
            </v-btn>
            <v-dialog v-model="dialog" width="auto">
                <v-card>
                    <v-card-text>
                        <v-btn class="botones-ej" @click="dialogCrear = true">
                            Crear ejercicio
                        </v-btn>
                        <v-dialog v-model="dialogCrear" width="auto">
                            <v-sheet class="sheet">
                                <v-row class="ml-8 pt-5">
                                    <v-btn color="secondary" @click="dialogSheet = true">
                                        <v-icon icon="mdi-chevron-left" size="x-large"></v-icon>
                                    </v-btn>
                                    <v-dialog v-model="dialogSheet" width="auto">
                                        <v-card>
                                            <v-card-actions>
                                                <v-card-text class="text-center">
                                                    Salir sin guardar?
                                                    <v-divider class="pt-2"></v-divider>
                                                    <v-btn color="primary" block width="50%"
                                                        @click="dialogSheet = false, dialogCrear = false">
                                                        Salir
                                                    </v-btn>
                                                    <v-btn @click="dialogSheet = false" class="pt-5">
                                                        Canelar
                                                    </v-btn>
                                                </v-card-text>
                                            </v-card-actions>
                                        </v-card>
                                    </v-dialog>
                                </v-row>
                                <CreateEx :from="true" />
                            </v-sheet>
                        </v-dialog>
                        <v-btn class="botones-ej" @click="dialogBuscar = true">Buscar ejercicio</v-btn>
                        <v-card-actions>
                            <v-dialog v-model="dialogBuscar" width="auto">
                                <v-sheet class="sheet">
                                    <v-row class=" pt-5">
                                        <v-btn color="secondary" @click="dialogSheet = true">
                                            <v-icon icon="mdi-chevron-left" size="x-large"></v-icon>
                                        </v-btn>
                                        <v-dialog v-model="dialogSheet" width="auto">
                                            <v-card>
                                                <v-card-actions>
                                                    <v-card-text class="text-center">
                                                        Salir sin guardar?
                                                        <v-divider class="pt-2"></v-divider>
                                                        <v-btn color="primary" block width="50%"
                                                            @click="dialogSheet = false, dialogBuscar = false">
                                                            Salir
                                                        </v-btn>
                                                        <v-btn @click="dialogSheet = false" class="pt-5">
                                                            Cancelar
                                                        </v-btn>
                                                    </v-card-text>
                                                </v-card-actions>
                                            </v-card>
                                        </v-dialog>
                                    </v-row>
                                    <v-row>
                                        <BuscadorEjercicios class="pt-3"/>
                                    </v-row>
                                </v-sheet>
                            </v-dialog>
                        </v-card-actions>
                    </v-card-text>
                    <v-card-actions>
                        <v-btn color="primary" block @click="dialog = false">Cerrar</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </v-app>
</template>

EL V-FOR DE AadEx's RECORRE EL ARRAY DE EJERCICIOS Q SE RECIBE COMO PROPS 

<script setup>
    import AadEx from '@/components/AddExCard.vue'
    import CreateEx from '@/components/CreateExcercice.vue'
    import BuscadorEjercicios from '@/components/SearchExercise.vue'
import { provide } from 'vue';
    import { inject } from 'vue';

    const props = defineProps(['title', 'type', 'arrayPos'])
    const title = props.title
    const type = props.type
    const arrayPos = props.arrayPos

    const ciclos = inject('ciclos');
    console.log(ciclos.value);
    console.log(ciclos.value[0].exercisesArray);

    const exercises = ciclos.value[arrayPos].exercisesArray;
    provide('exercises', exercises);

    // ciclos.value[arrayPos].cycleInfo.setOrder(numberOfSeries);
</script>

<script>
export default {
    data() {
        return {
            numberOfSeries: 1,
            dialog: false,
            dialogCrear: false,
            dialogBuscar: false,
            dialogSheet: false,
        };
    },
    computed: {
        seriesRules(){
            return [
                (v) => !!v || 'La cantidad de series es requerida',
                (v) => v > 0 || 'Series debe ser un número mayor a 0',
            ];
        }
    }
};
</script>

<style scoped>
.size {
    font-size: 25px;
}

.sheet {
    background-color: black;
    padding: 40px;
}

.exerciseComponents{
    /* padding: 50px; */
    margin-left: auto;
    margin-right: auto;
}

.botones-ej{
    margin:15px;
}


</style>