<template>
        <v-app id="searchScreen">
                <SearchBar text="Buscar ejercicios..."/>
        <v-container>
                <ExerciseIter :items="filteredExercises" :key="finished"/>
        </v-container>
        </v-app>
        
</template>

<script setup>
        import SearchBar from '@/components/SearchBar.vue';
        import { ExerciseApi } from '@/api/exercise';
        import ExerciseIter from '@/components/ExcerciceIter.vue'
        import { onBeforeMount, ref, provide, watch } from 'vue';

        const query = ref('');
        provide('query', query)
        
        const myexercises = ref([]);
        onBeforeMount(async () => {
                const exercises = ref([])
                const res = await ExerciseApi.getAllExercises();
                exercises.value = res.content;
                exercises.value.forEach(async (exercise) => {
                        const res = await ExerciseApi.getExerciseImage(exercise.id);
                        myexercises.value.push({ name: exercise.name, src: res?.url || 'https://www.thermaxglobal.com/wp-content/uploads/2020/05/image-not-found.jpg', title: exercise.name })
                });
                filteredExercises.value = myexercises.value;
                finished.value = !finished.value;

        })

        const filteredExercises = ref([]);
        const finished = ref(false)

        function queryExercises(query) {
                filteredExercises.value = myexercises.value;
                filteredExercises.value = filteredExercises.value.filter((exercise) => {
                        return exercise.name.toLowerCase().includes(query.toLowerCase());
                })
                finished.value = ! finished.value;
        }

        watch(query, (value, oldValue) => {
                queryExercises(value)
        })
        
        

</script>

<style scoped>
.search-box{
        width:80%;
        max-width: 500px;
        margin: auto;
}
</style>

