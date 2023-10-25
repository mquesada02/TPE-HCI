<template>
    <v-sheet color="background"
      class="mx-auto"
      max-width="100%"
    >
    <p class="pl-3 pt-3 text-h4 font-weight-medium">{{ title }}</p>
    <v-alert v-if="imgs.length==0" class="ma-2" type="warning">{{ text }}</v-alert>
      <v-slide-group
        class="pa-4"
        selected-class="bg-success"
        show-arrows
      >
        <v-slide-group-item
          v-for="img in imgs"
          :key="img"
          v-slot="{ isSelected, toggle, selectedClass }"
        >
          <ExcersiveCard :img="img.src" :title="img.title" :id="img.id" :selected-class="selectedClass" :toggle="toggle"/>
        </v-slide-group-item>
      </v-slide-group>
    </v-sheet>
</template>

<script setup>
    import { onBeforeMount, ref } from 'vue';
import ExcersiveCard from './ExcersiveCard.vue';
    const props = defineProps(['title','imgs','id']);
    const title = ref(props.title);
    const imgs = ref(props.imgs);
    const id = ref(props.id);
    onBeforeMount(async () => {
      title.value = props.title;
      imgs.value = await props.imgs;
      id.value = props.id;
    })
    
</script>