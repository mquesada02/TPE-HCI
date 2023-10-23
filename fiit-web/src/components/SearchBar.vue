<template>
    <div class="search_bar" color="secondary">
        <v-text-field 
        class="search_box"
        bg-color="lighter"
        label="Buscar rutinas públicas..."
        append-inner-icon="magnify"
        v-model:model-value="search"
        ></v-text-field>
        <v-icon icon="mdi-magnify" 
            size="x-large" 
            class="pt-10 pr-8 pl-2"
            @click="change()"></v-icon>
        <filters-menu class="pt-5"/>
    </div>
</template>

<script setup>
    import { useRoutineStore } from '@/stores/routineStore';
    import FiltersMenu from './FiltersMenu.vue';
    import { ref, provide, inject } from 'vue';

    const search = ref('');

    const query = inject('query')

    function change(){
        query.value=search.value
    }

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