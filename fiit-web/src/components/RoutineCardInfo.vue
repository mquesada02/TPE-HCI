<template>
    <v-sheet rounded="xl" color="lighter" width="35vw" min-height="100vh" class="pa-5 mt-5">
        <v-row v-if="routineUserID.value == userID.value" class="pa-5 d-flex justify-end">
            <v-icon icon="mdi-pencil" @click="overlay = !overlay"></v-icon>
        </v-row>
        <v-row class="d-flex align-center justify-center">
            <v-rating hover :length="5" :size="ratingSize" v-model:model-value="rating" color="black" active-color="primary" />
        </v-row>
        <v-row>
            <v-col>
                <h2>Descripción</h2>
                {{ description }}
            </v-col>
        </v-row>
        
        <v-row class="align-start">
            <v-col>
                <h2>Equipamiento</h2>
            </v-col>
            <v-col>
                <ul>
                    <li class="text-black" v-for="mat in material" :key="mat">
                        {{ mat }}
                    </li>
                </ul>
            </v-col>
        </v-row>
        <v-row class="align-start">
            <v-col>
                <h2>Músculos a trabajar</h2>
            </v-col>
            <v-col>
                <ul>
                    <li class="text-black" v-for="mus in props.muscles" :key="mus">
                        {{ mus }}
                    </li>
               </ul>
            </v-col>
        </v-row>
        <v-row class="align-start">
            <v-col>
                <h2>Intensidad</h2>
            </v-col>
            <v-col>
                <ul>
                    <li >
                        {{ props.intensity }}
                    </li>
                </ul>
            </v-col>
        </v-row>
        <v-row class="d-flex justify-end align-start pa-5">
            <v-rating hover :length="1" :size="32" color="black" active-color="black" v-model:model-value="favState" 
                empty-icon="mdi-heart-outline" half-icon="mdi-heart-half-full" full-icon="mdi-heart" clearable/>
            <v-icon v-if="routineUserID.value == userID.value" :size="32" class="pl-15" color="black" icon="mdi-delete" @click="deletee()"></v-icon>
        </v-row>
    </v-sheet>
    <v-overlay class="align-center justify-center" location-strategy="static" v-model:model-value="overlay">
        <v-card width="50vw" height="80%">
            <v-card-title class="text-center">
                <h1>Editar rutina</h1>
            </v-card-title>
            <v-card-text>
                <v-text-field v-model:model-value="routineName" label="Nombre" outlined></v-text-field>
                <v-textarea v-model:model-value="desc" label="Descripción" outlined></v-textarea>
                <v-text-field v-model:model-value="imagen" label="URL de la imagen de la rutina" outlined></v-text-field>
                <v-select v-model:model-value="mat" :items="routineStore.getMaterial()" label="Equipamiento" multiple outlined></v-select>
                <v-select v-model:model-value="musc" :items="routineStore.getMuscles()" label="Músculos a trabajar" multiple outlined></v-select>
                <v-select v-model:model-value="int" :items="routineStore.getIntensity()" label="Intensidad" outlined></v-select>
                <v-btn  text @click="cancel()">Cancelar</v-btn>
                <v-btn  text @click="save()">Guardar</v-btn>
            </v-card-text>
        </v-card>
    </v-overlay>
</template>
 
<script setup>
    import { useRoutineStore } from '@/stores/routineStore';
    import { useUserStore } from '@/stores/userStore';
import { watch } from 'vue';
import { onBeforeMount } from 'vue';
import { ref } from 'vue';
import { RoutineInfo} from '@/api/routine.js'
import router from '@/router';
import { computed } from 'vue';
import { useDisplay } from 'vuetify';
    const props = defineProps(['img','isPublic','name','id','description', 'muscles', 'material', 'intensity', 'difficulty'])
    const routineStore = useRoutineStore();
    const userStore = useUserStore();
    const id = props.id;
    const favState = ref(0);
    const favourites = ref([]);
    const isPublic = ref(props.isPublic);
    const difficulty = ref(props.difficulty);

    const routineName = ref(props.name);
    const desc = ref(props.description);
    const musc = ref(props.muscles);
    const mat = ref(props.material);
    const int = ref(props.intensity);
    const imagen = ref(props.img)
    const done = ref(false);

    const rating = ref(0);
    const {name} = useDisplay()
    const ratingSize = computed( () => {
        switch(name.value){
            case 'xs': return 25
            case 'sm': return 35
            case 'md': return 50
            case 'lg': return 60
            default: return 70
        }
    })

    const routineUserID = ref('');
    const userID = ref('');

    onBeforeMount(async () => {
        favourites.value = await routineStore.retrieveFavourites();
        favourites.value.forEach((elem) => {
            if (elem.id == id) { 
                favState.value = 1;
                routineUserID.value = elem.user.id;
            }
        })

        const allratings = await routineStore.getReviews(id);
        if (allratings.content.length !== 0) {
            allratings.content.forEach((elem) => { rating.value += elem.score; })
            rating.value = Math.trunc(rating.value / allratings.content.length);
        }
        const result = await userStore.getCurrentUser();
        userID.value = await result.id;
        
        routineName.value = props.name;
        desc.value = props.description;
        musc.value = props.muscles;
        mat.value = props.material;
        int.value = props.intensity;
        imagen.value = props.img;
        isPublic.value = props.isPublic;
        difficulty.value = props.difficulty;

        done.value = true;
    })
    const overlay = ref(false);
    function cancel() {
        overlay.value = !overlay.value;
    }

    async function save() {
        const metadata = {
            muscles: musc.value,
            materials: mat.value,
            img: imagen.value
        }
        const routineInfo = new RoutineInfo(routineName.value, desc.value, isPublic.value ,difficulty.value, metadata)
        await routineStore.modifyRoutine(id, routineInfo);
        overlay.value = !overlay.value
    }


    watch(favState, async (newVal, oldVal) => {
        if (newVal === 1) {
            try {
                await routineStore.markAsFavourite(props.id);
            } catch(error) {
                console.log(error.description);
            }
        } else {
            try{
                await routineStore.unmarkAsFavourite(props.id);
            } catch(error) {
                console.log(error.description);
            }
        }
    })

    watch(rating, async (newVal, oldVal) => {
        if (!done) return;
        await routineStore.addReview(id, newVal);
    })
    
    async function deletee() {
        try {
            await routineStore.deleteRoutine(id);
            router.go(-1);
        } catch(error) {
            console.log(error.description);
        }
    }

</script>

<style scoped>
    h2 {
        color: black;
    }

    ul {
        list-style-type: none;
    }
</style>
