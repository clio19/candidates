import { Component, OnInit } from '@angular/core';
import { JobService } from '../../_services/job.service';

@Component({
  selector: 'app-jobs-list',
  templateUrl: './jobs-list.component.html',
  styleUrls: ['./jobs-list.component.css'],
})
export class JobsListComponent implements OnInit {
  jobs: any;
  currentJob = null;
  currentIndex = -1;
  title = '';

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];

  constructor(private jobService: JobService) {}

  ngOnInit(): void {
    this.retrieveJobs();
  }

  getRequestParams(searchTitle, page, pageSize): any {
    // tslint:disable-next-line:prefer-const
    let params = {};

    if (searchTitle) {
      params[`title`] = searchTitle;
    }

    if (page) {
      params[`page`] = page - 1;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    return params;
  }

  retrieveJobs(): void {
    const params = this.getRequestParams(this.title, this.page, this.pageSize);

    this.jobService.getAll(params).subscribe(
      (response) => {
        const { jobs, totalItems } = response;
        this.jobs = jobs;
        this.count = totalItems;
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  handlePageChange(event): void {
    this.page = event;
    this.retrieveJobs();
  }

  handlePageSizeChange(event): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveJobs();
  }

  refreshList(): void {
    this.retrieveJobs();
    this.currentJob = null;
    this.currentIndex = -1;
  }
  setActiveJob(job, index): void {
    this.currentJob = job;
    this.currentIndex = index;
  }

  removeAllJobs(): void {
    this.jobService.deleteAll().subscribe(
      (response) => {
        console.log(response);
        this.retrieveJobs();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  searchTitle(): void {
    this.jobService.findByTitle(this.title).subscribe(
      (data) => {
        this.jobs = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
