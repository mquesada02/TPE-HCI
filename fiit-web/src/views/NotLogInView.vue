<template>
    <v-app>
      <v-img src="@/assets/img/blurred-homepage-bg.png" class="align-center">
        <v-container fill-height class="container">
          <v-card class="textS pl-4 pt-2 pb-2 pr-4">
            Necesitas inciar sesión para entrar a esta sección.
          </v-card>
        </v-container>
      </v-img>
    </v-app>
  </template>
  
  <style scoped>
    .container {
      display: flex;
      align-items: center;
      justify-content: center;
      position: fixed;
      top: 50%;
      left: 0;
      right: 0;
      transform: translateY(-50%);
    }
    .textS {
        font-size: 30px;
    }
  </style>