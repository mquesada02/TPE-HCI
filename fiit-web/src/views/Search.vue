<template>
    <v-app id="searchScreen">
        <Navbar />
        <SearchBar />
    </v-app>
</template>

<style scoped>
    .custom-class {
        background: rgb(var(--v-theme-background));
    }
</style>

<script setup>
    import Navbar from '@/components/Navbar.vue';
    import SearchBar from '@/components/SearchBar.vue';
</script>