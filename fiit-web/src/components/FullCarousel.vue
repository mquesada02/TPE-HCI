<!-- faltan correcciones. NO usar-->

<template>
    <v-carousel>
        <v-carousel-item v-for="(img, index) in imgs" :key="index" :src="img" cover />
    </v-carousel>
</template>

<script setup>
    import { defineProps } from 'vue';
    const props = defineProps(['imgs']);
    const imgs = props.imgs.map((img) => new URL('../assets/img/' + img, import.meta.url).href);
</script>