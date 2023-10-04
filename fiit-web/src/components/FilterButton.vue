<template>
    <v-select class="filter" 
        bg-color="lighter"
        :label="props.label"
        :items="props.items"
        variant="solo-filled"
    ></v-select>
</template>

<script setup>
    const props = defineProps(['label', 'items']);
</script>

<style scoped>
    .filter{
        width: 5%;
        padding: 10px;
    }
</style>