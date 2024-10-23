<script>
import axios from 'axios'
import { useVuelidate } from '@vuelidate/core'
import { helpers, maxLength, required } from '@vuelidate/validators'

export default {
	setup() {
		return {
			v$: useVuelidate()
		}
	},
	data() {
		return {
			firstName: '',
			lastName: '',
			technicalTopic: '',
			level: '',
			developers: []
		}
	},
	methods: {
		async searchDevelopers() {
			const isFormCorrect = await this.v$.$validate()
			if (isFormCorrect) {
				const query = new URLSearchParams({
					firstName: this.firstName,
					lastName: this.lastName,
					technicalTopic: this.technicalTopic,
					level: this.level
				}).toString()
				try {
					const response = await axios.get(`http://localhost:8080/developers/search?${query}`)
					//this.developers = await response.data
					//console.log(this.developers)
					this.$emit('update-developers', response.data)
				} catch (error) {
					console.error('Error fetching developers:', error)
				}
			} else {
				console.log('invalid')
			}
		}
	},
	validations() {
		return {
			firstName: {
				//required: helpers.withMessage(() => this.$t('validations.firstName.required'), required),
				maxLengthValue: helpers.withMessage(
					() => this.$t('validations.firstName.maxLength'),
					maxLength(5)
				)
			},
			lastName: {
				maxLengthValue: helpers.withMessage(
					() => this.$t('validations.lastName.maxLength'),
					maxLength(5)
				)
			},
			technicalTopic: {
				maxLengthValue: helpers.withMessage(
					() => this.$t('validations.technicalTopic.maxLength'),
					maxLength(5)
				)
			}
		}
	}
}
</script>
<template>
	<main>
		<div class="container mt-5">
			<h2 class="text-center"></h2>

			<div class="card mt-4 bg-light p-4">
				<form @submit.prevent="searchDevelopers">
					<div class="row align-items-center">
						<div class="col-sm-6 align-items-center">
							<label class="form-label mb-0 me-2">First name:</label>
							<input type="text" class="form-control" v-model="firstName" />
							<!-- Display error message using i18n -->

							<span v-if="v$.firstName.$error">
								<span
									v-for="error in v$.firstName.$errors"
									:key="error.$message"
									class="text-danger"
									>{{ error.$message }}</span
								>
							</span>
						</div>
						<div class="col-sm-6 align-items-center">
							<label class="form-label mb-0 me-2">Last name:</label>
							<input type="text" class="form-control" v-model="lastName" />
							<span v-if="v$.lastName.$error">
								<span
									v-for="error in v$.lastName.$errors"
									:key="error.$message"
									class="text-danger"
									>{{ error.$message }}</span
								>
							</span>
						</div>
					</div>
					<div class="row align-items-center mt-4">
						<div class="col-sm-6 align-items-center">
							<label class="form-label mb-0 me-2">Technical Skill:</label>
							<input type="text" class="form-control" v-model="technicalTopic" />
							<span v-if="v$.technicalTopic.$error">
								<span
									v-for="error in v$.technicalTopic.$errors"
									:key="error.$message"
									class="text-danger"
									>{{ error.$message }}</span
								>
							</span>
						</div>
						<div class="col-sm-6 align-items-center">
							<label class="form-label me-2">Level:</label>
							<div class="space-around row">
								<div class="col-3">
									<button value="Junior" class="btn btn-primary">Junior</button>
								</div>
								<div class="col-3">
									<button value="Senior" class="btn btn-primary">Senior</button>
								</div>
								<div class="col-3">
									<button value="Expert" class="btn btn-primary">Expert</button>
								</div>
							</div>
						</div>
					</div>
					<div class="text-center mt-5">
						<button @click="submitFilters" class="btn btn-primary mt-3">Show results</button>
					</div>
				</form>
			</div>
		</div>
	</main>
</template>

<style scoped></style>
