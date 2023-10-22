<template>
        <v-row align="center" justify="center">
                <v-text-field 
                class="search_box pt-5"
                bg-color="lighter"
                label="Buscar"
                append-inner-icon="magnify">
                </v-text-field>
        </v-row>
        <iterador-de-ej :items="recientes"/>
</template>

<script setup>
        import iteradorDeEj from '@/components/ExcerciceIter.vue'
        const recientes = [
    { src: 'abdominales bicicleta.webp', title: 'Abdominales en bicicleta' },
    { src: 'abductores.jpg', title: 'Abductores' },
    { src: 'dominadas.jpg', title: 'Dominadas' },
    { src: 'elevaciones laterales.jpeg', title: 'Elevaciones laterales' },
    { src: 'estiramiento abductores.jpg', title: 'Estiramiento de abductores' },
    { src: 'abdominales bicicleta.webp', title: 'Abdominales en bicicleta' },
    { src: 'abductores.jpg', title: 'Abductores' },
    { src: 'dominadas.jpg', title: 'Dominadas' },
    { src: 'elevaciones laterales.jpeg', title: 'Elevaciones laterales' },
    { src: 'estiramiento abductores.jpg', title: 'Estiramiento de abductores' }
  ];
</script>

