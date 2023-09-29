<template>
    <v-app-bar class="bg-secondary">
        <v-text-field 
        label="Buscar"
        append-inner-icon="magnify"></v-text-field>

        <v-selects grow>
            <v-select label="Musculo"
            :items="['Abdominales', 'Biceps', 'Cuádriceps', 'Espalda', 'Glúteos', 'Gemelos', 'Isquiotibiales', 'Hombro', 'Pectoral', 'Triceps']"
            variant="solo-filled"
            ></v-select>

            <v-select label="Intensidad"
            :items="['Baja', 'Media', 'Alta']"
            variant="solo-filled"
            ></v-select>

            <v-select label="Objetivo"
            :items="['Fuerza', 'Bajar de peso', 'Flexibilidad', 'Ganar músculo']"
            variant="solo-filled"
            ></v-select>

            <v-select label="Material"
            :items="['Sin material', 'Máquinas', 'Pesas', 'Banda elástica', 'Soga']"
            variant="solo-filled"
            ></v-select>
        </v-selects>
    </v-app-bar>
</template>

<style scoped>
    .custom-class {
        background: rgb(var(--v-theme-secondary));
    }
</style>