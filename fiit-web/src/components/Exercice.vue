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
                <h1 class="mt-4 mb-6 mr-4 ml-6 text-center">Descripción</h1>
                <h3 class="mb-4 ml-12 mr-12"> {{ descripcion }} </h3>
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
                 </v-sheet>
            </v-col>
        </v-row>
    </v-container>
</template>

<script setup>
    import { ref, onBeforeMount } from 'vue';
    import { useExerciseStore } from '@/stores/exerciseStore';
    import router from '@/router';
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
            router.go(-2);                                 //SOPECHOSO 
        } catch(error) {
            console.log(error.description);
        }
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