<template>
<v-container>

  <v-sheet  width="500" height="700" class="mx-auto mb-4" color="secondary">
   <v-row class="mb-6" align="center" justify="center"></v-row>
  <v-sheet  width="450" height="650" class="mx-auto mt-4"  color="lighter" rounded="lg" style="border: 1px solid black;" >
  <v-img src="@/assets/img/lapiz.png" class="mx-auto mt-4 mr-4" align="left"
          max-height="20"
          max-width="20"></v-img>
  <v-row class="mt-4" align="center" justify="center">
      <v-col cols="12" sm="6" md="4">
        <v-img
          src="@/assets/img/perfil.png"
          class="mx-auto mt-4 mb-4"
          max-height="200"
          max-width="200"
          
        ></v-img>
        </v-col>
    </v-row>
        <h1 class="mb-4 subrayado" align="center"> <b> Olivia Navarro</b></h1>
        <p class=" ml-12 mr-12 mb-4 myParagraph"> Me considero una persona apasionada por el fitness. No dudes en escribirme cualquier consulta!</p>
        <p  align="center" class="myParagraph"> Edad:42</p>
        <p  align="center" class="myParagraph"> Peso:70</p>
        <div class="d-flex flex-column align-center mb-4">
         <v-btn color="secondary" type="submit" class="text-center mt-8">Cerrar Sesion</v-btn>
         </div>
      
   
  </v-sheet>

  </v-sheet>
  </v-container>
</template>
<style>
.myParagraph {
  font-size: 27px;
}
 .subrayado {
      text-decoration: underline;
    }
  


</style>