<template>
    <v-app id="searchScreen">
        <SearchBar />
    </v-app>
</template>

<style scoped>
    .custom-class {
        background: rgb(var(--v-theme-background));
    }
</style>

<script setup>
    import SearchBar from '@/components/SearchBar.vue';
</script>