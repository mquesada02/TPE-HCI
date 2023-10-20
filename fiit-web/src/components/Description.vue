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
            Para completar esta sección se tiene que haber seleccionado minimo un elemento en Músculo, Intensidad, Objetivo, Estado y Equipameinto. 
            Además se debe haber completo el nombre y una breve descirpción. 
            </v-card-text>
            <v-card-actions>
            <v-btn color="primary" block @click="dialog = false">Close</v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </div>
    <v-form>
        <v-text-field
        class="pt-5"
        hide-details
        label="Nombre de la rutina:"
        single-line
        ></v-text-field>
        <v-divider></v-divider>
        <v-textarea
        counter
        label="Descripción..."
        maxlength="500"
        single-line
        ></v-textarea>
    </v-form>
    <v-card class="card pt-3">
        <v-row>
            <v-col>
                <div class="checkbox-list">
                    <h1 style="font-size: 25px;">Músculo:</h1>
                    <v-divider></v-divider>
                    <v-checkbox label="Abdominales" values="Abdominales"></v-checkbox>
                    <v-checkbox label="Biceps" values="Biceps"></v-checkbox>
                    <v-checkbox label="Cuádriceps" values="Cuádriceps"></v-checkbox>
                    <v-checkbox label="Espalda" values="Espalda"></v-checkbox>
                    <v-checkbox label="Glúteos" values="Glúteos"></v-checkbox>
                    <v-checkbox label="Gemelos" values="Gemelos"></v-checkbox>
                    <v-checkbox label="Isquiotibiales" values="Isquiotibiales"></v-checkbox>
                    <v-checkbox label="Hombro" values="Hombro"></v-checkbox>
                    <v-checkbox label="Pectoral" values="Pectoral"></v-checkbox>
                    <v-checkbox label="Triceps" values="Triceps"></v-checkbox>
                </div>
            </v-col>
            <v-col>
                <div class="checkbox-list">
                    <h1 style="font-size: 25px;">Intensidad:</h1>
                    <v-divider></v-divider>
                    <v-checkbox label="Baja" values="Baja"></v-checkbox>
                    <v-checkbox label="Media" values="Media"></v-checkbox>
                    <v-checkbox label="Alta" values="Alta"></v-checkbox>
                </div>
                <div class="checkbox-list pt-10">
                    <h1 style="font-size: 25px;">Objetivo:</h1>
                    <v-divider></v-divider>
                    <v-checkbox label="Fuerza" values="Fuerza"></v-checkbox>
                    <v-checkbox label="Bajar de peso" values="Bajar de peso"></v-checkbox>
                    <v-checkbox label="Flexibilidad" values="Flexibilidad"></v-checkbox>
                    <v-checkbox label="Ganar músculo" values="Ganar músculo"></v-checkbox>
                </div>
            </v-col>
            <v-col>
                <div class="checkbox-list">
                    <h1 style="font-size: 25px;">Equipamiento:</h1>
                    <v-divider></v-divider>
                    <v-checkbox label="Sin material" values="Sin material"></v-checkbox>
                    <v-checkbox label="Máquinas" values="Máquinas"></v-checkbox>
                    <v-checkbox label="Pesas" values="Pesas"></v-checkbox>
                    <v-checkbox label="Banda elástica" values="Banda elástica"></v-checkbox>
                    <v-checkbox label="Soga" values="Glúteos"></v-checkbox>
                </div>
                <div>
                    <h1 style="font-size: 25px">Estado:</h1>
                    <v-divider></v-divider>
                    <v-radio-group class="pt-4">
                    <v-radio label="Privada (solo la podras visualizar vos y la gente a la cual se la compartas)" value="one"></v-radio>
                    <v-radio label="Pública (podra ser vista por cualquier persona con una cuenta en fiit-web)" value="two"></v-radio>
                    </v-radio-group>
                </div>
            </v-col>
        </v-row>
    </v-card>
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

<style scoped>
.nextBtn {
  align-items: right;
  justify-content: right;
  position: fixed;
  top: 93%;
  right: 3%;
  transform: translateY(-50%);
}
/*
se debe crear una variable a partir de la info en api de cuantas cosas fueron seleccionadas
esta variable es boolean, y es la q va en el next
*/
</style>