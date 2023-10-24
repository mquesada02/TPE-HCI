<template>
    <h1 color="white" class="text-center">{{nombre}}</h1>
    <div class="container">
        {{ console.log(videoUrl) }}
        <div class="card-div">
            <v-icon icon="mdi-pencil" color="black" class="pb-5 pt-3" @click="overlay = !overlay" />
            <v-img  :src="img" height="300"/>
            <div class="text-right">
                <v-overlay class="align-center justify-center" location-strategy="static" v-model:model-value="overlay">
                    <v-card width="50vw" height="80%">
                        <v-card-title class="text-center mt-3">
                            <h1>Editar ejercicio</h1>
                        </v-card-title>
                        <v-card-text>
                            <v-text-field v-model:model-value="nombre" label="Nombre" outlined></v-text-field>
                            <v-textarea v-model:model-value="descripcion" label="Descripción" outlined></v-textarea>
                            <v-text-field v-model:model-value="img" label="URL de la imagen del ejercicio" outlined></v-text-field>
                            <v-text-field v-model:model-value="videoUrl" label="URL del video del ejercicio" outlined></v-text-field>
                            <v-btn  text @click="overlay=!overlay">Cancelar</v-btn>
                            <v-btn  text @click="save()">Guardar</v-btn>
                        </v-card-text>
                    </v-card>
                </v-overlay>
            </div>
            <h1 class="mt-4 mb-6 mr-4 ml-6 text-center hColor">Descripción</h1>
            <h3 class="mb-4 ml-12 mr-12 hColor"> {{ descripcion }} </h3>
            <div class="text-right">
                <v-icon v-if="exerciseUserID.value == userID.value" :size="32" color="black" icon="mdi-delete" @click="dialogDel=true"></v-icon>
                <v-dialog v-model="dialogDel" width="auto">
                    <v-card>
                        <v-card-actions>
                            <v-card-text class="text-center">
                                <v-divider class="pt-2"></v-divider>
                                    <v-btn color="primary" block width="50%" @click="dialogDel = false,  deletee()" to="/myExercices">
                                    Eliminar
                                    </v-btn>
                                    <v-btn @click="dialogDel = false" class="pt-5">
                                    Cancelar
                                    </v-btn>
                            </v-card-text>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </div>
        </div>
        <div v-if="videoUrl" class="video-div">
            <iframe 
                :src="videoUrl"
                allowfullscreen
            ></iframe>
        </div>
    </div>
</template>

<script setup>
    import { ref, inject } from 'vue';
    import { useExerciseStore } from '@/stores/exerciseStore';
    import router from '@/router';
    const overlay = ref(false);
    const exerciseStore = useExerciseStore();
    const exerciseUserID = ref('');
    const userID = ref('');
    const nombre = inject('nombre')
    const descripcion = inject('descripcion')
    const videoUrl = inject('videoUrl')
    const img = inject('img')
    const id = inject('id')

    console.log(videoUrl.value)
    console.log(img.value)

    async function deletee() {
        try {
            await exerciseStore.deleteExercise(id.value);
            router.go(-2);                               
        } catch(error) {
            console.log(error.description);
        }
    }
    async function save() {
        overlay.value = !overlay.value
        await exerciseStore.modifyExercise(id.value, nombre.value, descripcion.value, 'exercise');
        await exerciseStore.modifyExerciseImage(id.value, img.value);
        await exerciseStore.modifyExerciseVideo(id.value, videoUrl.value);
    }   
</script>

<script>
export default {
    data() {
        return {
            dialogDel: false,
        };
    },
}
</script>

<style scoped>
  .container{
    width:100%;
    margin:auto
  }
  .video-div{
    width:70%;
    margin:auto;
    margin-top:20px;
    height:300px;
  }
  .card-div{
    width:70%;
    padding:10px;
    margin:auto;
    background-color: #FFCCBC;
  }
  .hColor{
    color:black;
  }
  iframe{
    width:100%;
    height:100%
  }
</style>