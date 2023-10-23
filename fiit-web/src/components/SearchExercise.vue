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
        <ExerciseIter :items="myexercises"/>
</template>

<script setup>
        import { ExerciseApi } from '@/api/exercise';
        import iteradorDeEj from '@/components/ExcerciceIter.vue'
        import { onBeforeMount, ref } from 'vue';
        
        const myexercises = ref([]);
        onBeforeMount(async () => {
                const exercises = ref([])
                const res = await ExerciseApi.getAllExercises();
                exercises.value = res.content;
                exercises.value.forEach((exercise) => {
                        myexercises.value.push({ src: exercise.image, title: exercise.name })
                });

        })
        
        

</script>

<style scoped>
.search-box{
        width:80%;
        max-width: 500px;
        margin: auto;
}
</style>

