<template>
    <v-container>
    <v-row>
    <v-col :cols="videoUrl ? 6 : 12">
        <h1 color="white" class="mb-6 mt-4 ml-4">Plancha Baja</h1>

    <div v-if="videoUrl">
        <iframe
                    width="450"
                    height="300"
                    class="mb-8 ml-4"
                    :src="videoUrl"
                    frameborder="0"
                    allowfullscreen
                ></iframe>
        </div>

    <v-sheet color="secondary" :height="videoUrl ? 150 : 125" :width="videoUrl ? 450 : 650" :class="videoUrl? 'mt-4 mb-4 ml-4':'mx-auto mb-4'" rounded="lg" style="border: 1px solid black;"> 
        <h2 class="ml-8 mt-4 text-white">Músculos que trabaja:</h2>
        <h2 class="ml-8 mr-8">abdominales, espalda, piernas, glúteos y hombros</h2>
    </v-sheet>

</v-col>
 <v-col :cols="videoUrl ? 6 : 12">
    
        <v-sheet :width="videoUrl ? 550 : 650" :height="videoUrl ? 600 : 550" color="secondary" :class="videoUrl? 'mt-8 mb-6 mr-4':'mb-4 mx-auto'" rounded="lg" style="border: 1px solid black;">
        <h1 class="mt-4 mb-6 mr-4 ml-6 text-white text-center" >¿Cómo realizar una plancha correctamente?</h1>

        <h2 class="mb-4 ml-12 mr-12"> 1. Levanta el cuerpo del suelo, manteniendo el cuerpo en línea recta desde la cabeza hasta los talones, 
sin arquear la espalda ni elevar las caderas.</h2>

        <h2  class="mb-4 ml-12 mr-12">2. Traba los músculos abdominales y glúteos para mantener la posición y evitar que las caderas se hundan.</h2>

        <h2  class="mb-4 ml-12 mr-12">3. Mirar hacia abajo, manteniendo el cuello alineado con la columna vertebral</h2>
    </v-sheet>
        
       </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
  data() {
    return {
         videoUrl: "https://www.youtube.com/embed/ogfuXWgXVsg", 
      //si no hay video aparece una pag y si hay otra
    };
  },
};
</script>