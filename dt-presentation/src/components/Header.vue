<template>
	<nav class="navbar bg-primary">
		<div class="container-fluid d-flex justify-content-between">
			<span></span>
			<span class="navbar text-white h1">DreamTeam</span>
			<div>
				<button @click="changeLanguage('fr')" class="btn btn-outline-light ms-auto">
					<span class="fi fi-fr"></span>
				</button>
				<button @click="changeLanguage('en')" class="btn btn-outline-light ms-auto">
					<span class="fi fi-gb"></span>
				</button>
			</div>
		</div>
	</nav>
</template>

<script >
export default {
	name: 'Header',
	methods: {
		changeLanguage(lang) {
			this.$i18n.locale = lang
		}
	}
}
</script>

<style scoped>
</style>
