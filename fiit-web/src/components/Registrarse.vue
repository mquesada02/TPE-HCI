<template>
  <v-sheet  width="1000" class="mx-auto" color="white" rounded="lg">
  
    <p class="text-h3 text-center mt-5 ml-5 mb-7" >
             Registrarse
        </p>
        <div/>
        <v-form fast-fail @submit.prevent>
        <v-row>
        <v-col cols="12" md="6">
         <v-text-field
            v-model="email"
            label="Correo electrónico"
            variant="underlined"
            class="text-field-center ml-8"
            :rules="EMailRules">
        </v-text-field>
        <v-text-field
            label="Nombre" 
            v-model="nombre"
            variant="underlined"
            class="text-field-center ml-8"
            :rules="nombreRules">
        </v-text-field>
        <v-text-field
            label="Nombre de Usuario" 
             v-model="nombreDeUsuario"
             variant="underlined"
             class="text-field-center ml-8"
             :rules="usuarioRules">
        </v-text-field>
        <v-text-field
            label="Altura" 
             v-model="altura"
             variant="underlined"
             class="text-field-center ml-8"
             :rules="alturaRules">
        </v-text-field>
        </v-col>

        <v-col cols="12" md="6">
         <v-text-field
            label="Contraseña"
            v-model="contraseña" 
            variant="underlined"
            class="text-field-center mr-8"
            :rules="passwordRules">
        </v-text-field>
        
        <v-text-field
            label="Apellido"
            v-model="apellido"
            variant="underlined"
            class="text-field-center mr-8"
            :rules="apellidoRules">
         </v-text-field>
         
         <v-text-field
            label="Fecha de Nacimiento" 
             v-model="FechadeNacimiento"
             variant="underlined"
             class="text-field-center mr-8"
             :rules="fechaRules">
        </v-text-field>
         
         <v-text-field
            label="Peso" 
             v-model="peso"
             variant="underlined"
             class="text-field-center mr-8"
             :rules="pesoRules">
        </v-text-field>
        </v-col>
        </v-row>
         <p class="drop-shadow-lg text-h7 text-center mb-5"> ¿Ya tienes cuenta? <u>Inicia sesión</u></p>
         <div class="d-flex flex-column align-center mb-8">
         <v-btn color="secondary" type="submit" class="text-center mb-8">Registrarse</v-btn>
         </div>
    </v-form>
  </v-sheet>
</template>
<script>
export default {
  data() {
    return {
      email: '',
      contrasena: '',
      nombre: '',
      apellido: '',
      nombreDeUsuario: '',
      fechaDeNacimiento: '',
      altura: '',
      peso: '',
    };
  },
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
},
methods: {
  getDiasEnMes(anio, mes) {
    // Esta función devuelve la cantidad de días en un mes específico.
    return new Date(anio, mes, 0).getDate();
  },
    alturaRules() {
    return [
      (v) => {
        if (!v) {
          return 'La altura es requerida';
        }

        const alturaNum = parseFloat(v);
        if (isNaN(alturaNum) || alturaNum < 0 || alturaNum > 2.5) {
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
};
</script>