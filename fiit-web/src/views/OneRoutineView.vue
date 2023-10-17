<template>
    <div >
        <v-row>
            <v-col class="ma-10" cols="4">   
                <h1>Nombre de Rutina</h1>
                <v-row>
                    <div class="pt-5">
                    <v-img
                        width="610"
                        src="https://cdn.vuetifyjs.com/images/parallax/material.jpg" 
                        cover/>
                    </div>
                </v-row>
                <div class="pt-10">
                    <RoutineInfo/>
                </div>
            </v-col>
            <v-col class="ma-15">
                <MiniExercice/>
                <MiniExercice/>
                <MiniExercice/>
                <MiniExercice/>
            </v-col>
        </v-row>
    </div>
</template>


//el nombre de rutina es un parametro q se tiene q recibir desde donde sea q se clickee
//al igual q la foto
//en vez de muchos miniExcerise seria un v-for q recorre la info de la api sobre q ej estan en esta rutina

<script setup>
import RoutineInfo from '@/components/RoutineInfo.vue';
import MiniExercice from '@/components/MiniExercice.vue';
</script>