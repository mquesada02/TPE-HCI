<template>
    <v-sheet class="d-flex" color="secondary">
        <v-text-field 
        class="search_box"
        bg-color="lighter"
        label="Buscar"
        append-inner-icon="magnify"></v-text-field>

        <filter-button :items="musculoItems" label="Músculo"></filter-button>

        <filter-button :items="intensidadItems" label="Intensidad"></filter-button>

        <filter-button :items="objetivoItems" label="Objetivo"></filter-button>

        <filter-button :items="materialItems" label="Material"></filter-button>

    </v-sheet>
</template>

<script setup>
    import FilterButton from '@/components/FilterButton.vue';
    import { useRoutineStore } from '@/stores/routineStore';

    const routineStore = useRoutineStore();

    const musculoItems = routineStore.getMuscles();
    const intensidadItems = routineStore.getIntensity();
    const objetivoItems = routineStore.getGoal();
    const materialItems = routineStore.getMaterial();
</script>

<style scoped>
    .search_box{
        width: 30%;
        vertical-align: center;
        padding: 10px;
        padding-bottom: 0px;
    }
</style>