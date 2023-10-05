<template>
    <p class="pl-3 pt-10 text-h4 font-weight-medium">
        Mis favoritos:
    </p>
    <div v-for="product in displayedItems()">
        <ExcersiveCard :img="product.src" :title="product.title" />
    </div>
    <div class="text-center">
    <v-container>
      <v-row justify="center">
        <v-col cols="4">
          <v-container class="max-width">
            <v-pagination
            v-model="paginationData.currentPage"
            :length="paginationData.totalPages"
            @input="changePage"
            ></v-pagination>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </div>
    <div class="text-center">
        
    </div>
</template>

<script>
const props = defineProps(['items'])
const items = props.items
export default {
  data() {
    return {
      paginationData: {
        currentPage: 1, //no tiene q decir un numero, tiene decir la q se ese usando
        itemsPerPage: 2,  //se tiene q cambiar a 10, esto es solo de testeo
        totalPages: 15, //en realidad el numero total de páginas debe ser medido por cuantas rutinas tiene la persona, osea la api
      },
    };
  },
  computed: {
    displayedItems() {
      const startIndex = (this.paginationData.currentPage - 1) * this.paginationData.itemsPerPage;
      const endIndex = startIndex + this.paginationData.itemsPerPage;
      return this.items.slice(startIndex, endIndex);
    },
  },
  methods: {
    changePage(page) {
      this.paginationData.currentPage = page;
      //tiene q cambiar cuando hay un click sobre el v-pagging
    },
  },
};
</script>