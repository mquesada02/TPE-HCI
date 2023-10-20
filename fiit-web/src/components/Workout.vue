<template>
    <div class="text-right">
        <v-btn
            color="secondary"
            @click="dialog = true"
            >
            <v-icon icon="mdi-information" size="x-large"></v-icon>
        </v-btn>
        <v-dialog
            v-model="dialog"
            width="auto"
        >
        <v-card>
            <v-card-text>
            Para completar esta sección se tiene que 
            </v-card-text>
            <v-card-actions>
            <v-btn color="primary" block @click="dialog = false">Close</v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </div>
</template>

<script>
  export default {
    data () {
      return {
        dialog: false,
      }
    },
  }
</script>