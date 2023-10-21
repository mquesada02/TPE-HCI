<template>
    <v-app-bar fixed="top" color="primary">
        <!-- Router Link HERE-->
        <v-img grow rounded="0" src="@/assets/img/fiit-logo.png" href="/" height="50" width="50">
        </v-img>
        <!-- END -->
        <v-tabs grow >
            <v-tab v-if="!tooSmall" append-icon="mdi-home" to="/" text="Inicio" />
            <v-tab v-if="!tooSmall" append-icon="mdi-plus" to="/addRoutine" text="Crear rutina"/>
            <v-tab v-if="!tooSmall" append-icon="mdi-folder" to="/myRoutines" text="Mis rutinas"/>
            <v-tab v-if="!tooSmall" append-icon="mdi-heart" to="/favourites" text="Favoritos"/>
            <v-tab v-if="!tooSmall" append-icon="mdi-magnify" to="/search" text="Búsqueda"/>
            <v-tab v-if="!tooSmall && !user.isLoggedIn" append-icon="mdi-account" to="/login" text="Iniciar Sesión"/>
            <v-tab v-if="!tooSmall && user.isLoggedIn" append-icon="mdi-account" to="/profile" text="Perfil"/>
            <v-tab v-if="tooSmall" append-icon="mdi-home" to="/" />
            <v-tab v-if="tooSmall" append-icon="mdi-plus" to="/addRoutine"/>
            <v-tab v-if="tooSmall" append-icon="mdi-folder" to="/myRoutines"/>
            <v-tab v-if="tooSmall" append-icon="mdi-heart" to="/favourites"/>
            <v-tab v-if="tooSmall" append-icon="mdi-magnify" to="/search"/>
            <v-tab v-if="tooSmall && !user.isLoggedIn" append-icon="mdi-account" to="/login"/>
            <v-tab v-if="tooSmall && user.isLoggedIn" append-icon="mdi-account" to="/profile"/>
        </v-tabs>
    </v-app-bar>
</template>

<script setup>
    import { useUserStore } from '@/stores/userStore';
    import { useDisplay } from 'vuetify';
    import { computed } from 'vue';
    const user = useUserStore();

    const { name } = useDisplay();

    const tooSmall = computed(() => {
        switch(name.value) {
            case 'xs': return true;
            case 'sm': return true;
        }
        return false;
    })

</script>
