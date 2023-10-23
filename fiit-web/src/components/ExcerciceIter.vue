<template>
  <v-data-iterator
    v-model:items-per-page="itemsPerPage"
    v-model:page="page"
    :items="items"
    :search="search"
    :sort-by="sortBy"
  >
    <template v-slot:no-data>
      <v-alert class="ma-2" type="warning">No hay ningún ejercicio creado</v-alert>
    </template>

    <template v-slot:default="{items}" >
      <v-row>
        <v-col
          v-for="item in items"
          :key="item"
          cols="4"
          sm="6"
          md="4"
          lg="3"
        >
          <ExcersiveCard :title="item.raw.title" :img="item.raw.src" :id="item.raw.id"></ExcersiveCard>
        </v-col>
      </v-row>
    </template>

    <template v-slot:footer>
    <div class="d-flex align-center justify-space-around pa-4">
        <v-spacer></v-spacer>
        <span class="mr-4 grey--text">
          Página {{ page }} de {{ numberOfPages }}
        </span>
        <v-btn icon size="small" @click="prevPage">
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
        <v-btn icon size="small" class="ml-2" @click="nextPage">
          <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
    </div>
    </template>
  </v-data-iterator>
</template>


<script setup>
  import ExcersiveCard from './ExcersiveCard.vue';
  import { computed, ref } from 'vue'
  const props = defineProps(['items'])
  const items = props.items;
  for (let i = 0; i < items.length; i++) {
      items[i].src = new URL('../assets/img/ejercicios/' + items[i].src, import.meta.url).href;
    }
  const itemsPerPage = ref(8)
  const page = ref(1)
  const numberOfPages = computed(() => {
    if(props.items.length==0){
        return 1;
    }
    return Math.ceil(props.items.length / itemsPerPage.value)
  })
  function nextPage () {
    if (page.value + 1 <= numberOfPages.value) page.value += 1
  }
  function prevPage () {
    if (page.value - 1 >= 1) page.value -= 1
  }
</script>

