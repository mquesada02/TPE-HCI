<template>
    <v-card class="mx-auto">
    <v-card-title>
        Ejercicios:
    </v-card-title>
    <v-divider></v-divider>
    <v-virtual-scroll
        :height="650"
        :items="['1', '2', '3', '4', '5', '6', '7']">
        <template v-slot:default="{ item }">
            <div style="background-color: #FF8A65;" class="rounded-template" max-width="900">
                <v-row>
                    <v-col cols="3">
                        <div class="ml-5 pt-2">
                        <v-img
                            width="175"
                            src="https://cdn.vuetifyjs.com/images/parallax/material.jpg" 
                            cover/>
                        </div>
                    </v-col>
                    <v-col cols="7">
                        <div class="pt-8">
                            <h1>Nombre del ejercicio: {{ item }}</h1>
                        </div>
                    </v-col>
                    <v-col>
                        <div class="pt-8">
                        <v-btn size="large" icon="mdi-play" to="/oneExercice"></v-btn>
                        </div>
                    </v-col>
                </v-row>
            </div>
        </template>
    </v-virtual-scroll>
    </v-card>
  </template>

//La src de la imagen la tiene q recibir a traves de item.src q obtendra la ifno desde la api
//Al igual q el nombre de item.name
//Al igual q la dirección a la q debe redireccionar cada play

<script setup>
    const props = defineProps(['itemss']);
    const itemss = props.itemss;    
</script>

