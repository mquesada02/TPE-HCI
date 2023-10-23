<template>
    <v-card color="secondary" :class="['ma-4', selectedClass]"
            height="200"
            width="225"
            @:click="toggle"
            :to="link" 
    >
        <v-card height="65%">
            <v-img :src="img" cover/>  
        </v-card>
            <v-card-text class="pa-2 font-weight-medium text-center" height="35%">{{ cardTitle }}</v-card-text>
        <v-row class="ml-1">
            <v-rating v-if="favState"
                    hover
                    readonly
                    :length="1"
                    :size="32"
                    :model-value="1"
                    empty-icon="mdi-heart-outline"
                    full-icon="mdi-heart"
                    clearable
                    style="position: absolute; bottom: 0; right: 0; z-index: 1;"/> 
            <div>
                <v-rating
                hover
                readonly
                :length="1"
                :size="32"
                active-color="primary"
                style="position: absolute; bottom: 0; z-index: 1;"/>
                <v-card-text class="pt-5 ml-4">numerito {{ Rating }}</v-card-text>
            </div>
        </v-row>
    </v-card>
</template>

<script setup>
    import { onBeforeMount, ref } from 'vue';
    import { useRoutineStore } from '@/stores/routineStore';
    const props = defineProps(['img','title', 'selectedClass', 'toggle', 'rating', 'id']);
    const img = props.img;
    const cardTitle = props.title;
    const selectedClass = props.selectedClass;
    const toggle = props.toggle;
    const Rating = props.rating;
    const id = props.id;
    const link = `/newRoutine?id=${id}`;
    const favState = ref(false);
    const routineStore = useRoutineStore();
    const favourites = ref([]);
    onBeforeMount(async () => {
        favourites.value = await routineStore.retrieveFavourites();
        favourites.value.forEach((elem) => {
            if (elem.id == id) { 
                favState.value = 1;
            }
        })
    })
</script>




