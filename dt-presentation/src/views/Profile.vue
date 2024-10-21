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
			pictureProfil: null,
			// users: [],
			user: {
				lastName: '',
				firstName: '',
				email: '',
				internalNumber: 'abc1234',
				description: '',
				linkedin: ''
			},
			descriptionError: '',
			technicalSkills: [],
			softSkills: [],
			userTechnicalSkills: [],
			userSoftSkills: []
		}
	},
	beforeMount() {
		this.getProfile()
	},
	methods: {
		async getProfile() {
			try {
				const response = await fetch(`http://localhost:8080/developers/${this.user.internalNumber}`)
				const profile = await response.json()
				this.user.firstName = profile.firstName
				this.user.lastName = profile.lastName
				this.user.email = profile.email
				this.user.internalNumber = profile.internalNumber
				this.user.description = profile.description
				this.user.linkedin = profile.linkedin
				const skills = profile.skills
				this.userTechnicalSkills = skills.filter((skill) => skill.isTechnical === true)
				this.userSoftSkills = skills.filter((skill) => skill.isTechnical === false)
				console.log(this.userTechnicalSkills)
				console.log(this.userSoftSkills)
			} catch (error) {
				console.log('Erreur lors de la récupération des données', error)
			}
		},
		onFileChange(event) {
			const file = event.target.files[0]
			console.log(file)
			if (file) {
				this.picture = URL.createObjectURL(file)
				this.user.picture = file
			}
		},
		// Change description length > 5000 -> 5 is a test
		validate() {
			if (this.user.description.length > 5) {
				this.descriptionError = 'La description ne peut pas dépasser 5000 caractères.'
			} else {
				this.descriptionError = ''
			}
		},
		updateTechnicalSkills(skills) {
			this.technicalSkills = skills 
		},
		updateSoftSkills(skills) {
			this.softSkills = skills 
		},
		async update() {
			const formData = new FormData()
			formData.append('description', this.user.description)
			formData.append('linkedin', this.user.linkedin)

			if (this.user.picture instanceof File) {
				formData.append('picture', this.user.picture)
			}
			const skillsBlob = new Blob([JSON.stringify(this.softSkills.concat(this.technicalSkills))], {
				type: 'application/json'
			})
			formData.append(`skills`, skillsBlob)

			try {
				await fetch(
					`http://localhost:8080/developers/${this.user.internalNumber}`,
					{
						method: 'PATCH',
						body: formData
					}
				)
				console.log('Profil mis à jour avec succès')
			} catch (error) {
				console.error('Erreur lors de la mise à jour du profil', error)
			}
		}
	}
}
</script>

<template>
  <main>
    <div class="container mt-5">
      <form @submit.prevent="update">
        <h1 class="text-center">{{ $t('Profil') }}</h1>

        <!-- Informations personnelles -->
        <div>
          <label for="personalInfo" class="form-label fs-5">{{
              $t('Informations personnelles')
            }}</label>
          <div class="d-flex flex-row align-items-start mt-4 card bg-light p-4">
            <!-- Picture and File input  -->
            <div class="col-12 col-lg-3 d-flex flex-column align-items-center mb-3 mb-lg-0">
              <!-- Picture -->
              <div v-if="pictureProfil" class="profile-picture-preview mb-2">
                <img :src="pictureProfil" alt="Profile" class="rounded-circle img-fluid"/>
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
                      @change="handleFileUpload"
                      type="text"
                      class="form-control mb-3"
                      placeholder="Matricule"
                      disabled
                  />
                </div>
                <div class="col-12 col-lg-3 mb-3">
                  <a class="btn btn-primary" href="https://www.linkedin.com" target="_blank">
                    <i class="fab fa-linkedin"></i>
                  </a>
                  <input v-model="user.linkedin" type="text" class="form-control"/>
                </div>
              </div>
            </div>
          </div>

          <!-- Description -->
          <label for="description" class="form-label fs-5 mb-2 mt-4">{{ $t('Description') }}</label>
          <div class="card bg-light">
            <div class="card-body d-flex flex-column">
							<textarea
