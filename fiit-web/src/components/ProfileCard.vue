<template>
    <v-container class="d-flex justify-center pa-10">
        <v-sheet height="100%" width="40%" color="secondary" rounded="xl" elevation="10" class="d-flex justify-center pa-5">
            <v-sheet v-if="!modifyMode" height="100%" width="100%" color="lighter" rounded="xl" class="pa-0 text-center">
                <div class="pa-4 d-flex flex-row-reverse">
                    <v-btn @click="modify()" size="50" color="primary" icon="mdi-pencil" />
                </div>
                <v-avatar color="background" size="150">
                    <v-icon v-if="!avatar" size="150" class="mdi" icon="mdi-account" />
                    <v-img v-else :src="avatar" class="white--text" height="150" width="150" />
                </v-avatar>
                <p class="pt-4 font-weight-bold text-h2 text-center drop-shadow-lg mb-4">{{ user.username }}</p>
                <p class="text-h5 text-center drop-shadow-lg mb-4">{{ firstName }} {{ lastName }}</p>
                <p class="text-h6 text-center drop-shadow-lg mb-4">Edad: {{ age }} años</p>
                <p class="text-h6 text-center drop-shadow-lg mb-4">Peso: {{ weight }}kg</p>
                <p class="text-h6 text-center drop-shadow-lg mb-4">Altura: {{ height }}cm</p>
                <v-btn color="secondary" type="submit" @click="logOut" class="text-center mb-8">Cerrar Sesion</v-btn>
            </v-sheet>
            <v-sheet v-else height="100%" width="100%" color="lighter" rounded="xl" class="pa-0 text-center">
                <v-form @submit.prevent="onSubmit">
                    <v-text-field type="text" v-model:model-value="avatar" label="Avatar URL"></v-text-field>
                    <img v-if="avatar" :src="avatar" height="150" width="150" />
                    <v-text-field type="text" v-model:model-value="firstName" label="Nombre"></v-text-field>
                    <v-text-field type="text" v-model:model-value="lastName" label="Apellido"></v-text-field>
                    <v-text-field type="text" v-model:model-value="age" label="Edad"></v-text-field>
                    <v-text-field type="text" v-model:model-value="weight" label="Peso"></v-text-field>
                    <v-text-field type="text" v-model:model-value="height" label="Altura"></v-text-field>
                    <v-btn variant="outlined" :loading="loading" type="submit" block class="text-center"> Guardar cambios </v-btn>
                </v-form>
            </v-sheet>
        </v-sheet>
        <AlertSnackbar />
    </v-container>
</template>

<script setup>
import { UserInfoWithAvatar } from '@/api/user';
import { useUserStore } from '@/stores/userStore';
import { onMounted } from 'vue';
import { ref, provide } from 'vue';
import AlertSnackbar from './AlertSnackbar.vue';
import router from '@/router';
const user = ref('');
const modifyMode = ref(false);


const snackbar = ref(false)
const text = ref('');
provide('snackbar', snackbar);
provide('text', text);


const avatar = ref('');
const firstName = ref('');
const lastName = ref('');
const age = ref('');
const weight = ref('');
const height = ref('');

const userStore = useUserStore();

const loading = ref(false);

onMounted(async () => {
    user.value = await userStore.getCurrentUser();
    avatar.value = user.value.avatarUrl;
    firstName.value = user.value.firstName;
    lastName.value = user.value.lastName;
    age.value = new Date().getFullYear() - new Date(user.value.date).getFullYear();
    weight.value = user.value.metadata.weight;
    height.value = user.value.metadata.height;
})

function modify() {
    modifyMode.value = true;
}

async function logOut () {
    try {
      await userStore.logout();
      
    } catch (error) {
      
    } finally {
        router.push('/');
    }
  };

async function onSubmit() {
    loading.value = true;
    const newBirthdate = new Date('01-01-' + (new Date().getFullYear() - age.value)+1);
    const userInfo = new UserInfoWithAvatar(
        firstName.value ? firstName.value : user.value.firstName,
        lastName.value ? lastName.value : user.value.lastName,
        user.value.email,
        age.value ? newBirthdate : user.value.birthdate,
        height.value ? height.value : user.value.metadata.height,
        weight.value ? weight.value : user.value.metadata.weight,
        avatar.value ? avatar.value : user.value.avatarUrl,
        );
    try {
        await userStore.modifyCurrentUser(userInfo);
        
    } catch (error) {
        text.value = error.description;
        snackbar.value = true;
    } finally {
        modifyMode.value = false;
        loading.value = false;
    }
        
    
}



</script>