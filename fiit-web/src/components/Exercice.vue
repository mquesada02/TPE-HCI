<template>
    <v-container>
        <v-row>
            <v-col :cols="videoUrl.value ? 6 : 12">
                <h1 color="white" class="mb-6 mt-4 ml-4">{{nombre}}</h1>
                <div v-if="videoUrl.value">
                <iframe
                    width="450"
                    height="300"
                    class="mb-8 ml-4"
                    :src="videoUrl.value"
                    frameborder="0"
                    allowfullscreen
                ></iframe>
                </div>
            </v-col>
            <v-col :cols="videoUrl.value ? 6 : 12">
                <v-sheet :width="videoUrl.value ? 550 : 650" color="secondary" :class="videoUrl.value? 'mt-8 mb-6 mr-4 pa-5':'mb-4 mx-auto pa-5'" rounded="lg" style="border: 1px solid black;">
                <v-img  :src="img" height="300"/>
                <div class="text-right">
                    <v-icon icon="mdi-pencil" @click="overlay = !overlay"></v-icon>
                    <v-overlay class="align-center justify-center" location-strategy="static" v-model:model-value="overlay">
                        <v-card width="50vw" height="80%">
                            <v-card-title class="text-center">
                                <h1>Editar ejercicio</h1>
                            </v-card-title>
                            <v-card-text>
                                <v-text-field v-model:model-value="nombre" label="Nombre" outlined></v-text-field>
                                <v-textarea v-model:model-value="descripcion" label="Descripción" outlined></v-textarea>
                                <v-text-field v-model:model-value="img" label="URL de la imagen del ejercicio" outlined></v-text-field>
                                <v-text-field v-model:model-value="videoUrl" label="URL del del ejercicio" outlined></v-text-field>
                                <v-btn  text @click="overlay=!overlay">Cancelar</v-btn>
                                <v-btn  text @click="save(), overlay=!overlay">Guardar</v-btn>
                            </v-card-text>
                        </v-card>
                    </v-overlay>
                </div>
                <h1 class="mt-4 mb-6 mr-4 ml-6 text-center">Descripción</h1>
                <h3 class="mb-4 ml-12 mr-12"> {{ descripcion }} </h3>
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
                 </v-sheet>
            </v-col>
        </v-row>
    </v-container>
</template>

<script setup>
    import { ref, onBeforeMount } from 'vue';
    import { useExerciseStore } from '@/stores/exerciseStore';
    import { ExerciseInfo} from '@/api/exercise.js'
    import router from '@/router';
    const overlay = ref(false);
    const exerciseStore = useExerciseStore();
    const exerciseUserID = ref('');
    const userID = ref('');
    const props = defineProps(['nombre','descripcion', 'img', 'video', 'id'])
    const nombre = ref(props.nombre)
    const descripcion = ref(props.descripcion)
    const videoUrl = ref(props.video)
    const img = ref(props.img)
    const id = props.id;

    onBeforeMount(()=>{
        nombre.value=props.nombre
        descripcion.value = props.descripcion
        videoUrl.value = props.video
        img.value = props.img
    })

    async function deletee() {
        try {
            await exerciseStore.deleteExercise(id);
            router.go(-2);                               
        } catch(error) {
            console.log(error.description);
        }
    }
    async function save() {
        const metadata = {
            img: img.value
        }
        const exerciseInfo = new ExerciseInfo(nombre.value, descripcion.value, metadata)
        await exerciseStore.modifyExercise(id, exerciseInfo);
        overlay.value = !overlay.value
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

<!-- <script>
    export default {
    data() {
        return {
            videoUrl: "https://www.youtube.com/embed/ogfuXWgXVsg", 
        //si no hay video aparece una pag y si hay otra
        };
    },
    };
</script> -->