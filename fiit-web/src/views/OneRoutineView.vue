<template>
    <div >
        <v-row>
            <v-col class="ma-10" cols="4">   
                <h1>{{name}}</h1>
                <v-row>
                    <div class="pt-5">
                    <v-img
                        width="500"
                        :src="src" 
                        cover/>
                    </div>
                </v-row>
                <div class="pt-10">
                    <RoutineInfo :description="description" :intensity="intensity" :muscles="muscles" :material="material"/>
                </div>
            </v-col>
            <v-col class="ma-15">
                <div class="pt-4">
                  <ExcerciceScroller/>
                </div>
            </v-col>
        </v-row>
    </div>
</template>


//el nombre de rutina es un parametro q se tiene q recibir desde donde sea q se clickee
//al igual q la foto
//en vez de muchos miniExcerise seria un v-for q recorre la info de la api sobre q ej estan en esta rutina


<script setup>
    import RoutineInfo from '@/components/RoutineInfo.vue';
    import ExcerciceScroller from '@/components/ExcerciceScroller.vue';
    
    import { useRoutineStore } from '@/stores/routineStore';
    import { onBeforeMount, ref } from 'vue';

    const src = ref('');
    const muscles = ref([]);
    const material = ref([]);
    const description = ref('');
    const intensity = ref('');
    const name = ref('');
    
    onBeforeMount(async () => {
        const routineStore = useRoutineStore();
        const id = ref('');
        const params = new URLSearchParams(location.search);
        id.value = params.get("id");
        const routine = await routineStore.retrieveRoutineById(id.value);
        src.value = routine.metadata.img;
        name.value = routine.name;
        intensity.value = routine.difficulty === 'rookie' ? 'Bajo' : routine.difficulty === 'intermediate' ? 'Medio' : 'Alto';
        description.value = routine.detail;
        muscles.value = routine.metadata.muscles;
        material.value = routine.metadata.materials;
        console.log(routine);
    })
</script>