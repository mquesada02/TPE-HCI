<template>
    <v-container>
    <v-row>
    <v-col :cols="videoUrl.value ? 6 : 12">
        <h1 color="white" class="mb-6 mt-4 ml-4">{{nombre}}</h1>

    <div v-if="videoUrl.value">
        <iframe
                    width="450"
                    height="300"
                    class="mb-8 ml-4"
                    :src="videoUrl.value"
                    frameborder="0"
                    allowfullscreen
                ></iframe>
        </div>

</v-col>
 <v-col :cols="videoUrl.value ? 6 : 12">
    
        <v-sheet :width="videoUrl.value ? 550 : 650" color="secondary" :class="videoUrl.value? 'mt-8 mb-6 mr-4 pa-5':'mb-4 mx-auto pa-5'" rounded="lg" style="border: 1px solid black;">
        <v-img  :src="img" height="300"/>
        <h1 class="mt-4 mb-6 mr-4 ml-6 text-center">Descripción</h1>

        <h3 class="mb-4 ml-12 mr-12"> {{ descripcion }} </h3>
    </v-sheet>
        
       </v-col>
        </v-row>
    </v-container>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue';

  const props = defineProps(['nombre','descripcion', 'img', 'video'])

  const nombre = ref(props.nombre)
  const descripcion = ref(props.descripcion)
  const videoUrl = ref(props.video)
  const img = ref(props.img)
onBeforeMount(()=>{
    nombre.value=props.nombre
    descripcion.value = props.descripcion
    videoUrl.value = props.video
    img.value = props.img
})

  
</script>

<!-- <script>
    export default {
    data() {
        return {
            videoUrl: "https://www.youtube.com/embed/ogfuXWgXVsg", 
        //si no hay video aparece una pag y si hay otra
        };
    },
    };
</script> -->