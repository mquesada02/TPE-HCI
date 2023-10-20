import { ref } from "vue";
import { defineStore } from "pinia";
import { ExerciseApi, Exercise, BasicExercise } from "@/api/exercise";


export const useExerciseStore = defineStore('exercise', () => {

    const exercises = ref([]);
    const exercisesImgs = ref([]);
    

    function pushExercise(basicExercise) {
        exercises.value.push(basicExercise);
    }

    function setExercises(newExercises) {
        exercises.value = newExercises;
    }

    async function initialize() {
        try {
            const result = await ExerciseApi.getAllExercises().content;
            setExercises(result);
        } catch (error) {
            alert(error.description);
        }
    }

    async function createExercise(exercise) {
            const result = await ExerciseApi.addExercise(...exercise.BasicData());
            pushExercise(exercise.BasicData());
            if (exercise instanceof Exercise) {
                const id = result.id;
                // then add an image and video to the exercise
                await ExerciseApi.addExerciseImage(id, exercise.img);
                await ExerciseApi.addExerciseVideo(id, exercise.video);
            }
    }

    async function getExercise(id) {
        if (exercises.value) {
            const value = exercises.value.find(exercise => exercise.id === id);
            if (value) {
                return value;
            }
            throw new Error('Exercise not found!');
        }
    }

    async function modifyExercise(id, basicExercise) {
        const result = await ExerciseApi.modifyExercise(id, basicExercise);
        const index = exercises.value.findIndex(exercise => exercise.id === id);
        exercises.value[index] = basicExercise;
    }

    async function deleteExercise(id) {
        const result = await ExerciseApi.removeExercise(id);
        const index = exercises.value.findIndex(exercise => exercise.id === id);
        exercises.value.splice(index, 1);
    }

    return {
        exercises,
        initialize,
        createExercise,
        getExercise,
        modifyExercise,
        deleteExercise
    }

});