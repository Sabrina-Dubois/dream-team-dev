import './assets/main.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import '@fortawesome/fontawesome-free/css/all.min.css'

import {createApp} from 'vue'
import {createI18n} from 'vue-i18n'

import App from './App.vue'
import router from './router'

import fr from './i18n/fr.json'
import en from './i18n/en.json'

const app = createApp(App)

const i18n = createI18n({
    locale: 'fr',
    fallbackLocale: 'en',
    messages: {
        en,
        fr
    }
})

app.use(router)
app.use(i18n)
app.mount('#app')

