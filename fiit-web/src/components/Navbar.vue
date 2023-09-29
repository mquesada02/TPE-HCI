<template>
    <v-app-bar absolute class="bg-primary">
        <!-- Router Link HERE-->
        <v-img grow rounded="0" src="@/assets/img/fiit-logo.png" href="/" />
        <!-- END -->
        <v-tabs grow >
            <v-tab append-icon="mdi-home" to="home" text="Inicio" />
            <v-tab append-icon="mdi-plus" to="newRoutine" text="Crear rutina"/>
            <v-tab append-icon="mdi-folder" to="myRoutines" text="Mis rutinas"/>
            <v-tab append-icon="mdi-heart" to="favourites" text="Favoritos"/>
            <v-tab append-icon="mdi-magnify" to="search" text="Búsqueda"/>
            <v-tab append-icon="mdi-account" to="profile" text="Usuario"/>
        </v-tabs>
    </v-app-bar>
</template>

<style scoped>
    .custom-class {
        background: rgb(var(--v-theme-primary));
    }
</style>

