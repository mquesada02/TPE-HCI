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



        
        <v-row justify="center" align="center" >
                <div class="size text-center mr-5">Añadir ejercicio</div>
                <v-btn
                    color="secondary"
                    @click="dialog = true"
                    >
                    <v-icon icon="mdi-plus" size="x-large"></v-icon>
                </v-btn>
                <v-dialog
                    v-model="dialog"
                    width="auto"
                >
                <v-card>
                    <v-card-text>
                    <v-btn>Crear ejercicio</v-btn>
                    <v-btn>Buscar ejercicio</v-btn>
                    </v-card-text>
                    <v-card-actions>
                    <v-btn color="primary" block @click="dialog = false">Close</v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
        </v-row>
    </v-app>
</template>


AAA NO FUNCIONA EL PROPS DEL TITULO NI EL DE TYPE
EL ESPACIO DEL MEDIO ES PARA EL V-FOR Q MUESTRA LOS EJERCICIOS AGREGADOS

<script setup>
    import { defineProps } from 'vue'
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
        };
    },
    };
</script>

<style scoped>
    .size {
    font-size: 25px;
    }
</style>