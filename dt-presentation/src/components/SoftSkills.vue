<template>
	<label for="TechnicalSkills" class="form-label fs-5 mb-2 mt-4">{{ $t('Soft Skills') }}</label>
	<div class="card bg-light">
		<div class="card-body d-flex flex-column">
			<div class="mb-2">
				<span v-for="(skill, index) in selectedSkills" :key="index" class="badge bg-primary me-2">
					{{ skill.name }}
					<button
						type="button"
						class="btn-close btn-close-white btn-sm ms-2"
						aria-label="Remove"
						@click="removeSkill(index)"
					></button>
				</span>
			</div>

			<!-- Select skills -->
			<div class="input-group mb-3">
				<input v-model="newSkill" class="form-control" list="datalistSoftSkills" />
				<datalist id="datalistSoftSkills">
					<option
						v-for="(skill, index) in availableSoftSkills"
						:key="index"
						:value="skill.name"
					></option>
					<!-- <option value="TRAVAIL EN EQUIPE"></option>
							<option value="CURIEUX"></option>
							<option value="PATIENT"></option> -->
				</datalist>

				<!-- Add new skill -->
				<button
					class="btn btn-outline-secondary ms-2 rounded-circle"
					@click="addSkill"
					style="border-radius: 50%"
				>
					+
				</button>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: 'SoftSkills',
	data() {
		return {
			selectedSkills: [],
			newSkill: '',
			availableSoftSkills: []
		}
	},
	mounted() {
		this.getSoftSkills()
	},
	methods: {
		// Function
		addSkill() {
			if (this.newSkill && !this.selectedSkills.some((skill) => skill.name === this.newSkill)) {
				this.selectedSkills.push({
					name: this.newSkill
				})
				this.newSkill = ''
				this.emitSkills()
			}
		},
		// Remove a skill
		removeSkill(index) {
			this.selectedSkills.splice(index, 1)
			this.emitSkills()
		},
		emitSkills() {
			this.$emit('update-soft-skills', this.selectedSkills) // Emit the selected skills to the parent
		},
		async getSoftSkills() {
			try {
				const response = await fetch(
					`http://localhost:8080/topics/search?label=TeamWork&isTechnical=false`
				)
				const data = await response.json()
				this.availableSoftSkills = data
				console.log(this.availableSoftSkills)
			} catch {
				console.error('Erreur lors de la récupération des soft skills:', error)
			}
		},
		async updateSoftSkills() {
			const formData = new FormData()
			formData.append('softSkills', JSON.stringify(this.user.softSkills))
			try {
				const response = await fetch(`http://localhost:8080/developers`, {
					method: 'PATCH',
					body: formData
				})
				const data = await response.json()
				console.log('Profil mis à jour avec succès', data)
			} catch (error) {
				console.error(error)
			}
		}
	}
}
</script>

<style scoped>
.badge {
	display: inline-flex;
	align-items: center;
}
</style>
