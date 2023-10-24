<template>
    <v-card color="secondary" class="ma-4"
            width="200"
            @:click="toggle"
            :to="link"
            >
        <v-card height="75%">
            <v-img :src="img" cover/>   
        </v-card>
        <v-card-text class="pa-2 font-weight-medium text-center" height="25%"> {{ cardTitle }}</v-card-text>
    </v-card>  
</template>

<script setup>
    const props = defineProps(['img','title', 'id', 'toggle']);
    const img = props.img;
    const cardTitle = props.title;
    const id = props.id;
    const toggle = props.toggle;

    const link = `/oneExercice?id=${id}`;
</script>