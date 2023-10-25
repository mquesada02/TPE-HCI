<template>
    <div >
        <v-btn
          color="secondary"
          class="mt-3 ml-3"
          @click="goBack()"
          >
          <v-icon icon="mdi-chevron-left" size="x-large"></v-icon>
        </v-btn>
        <v-row>
            <v-col class="ma-10" cols="4">   
                <h1>{{name}}</h1>
                <v-row class="d-flex justify-center align-center">
                    <div class="pt-5">
                    <v-img
                        width="300"
                        :src="src" 
                        cover/>
                    </div>
                </v-row>
                <RoutineCardInfo :img="img" :difficulty="difficulty" :isPublic="isPublic" :id="id" :name="name" 
                :description="description" :intensity="intensity" :muscles="muscles" :material="material"/>
            </v-col>
            <v-col class="text-center pt-15">
                  <v-container v-for="cycle in cycles">
                    <ExerciseCarousel :title="cycle.name" :imgs="getExArray(cycle)"/>
                  </v-container>
            </v-col>
        </v-row>
    </div>
</template>


<script setup>
    import RoutineCardInfo from '@/components/RoutineCardInfo.vue';
    import { useRoutineStore } from '@/stores/routineStore';
    import { useExerciseStore } from '@/stores/exerciseStore';
    import { onBeforeMount, ref } from 'vue';
    import ExerciseCarousel from '@/components/ExerciseCarousel.vue';
    import router from '@/router';
    const src = ref('');
    const muscles = ref([]);
    const material = ref([]);
    const description = ref('');
    const intensity = ref('');
    const name = ref('');
    const isPublic = ref(false);
    const difficulty = ref('');
    const img = ref('');
    const id = ref('');

    const cycles = ref([]);
    const ExArray = ref([])

    const exerciseStore = useExerciseStore();
    const routineStore = useRoutineStore();

    onBeforeMount(async () => {
        const routineStore = useRoutineStore();
        const params = new URLSearchParams(location.search);
        id.value = params.get("id");
        const routine = await routineStore.retrieveRoutineById(id.value);
        src.value = routine.metadata.img;
        name.value = routine.name;
        difficulty.value = routine.difficulty
        intensity.value = routine.difficulty === 'rookie' ? 'Baja' : routine.difficulty === 'intermediate' ? 'Media' : 'Alta';
        description.value = routine.detail;
        muscles.value = routine.metadata.muscles;
        material.value = routine.metadata.materials;
        isPublic.value = routine.isPublic;
        img.value = routine.metadata.img;
        
        cycles.value = await routineStore.getRoutineCycles(id.value);

    })

    async function getExArray(cycle) {
        const exercises = await routineStore.getCycleExercises(cycle.id)
        console.log('IDDD')
        console.log(cycle.id)
        exercises.forEach(async (ex) => {
            const img = await exerciseStore.exerciseImage(ex.id)
            ExArray.value.push({src: img, title: ex.name, id: ex.id})
        })
        return ExArray
    }

    function goBack(){
      router.go(-1);
    }
</script>