import { ref } from "vue";
import { defineStore } from "pinia";
import { ExerciseApi} from "@/api/exercise";


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
        if (image) { 
            await ExerciseApi.addExerciseImage(id, image);
        }
        if(video){
            await ExerciseApi.addExerciseVideo(id, video);
        }
        return id
    }

    async function getExercise(id) {
        console.log(exercises.value)
        //if (!exercises.value.length){
        const res = await ExerciseApi.getAllExercises();
        exercises.value = res.content
        //}
        const value = exercises.value.find(exercise => exercise.id === parseInt(id));
        if (value) {
            return value;
        }
        throw new Error('Exercise not found!');
    }

    async function getExerciseFromApi(id){
        return await ExerciseApi.getExercise(id)
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

    async function exerciseImage(id){
        const result = await ExerciseApi.getExerciseImage(id);
        return result.url
    }

    async function exerciseVideo(id){
        const result = await ExerciseApi.getExerciseVideo(id);
        return result.url
    }

    return {
        exercises,
        initialize,
        createExercise,
        getExercise,
        modifyExercise,
        deleteExercise,
        exerciseImage,
        exerciseVideo,
        getExerciseFromApi
    }

});