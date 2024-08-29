<template>
	<main>
		<div class="container mt-5">
			<h1 class="text-center">{{ $t('Profil') }}</h1>

			<!-- Informations personnelles -->
			<div>
				<label for="personalInfo" class="form-label fs-5">{{
					$t('Informations personnelles')
				}}</label>
				<div class="d-flex flex-row align-items-start mt-4 card bg-light p-4">
					<!-- Picture and File input  -->
					<div class="d-flex flex-column align-items-center mx-3">
						<!-- Picture -->
						<div v-if="imageUrl" class="profile-picture-preview mb-2">
							<img :src="imageUrl" alt="Profile" class="rounded-circle img-fluid" />
						</div>

						<div class="mb-2">
							<input
								class="form-control form-control-sm"
								type="file"
								@change="onFileChange"
								accept="image/*"
							/>
						</div>
					</div>

					<!-- Input fields -->
					<div>
						<div class="row">
							<div class="col">
								<input
									type="text"
									class="form-control mb-3"
									placeholder="Nom"
									v-model="user.nom"
									@input="fetchData"
								/>
							</div>
							<div class="col">
								<input
									type="text"
									class="form-control mb-3"
									placeholder="Prénom"
									v-model="user.prenom"
									@input="fetchData"
								/>
							</div>
							<div class="col">
								<input
									type="email"
									class="form-control mb-3"
									placeholder="Email"
									v-model="user.email"
									@input="fetchData"
								/>
							</div>
							<div class="col">
								<input
									type="text"
									class="form-control mb-3"
									placeholder="Matricule"
									v-model="user.matricule"
									@input="fetchData"
								/>
							</div>
							<div class="col">
								<a class="btn btn-primary" href="https://www.linkedin.com" target="_blank">
									<i class="fab fa-linkedin"></i>
								</a>
							</div>
						</div>
					</div>
				</div>

				<!-- Description -->
				<label for="description" class="form-label fs-5 mb-2 mt-4">{{ $t('Description') }}</label>
				<div class="card bg-light">
					<div class="card-body d-flex flex-column">
						<textarea class="form-control" id="floatingTextarea2" style="height: 100px"></textarea>
					</div>
				</div>

				<TechnicalSkills />
				<SoftSkills />

				<!-- <label for="Softskills" class="form-label fs-5 mb-2 mt-4">{{ $t('Soft Skills') }}</label>
				<div class="card bg-light">
					<div class="card-body d-flex flex-column">
						<input class="form-control" list="datalistSoftSkills" id="exampleDataList" />
						
					</div>
				</div> -->

				<div class="d-flex justify-content-center mt-3">
					<button class="btn btn-primary">{{ $t('ENVOYER') }}</button>
				</div>
			</div>
		</div>
	</main>
</template>

<script >
import TechnicalSkills from '@/components/TechnicalSkills.vue';
import SoftSkills from '@/components/SoftSkills.vue';

export default {
	name: 'ProfilView',
	components: {
		TechnicalSkills,
		SoftSkills
	},
	data() {
		return {
			imageUrl: null,
			imageUrl: null,
			user: {
				nom: '',
				prenom: '',
				email: '',
				matricule: ''
			}
		}
	},
	methods: {
		onFileChange(event) {
			const file = event.target.files[0]
			if (file) {
				this.imageUrl = URL.createObjectURL(file)
			}
		},
		fetchData() {
			// Logique pour récupérer les données en fonction des champs de saisie
			console.log('Fetching data from backend', this.user)
		}
	}
}
</script>

<style scoped>
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