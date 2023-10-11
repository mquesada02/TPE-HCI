<template>
  <v-sheet width="550" class="mx-auto" rounded color="white">
    <v-form fast-fail @submit.prevent>
    <p class="pt-4 font-weight-bold text-h4 text-center drop-shadow-lg mb-4" >
             Iniciar Sesión
    </p>
    <v-container>
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
             ¿No tenes cuenta? 
             <v-btn type="submit" color="secondary" to="/register">Registrate</v-btn> 
    </p>
    <v-btn variant="outlined" type="submit" block class="text-center"> Iniciar Sesión </v-btn>
    </v-container>
    </v-form>
  </v-sheet>
</template>
