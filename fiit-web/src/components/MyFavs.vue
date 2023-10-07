<template>
    <p class="pl-3 pt-10 text-h4 font-weight-medium">
        Mis favoritos:
    </p>
    <div class="text-center">
    <v-container>
      <v-row justify="center">
        <v-col cols="4">
          <v-container class="max-width">
            <v-pagination
            :length="5"
            ></v-pagination>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import Card from "@/components/ExcersiveCard.vue";
</script>