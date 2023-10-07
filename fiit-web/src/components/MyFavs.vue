<template>
    <p class="pl-3 pt-10 text-h4 font-weight-medium">
        Mis favoritos:
    </p>
    <div class="text-center">
    <v-container>
      <v-row justify="center">
        <v-col cols="5">
          <v-container class="max-width">
            <v-pagination
            :length="2"
            ></v-pagination>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import Card from "@/components/ExcersiveCard.vue";
import { defineProps } from 'vue';
const props = defineProps('allCards');
const allCards = [                                                               //si lo igualo a props.allCards tira pantallos en blanco
{ src: 'abdominales bicicleta.webp', title: 'Abdominales en bicicleta' },
    { src: 'abductores.jpg', title: 'Abductores' },
    { src: 'dominadas.jpg', title: 'Dominadas' },
    { src: 'elevaciones laterales.jpeg', title: 'Elevaciones laterales' },
    { src: 'estiramiento abductores.jpg', title: 'Estiramiento de abductores' }
];                               
let currentPage = 1;
const itemsPerPage = 5; // Numero random, hay q ver q queda mejor
const totalPages = Math.ceil(allCards.length / itemsPerPage); //no va 10, va allCards.length
const showThisCards = {} //computed(() => {                                                  NO LE GUSTAAAA
 // const startIndex = (currentPage - 1) * itemsPerPage;
 //const endIndex = startIndex + itemsPerPage;
 //return allCards.slice(startIndex, endIndex);
//});

</script>