<template>
    <v-sheet width="550" class="mx-auto" rounded color="white">
    <v-form @submit.prevent="onSubmit">
      <p class="pt-4 font-weight-bold text-h4 text-center drop-shadow-lg mb-4">
        Código de verificación
      </p>
      <p class="text-center">Ingrese el código de verificación obtenido por email.</p>
      <v-container>
        <v-text-field type="text" v-model:model-value="code" label="Código de verificación"></v-text-field>
        <v-btn variant="outlined" type="submit" block class="text-center"> Validar código </v-btn>
      </v-container>
    </v-form>
  </v-sheet>
</template>

<script setup>
    import { ref } from 'vue';
    import { useUserStore } from '@/stores/userStore';
    import router from '@/router';

    const userStore = useUserStore();

    const code = ref('');

    async function onSubmit () {

    const successVerify = await userStore.verifyEmail(localStorage.getItem('email').toString(), code.value);
    if(successVerify){
      router.push('/');
    }
  };
</script>