<template>
  <v-container fluid color="secondary">
    <v-row justify="center">
        <v-col>
            <ExCard title="prueba"/>
        </v-col>
        <v-col>
            <div class="d-flex pa-5" v-if="notDescanso">
                <v-checkbox-btn
                v-model="enabledReps"
                ></v-checkbox-btn>
                <v-text-field
                :disabled="!enabledReps"
                hide-details
                label="Repeticones:"
                style="width: 125px;"
                ></v-text-field>
            </div>
            <div class="d-flex pa-4">
                <v-checkbox-btn
                v-model="enabledSeg"
                ></v-checkbox-btn>
                <v-text-field
                :disabled="!enabledSeg"
                hide-details
                label="Segundos:"
                style="width: 125px;"
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

LA CONST notDescanso TIENE Q IGUALARSE A LA VARIABLE DE API Q DIFERENCIA ENTRE EJERCICIO Y DESCANSO 

<script setup>
    import ExCard from '@/components/ExcersiveCard.vue'
    const notDescanso = true;
</script>

<style scoped>
.centered-text {
  font-size: 24px;
  text-align: center;
}
</style>
