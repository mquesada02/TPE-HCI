<template>
    <div class="text-right">
      <v-menu
        v-model="menu"
        :close-on-content-click="false"
        location="end"
      >
        <template v-slot:activator="{ props }">
          <v-btn
            color="lighter"
            v-bind="props"
          >
          Filtrar
          </v-btn>
        </template>
  
        <v-card min-width="300">
          <v-list>
            <v-list-item
              prepend-avatar="https://cdn.vuetifyjs.com/images/john.jpg"
              title="John Leider"
              subtitle="Founder of Vuetify"
            >
            </v-list-item>
          </v-list>
  
          <v-divider></v-divider>
  
          <v-list>
            <v-list-item>
              <v-switch
                v-model="message"
                color="purple"
                label="Enable messages"
                hide-details
              ></v-switch>
            </v-list-item>
  
            <v-list-item>
              <v-switch
                v-model="hints"
                color="purple"
                label="Enable hints"
                hide-details
              ></v-switch>
            </v-list-item>
          </v-list>
  
          <v-card-actions>
            <v-spacer />
            <v-btn
              color="primary"
              variant="text"
              @click="menu = false"
              icon="mdi-check"
            >
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-menu>
    </div>
  </template>
  <script>
    export default {
      data: () => ({
        fav: true,
        menu: false,
        message: false,
        hints: true,
      }),
    }
  </script>