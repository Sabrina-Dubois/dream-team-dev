<template>
	<main>
		<div class="container mt-5">
			<h1 class="text-center">{{ $t('Filters Management') }}</h1>

			<div v-for="(filter, index) in filters" :key="index" class="card mt-4 bg-light p-4">
				<div class="row align-items-center">
					<div class="col-4 d-flex align-items-center">
						<label class="form-label mb-0 me-2 col-2 ">Skill 1:</label>
						<select v-model="filter.skills[0]" class="form-select">
							<option v-for="skill in skillsList" :key="skill" :value="skill">
								{{ skill }}
							</option>
						</select>
					</div>

					<div class="col-4 d-flex align-items-center">
						<label class="form-label mb-0 me-2 col-2">Skill 2:</label>

						<select v-model="filter.skills[1]" class="form-select">
							<option v-for="skill in skillsList" :key="skill" :value="skill">
								{{ skill }}
							</option>
						</select>
					</div>
					<div class="col-4 d-flex align-items-center">
						<label clas="form-label ">Keyword:</label>
						<input
							type="text"
							v-model="filter.keyword"
							class="form-control"
						/>
					</div>
				</div>
			</div>

			<div class="text-center mt-3">
				<button v-if="filters.length < 5" @click="addFilter" 
                class="btn btn-outline-primary ms-2 rounded-circle" 
            > + </button>
			</div>
			<div class="text-center mt-5">
				<button type="submit" 
                class="btn btn-primary mt-3">Validate</button>
			</div>
		</div>
	</main>
</template>

<script>
export default {
	name: 'FiltersView',
	components: {},
	data() {
		return {
			filters: [
				{
					skills: ['', ''],
					keyword: ''
				}
			],
			skillsList: ['JS','HTML', 'Python','Vue']
		}
	},

	methods: {
		addFilter() {
			if (this.filters.length < 5) {
				this.filters.push({
					skills: ['', ''],
					keyword: ''
				})

			}
		}
	}
}
</script>

<style scoped>

</style>
