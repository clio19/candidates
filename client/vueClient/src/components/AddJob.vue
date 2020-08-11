<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title">Title</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          v-model="job.title"
          name="title"
        />
      </div>

      <div class="form-group">
        <label for="description">Description</label>
        <input
          class="form-control"
          id="description"
          required
          v-model="job.description"
          name="description"
        />
      </div>

      <button @click="saveJob" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newJob">Add</button>
    </div>
  </div>
</template>

<script>
import JobDataService from "../services/JobDataService";

export default {
  name: "add-job",
  data() {
    return {
      job: {
        id: null,
        title: "",
        description: "",
        published: false,
      },
      submitted: false,
    };
  },
  methods: {
    saveJob() {
      var data = {
        title: this.job.title,
        description: this.job.description,
      };

      JobDataService.create(data)
        .then((response) => {
          this.job.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    newJob() {
      this.submitted = false;
      this.job = {};
    },
  },
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>