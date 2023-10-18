<template>
  <v-sheet  width="1000" class="mx-auto" color="white" rounded="lg">
  
    <p class="pt-6 text-h3 text-center ml-5 mb-7 font-weight-bold" >
             Registrarse
        </p>
        <div/>
        <v-form fast-fail @submit.prevent="onSubmit">
        <v-row>
        <v-col cols="12" md="6">
         <v-text-field
            v-model:model-value="email"
            label="Correo electrónico"
            variant="underlined"
            class="text-field-center ml-8"
            :rules="emailRules">
        </v-text-field>
        <v-text-field
            label="Nombre" 
            v-model:model-value="name"
            variant="underlined"
            class="text-field-center ml-8"
            :rules="nombreRules">
        </v-text-field>
        <v-text-field
            label="Nombre de Usuario" 
            v-model:model-value="user"
             variant="underlined"
             class="text-field-center ml-8"
             :rules="usuarioRules">
        </v-text-field>
        <v-text-field
            label="Altura (cm)" 
            v-model:model-value="height"
             variant="underlined"
             class="text-field-center ml-8"
             :rules="alturaRules">
        </v-text-field>
        </v-col>

        <v-col cols="12" md="6">
         <v-text-field
            label="Contraseña"
            type="password"
            v-model:model-value="pass" 
            variant="underlined"
            class="text-field-center mr-8"
            :rules="passwordRules">
        </v-text-field>
        
        <v-text-field
            label="Apellido"
            v-model:model-value="surname"
            variant="underlined"
            class="text-field-center mr-8"
            :rules="apellidoRules">
         </v-text-field>
         
         <v-text-field
            label="Fecha de Nacimiento" 
            v-model:model-value="birth"
             variant="underlined"
             class="text-field-center mr-8"
             :rules="fechaRules">
        </v-text-field>
         
         <v-text-field
            label="Peso (kg)" 
            v-model:model-value="weight"
             variant="underlined"
             class="text-field-center mr-8"
             :rules="pesoRules">
        </v-text-field>
        </v-col>
        </v-row>
         <p class="drop-shadow-lg text-h7 text-center mb-5"> ¿Ya tienes cuenta? <router-link to="/login">Inicia sesión</router-link></p>
         <div class="d-flex flex-column align-center mb-8">
         <v-btn color="secondary" type="submit" class="text-center mb-8">Registrarse</v-btn>
         </div>
    </v-form>
  </v-sheet>
</template>

<script setup>

  import { ref } from 'vue';
  import { useUserStore } from '@/stores/userStore';
  import { useRouter } from 'vue-router';
  import { Credentials, UserInfo } from '@/api/user.js';

  const router = useRouter();

  const email = ref('');
  const pass = ref('');
  const name = ref('');
  const surname = ref('');
  const user = ref('');
  const birth = ref('');
  const height =  ref('');
  const weight = ref('');

  const userStore = useUserStore();
  
  async function onSubmit () {
    try {
      const credentials = new Credentials(user.value, pass.value);
      const userInfo = new UserInfo(name.value, surname.value, email.value, birth.value, height.value, weight.value);
      await userStore.register(credentials,userInfo);
      router.push('/verify')
    } catch (error) {
      alert(error.description);
    }
  };


</script>

<script>

export default {
  computed: {
    emailRules() {
      return [
        (v) => !!v || 'El correo electrónico es requerido',
        (v) =>
          /.+@.+/.test(v) ||
          'El correo electrónico debe tener un formato válido',
      ];
    },
    passwordRules() {
      return [
        (v) => !!v || 'La contraseña es requerida',
        (v) =>
          v.length >= 8 ||
          'La contraseña debe tener al menos 8 caracteres',
      ];
    },
    nombreRules() {
      return [(v) => !!v || 'El nombre es requerido'];
    },
    apellidoRules() {
      return [(v) => !!v || 'El apellido es requerido'];
    },
    usuarioRules() {
      return [
        (v) => !!v || 'El nombre de usuario es requerido',
        (v) => v.length >= 3 || 'El nombre de usuario debe tener al menos 3 caracteres',
      ];
    },
     fechaRules() {
    return [
      (v) => {
        const regex = /^(\d{1,2})-(\d{1,2})-(\d{4})$/;
        if (!regex.test(v)) {
          return 'Formato de fecha incorrecto (DD-MM-YYYY)';
        }

        const [, dia, mes, anio] = v.match(regex);
        const diaNum = parseInt(dia, 10);
        const mesNum = parseInt(mes, 10);

        if (
          mesNum < 1 || mesNum > 12 ||
          diaNum < 1 || diaNum > this.getDiasEnMes(anio, mesNum)
        ) {
          return 'Fecha no válida';
        }

        return true;
      },
    ];
  },
  alturaRules() {
    return [
      (v) => {
        if (!v) {
          return 'La altura es requerida';
        }

        const alturaNum = parseFloat(v);
        if (isNaN(alturaNum) || alturaNum < 0 || alturaNum > 250) {
          return 'Ingrese una altura valida';
        }

        return true;
      },
    ];
  },
    pesoRules() {
      return [
        (v) => !!v || 'El peso es requerido',
        (v) => /^\d+(\.\d+)?$/.test(v) || 'Ingrese un número válido',
        (v) => v >= 0 && v <= 500 || 'El peso debe estar entre 0 y 500',
      ];
    },
},
methods: {
  getDiasEnMes(anio, mes) {
    // Esta función devuelve la cantidad de días en un mes específico.
    return new Date(anio, mes, 0).getDate();
  },
  },
};
</script>