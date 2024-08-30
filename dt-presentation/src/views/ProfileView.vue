<template>
	<main>
		<div class="container mt-5">
			<form @submit.prevent="updateProfile">
				<h1 class="text-center">{{ $t('Profile') }}</h1>

				<!-- Informations personnelles -->
				<div>
					<label for="personalInfo" class="form-label fs-5">{{ $t('Informations personnelles') }}</label>
					<div class="d-flex flex-row align-items-start mt-4 card bg-light p-4">
						<!-- Picture and File input  -->
						<div class="d-flex flex-column align-items-center mx-3">
							<!-- Picture -->
							<div v-if="picture" class="profile-picture-preview mb-2">
								<img :src="picture" alt="Profile" class="rounded-circle img-fluid" />
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
										v-model="user.lastName"
										type="text"
										class="form-control mb-3"
										placeholder="Nom"
										disabled
									/>
								</div>
								<div class="col">
									<input
										v-model="user.firstName"
										type="text"
										class="form-control mb-3"
										placeholder="Prénom"
										disabled
									/>
								</div>
								<div class="col">
									<input
										v-model="user.email"
										type="email"
										class="form-control mb-3"
										placeholder="Email"
										disabled
									/>
								</div>
								<div class="col">
									<input
										v-model="user.internalNumber"
										type="text"
										class="form-control mb-3"
										placeholder="Matricule"
										disabled
									/>
								</div>
								<div class="col">
									<a class="btn btn-primary" href="https://www.linkedin.com" target="_blank">
										<i class="fab fa-linkedin"></i>
									</a>
									<input
										v-model="user.linkedin"
										type="text"
										class="form-control"										
									/>
								</div>
							</div>
						</div>
					</div>

					<!-- Description -->
					<label for="description" class="form-label fs-5 mb-2 mt-4">{{ $t('Description') }}</label>
					<div class="card bg-light">
						<div class="card-body d-flex flex-column">
							<textarea
								v-model="user.description"
								class="form-control"
								id="floatingTextarea2"
								style="height: 100px"
							></textarea>
						</div>
					</div>

					<TechnicalSkills />
					<SoftSkills />

					<div class="d-flex justify-content-center mt-3">
						<button class="btn btn-primary">{{ $t('SOUMETTRE') }}</button>
					</div>
				</div>
			</form>
		</div>
	</main>
</template>

<script>
import TechnicalSkills from '@/components/TechnicalSkills.vue'
import SoftSkills from '@/components/SoftSkills.vue'

export default {
	name: 'ProfileView',
	components: {
		TechnicalSkills,
		SoftSkills
	},
	data() {
		return {
			picture: null,
			user: {
				lastName: '',
				firstName: '',
				email: '',
				internalNumber: 'abc1234',
				description: ''  
			}
		}
	},
	mounted() {
		this.getProfile();
	},
	methods: {
		async getProfile() {
			try {
				const response = await fetch(`http://localhost:8080/developers/${this.user.internalNumber}`);
				const data = await response.json();
				this.user.firstName = data.firstName;
				this.user.lastName = data.lastName;
				this.user.email = data.email;
				this.user.internalNumber = data.internalNumber;
				this.user.description = data.description;
				this.user.linkedin = data.linkedin;
			} catch (error) {
				console.log('Erreur lors de la récupération des données', error);
			}
		},
		onFileChange(event) {
			const file = event.target.files[0];
			if (file) {
				this.picture = URL.createObjectURL(file);
				this.user.picture = file;  
			}
		},
		async updateProfile() {
			const formData = new FormData();
			formData.append('description', this.user.description);
			formData.append('linkedin', this.user.linkedin); 

			if (this.user.picture) {
				formData.append('picture', this.user.picture);  // Ajouter le fichier image à FormData
			}

			try {
				const response = await fetch(`http://localhost:8080/developers/${this.user.internalNumber}`, {
					method: 'PATCH',
					body: formData
				});
				if (response.ok) {
					console.log('Profil mis à jour avec succès');
				} else {
					console.error('Échec de la mise à jour du profil');
				}
			} catch (error) {
				console.error('Erreur lors de la mise à jour du profil', error);
			}
		}
	}
}
</script>

<style scoped>
.profile-picture-preview {
	width: 50px;
	height: 50px;
	overflow: hidden;
}

.profile-picture-preview img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}
</style>
