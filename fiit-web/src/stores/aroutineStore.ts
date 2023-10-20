import { defineStore } from "pinia";
import { ref } from "vue";

export const useRoutineStore = defineStore('routine', () => {
    
    class Exercise {
        protected name: String;
        protected desc: String;
        protected img: URL;
        protected video: URL;
        protected material: String;

        constructor(name: String, desc: String, img: URL, video: URL, material: String) {
            this.name = name;
            this.desc = desc;
            this.img = img;
            this.video = video;
            this.material = material;
        }

        // getters

        public getName() { return this.name; }
        public getDesc() { return this.desc; }
        public getImg() { return this.img; }
        public getVideo() { return this.video; }
        public getMaterial() { return this.material; }

        // setters

        public setName(name: String) { this.name = name; }
        public setDesc(desc: String) { this.desc = desc; }
        public setImg(img: URL) { this.img = img; }
        public setVideo(video: URL) { this.video = video; }
        public setMaterial(material: String) { this.material = material; }

    }
    
    class UserExercise extends Exercise {
        private username: String;

        constructor(name: String, desc: String, img: URL, video: URL, material: String, username: String) {
            super(name, desc, img, video, material);
            this.username = username;
        }

        // getters

        public getUsername() { return this.username; }

        // setters

        public setUsername(username: String) { this.username = username; }
    }

    class ExercisePair {
        private exercise: Exercise;
        private reps: Number;
        private secs: Number;

        constructor(exercise: Exercise, reps: Number, secs: Number) {
            this.exercise = exercise;
            this.reps = reps;
            this.secs = secs;
        }

        // getters

        public getExercise() { return this.exercise; }
        public getReps() { return this.reps; }
        public getSecs() { return this.secs; }

        // setters

        public setExercise(exercise: Exercise) { this.exercise = exercise; }
        public setReps(reps: Number) { this.reps = reps; }
        public setSecs(secs: Number) { this.secs = secs; }
        
    }

    class Cycle {
        private exercises : ExercisePair[];
        private series : Number;

        constructor(exercises: ExercisePair[], series: Number) {
            this.exercises = exercises;
            this.series = series;
        }

        // getters

        public getExercises() { return this.exercises; }
        public getSeries() { return this.series; }

        // setters

        public setExercises(exercises: ExercisePair[]) { this.exercises = exercises; }
        public setSeries(series: Number) { this.series = series; }
    }

    class RoutineCard {
        private name : String;
        private desc : String;

        private muscles : String[];
        private goal : String;
        private intensity : String;
        private materials : String[];

        private priv : Boolean = true;

        constructor(name: String, desc: String, muscles: String[], goal: String, intensity: String, materials: String[], priv: Boolean) {
            this.name = name;
            this.desc = desc;
            this.muscles = muscles;
            this.goal = goal;
            this.intensity = intensity;
            this.materials = materials;
            this.priv = priv;
        }

        // getters

        public getName() { return this.name; }
        public getDesc() { return this.desc; }

        public getMuscles() { return this.muscles; }
        public getGoal() { return this.goal; }
        public getIntensity() { return this.intensity; }
        public getMaterials() { return this.materials; }

        public getPriv() { return this.priv; }

        // setters

        public setName(name: String) { this.name = name; }
        public setDesc(desc: String) { this.desc = desc; }

        public setMuscles(muscles: String[]) { this.muscles = muscles; }
        public setGoal(goal: String) { this.goal = goal; }
        public setIntensity(intensity: String) { this.intensity = intensity; }
        public setMaterials(materials: String[]) { this.materials = materials; }

        public setPriv(priv: Boolean) { this.priv = priv; }
    }

    class Routine {
        private card : RoutineCard;

        private warmup : Cycle;
        private exercise : Cycle[];
        private cooldown : Cycle;

        constructor(card: RoutineCard, warmup: Cycle, exercise: Cycle[], cooldown: Cycle) {
            this.card = card;
            this.warmup = warmup;
            this.exercise = exercise;
            this.cooldown = cooldown;
        }

        // getters

        public getCard() { return this.card; }

        public getWarmup() { return this.warmup; }
        public getExercise() { return this.exercise; }
        public getCooldown() { return this.cooldown; }

        // setters

        public setCard(card: RoutineCard) { this.card = card; }
        
        public setWarmup(warmup: Cycle) { this.warmup = warmup; }
        public setExercise(exercise: Cycle[]) { this.exercise = exercise; }
        public setCooldown(cooldown: Cycle) { this.cooldown = cooldown; }
    }


    

    // states
    /*
    const flexiones = new Exercise("Flexiones", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vitonica.com%2Fmusculacion%2Fguia-para-principiantes-x-flexiones-brazos-suelo&psig=AOvVaw0kC8tBaA5tebPsE9SB1RuV&ust=1697139268567000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCJDq4sve7oEDFQAAAAAdAAAAABAE", 15, "repeticiones");
    const sentadillas = new Exercise("sentadillas",)
    */
    const exercises = ref([
        /*
        new Exercise("Sentadillas", "sentadillas.jpg", 20, "repeticiones"),
        new Exercise("Plancha", "plancha.jpg", 30, "segundos"),
        new Exercise("Zancadas", "zancadas.jpg", 20, "repeticiones"),
        new Exercise("Saltos de tijera", "saltosdetijera.jpg", 30, "segundos"),
        new Exercise("Burpees", "burpees.jpg", 10, "repeticiones"),
        new Exercise("Mountain Climbers", "mountainclimbers.jpg", 20, "repeticiones"),
        new Exercise("Bicycle Crunches", "bicyclecrunches.jpg", 30, "segundos"),
        new Exercise("Elevación de piernas", "elevaciondepiernas.jpg", 15, "repeticiones"),
        new Exercise("Giros rusos", "girosrusos.jpg", 20, "repeticiones"),
        new Exercise("Curl de bíceps con mancuernas", "curldebiceps.jpg", 12, "repeticiones"),
        new Exercise("Salto de cuerda", "saltodecuerda.jpg", 1, "minuto"),
        new Exercise("Plancha lateral", "planchalateral.jpg", 30, "segundos"),
        new Exercise("Flexiones con rotación", "flexionesconrotacion.jpg", 12, "repeticiones"),
        new Exercise("Sprint en el lugar", "sprintenellugar.jpg", 30, "segundos"),
        new Exercise("Sentadilla en la pared", "sentadillaenlapared.jpg", 45, "segundos"),
        new Exercise("Rodillas al pecho", "rodillasalpecho.jpg", 30, "segundos"),
        new Exercise("Zancadas inversas", "zancadasinversas.jpg", 20, "repeticiones"),
        new Exercise("Sentadillas con salto", "sentadillasconsalto.jpg", 15, "repeticiones"),
        new Exercise("Flexiones con variaciones", "flexionesconvariaciones.jpg", 12, "repeticiones"),
        new Exercise("Elevación de pierna en plancha", "elevaciondepiernaenplancha.jpg", 20, "repeticiones"),
        new Exercise("Superman", "superman.jpg", 30, "segundos"),
        new Exercise("Fondos de tríceps", "fondosdetriceps.jpg", 15, "repeticiones"),
        new Exercise("Patadas de glúteos", "patadasdegluteos.jpg", 30, "segundos"),
        new Exercise("Saltos de sentadilla", "saltosdesentadilla.jpg", 15, "repeticiones"),
        new Exercise("Flexiones diamante", "flexionesdiamante.jpg", 12, "repeticiones"),
        new Exercise("Burpees con saltos", "burpeesconsaltos.jpg", 10, "repeticiones"),
        new Exercise("Escaladores", "escaladores.jpg", 30, "segundos"),
        new Exercise("Sentadillas con salto lateral", "sentadillasconsaltolateral.jpg", 15, "repeticiones"),
        new Exercise("Abdominales bicicleta", "abdominalesbicicleta.jpg", 20, "repeticiones"),
        new Exercise("Estocadas inversas", "estocadasinversas.jpg", 20, "repeticiones"),
        new Exercise("Flexiones de brazos en T", "flexionesdebrazosenT.jpg", 12, "repeticiones"),
        new Exercise("Plancha con rodilla al codo", "planchaconrodillaalcodo.jpg", 30, "segundos"),
        new Exercise("Elevación de talones", "elevaciondetalones.jpg", 15, "repeticiones"),
        new Exercise("Tijeras rusas", "tijerasrusas.jpg", 30, "segundos"),
        new Exercise("Flexiones con manos elevadas", "flexionesconmanoselevadas.jpg", 12, "repeticiones"),
        new Exercise("Salto a una pierna", "saltoaunapierna.jpg", 1, "minuto"),
        new Exercise("Plancha con giro", "planchacongiro.jpg", 30, "segundos"),
        new Exercise("Sentadillas isométricas", "sentadillasisometricas.jpg", 45, "segundos"),
        new Exercise("Skipping", "skipping.jpg", 30, "segundos"),
        new Exercise("Tobillo a glúteo", "tobilloagluteo.jpg", 30, "segundos"),
        new Exercise("Sentadillas sumo", "sentadillassumo.jpg", 20, "repeticiones"),
        new Exercise("Flexiones de brazos declinadas", "flexionesdebrazosdeclinadas.jpg", 12, "repeticiones"),
        new Exercise("Plancha lateral con elevación de pierna", "planchalateralconelevaciondepierna.jpg", 20, "repeticiones"),
        new Exercise("Levantamiento de cadera", "levantamientodecadera.jpg", 30, "segundos"),
        new Exercise("Abdominales laterales", "abdominaleslaterales.jpg", 30, "segundos"),
        new Exercise("Saltos con tijera", "saltoscontijera.jpg", 15, "repeticiones"),
        new Exercise("Flexiones de tríceps", "flexionesdetriceps.jpg", 12, "repeticiones"),
        new Exercise("Zigzag con conos", "zigzagconconos.jpg", 1, "minuto"),
        new Exercise("Plancha con extensión de brazo", "planchaconextensiondebrazo.jpg", 30, "segundos"),
        new Exercise("Elevación de rodillas en paralelas", "elevacionderodillasenparalelas.jpg", 15, "repeticiones"),
        new Exercise("Estiramientos de cuádriceps", "estiramientosdecuadriceps.jpg", 30, "segundos"),
        new Exercise("Desplantes laterales", "desplanteslaterales.jpg", 20, "repeticiones"),
        new Exercise("Flexiones en pike", "flexionesenpike.jpg", 12, "repeticiones"),
        new Exercise("Sprint en el lugar con rodilla alta", "sprintenellugarconrodillaalta.jpg", 30, "segundos"),
        */
    ])

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
    
    const getExercises = () => { return exercises.value}
    const getRoutines = () => { return routines.value }
    
    const getMuscles = () => { return muscles }
    const getIntensity = () => { return intensity }
    const getGoal = () => { return goal }
    const getMaterial = () => { return material }


    const filterByMuscle = (muscle) => {
        return routines.value.filter((routine) => routine.getCard().getMuscles().includes(muscle));
    }
    const filterByIntensity = (intensity) => {
        return routines.value.filter((routine) => routine.getCard().getIntensity() === intensity);
    }
    const filterByGoal = (goal) => {
        return routines.value.filter((routine) => routine.getCard().getGoal() === goal);
    }
    const filterByMaterial = (material) => {
        return routines.value.filter((routine) => routine.getCard().getMaterials().includes(material));
    }

    const getRoutineSize = () => { return routines.value.length }

    // actions

    function pushRoutine(routine) {
        routines.value.push(routine);
    }

    function addRoutine(routine) {
        // validate required fields
        pushRoutine(routine);

    }

    function removeRoutine(routine) {
        routines.value = routines.value.filter((routineElem) => routineElem !== routine);
    }

    return {
        // state
        exercises,
        routines,
        // getters
        getRoutines, getMuscles, getIntensity, getGoal, getMaterial,
        filterByMuscle, filterByIntensity, filterByGoal,
        filterByMaterial, getRoutineSize,
        // actions
        addRoutine, removeRoutine
    }
})