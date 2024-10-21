import "./assets/main.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import "@fortawesome/fontawesome-free/css/all.min.css";

<<<<<<< HEAD
import {createApp} from 'vue'
import {createI18n} from 'vue-i18n'
=======
import { createApp } from "vue";
import { createI18n } from "vue-i18n";
>>>>>>> 0115b2c63c965443f35321160de409cd400ec344

import App from "./App.vue";
import router from "./router";

<<<<<<< HEAD
import fr from './i18n/fr.json'
import en from './i18n/en.json'
=======
import fr from "./i18n/fr.json";
import en from "./i18n/en.json";
>>>>>>> 0115b2c63c965443f35321160de409cd400ec344

const app = createApp(App);

const i18n = createI18n({
<<<<<<< HEAD
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
=======
	locale: "fr",
	fallbackLocale: "en",
	messages: {
		en,
		fr,
	},
});
>>>>>>> 0115b2c63c965443f35321160de409cd400ec344

app.use(router);
app.use(i18n);
app.mount("#app");
