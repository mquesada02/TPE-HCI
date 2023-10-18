<template>
    <div class="search_bar" color="secondary">
        <v-text-field 
        class="search_box"
        bg-color="lighter"
        label="Buscar"
        append-inner-icon="magnify"></v-text-field>

        <filters-menu />
        <!-- <filter-button :items="musculoItems" label="Músculo"></filter-button>

        <filter-button :items="intensidadItems" label="Intensidad"></filter-button>

        <filter-button :items="objetivoItems" label="Objetivo"></filter-button>

        <filter-button :items="materialItems" label="Material"></filter-button> -->

        </div>
</template>

<script setup>
    import FilterButton from '@/components/FilterButton.vue';
    import { useRoutineStore } from '@/stores/routineStore';
import FiltersMenu from './FiltersMenu.vue';

    const routineStore = useRoutineStore();

    const musculoItems = routineStore.getMuscles();
    const intensidadItems = routineStore.getIntensity();
    const objetivoItems = routineStore.getGoal();
    const materialItems = routineStore.getMaterial();
</script>

<style scoped>
    .search_box{
        width: 30%;
        padding: 10px;
        padding-bottom: 0px;
    }

    .search_bar{
        align-self: center;
        width: 80%;
        max-width: 500px;
        display: flex;
        justify-content: space-between;
    }

    filters-menu{
        vertical-align: center;
    }
</style>