<template>
  <v-sheet width="550" class="mx-auto" rounded color="white">
    <v-form @submit.prevent="onSubmit">
      <p class="pt-4 font-weight-bold text-h4 text-center drop-shadow-lg mb-4">
        Iniciar Sesión
      </p>
      <v-container>
        <v-text-field type="text" v-model:model-value="user" label="Usuario" :rules="userRequired"></v-text-field>

        <v-text-field type="password" v-model:model-value="password" label="Contraseña" :rules="passwordRequired"></v-text-field>
        <p class="drop-shadow-lg  text-center my-4">
          ¿No tenes cuenta?
          <v-btn color="secondary" to="/register">Registrate</v-btn>
        </p>
        <v-btn variant="outlined" type="submit" block class="text-center"> Iniciar Sesión </v-btn>
      </v-container>
    </v-form>
  </v-sheet>
</template>

<script setup>
  import { useUserStore } from '@/stores/userStore';
  import { ref } from 'vue';
  import router from '@/router';

  const userStore = useUserStore();

  const user = ref('');
  const password = ref('');

  async function onSubmit () {
    const successfullLogin = await userStore.logIn(user.value, password.value);
    if(successfullLogin){
      router.push('/');
    }
  };
  
</script>

<script>
export default {
  computed: {
    userRequired() { return [ (v) => !!v || 'El usuario es requerido',] },
    passwordRequired() { return [ (v) => !!v || 'La contraseña es requerida',] },
  }
}
</script>
