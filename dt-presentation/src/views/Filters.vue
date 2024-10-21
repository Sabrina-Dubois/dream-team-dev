<template>
  <main>
    <div class="container mt-5">
      <h1 class="text-center">{{ $t('Filters Management') }}</h1>

      <!-- Filter name -->
      <div v-for="(filter, index) in filters" :key="index" class="card mt-4 bg-light p-4">
        <div class="row align-items-center">
          <h5>
            <input v-model="filter.name" type="text" class="form-control"/>
          </h5>

          <!-- First Skill -->
          <div class="col-4 d-flex align-items-center">
            <label class="form-label mb-0 me-2 col-3">First Skill:</label>
            <select v-model="filter.skills[0]" class="form-select">
              <option v-for="skill in skillsList" :key="skill" :value="skill">
                {{ skill }}
              </option>
            </select>
          </div>

          <!-- Second Skill -->
          <div class="col-4 d-flex align-items-center">
            <label class="form-label mb-0 me-2 col-3">Second Skill:</label>
            <select v-model="filter.skills[1]" class="form-select">
              <option v-for="skill in skillsList" :key="skill" :value="skill">
                {{ skill }}
              </option>
            </select>
          </div>

          <!-- Keyword -->
          <div class="col-4 d-flex align-items-center">
            <label class="form-label">Keyword:</label> <!-- Correction ici -->
            <input type="text" v-model="filter.keyword" class="form-control"/>
          </div>
        </div>
      </div>

      <!-- Add button -->
      <div class="text-center mt-3">
        <button
            v-if="filters.length < 5"
            @click="add"
            class="btn btn-outline-primary ms-2 rounded-circle"
        >
          +
        </button>
      </div>

      <!-- Submit button -->
      <div class="text-center mt-5">
        <button @click="submit" class="btn btn-primary mt-3">Validate</button>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  name: 'FiltersView',
  data() {
    return {
      filters: [
        {
          name: '',
          skills: ['', ''],
          keyword: ''
        }
      ],
      skillsList: ['Skill1', 'Skill2', 'Skill3']
    }
  },
  methods: {
    add() {
      if (this.filters.length < 5) {
        this.filters.push({
          name: '',
          skills: ['', ''],
          keyword: ''
        })
      }
    },

    async submit() {
      try {
        for (const filter of this.filters) {
          const response = await fetch('http://localhost:8080/filters/create', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              name: filter.name,
              firstSkill: filter.skills[0],
              secondSkill: filter.skills[1],
              keyword: filter.keyword
            })
          })

          if (!response.ok) {
            throw new Error('Failed to save the filter');
          }

          const data = await response.json()
          console.log('Filter saved successfully:', data)
        }
        this.resetFilters();
      } catch (error) {
        console.error('Error saving filters:', error);
      }
    },

    resetFilters() {
      this.filters = [
        {
          name: '',
          skills: ['', ''],
          keyword: ''
        }
      ]
    }
  }
};
</script>

<style scoped>
</style>
