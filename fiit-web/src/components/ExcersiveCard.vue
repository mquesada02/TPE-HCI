<template>
    <v-card color="secondary" :class="['ma-4', selectedClass]"
            height="150"
            width="200"
            @:click="toggle"
            to="/oneExercice"
            >
        <v-card height="75%">
            <v-img :src="img" cover/>   
        </v-card>
        <v-card-text class="pa-2 font-weight-medium text-center" height="25%"> {{ cardTitle }}</v-card-text>
    </v-card>  
</template>

<script setup>
    const props = defineProps(['img','title', 'selectedClass', 'toggle']);
    const img = props.img;
    const cardTitle = props.title;
    const selectedClass = props.selectedClass;
    const toggle = props.toggle;
</script>