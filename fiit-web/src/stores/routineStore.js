import { defineStore } from 'pinia'
import { RoutineApi } from '@/api/routine';
import { ref } from 'vue';

export const useRoutineStore = defineStore('routine', () => {

    const routines = ref([]);
    const userRoutines = ref([]);
    const exercises = ref([]);

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

    async function newRoutine(name, detail, state, intensity, muscles, goals, materials, img) {
        console.log(img);
        const metadata = {muscles, goals, materials, img}
        const difficulty = intensity === 'Baja' ? 'rookie' : intensity === 'Media' ? 'intermediate' : 'expert'
        return await RoutineApi.createRoutine(name, detail, state, difficulty, metadata);
    }

    async function retrieveRoutines() {
        if (routines.value || routines.value.length === 0) {
            routines.value = await RoutineApi.getRoutines();
        }
       return routines.value;
    }

    async function retrieveRoutineById(id) {
        return await RoutineApi.getRoutineById(id);
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
        retrieveRoutineById,
        retrieveRoutines,
    }
});