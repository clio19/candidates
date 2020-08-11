<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by title" v-model="title" />
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" @click="searchTitle">Search</button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Jobs List</h4>
      <ul class="list-group">
        <li
          class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(job, index) in jobs"
          :key="index"
          @click="setActiveJob(job, index)"
        >{{ job.title }}</li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllJobs">Remove All</button>
    </div>
    <div class="col-md-6">
      <div v-if="currentJob">
        <h4>Job</h4>
        <div>
          <label>
            <strong>Title:</strong>
          </label>
          {{ currentJob.title }}
        </div>
        <div>
          <label>
            <strong>Description:</strong>
          </label>
          {{ currentJob.description }}
        </div>
        <div>
          <label>
            <strong>Status:</strong>
          </label>
          {{ currentJob.published ? "Published" : "Pending" }}
        </div>

        <a class="badge badge-warning" :href="'/jobs/' + currentJob.id">Edit</a>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Job...</p>
      </div>
    </div>
  </div>
</template>

<script>
import JobDataService from "../services/JobDataService";

export default {
  name: "jobs-list",
  data() {
    return {
      jobs: [],
      currentJob: null,
      currentIndex: -1,
      title: "",
    };
  },
  methods: {
    retrieveJobs() {
      JobDataService.getAll()
        .then((response) => {
          this.jobs = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveJobs();
      this.currentJob = null;
      this.currentIndex = -1;
    },

    setActiveJob(job, index) {
      this.currentJob = job;
      this.currentIndex = index;
    },

    removeAllJobs() {
      JobDataService.deleteAll()
        .then((response) => {
          console.log(response.data);
          this.refreshList();
        })
        .catch((e) => {
          console.log(e);
        });
    },

    searchTitle() {
      JobDataService.findByTitle(this.title)
        .then((response) => {
          this.jobs = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.retrieveJobs();
  },
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>