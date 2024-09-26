import { createRouter, createWebHistory } from "vue-router";
import ProfileView from "../views/ProfileView.vue";
import SearchDevelopers from "@/views/SearchDevelopers.vue";

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "/",
			name: "Profil",
			component: ProfileView,
		},
		{
			path: "/search",
			name: "Search developers",
			component: SearchDevelopers,
		},
	],
});

export default router;
