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
          <myExercices :items="exercices"/>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
<script setup>
    import myExercices from '@/components/ExcerciceIter.vue';
    import CreateEx from '@/components/CreateExcercice.vue'
    import { onBeforeMount, ref } from 'vue';
    import { useUserStore } from '@/stores/userStore';
    import { useRoutineStore} from '@/stores/routineStore';
    const exercices = ref([]);
    onBeforeMount( async () => {
      const userStore = useUserStore();
      const ejercicios = ref(null);
      const routineStore = useRoutineStore();
      const var1 = ref(null);
      let i = 0;
      ejercicios.value = await userStore.getCurrentMyExcrcicesByPage(i);                      
      ejercicios.value.content.forEach( async (elem) => {
        var1.value= await routineStore.getExerciseImage(elem.id);
        exercices.value.push({src: var1.value.url, title: elem.name, id: elem.id})
      })
      while (!ejercicios.value.isLastPage) {
        ejercicios.value = await userStore.getCurrentMyExcrcicesByPage(++i);                        
        ejercicios.value.content.forEach( async (elem) => {
          var1.value= await routineStore.getExerciseImage(elem.id);
          exercices.value.push({src: var1.value.url, title: elem.name, id: elem.id})
        })
      } 
    })
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