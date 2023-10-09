import { defineStore } from "pinia";
import { ref } from "vue";

export const useRoutineStore = defineStore('routine', () => {
   
    /*
        Prototype of class Exercise:

        name: String,
        img: String,
        count: int,
        type: String // (bool repetitions or seconds)

        ----------------------------------------------

        Prototype of class MultipleExercisePair:

        exercises: ExerciseObject[],
        series: int

        ----------------------------------------------

        Prototype of class Routine:

        name: String,
        img: String,
        desc: String,
        muscles: String[],
        intensity: String,
        materials: String[],
        privacy: String // (bool public or private),
        warmup: MultipleExercisePair,
        exercises: MultipleExercisePair[],
        cooling: MultipleExercisePair
    
    */
    class Exercise {
        // Fields
        #name;
        #img;
        #count;
        #type;
    
        // Validation function 
        #validTypes(name, img, count, type) {
            return typeof name === 'string' &&
                typeof img === 'string' &&
                typeof count === 'number' &&
                typeof type === 'string';
        }
    
        // Constructor
        constructor(name, img, count, type = 'repetitions') {
            if (!this.#validTypes(name, img, count, type)) 
                throw new Error('Illegal arguments');
            else {
                this.#name = name;
                this.#img = img;
                this.#count = count;
                this.#type = type;
            }
        }
    
        // Getters
        getName() {
            return this.#name;
        }
        getImg() {
            return this.#img;
        }
        getCount() {
            return this.#count;
        }
        getType() {
            return this.#type;
        }
    
        // Setters
        setName(name) {
            if (typeof name === 'string') this.#name = name;
        }
        setImg(img) {
            if (typeof img === 'string') this.#img = img;
        }
        setCount(count) {
            if (typeof count === 'number') this.#count = count;
        }
        setType(type) {
            if (typeof type === 'string') this.#type = type;
        }
    }

    class MultipleExercisePair {
        //Fields
        #exercises;
        #series;

        // Constructor
        constructor(exercises, series = 1) {
            if (!(/*exercises.isArray() && */typeof series === 'number'))
                throw new Error('Illegal arguments');
            else {
                this.#exercises = exercises;
                this.#series = series;
            }
        }

        // Getters
        getExercises() {
            return this.#exercises;
        }
        getSeries() {
            return this.#series;
        }

        // Setters
        setExercise(exercises) {
            /*if (exercises.isArray())*/ this.#exercises = exercises;
        }
        setSeries(series) {
            if (typeof series === 'number') this.#series = series;
        }
    }

    class Routine {
       // Fields
       #name;
       #img;
       #desc;
       #muscles;
       #intensity;
       #materials;
       #privacy;
       #warmup;
       #exercises;
       #cooling;

       // Validation Function
       #validTypes(name, img, desc, muscles, intensity, materials, privacy, warmup, exercises, cooling) {
            return 
                typeof name === 'string' &&
                typeof img === 'string' &&
                typeof desc === 'string' &&
                /*muscles.isArray() && */ /* no valida el tipo */
                typeof intensity === 'string' &&
                /*materials.isArray() && */ /* no valida el tipo */
                typeof privacy === 'string' &&
                warmup instanceof MultipleExercisePair &&
                /* exercises.isArray() && */ /* no valida el tipo */
                cooling instanceof MultipleExercisePair;
       }

       // Constructor
       constructor(name, img, desc, muscles, intensity, materials, privacy, warmup, exercises, cooling) {
            if (!this.#validTypes(name, img, desc, muscles, intensity, materials, privacy, warmup, exercises, cooling))
                throw new Error('Illegal arguments');
            else {
                this.#name = name;
                this.#img = img;
                this.#desc = desc;
                this.#muscles = muscles;
                this.#intensity = intensity;
                this.#materials = materials;
                this.#privacy = privacy;
                this.#warmup = warmup;
                this.#exercises = exercises;
                this.#cooling = cooling;
            }
       }

         // Getters
            getName() {
                return this.#name;
            }
            getImg() {
                return this.#img;
            }
            getDesc() {
                return this.#desc;
            }
            getMuscles() {
                return this.#muscles;
            }
            getIntensity() {
                return this.#intensity;
            }
            getMaterials() {
                return this.#materials;
            }
            getPrivacy() {
                return this.#privacy;
            }
            getWarmup() {
                return this.#warmup;
            }
            getExercises() {
                return this.#exercises;
            }
            getCooling() {
                return this.#cooling;
            }

            // Setters
            setName(name) {
                if (typeof name === 'string') this.#name = name;
            }
            setImg(img) {
                if (typeof img === 'string') this.#img = img;
            }
            setDesc(desc) {
                if (typeof desc === 'string') this.#desc = desc;
            }
            setMuscles(muscles) {
                /*if (muscles.isArray())*/ this.#muscles = muscles;
            }
            setIntensity(intensity) {
                if (typeof intensity === 'string') this.#intensity = intensity;
            }
            setMaterials(materials) {
                /*if (materials.isArray())*/ this.#materials = materials;
            }
            setPrivacy(privacy) {
                if (typeof privacy === 'string') this.#privacy = privacy;
            }
            setWarmup(warmup) {
                if (warmup instanceof ExercisePair) this.#warmup = warmup;
            }
            setExercises(exercises) {
                /*if (exercises.isArray()) */ this.#exercises = exercises;
            }
            setCooling(cooling) {
                if (cooling instanceof ExercisePair) this.#cooling = cooling;
            }

    }
    /*

    const warmupExercise = new Exercise(
        'exampleExercise',
        'imgSrcInAssets@excersises',
        25,
        'repetitions'
    );
    
    const exerciseExercise = new Exercise(
        'exampleExercise',
        'imgSrcInAssets@excersises',
        120,
        'seconds'
    );
    
    const coolingExercise = new Exercise(
        'exampleExercise',
        'imgSrcInAssets@excersises',
        60,
        'seconds'
    );

    const warmupPair = new MultipleExercisePair([warmupExercise], 2);
    const coolingPair = new MultipleExercisePair([coolingExercise], 1);

    const multipleExercisePair1 = new MultipleExercisePair([exerciseExercise, exerciseExercise], 3);
    const multipleExercisePair2 = new MultipleExercisePair([exerciseExercise], 5);
    
    const exampleRoutine = new Routine(
        'example',
        'imgSrcInAssets@routines(only the name.png/jpg/etc)',
        'Esta es una rutina de ejemplo.',
        ['Abdominales','Pectoral'],
        'Alta',
        ['Maquinas','Soga'],
        'Public',
        warmupPair,
        [multipleExercisePair1, multipleExercisePair2],
        coolingPair
    );
    */
    // states
    
    const routines = ref([
        
    ]);

    // categories for filters
    const muscles = [
        'Abdominales', 'Biceps', 'Cuádriceps', 'Espalda', 'Glúteos', 
        'Gemelos', 'Isquiotibiales', 'Hombro', 'Pectoral', 'Triceps',
    ]

    const intensity = [
        'Baja', 'Media', 'Alta',
    ]

    const goal = [
        'Fuerza', 'Bajar de peso', 'Flexibilidad', 'Ganar músculo'
    ]

    const material = [
        'Sin material', 'Máquinas', 'Pesas', 'Banda elástica', 'Soga'
    ]

    // getters

    const getRoutines = () => {return routines.value}
    const getMuscles = () => {return muscles}
    const getIntensity = () => {return intensity}
    const getGoal = () => {return goal}
    const getMaterial = () => {return material}


    const filterByMuscle = (muscle) => {
        return routines.value.filter( (routine) => routine.muscles.includes(muscle));
    }
    const filterByIntensity = (intensity) => {
        return routines.value.filter( (routine) => routine.intensity === intensity);
    }
    const filterByGoal = (goal) => {
        return routines.value.filter( (routine) => routine.goal === goal);
    }
    const filterByMaterial = (material) => {
        return routines.value.filter( (routine) => routine.materials.includes(material));
    }

    const getRoutineSize = () => {return routines.value.length}

    // actions

    function pushRoutine(routine) {
        routines.value.push(routine);
    }

    function addRoutine(routine) {
        // validate required fields
        pushRoutine(routine);

    }
    
    function removeRoutine(routine) {
        routines.value = routines.value.filter((routineElem) => routineElem !== routine );
    }

    return {
        // state
        routines,
        // getters
        getRoutines, getMuscles, getIntensity, getGoal, getMaterial,
        filterByMuscle, filterByIntensity, filterByGoal,
        filterByMaterial, getRoutineSize,
        // actions
        addRoutine, removeRoutine
    }
})