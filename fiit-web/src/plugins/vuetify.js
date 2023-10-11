/**
 * plugins/vuetify.js
 *
 * Framework documentation: https://vuetifyjs.com`
 */

// Styles
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'

// Composables
import { createVuetify } from 'vuetify'

// https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
export default createVuetify({
  theme: {
    defaultTheme: 'dark',
    themes: {
      dark: {
        colors: {
          background: '#121212',
          surface: '#212121',
          primary: '#DD2C00',
          secondary: '#FF8A65',
          lighter: '#FFCCBC',
          error: '#E90000',
          info: '#F55E3D',
          success: '#4CAF50',
          warning: '#FB8C00'
        },
      },
    },
  },
  icons: {
    defaultSet: 'mdi',
  },
})
