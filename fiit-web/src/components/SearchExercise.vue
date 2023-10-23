<template>
        <div class="search-box">
                <v-text-field 
                clearable
                class="search_box pt-5"
                bg-color="lighter"
                label="Buscar"
                append-inner-icon="magnify">
                </v-text-field>
        </div>
        <ExerciseIter :items="recientes"/>
</template>

<script setup>
        import ExerciseIter from '@/components/ExcerciceIter.vue'
        const recientes = []
</script>

<style scoped>
.search-box{
        width:80%;
        max-width: 500px;
        margin: auto;
}
</style>

