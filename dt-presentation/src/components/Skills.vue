<template>
	<label for="TechnicalSkills" class="form-label fs-5 mb-2 mt-4">Compétences techniques</label>
	<div class="card bg-light">
		<div class="card-body d-flex flex-column">
			<div class="input-group form-label fs-5 mb-2 mt-4">
				<input type="text" class="form-control" />
				
				<select class="form-select col-3" aria-label="Default select example">
					<option selected>Level</option>
					<option value="junior">Junior</option>
					<option value="senior">Senior</option>
					<option value="expert">Expert</option>
				</select>
			</div>

			<input class="form-control" list="datalistTechnicalSkills" id="exampleDataList" />
			<datalist id="datalistTechnicalSkills">
				<option value="JAVA"></option>
				<option value="JS"></option>
				<option value="PYTHON"></option>
			</datalist>
		</div>
	</div>
</template>

<script >
export default {
	name: 'Skills',
	data() {
		
	},
	methods: {
		
	}
}
</script>

<style scoped>
</style>