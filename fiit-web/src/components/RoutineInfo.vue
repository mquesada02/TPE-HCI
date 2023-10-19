<template color="lighter">
    <div style="background-color: #FFCCBC;" class="rounded-template">
        <div class="pt-8 d-flex align-center justify-center flex-column">
            <v-row>
                <v-rating
                v-model="dif"
                class="ma-2"
                item-label-position="top"
                style="color: black;"
                ></v-rating>
            </v-row>
        </div>
        <div class="pt-8 d-flex align-center justify-center flex-column">
        <v-row> 
            <div v-if="false">
                <v-btn color="secondary"> 
                    <v-rating
                        hover
                        :length="1"
                        :size="32"
                        :model-value="1"
                        active-color="primary"
                        empty-icon="mdi-heart-outline"
                        half-icon="mdi-heart-half-full"
                        full-icon="mdi-heart"
                        clearable/>
                </v-btn>
            </div>
            <div v-else>
                <v-btn color="secondary"> 
                    <v-rating
                        hover
                        :length="1"
                        :size="32"
                        active-color="primary"
                        empty-icon="mdi-heart-outline"
                        half-icon="mdi-heart-half-full"
                        full-icon="mdi-heart"
                        clearable/>
                </v-btn>
            </div>
        </v-row>
        <v-row>
            <v-col>
               <h1 style="color: black;">Duración:  </h1> 
            </v-col>
        </v-row>
        <v-row>
            <v-col>
               <h1 style="color: black;">Musculos a trabajar: </h1> 
            </v-col>
        </v-row>
        </div>  
    </div>
</template>

//cambia el active-color del corazon porq queda feo ese rojoo 

<script>
  export default {
    data: () => ({ 
        dif: 3, 
        punt: 3, 
    }),
  }
</script>
<script setup>
    import { computed } from 'vue';
    const props = defineProps(['favState'])
    const isFav = computed(() => {
        
    }) 

</script>
<style scoped>
.rounded-template {
  border-radius: 10px; /* Adjust the radius as needed */
  background-color: #f0f0f0; /* Set the background color */
  padding: 20px; /* Add padding to your template */
}
</style>
