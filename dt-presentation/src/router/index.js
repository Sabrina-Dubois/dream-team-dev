import { createRouter, createWebHistory } from "vue-router";
import Profile from "../views/Profile.vue";
import SearchDevelopers from "@/views/SearchDevelopers.vue";
import Filters from "../views/Filters.vue";

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "/",
			name: "Profile",
			component: Profile,
		},
		{
			path: "/search",
			name: "Search developers",
			component: SearchDevelopers,
		},
		{
			path: "/filters",
			name: "Filters",
			component: Filters,
		},
	],
});

export default router;
