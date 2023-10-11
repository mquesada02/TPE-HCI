<template>
    <v-select class="filter" 
        bg-color="lighter"
        :label="props.label"
        :items="props.items"
        
    ></v-select>
</template>

<script setup>
    const props = defineProps(['label', 'items']);
</script>

<style scoped>
    .filter{
        width: 5%;
        padding: 10px;
        padding-bottom: 0px;
    }
</style>