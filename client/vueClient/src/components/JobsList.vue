<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by title" v-model="searchTitle" />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="page = 1; retrieveJobs();"
          >Search</button>
        </div>
      </div>
    </div>

    <div class="col-md-12">
      <div class="mb-3">
        Items per Page:
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <option v-for="size in pageSizes" :key="size" :value="size">{{ size }}</option>
        </select>
      </div>

      <div style="display: flex;margin:0;padding:0;width:400px;">
        <div style="margin:0;padding:0;width:300px;">
          <b-pagination
            v-model="page"
            :total-rows="count"
            :per-page="pageSize"
            prev-text="Prev"
            next-text="Next"
            @change="handlePageChange"
          ></b-pagination>
        </div>
        <div style="margin:auto;text-align: left;">
          <ul class="pagination">
            <li class="page-item active">
              <a class="page-link">Page {{page}}</a>
            </li>
          </ul>
        </div>
      </div>
      <!-- 
      <b-pagination
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        prev-text="Prev"
        next-text="Next"
        @change="handlePageChange"
      ></b-pagination>-->

      <p class="mt-3">Current Page: {{ page }}</p>
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
      searchTitle: "",

      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],
    };
  },
  methods: {
    getRequestParams(searchTitle, page, pageSize) {
      let params = {};

      if (searchTitle) {
        params["title"] = searchTitle;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }

      return params;
    },
    retrieveJobs() {
      const params = this.getRequestParams(
        this.searchTitle,
        this.page,
        this.pageSize
      );

      JobDataService.getAll(params)
        .then((response) => {
          // this.jobs = response.data;
          const { jobs, totalItems } = response.data;
          this.jobs = jobs;
          this.count = totalItems;

          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    handlePageChange(value) {
      this.page = value;
      this.retrieveJobs();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveJobs();
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