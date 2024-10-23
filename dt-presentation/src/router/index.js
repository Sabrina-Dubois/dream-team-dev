import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "/",
			name: "Profile",
			component: () => import("../views/Profile.vue"),
		},
		{
			path: "/search",
			name: "Search developers",
			component: () => import("../views/SearchDevelopers.vue"),
		},
		{
			path: "/filters",
			name: "Filters",
			component: () => import("../views/Filters.vue"),
		},
	],
});

export default router;
