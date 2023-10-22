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

    async function createExercise(name, detail, type, image, video) {
        const result = await ExerciseApi.addExercise(name, detail, type);
        pushExercise(result);
        const id = result.id;
        if (image) { //no se bien como poner esto?
            // then add an image and video to the exercise
            await ExerciseApi.addExerciseImage(id, image);
        }
        if(video){
            await ExerciseApi.addExerciseVideo(id, video);
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