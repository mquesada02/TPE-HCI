<template color="lighter">
    <div class="rounded-template">
        <h1 > {{ description }} </h1>
        <div class="pt-8 d-flex align-center justify-center flex-column">
        <v-row> 
            <v-col>
                <v-row>
                <v-rating
                v-model="dif"
                class="ma-2 text-black"
                item-label-position="top"
                ></v-rating>
            </v-row>
            </v-col>
            <v-col>
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
            </v-col>
        </v-row>
        <v-row>
            <v-col>
               <h1>Equipamiento: </h1>
               <ul>
                    <li class="text-black" v-for="mat in material" :key="mat">
                        {{ mat }}
                    </li>
               </ul> 
            </v-col>
        </v-row>
        <v-row>
            <v-col>
               <h1 >Musculos a trabajar: </h1> 
               <ul>
                    <li class="text-black" v-for="mus in muscles" :key="mus">
                        {{ mus }}
                    </li>
               </ul>
            </v-col>
            <v-col>
               <h1>Intensidad: </h1>
               <h4 class="text-black">{{ intensity }}</h4> 
            </v-col>
        </v-row>
        <v-row>
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
    const props = defineProps(['favState', 'description', 'muscles', 'material', 'intensity'])
    const isFav = computed(() => {
        
    }) 

</script>
<style scoped>
h1 {
    color: black;
}

.rounded-template {
  border-radius: 10px; /* Adjust the radius as needed */
  background-color: #FFCCBC; /* #f2f2f2 */
  padding: 20px; /* Add padding to your template */
}
</style>
