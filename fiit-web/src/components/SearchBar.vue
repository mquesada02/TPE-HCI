<template>
    <v-sheet class="d-flex flex-row" color="secondary">
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
    const musculoItems = ['Abdominales', 'Biceps', 'Cuádriceps', 'Espalda', 'Glúteos', 'Gemelos', 'Isquiotibiales', 'Hombro', 'Pectoral', 'Triceps'];
    const intensidadItems = ['Baja', 'Media', 'Alta'];
    const objetivoItems = ['Fuerza', 'Bajar de peso', 'Flexibilidad', 'Ganar músculo'];
    const materialItems = ['Sin material', 'Máquinas', 'Pesas', 'Banda elástica', 'Soga'];
</script>

<style scoped>
    .search_box{
        width: 30%;
        padding: 10px;
    }
    .filter{
        width: 5%;
        padding: 10px;
    }
</style>