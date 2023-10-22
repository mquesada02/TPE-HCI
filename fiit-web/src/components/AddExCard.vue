<template>
  <v-container fluid color="secondary">
    <v-row justify="center">
        <v-col>
            <ExCard title="prueba"/>
        </v-col>
        <v-col>
            <div class="d-flex pa-4">
                <v-checkbox-btn
                v-model="enabledReps"
                class="pe-2"
                ></v-checkbox-btn>
                <v-text-field
                :disabled="!enabledReps"
                hide-details
                label="Repeticones:"
                ></v-text-field>
            </div>
            <div class="d-flex pa-4">
                <v-checkbox-btn
                v-model="enabledSeg"
                class="pe-2"
                ></v-checkbox-btn>
                <v-text-field
                :disabled="!enabledSeg"
                hide-details
                label="Segundos:"
                ></v-text-field>
            </div>
        </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
      enabledReps: false,
      enabledSeg: false,
    }),
  }
</script>

<script setup>
    import ExCard from '@/components/ExcersiveCard.vue'
</script>

<style scoped>
.centered-text {
  font-size: 24px;
  text-align: center;
}
</style>
