<template>
    <v-app>
        <div v-if="type">
        <v-app-bar app>
            <v-toolbar-title class="size">
                Ciclo
                <span>{{ title }}</span>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <div class="pt-6 mr-4">
                <v-text-field
                    v-model="numberOfSeries"
                    outlined
                    dense>
                <template v-slot:prepend>
                <span class="size">Series:</span>
                </template>
                </v-text-field>
            </div>
        </v-app-bar>
        </div>
        <div v-else>
            <v-app-bar app>
                <v-toolbar-title class="size">
                    <div class="pt-6">
                    <v-text-field
                        outlined
                        dense>
                    <template v-slot:prepend>
                    <span class="size">Ciclo:</span>
                    </template>
                    </v-text-field>
                </div>
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <div class="pt-6 mr-4">
                    <v-text-field
                        v-model="numberOfSeries"
                        outlined
                        dense>
                    <template v-slot:prepend>
                    <span class="size">Series:</span>
                    </template>
                    </v-text-field>
                </div>
            </v-app-bar>
        </div>
        <div class="pt-15">
            <AadEx/>
        </div>
        <v-row justify="center" align="center" >
            <div class="size text-center mr-5">Añadir ejercicio</div>
            <v-btn color="secondary" @click="dialog = true">
                <v-icon icon="mdi-plus" size="x-large"/>
            </v-btn>
            <v-dialog v-model="dialog" width="auto">
                <v-card>
                    <v-card-text>
                        <v-btn @click="dialogCrear = true">
                            Crear ejercicio
                        </v-btn>
                        <v-dialog v-model="dialogCrear" width="auto">
                            <v-sheet class="sheet">
                                    <v-row class="ml-8 pt-5">
                                        <v-btn
                                        color="secondary"
                                        @click="dialogSheet = true"
                                        >
                                        <v-icon icon="mdi-chevron-left" size="x-large"></v-icon>
                                        </v-btn>
                                        <v-dialog
                                            v-model="dialogSheet"
                                            width="auto"
                                        >
                                        <v-card>
                                            <v-card-actions>
                                                <v-card-text class="text-center">
                                                    <v-btn @click="dialogSheet=false" class="pt-5">
                                                        Queeeeeeddaaettte atte:Quevedo
                                                    </v-btn>
                                                    <v-btn color="primary" block @click="dialogSheet = false, dialogCrear=false">
                                                        Si usted vuelve a la creación de rutina perdera el progreso de este ejercicio
                                                    </v-btn>
                                                </v-card-text>  
                                            </v-card-actions>
                                        </v-card>
                                        </v-dialog>
                                    </v-row>
                                <CreateEx from="True"/>
                            </v-sheet>
                        </v-dialog>
                    <v-btn>Buscar ejercicio</v-btn>
                    </v-card-text>
                    <v-card-actions>
                    <v-btn color="primary" block @click="dialog = false">Cerrar</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </v-app>
</template>



AAA NO FUNCIONA EL PROPS DEL TITULO NI EL DE TYPE
EL ESPACIO DEL MEDIO ES PARA EL V-FOR Q MUESTRA LOS EJERCICIOS AGREGADOS
EN EL ADDEX EN REALIDAD VA UN V-FOR Q USA EL ARRAY Q RECIBA ESTE CICLO EN ESPECIFICO

<script setup>
    import { defineProps } from 'vue'
    import AadEx from '@/components/AddExCard.vue'
    import CreateEx from '@/components/CreateExcercice.vue'
    const props = defineProps(['title', 'type'])
    const title = props.title
    const type = props.type
</script>

<script>
    export default {
    data() {
        return {
        numberOfSeries: 0,
        dialog: false,
        dialogCrear: false,
        dialogBuscar: false,
        dialogSheet: false,
        };
    },
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
</style>