<<<<<<< HEAD:dt-presentation/src/views/ProfileView.vue
                  v-model="user.description"
                  @input="validate"
                  class="{'form-control' : true, 'error':descriptionError"
                  id="description"
                  style="height: 100px"
              ></textarea>
            </div>
          </div>

          <!-- Technical Skills -->
          <TechnicalSkills
              @update-skills="updateTechnicalSkills"
              :getTechnicalSkillsSelect="fetchTechnicalSkills"
          />

          <!-- Soft Skills -->
          <SoftSkills
              @update-soft-skills="updateSoftSkills"
              :getSoftSkillsSelect="fetchSoftSkills"
          />
=======
								v-model="user.description"
								@input="validate"
								class="'form-control' : true, 'error':descriptionError"
								id="description"
								style="height: 100px"
							></textarea>
						</div>
					</div>

					<!-- Technical Skills -->
					<TechnicalSkills
						@update-skills="updateTechnicalSkills"
						:getTechnicalSkillsSelect="userTechnicalSkills"
					/>

					<!-- Soft Skills -->
					<SoftSkills
						@update-soft-skills="updateSoftSkills"
						:getSoftSkillsSelect="userSoftSkills"
					/>
>>>>>>> 0115b2c63c965443f35321160de409cd400ec344:dt-presentation/src/views/Profile.vue

          <div class="d-flex justify-content-center mt-3">
            <button class="btn btn-primary">{{ $t('SOUMETTRE') }}</button>
          </div>
        </div>
      </form>
    </div>
  </main>
</template>

<<<<<<< HEAD:dt-presentation/src/views/ProfileView.vue
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
      pictureProfil: null,
      // users: [],
      user: {
        lastName: '',
        firstName: '',
        email: '',
        internalNumber: 'abc1234',
        description: '',
        linkedin: '',

      },
      descriptionError: '',
      technicalSkills: [],
      softSkills: [],
      fetchTechnicalSkills: [],
      fetchSoftSkills: []
    }
  },
  beforeMount() {
    this.getProfile()
  },
  methods: {
    async getProfile() {
      try {
        const response = await fetch(`http://localhost:8080/developers/${this.user.internalNumber}`)
        const profile = await response.json()
        this.user.firstName = profile.firstName
        this.user.lastName = profile.lastName
        this.user.email = profile.email
        this.user.internalNumber = profile.internalNumber
        this.user.description = profile.description
        this.user.linkedin = profile.linkedin
        this.pictureProfil = 'images/' + profile.picture
        const skills = profile.skills
        this.fetchTechnicalSkills = skills.filter((skill) => skill.isTechnical === true)
        this.fetchSoftSkills = skills.filter((skill) => skill.isTechnical === false)
        console.log(this.fetchTechnicalSkills)
        console.log(this.fetchSoftSkills)
      } catch (error) {
        console.log('Erreur lors de la récupération des données', error)
      }
    },
    onFileChange(event) {
      const file = event.target.files[0]
      console.log(file)
      if (file) {
        this.picture = URL.createObjectURL(file)
        this.user.picture = file
      }
    },
    // Change description length > 5000 -> 5 is a test
    validate() {
      if (this.user.description.length > 5) {
        this.descriptionError = 'La description ne peut pas dépasser 5000 caractères.'
      } else {
        this.descriptionError = ''
      }
    },
    updateTechnicalSkills(skills) {
      this.technicalSkills = skills
    },
    updateSoftSkills(skills) {
      this.softSkills = skills
    },
    async update() {
      const formData = new FormData()
      formData.append('description', this.user.description)
      formData.append('linkedin', this.user.linkedin)

      if (this.user.picture instanceof File) {
        formData.append('picture', this.user.picture)
      }
      console.log([this.softSkills.concat(this.technicalSkills)])
      const skillsBlob = new Blob([JSON.stringify(this.softSkills.concat(this.technicalSkills))], {
        type: 'application/json'
      })
      formData.append(`skills`, skillsBlob)

      try {
        const response = await fetch(
            `http://localhost:8080/developers/${this.user.internalNumber}`,
            {
              method: 'PATCH',
              body: formData
            }
        )
        // const data = await response.json()
        console.log('Profil mis à jour avec succès')
      } catch (error) {
        console.error('Erreur lors de la mise à jour du profil', error)
      }
    }
  }
}
</script>
=======
>>>>>>> 0115b2c63c965443f35321160de409cd400ec344:dt-presentation/src/views/Profile.vue

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

.error {
  border-color: red;
}
</style>

