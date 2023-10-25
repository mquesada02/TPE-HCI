import { defineStore } from 'pinia'
import { RoutineApi } from '@/api/routine';
import { ref } from 'vue';
import { FavouritesApi } from '@/api/favourites';
import { ExerciseApi } from '@/api/exercise';
import { ReviewApi } from '@/api/review';
import { CycleApi } from '@/api/cycle';

export const useRoutineStore = defineStore('routine', () => {

    const routines = ref([]);
    const userRoutines = ref([]);
    const exercises = ref([]);

    const favourites = ref([]);

    const muscles = [
        'Abdominales', 'Biceps', 'Cuádriceps', 'Espalda', 'Glúteos',
        'Gemelos', 'Isquiotibiales', 'Hombro', 'Pectoral', 'Triceps',
    ]

    /* dificultad */
    const intensity = [
        'Baja', 'Media', 'Alta',
    ]

    const goal = [
        'Fuerza', 'Bajar de peso', 'Flexibilidad', 'Ganar músculo'
    ]

    const material = [
        'Sin material', 'Máquinas', 'Pesas', 'Banda elástica', 'Soga'
    ]

    const getMuscles = () => { return muscles }
    const getIntensity = () => { return intensity }
    const getGoal = () => { return goal }
    const getMaterial = () => { return material }

    async function newRoutine(name, detail, isPublic, intensity, muscles, goals, materials, img) {
        const metadata = {muscles, goals, materials, img}
        const difficulty = intensity === 'Baja' ? 'rookie' : intensity === 'Media' ? 'intermediate' : 'expert'
        const res = await RoutineApi.createRoutine(name, detail, isPublic, difficulty, metadata);
        if (res) {
            routines.value.push(res);
        }
        return res;
    }

    async function modifyRoutine(id, routineInfo) {
        return await RoutineApi.modifyRoutine(id, routineInfo);
    }

    async function deleteRoutine(id) {
        return await RoutineApi.removeRoutine(id);
    }

    async function retrieveRoutines() {
        if (!routines.value || routines.value.length === 0) {
            routines.value = await RoutineApi.getRoutines();
        }
       return routines.value;
    }

    async function filterRoutinesByPage(page, query) {
        return await RoutineApi.getFilteredRoutinesByPage(page, query);
    }

    async function getRoutinesByPage(page) {
        return await RoutineApi.getRoutinesByPage(page);
    }

    async function retrieveRoutineById(id) {
        // o buscar en el array
        return await RoutineApi.getRoutineById(id);
    }

    async function addCycle(routineId, cycleInfo) {
        return await RoutineApi.createCycle(routineId, cycleInfo);
    }

    async function addExerciseToCycle(cycleId, exerciseId, exerciseBody) {
        return await CycleApi.addExercise(cycleId, exerciseId, exerciseBody);
    }

    async function retrieveFavourites() {
        //if (!favourites.value || favourites.value.length === 0) {
            const res = await FavouritesApi.getFavourites();
            favourites.value = await res.content;
        //}
        return favourites.value;
    }

    async function markAsFavourite(routineId) {
        await FavouritesApi.addFavourite(routineId);
        favourites.value.push(routines.value.find(r => r.id === routineId));
    }

    async function unmarkAsFavourite(routineId) {
        await FavouritesApi.deleteFavourite(routineId);
        if (favourites.value)
            favourites.value = favourites.value.filter(r => r.id !== routineId);
    }

    async function addReview(routineId, score) {
        return await ReviewApi.addReview(routineId, score);
    }

    async function getReviews(routineId) {
        return await ReviewApi.getReviews(routineId);
    }

    async function getExerciseImage(id) {
        return await ExerciseApi.getExerciseImage(id);
    }

    async function getRoutineCycles(id){
        return await RoutineApi.retrieveRoutineCycles(id)
    }

    async function getCycleExercises(cycleId){
        return await RoutineApi.getCycleExercises(cycleId);
    }

    return {
        muscles,
        intensity,
        goal,
        material,
        getMuscles,
        getIntensity,
        getGoal,
        getMaterial,
        deleteRoutine,
        newRoutine,
        modifyRoutine,
        retrieveRoutineById,
        retrieveRoutines,
        filterRoutinesByPage,
        getRoutinesByPage,
        retrieveFavourites,
        addCycle,
        addExerciseToCycle,
        markAsFavourite,
        unmarkAsFavourite,
        addReview,
        getReviews,
        getExerciseImage,
        getRoutineCycles,
        getCycleExercises,
    }
});