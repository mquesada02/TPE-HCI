<template>
        <v-card width="100vw" height="80vh">

        </v-card>
                
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

