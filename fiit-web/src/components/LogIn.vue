<template>
  <v-sheet width="550" class="mx-auto" rounded color="white">
    <v-form @submit.prevent="onSubmit">
      <p class="pt-4 font-weight-bold text-h4 text-center drop-shadow-lg mb-4">
        Iniciar Sesión
      </p>
      <v-container>
        <v-text-field type="text" v-model:model-value="user" label="Usuario" :rules="userRequired"></v-text-field>
        <v-text-field type="password" v-model:model-value="password" label="Contraseña" :rules="passwordRequired"></v-text-field>
        <v-checkbox v-model="remember" label="Recuérdame"></v-checkbox>
        <p class="drop-shadow-lg  text-center my-4">
          ¿No tenes cuenta?
          <v-btn color="secondary" to="/register">Registrate</v-btn>
        </p>
        <v-btn variant="outlined" :loading="loading" type="submit" block class="text-center"> Iniciar Sesión </v-btn>
      </v-container>
    </v-form>
    <AlertSnackbar />
  </v-sheet>
</template>

<script setup>
  import { ref } from 'vue';
  import router from '@/router';
  import { useUserStore } from '@/stores/userStore';
  import { Credentials } from '@/api/user.js';
  import AlertSnackbar from '@/components/AlertSnackbar.vue';
  import { provide } from 'vue';
  const userStore = useUserStore();
  const remember = ref(false);
  const user = ref('');
  const password = ref('');
  const snackbar = ref(false)
  const text = ref('');
  provide('snackbar', snackbar);
  provide('text', text);
  const params = new URLSearchParams(location.search);
  user.value = params.get("user");
  const loading = ref(false);
  async function onSubmit () {
    try {
      const credentials = new Credentials(user.value, password.value);
      loading.value = true;
      await userStore.login(credentials, remember);
      router.push('/');
    } catch (error) {
      text.value = error.description;
      snackbar.value = true;
      if (error.code === 8) {
        router.push('/verify')
      }
    } finally {
      loading.value = false;
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
