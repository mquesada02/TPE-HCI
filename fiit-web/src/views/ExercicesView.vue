<template>
    <v-app id="rutines">
      <v-main color="background">
        <v-tabs grow >
            <v-tab  to="/myRoutines" text="Mis rutinas" />
            <v-tab  to="/myExercices" text="Mis ejercicios"/>
        </v-tabs>
        <v-container class="pt-15">
          <v-row align="center" justify="center">
            <v-btn  size="x-large" variant="outlined" color="secondary" @click="dialogCrear = true">
              Crear nuevo ejercicio <v-icon icon="mdi-plus" size="x-large"/>
            </v-btn>
            <v-dialog v-model="dialogCrear" width="auto">
                <v-sheet class="sheet">
                <div>
                  <v-row class="ml-8 pt-5">
                      <v-btn
                        color="secondary"
                        @click="dialogSheet = true"
                      >
                      <v-icon icon="mdi-chevron-left" size="x-large"></v-icon>
                      </v-btn>
                      <v-dialog
                          v-model="dialogSheet"
                          width="auto"
                      >
                      <v-card>
                          <v-card-actions>
                            <v-card-text class="text-center">
                                <v-btn @click="dialogSheet=false" class="pt-5">
                                    Queeeeeeddaaettte atte:Quevedo
                                </v-btn>
                                <v-btn color="primary" block @click="dialogSheet = false, dialogCrear=false">
                                    Si usted vuelve a la busqueda de rutina perdera el progreso de este ejercicio
                                </v-btn>
                            </v-card-text>
                          </v-card-actions>
                      </v-card>
                      </v-dialog>
                  </v-row>
                </div>
                <CreateEx :from="false"/>
                </v-sheet>
            </v-dialog>
          </v-row>
          <h1 class="pt-5">Mis ejercicios:</h1>
          <myExercices :items="recientes"/>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script setup>
      import myExercices from '@/components/ExcerciceIter.vue';
      import CreateEx from '@/components/CreateExcercice.vue'
      const recientes = [
  ];
  </script>

<script>
export default {
data() {
    return {
    dialogCrear: false,
    dialogSheet: false,
    };
},
};
</script>

<style scoped>
 .sheet {
        background-color: black;
        padding: 40px;
    }
</style>