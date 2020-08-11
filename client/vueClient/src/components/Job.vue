<template>
  <div v-if="currentJob" class="edit-form">
    <h4>Job</h4>
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title" v-model="currentJob.title" />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description" v-model="currentJob.description" />
      </div>

      <div class="form-group">
        <label>
          <strong>Status:</strong>
        </label>
        {{ currentJob.published ? "Published" : "Pending" }}
      </div>
    </form>

    <button
      class="badge badge-primary mr-2"
      v-if="currentJob.published"
      @click="updatePublished(false)"
    >UnPublish</button>
    <button v-else class="badge badge-primary mr-2" @click="updatePublished(true)">Publish</button>

    <button class="badge badge-danger mr-2" @click="deleteJob">Delete</button>

    <button type="submit" class="badge badge-success" @click="updateJob">Update</button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Job...</p>
  </div>
</template>

<script>
import JobDataService from "../services/JobDataService";

export default {
  name: "job",
  data() {
    return {
      currentJob: null,
      message: "",
    };
  },
  methods: {
    getJob(id) {
      JobDataService.get(id)
        .then((response) => {
          this.currentJob = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    updatePublished(status) {
      var data = {
        id: this.currentJob.id,
        title: this.currentJob.title,
        description: this.currentJob.description,
        published: status,
      };

      JobDataService.update(this.currentJob.id, data)
        .then((response) => {
          this.currentJob.published = status;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    updateJob() {
      JobDataService.update(this.currentJob.id, this.currentJob)
        .then((response) => {
          console.log(response.data);
          this.message = "The job was updated successfully!";
        })
        .catch((e) => {
          console.log(e);
        });
    },

    deleteJob() {
      JobDataService.delete(this.currentJob.id)
        .then((response) => {
          console.log(response.data);
          this.$router.push({ name: "jobs" });
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.message = "";
    this.getJob(this.$route.params.id);
  },
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
