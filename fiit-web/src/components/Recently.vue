<template>
    <p class="pt-5 text-h4 font-weight-medium">{{ title }}</p>
    <v-row class="pt-2">
      <v-col 
        v-for="img in imgs"
        cols="4"
        sm="6"
        md="4"
        lg="3">
        <RoutineCard :img="img.src" :title="img.title" :favState="true"/>
      </v-col>
      <v-row v-if="hasItems">
        <v-alert class="ma-2" type="warning">No has visitado ninguna rutina recientement</v-alert>
      </v-row>
    </v-row>      
</template>

//NO CONTROLA CUANTO RECIBE DESDE LA API
//SE ASUME Q LA API RECIENTES SOLO MANDA LOS ÚLTIMOS 4

<script setup>
    import RoutineCard from './RoutineCard.vue';
    import { computed } from 'vue';
    const props = defineProps(['title','imgs', 'length']);
    const title = props.title;
    const imgs = props.imgs;
    const length = props.length;
    for (let i = 0; i < imgs.length; i++) {
      imgs[i].src = new URL('../assets/img/ejercicios/' + imgs[i].src, import.meta.url).href;
    }
    const hasItems = computed (() => {
      return length==0;
    })
</script>