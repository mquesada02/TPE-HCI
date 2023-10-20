<template>
    <div class="pt-5">
        <v-card color="lighter">
            <div class="pt-10 ml-5">
                <v-row>
                    <h1>Ciclo {{ props.title }}</h1>
                    <div class="text-right">
                        <v-row>
                        <h1>Series:</h1> 
                        <v-text-field 
                        class="series" 
                        />
                        </v-row>
                    </div>
                </v-row>
            </div>
        <v-divider></v-divider>
            <v-row
                v-for="item in items"
                :key="item"
                cols="4"
                sm="6"
                md="4"
                lg="3"
                >
                <ExcersiveCard :title="item.raw.title" :img="item.raw.src"></ExcersiveCard>
            </v-row>
        <v-divider></v-divider>
        <div class="ml-10">
            <v-btn color="secondary" @click="dialog = true">
                <v-icon icon="mdi-plus" size="x-large"></v-icon>
            </v-btn>
            <v-dialog v-model="dialog" width="auto">
            <v-card>
                <v-card-actions>
                    <v-row>
                        <v-btn>Buscar ejercicio</v-btn>
                        <v-btn>Crear ejercicio</v-btn>
                    </v-row>
                    <v-row>
                    <v-btn color="primary" block @click="dialog = false">Close</v-btn>
                    </v-row>
                </v-card-actions>
            </v-card>
            </v-dialog>
        </div>
        </v-card>
    </div>
</template>

<script setup>
    import ExcersiveCard from './ExcersiveCard.vue';
    const props = defineProps(['title', 'items']);
    const items = props.items;
    for (let i = 0; i < items.length; i++) {
        items[i].src = new URL('../assets/img/ejercicios/' + items[i].src, import.meta.url).href;
      }
</script>


<style scoped>
    .series{
        width: 10%;
        padding-bottom: 20px;
    }
</style>

<script>
  export default {
    data () {
      return {
        dialog: false,
      }
    },
  }
</script>