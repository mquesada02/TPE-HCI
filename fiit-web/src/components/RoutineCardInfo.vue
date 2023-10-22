<template>
    <v-sheet rounded="xl" color="lighter" width="35vw" min-height="100vh" class="pa-5 mt-5">
        <v-row class="pa-5 d-flex justify-end">
            <v-icon icon="mdi-pencil" @click="deletee()"></v-icon>
        </v-row>
        <v-row class="d-flex align-center justify-center">
            <v-rating hover :length="5" :size="70" :model-value="3" color="black" active-color="primary" />
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
            <v-icon size="24" class="pl-15" color="black" icon="mdi-delete" @click="deletee()"></v-icon>
        </v-row>
    </v-sheet>
</template>
 
<script setup>
    import { useRoutineStore } from '@/stores/routineStore';
import { watch } from 'vue';
import { onBeforeMount } from 'vue';
import { ref } from 'vue';
    const props = defineProps(['id','description', 'muscles', 'material', 'intensity'])
    const routineStore = useRoutineStore();
    const id = props.id;
    const favState = ref(0);
    const favourites = ref([]);
    onBeforeMount(async () => {
        favourites.value = await routineStore.retrieveFavourites();
        favourites.value.forEach((elem) => {
            if (elem.id == id) { 
                favState.value = 1;
            }
        })
    })

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

    function modify() {

    }
    
    function deletee() {

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
