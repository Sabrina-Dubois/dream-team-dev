<template>
	<label for="TechnicalSkills" class="form-label fs-5 mb-2 mt-4">{{
		$t('Compétences Techniques')
	}}</label>
	<div class="card bg-light">
		<div class="card-body d-flex flex-column">
			<div class="mb-2">
				<span v-for="(skill, index) in selectedSkills" :key="index" class="badge bg-primary me-2">
					{{ skill.name }} - {{ skill.levelName }}
					<button
						type="button"
						class="btn-close btn-close-white btn-sm ms-2"
						aria-label="Remove"
						@click="removeSkill(index)"
					></button>
				</span>
<<<<<<< HEAD
      </div>
      <!-- Select skills -->
      <div class="input-group mb-3">
        <input
            v-model="newSkill"
            class="form-control"
            list="datalistTechnicalSkills"
            autocomplete="off"
        />
        <datalist id="datalistTechnicalSkills">
          <option v-for="(skill, index) in availableTechnicalSkills" :key="index" :value="skill.name">
          </option>
          <!-- <option value="JAVA"></option>
          <option value="JS"></option>
          <option value="PYTHON"></option> -->
        </datalist>
        <select v-model="newLevel" class="form-select col-3" aria-label="Select level">
          <option selected>Level</option>
          <option v-for="(level, index) in levels" :key="index">{{ $t(level.name) }}</option>
        </select>
        <!-- Add new skill -->
        <button class="btn btn-outline-secondary ms-2 rounded-circle" @click.prevent="addSkill"
                style="border-radius: 50%"> +
        </button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'TechnicalSkills',
  props: {
    getTechnicalSkillsSelect: {
      type: Array,
      required: true
    },
  },
  watch: {
    getTechnicalSkillsSelect(newVal, oldVal) {
      this.getSkillUser()
      console.log('New value:', newVal);
      console.log('Old value:', oldVal);
    }
  },
  data() {
    return {
      selectedSkills: [],
      newSkill: '',
      newLevel: '',
      levels: [],
      availableTechnicalSkills: [],

    }
  },
  mounted() {
    this.getLevels()
    this.getTechnicalSkills()
  },
  methods: {
    addSkill() {
      if (
          this.newSkill &&
          this.newLevel &&
          !this.selectedSkills.some((skill) => skill.name === this.newSkill)
      ) {
        this.selectedSkills.push({
          name: this.newSkill,
          levelName: this.newLevel,
          isTechnical: true
        })
        this.newSkill = ''
        this.newLevel = ''
        this.emitSkills()
      }
    },
    // Remove a skill
    removeSkill(index) {
      this.selectedSkills.splice(index, 1);
      this.emitSkills();
    },
    emitSkills() {
      this.$emit('update-skills', this.selectedSkills)
    },
=======
			</div>
			<!-- Select skills -->
			<div class="input-group mb-3">
				<input
					v-model="newSkill"
					class="form-control"
					list="datalistTechnicalSkills"
					autocomplete="off"
				/>
				<datalist id="datalistTechnicalSkills">
					<option
						v-for="(skill, index) in availableTechnicalSkills"
						:key="index"
						:value="skill.name"
					></option>
				</datalist>
				<select v-model="newLevel" class="form-select col-3" aria-label="Select level">
					<option selected>Level</option>
					<option v-for="(level, index) in levels" :key="index">{{ $t(level.name) }}</option>
				</select>
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
	name: 'TechnicalSkills',
	props: {
		getTechnicalSkillsSelect: {
			type: Array,
			required: true
		}
	},
	data() {
		return {
			selectedSkills: [],
			newSkill: '',
			newLevel: '',
			levels: [],
			availableTechnicalSkills: []
		}
	},
	mounted() {
		this.getSkillUser()
		this.getLevels()
		this.getTechnicalSkills()
	},
	methods: {
		addSkill() {
			if (
				this.newSkill &&
				this.newLevel &&
				!this.selectedSkills.some((skill) => skill.name === this.newSkill)
			) {
				this.selectedSkills.push({
					name: this.newSkill,
					levelName: this.newLevel,
					isTechnical: true
				})
				this.newSkill = ''
				this.newLevel = ''
				this.emitSkills()
			}
		},
		// Remove a skill
		removeSkill(index) {
			this.selectedSkills.splice(index, 1)
			this.emitSkills()
		},
		emitSkills() {
			this.$emit('update-skills', this.selectedSkills)
		},
>>>>>>> 0115b2c63c965443f35321160de409cd400ec344

		getSkillUser() {
			console.log(this.getTechnicalSkillsSelect)
			this.selectedSkills = [...this.getTechnicalSkillsSelect]
		},

<<<<<<< HEAD

    async getLevels() {
      try {
        const response = await fetch(`http://localhost:8080/levels`)
        const data = await response.json()
        this.levels = data
        console.log(this.levels)
      } catch {
        console.error('Erreur lors de la récupération des niveaux:', error)
      }
    },
=======
		async getLevels() {
			try {
				const response = await fetch(`http://localhost:8080/levels`)
				const data = await response.json()
				this.levels = data
				console.log(this.levels)
			} catch (error) {
				console.error('Erreur lors de la récupération des niveaux:', error)
			}
		},
>>>>>>> 0115b2c63c965443f35321160de409cd400ec344

		async getTechnicalSkills() {
			try {
				const response = await fetch(`http://localhost:8080/topics/search?label=${this.newSkill}`)
				const data = await response.json()
				this.availableTechnicalSkills = data
				console.log(this.availableTechnicalSkills)
			} catch (error) {
				console.error('Erreur lors de la récupération des niveaux:', error)
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
