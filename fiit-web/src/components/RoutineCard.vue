<template>
    <v-card color="secondary" :class="['ma-4', selectedClass]"
            height="150"
            width="225"
            @:click="toggle"
            :to="link" 
    >
        <v-card height="75%">
            <v-img :src="img" cover/>  
        </v-card>
            <v-card-text class="pa-2 font-weight-medium text-center" height="25%">{{ cardTitle }}</v-card-text>
            <v-rating v-if="FavState"
                    hover
                    readonly
                    :length="1"
                    :size="32"
                    :model-value="1"
                    active-color="primary"
                    empty-icon="mdi-heart-outline"
                    full-icon="mdi-heart"
                    clearable
                    style="position: absolute; bottom: 0; right: 0; z-index: 1;"
            /> 
            <v-rating v-else
                    hover
                    readonly
                    :length="1"
                    :size="32"
                    active-color="primary"
                    empty-icon="mdi-heart-outline"
                    full-icon="mdi-heart"
                    clearable
                    style="position: absolute; bottom: 0; right: 0; z-index: 1;"
            />
            <v-rating v-if="FavState"
                hover
                readonly
                :length="1"
                :size="32"
                :model-value="1"
                active-color="primary"
                style="position: absolute; bottom: 0; z-index: 1;"
            />
            <v-rating v-else
                hover
                readonly
                :length="1"
                :size="32"
                active-color="primary"
                style="position: absolute; bottom: 0; z-index: 1;"
            />
    </v-card>
</template>


//Recibe dos parametros más q ExersiveCard, estos son el FavState (boolean), para saber si estan en favs o no (si no esta loged in va automatico a no)
//claramente si esta es true, si no esta es false
//y tmb el Rating, para saber la puntuación 

<script setup>
    const props = defineProps(['img','title', 'selectedClass', 'toggle', 'favState', 'rating', 'id']);
    const img = props.img;
    const cardTitle = props.title;
    const selectedClass = props.selectedClass;
    const toggle = props.toggle;
    const FavState = props.favState;
    const id = props.id;
    
    const link = `/newRoutine?id=${id}`;
</script>




