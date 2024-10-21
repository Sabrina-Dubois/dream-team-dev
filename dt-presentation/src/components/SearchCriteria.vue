<script>
import axios from "axios";

export default {
  data() {
    return {
      firstName: '',
      lastName: '',
      technicalTopic: '',
      level: '',
      developers: []
    };
  },
  methods: {
    async searchDevelopers() {
      const query = new URLSearchParams({
        firstName: this.firstName,
        lastName: this.lastName,
        technicalTopic: this.technicalTopic,
        level: this.level
      }).toString();

      try {
        const response = await axios.get(`http://localhost:8080/developers/search?${query}`);
        this.developers = await response.data;
        console.log(this.developers);

      } catch (error) {
        console.error("Error fetching developers:", error);
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
              <input type="text" class="form-control"/>
            </div>
            <div class="col-sm-6 align-items-center">
              <label class="form-label mb-0 me-2">Last name:</label>
              <input type="text" class="form-control"/>
            </div>
          </div>
          <div class="row align-items-center mt-4">
            <div class="col-sm-6 align-items-center">
              <label class="form-label mb-0 me-2">Technical Skill:</label>
              <input type="text" class="form-control"/>
            </div>
            <div class="col-sm-6 align-items-center">
              <label class="form-label me-2">Level:</label>
              <div class="space-around row">
                <div class="col-3">
                  <button @click="" value="Junior" class="btn btn-primary">Junior</button>
                </div>
                <div class="col-3">
                  <button @click="" value="Senior" class="btn btn-primary">Senior</button>
                </div>
                <div class="col-3">
                  <button @click="" value="Expert" class="btn btn-primary">Expert</button>
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
