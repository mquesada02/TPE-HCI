<template>
    <div v-if="userStore.isLoggedIn">
        <v-app id="searchScreen">
            <SearchBar />
        </v-app>
    </div>
    <div v-else>
        <NotLogIn/>
    </div>
</template>

<style scoped>
    .custom-class {
        background: rgb(var(--v-theme-background));
    }
</style>

<script setup>
    import SearchBar from '@/components/SearchBar.vue';
    import NotLogIn from './NotLogInView.vue';
    import { useUserStore } from '@/stores/userStore';
    const userStore = useUserStore();
</script>