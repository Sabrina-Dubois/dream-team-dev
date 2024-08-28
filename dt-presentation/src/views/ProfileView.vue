<template>
	<main>
		<div class="container mt-5">
			<h1 class="text-center">Profil</h1>

			<div>
				<label for="personalInfo" class="form-label fs-5">Informations personnelles</label>
				<div class="d-flex flex-column align-items-center mt-4 card bg-light">

					<!-- Picture -->
					<div v-if="imageUrl" class="profile-picture-preview mb-3">
						<img :src="imageUrl" alt="Profile" class="rounded-circle img-fluid" />
					</div>

					<!-- File input -->
					<div class="mb-3">
						<input type="file" @change="onFileChange" class="form-control" accept="image/*" />
					</div>
				</div>

				<label for="description" class="form-label fs-5 mb-2 mt-4">Description</label>
				<div class="card bg-light">
					<div class="card-body d-flex flex-column">
						<div class="mb-3">
							<a class="btn btn-primary" href="https://www.linkedin.com" target="_blank">
								<i class="fab fa-linkedin"></i>
							</a>
						</div>
						<textarea class="form-control" id="floatingTextarea2" style="height: 100px"></textarea>
					</div>
				</div>

				<Skills />

				<label for="Softskills" class="form-label fs-5 mb-2 mt-4">Soft Skills</label>
				<div class="card bg-light">
					<div class="card-body d-flex flex-column">
						<input class="form-control" list="datalistSoftSkills" id="exampleDataList" />
						<datalist id="datalistSoftSkills">
							<option value="TRAVAIL EN EQUIPE"></option>
							<option value="CURIEUX"></option>
							<option value="PATIENT"></option>
						</datalist>
					</div>
				</div>

				<div class="d-flex justify-content-center mt-3">
					<button class="btn btn-primary">VALIDER</button>
				</div>
			</div>
		</div>
	</main>
</template>

<script >
import Skills from '@/components/Skills.vue'

export default {
	name: 'ProfilView',
	components: {
		Skills
	},
	data() {
		return {
			imageUrl: null 
		}
	},
	methods: {
		onFileChange(event) {
			const file = event.target.files[0]
			if (file) {
				this.imageUrl = URL.createObjectURL(file)
			}
		}
	}
}
</script>

<style scoped>
/* Circular image */
.profile-picture-preview {
	width: 100px;
	height: 100px;
	overflow: hidden;
}

.profile-picture-preview img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}
</style>