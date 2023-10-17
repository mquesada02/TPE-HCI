<template color="lighter">
    <div style="background-color: #FFCCBC;">
        <v-row>
            <v-col cols="4"><h1 style="color: black;">Puntuación:</h1></v-col>
            <div class="d-flex align-center justify-center flex-column">
                <v-rating
                v-model="punt"
                class="ma-2"
                :item-labels="[':(', '', '', '', 'Me encanto']"
                item-label-position="top"
                style="color: black;"
                ></v-rating>
            </div>
        </v-row>
        <v-row>
            <v-col cols="4"><h1 style="color: black;">Dificultad:</h1></v-col>
                    <div class="d-flex align-center justify-center flex-column">
                        <v-rating
                        v-model="dif"
                        class="ma-2"
                        :item-labels="['facil', '', '', '', 'dificil']"
                        item-label-position="top"
                        style="color: black;"
                        ></v-rating>
                    </div>
        </v-row>
        <div class="pt-8 d-flex align-center justify-center flex-column">
        <v-row> 
            <v-btn color="secondary">Agregar a favoritos 
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
        </v-row>
        <v-row class="pt-5">
            <v-btn color="secondary">Compartir</v-btn>
        </v-row>
        </div>  
    </div>
</template>

//cambia el active-color del corazon porq queda feo ese rojoo 

<script>
  export default {
    data: () => ({ 
        dif: 3, 
        punt: 3 }),
  }
</script>