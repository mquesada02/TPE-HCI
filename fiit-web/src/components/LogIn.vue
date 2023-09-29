<template>
  <v-sheet width="300" class="mx-auto">
    <v-form fast-fail @submit.prevent>
    <p class="text-h3 text-center drop-shadow-lg my-10" >
             Iniciar Sesion
        </p>
      <v-text-field
        v-model="firstName"
        label="Usuario/E-Mail"
        :rules="UsuarioEMailRules"
      ></v-text-field>

      <v-text-field
        v-model= "lastName"
        label= "Contraseña"
        :rules="contraseñaRules"
     ></v-text-field>
    <p class="drop-shadow-lg  text-center my-4">
             ¿No tenes cuenta? <v-btn type="submit">Registrate </v-btn> 
    </p>
    <v-btn variant="outlined" type="submit" block class="text-center"> Iniciar Sesión </v-btn>
    </v-form>
  </v-sheet>
</template>
