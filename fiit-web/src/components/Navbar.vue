<template>
    <v-app-bar fixed="top" color="primary">
        <!-- Router Link HERE-->
        <v-img grow rounded="0" src="@/assets/img/fiit-logo.png" href="/">
            
        </v-img>
        <!-- END -->
        <v-tabs grow >
            <v-tab append-icon="mdi-home" to="/" text="Inicio" />
            <v-tab append-icon="mdi-plus" to="/newRoutine" text="Crear rutina"/>
            <v-tab append-icon="mdi-folder" to="myRoutines" text="Mis rutinas"/>
            <v-tab append-icon="mdi-heart" to="favourites" text="Favoritos"/>
            <v-tab append-icon="mdi-magnify" to="search" text="Búsqueda"/>
            <v-tab append-icon="mdi-account" to="login" text="Usuario"/>
        </v-tabs>
    </v-app-bar>
</template>


