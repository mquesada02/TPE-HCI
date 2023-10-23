import { defineStore } from 'pinia'
import { RoutineApi } from '@/api/routine';
import { ref } from 'vue';
import { FavouritesApi } from '@/api/favourites';
import { ExerciseApi } from '@/api/exercise';

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
            routines.push(res);
        }
        return res;
    }

    async function modifyRoutine(id, routineInfo) {
        return await RoutineApi.modifyRoutine(id, routineInfo);
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

    async function retrieveRoutineById(id) {
        // o buscar en el array
        return await RoutineApi.getRoutineById(id);
    }

    async function addCycle(routineId, cycleInfo) {
        return await RoutineApi.createCycle(routineId, cycleInfo);
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

    async function getExerciseImage(id) {
        return await ExerciseApi.getExerciseImage(id);
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
        newRoutine,
        modifyRoutine,
        retrieveRoutineById,
        retrieveRoutines,
        filterRoutinesByPage,
        retrieveFavourites,
        addCycle,
        markAsFavourite,
        unmarkAsFavourite,
        getExerciseImage,
    }
});