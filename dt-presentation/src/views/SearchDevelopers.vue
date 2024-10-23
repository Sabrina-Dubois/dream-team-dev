<script>
import DeveloperItem from '@/components/DeveloperItem.vue'
import SearchCriteria from '@/components/SearchCriteria.vue'
import axios from 'axios'

export default {
	components: {
		DeveloperItem,
		SearchCriteria
	},
	data() {
		return {
			Developers: []
		}
	},
	mounted() {
		this.getDevelopers()
	},
	methods: {
		async getDevelopers() {
			try {
				const response = await axios.get(`http://localhost:8080/developers`)
				const data = await response.data
				this.Developers = data
				console.log(this.Developers)
			} catch {
				console.error('Erreur lors de la récupération des niveaux:', error)
			}
		},
		searchDevelopers(filteredDevelopers) {
			// Update the Developer list with the filtered Developers from the child component
			this.Developers = filteredDevelopers
		}
	}
}
</script>

<template>
	<main>
		<div class="container mt-5">
			<h1 class="text-center">{{ $t('Search dev title') }}</h1>
			<SearchCriteria @update-developers="searchDevelopers" />
			<section>
				<h2>{{ $t('Search results title') }}</h2>
				<div class="text-center w-75 mx-5">
					<DeveloperItem
						v-for="developer in Developers"
						:key="developer.id"
						:developer="developer"
					/>
				</div>
			</section>
		</div>
	</main>
</template>

<style scoped></style>
