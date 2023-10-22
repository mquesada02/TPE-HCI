<template color="lighter">
    <div class="rounded-template">
        <v-row>
            <div class="mr-15">
                <h3 > {{ description }} </h3>
            </div>
            <v-spacer></v-spacer>
            <div class="pt-2 mr-2">
                <v-btn color="secondary" @click="dialogEdit = true"><v-icon icon="mdi-pencil"></v-icon></v-btn>
                <v-dialog v-model="dialogEdit" width="auto">
                    <v-card color="black"  width="1000">
                        <v-row class="ml-8 pt-5">
                            <v-btn color="secondary" @click="dialogSheet = true">
                                <v-icon icon="mdi-chevron-left" size="x-large"></v-icon>
                            </v-btn>
                            <v-dialog v-model="dialogSheet" width="auto">
                                <v-card>
                                    <v-card-actions>
                                        <v-card-text class="text-center">
                                            <v-btn @click="dialogSheet = false, dialogEdit=false " class="pt-5">
                                                Volver
                                            </v-btn>
                                            <v-btn color="primary" block
                                                @click="dialogSheet = false, dialogEdit = false">
                                                Salir sin guardar
                                            </v-btn>
                                        </v-card-text>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </v-row>
                        <Desc />
                    </v-card>
                </v-dialog>
            </div>
        </v-row> 
        <div class="pt-8 d-flex flex-column">
        <div class="align-center justify-center d-flex flex-column"> 
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
                
                
                     
                        <v-rating
                            hover
                            :length="1"
                            :size="32"
                            color="primary"
                            active-color="primary"
                            empty-icon="mdi-heart-outline"
                            half-icon="mdi-heart-half-full"
                            full-icon="mdi-heart"
                            clearable/>
                
            </v-col>
        </v-row>
        </div>
        <v-row class="pt-5">
            <div>
                <h1>Equipamiento: </h1>
            </div>
            <div class="pt-4 ml-9">
                <ul>
                    <li class="text-black" v-for="mat in material" :key="mat">
                        {{ mat }}
                    </li>
                </ul>
            </div>
        </v-row>
        <v-row>
            <div>
                <h1 >Musculos a trabajar:  </h1> 
            </div>
            <div class="pt-4 ml-6">
                <ul>
                    <li class="text-black" v-for="mus in muscles" :key="mus">
                        {{ mus }}
                    </li>
               </ul>
            </div>
        </v-row>
        <v-row>
            <div>
                <h1>Intensidad: </h1>
            </div>
            <div class="pt-4 ml-5">
                <ul>
                    <li class="text-black">
                        {{ intensity }}
                    </li>
               </ul> 
            </div>
        </v-row>
        </div> 
        <div class="text-right mr-2">
            <v-btn color="secondary" @click="dialog = true">
                <v-icon icon="mdi-delete"></v-icon>
            </v-btn>
            <v-dialog v-model="dialog" width="auto">
                <v-card>
                    <v-card-actions>
                        <v-card-text class="text-center">
                            <v-btn @click="dialog = false" class="pt-5">
                                Volver
                            </v-btn>
                            <v-btn color="primary" block
                                class="pt-5"
                                @click="dialogSheet = false, dialogCrear = false">
                                Si borras esta rutina no habra manera de recuperarla
                            </v-btn>
                        </v-card-text>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </div> 
    </div>
</template>

//cambia el active-color del corazon porq queda feo ese rojoo 

<script>
  export default {
    data: () => ({ 
        dif: 3, 
        punt: 3, 
        dialog: false,
        dialogEdit: false,
        dialogSheet: false, 
    }),
  }
</script>

ISFAV DEBE SER SACADO DE LA API 
NO ES FUNCIONAL EL EDIT EN NINGUN SENTIDO 

<script setup>
    import { computed } from 'vue';
    import Desc from '@/components/Description.vue'
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

ul {
  list-style-type: none;
}
</style>
