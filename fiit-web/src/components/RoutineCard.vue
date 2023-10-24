<template>
    <v-card :rounded="0" color="secondary" :class="['ma-4', selectedClass]"
            height="200"
            width="225"
            @:click="toggle"
            :to="link" 
    >
        <v-card :rounded="0" height="65%">
            <v-img :src="img" cover/>  
        </v-card>
            <v-card-text class="pa-2 font-weight-medium text-center" height="35%">{{ cardTitle }}</v-card-text>
        <v-row class="ml-1">
            <v-rating v-if="favState"
                    readonly
                    :length="1"
                    :size="32"
                    :model-value="1"
                    full-icon="mdi-heart"
                    style="position: absolute; 
                    bottom: 0; right: 0; z-index: 1;"/> 
            <div>
                <v-rating
                hover
                readonly
                :length="1"
                :size="32"
                active-color="primary"
                style="position: absolute; bottom: 0; z-index: 1;"/>
                <v-card-text class="pt-5 ml-4"> {{ rating }}</v-card-text>
            </div>
        </v-row>
    </v-card>
</template>

<script setup>
    import { onBeforeMount, ref } from 'vue';
    import { useRoutineStore } from '@/stores/routineStore';
    const props = defineProps(['img','title', 'selectedClass', 'toggle', 'id']);
    const img = props.img;
    const cardTitle = props.title;
    const selectedClass = props.selectedClass;
    const toggle = props.toggle;
    const id = props.id;
    const link = `/newRoutine?id=${id}`;
    const favState = ref(false);
    const rating = ref(0);
    const routineStore = useRoutineStore();
    const favourites = ref([]);
    onBeforeMount(async () => {
        favourites.value = await routineStore.retrieveFavourites();
        favourites.value.forEach((elem) => {
            if (elem.id == id) { 
                favState.value = 1;
            }
        })
        const allratings = await routineStore.getReviews(id);
        
        if (allratings.content.length !== 0) {
            rating.value = 0;
            allratings.content.forEach((elem) => { rating.value += elem.score; })
            rating.value = Math.trunc(rating.value / allratings.content.length);
        } else {
            rating.value = 'No rating';
        }
    })
</script>




