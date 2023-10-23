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
import { VDataIterator } from 'vuetify/labs/VDataIterator'

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
          warning: '#212121',
          completed: '#32CD32',
          white: '#FFFFFF'
        },
      },
    },
  },
  icons: {
    defaultSet: 'mdi',
  },
  display: {
    mobileBreakpoint: 'sm',
    thresholds: {
      xs: 340,
      sm: 540,
      md: 800,
      lg: 1280,
      xl: 1920,
    },
  },
  components: {
    VDataIterator,
  },
})
