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
  constructor(private jobService: JobService) {}

  ngOnInit(): void {
    this.retrieveJobs();
  }
  retrieveJobs(): void {
    this.jobService.getAll().subscribe(
      (data) => {
        this.jobs = data.jobs;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
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
