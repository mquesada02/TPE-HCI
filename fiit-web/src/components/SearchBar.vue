<template>
    <div class="search_bar" color="secondary">
        <v-text-field 
        class="search_box"
        bg-color="lighter"
        :label="text"
        append-inner-icon="magnify"
        v-model:model-value="search"
        ></v-text-field>
        <v-icon icon="mdi-magnify" 
            size="x-large" 
            class="pt-10 pr-8 pl-2"
            @click="change()"></v-icon>
        <filters-menu v-if="withFilters" class="pt-5"/>
    </div>
</template>

<script setup>
    import FiltersMenu from './FiltersMenu.vue';
    import { ref, inject } from 'vue';

    const props = defineProps(['text', 'withFilters']);
    const withFilters = props.withFilters;
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
        margin-top: 10px;
    }

    filters-menu{
        vertical-align: center;
    }
</style>