import { Component, OnInit } from '@angular/core';
import { JobService } from '../../_services/job.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css'],
})
export class JobDetailsComponent implements OnInit {
  currentJob = null;
  message = '';

  constructor(
    private jobService: JobService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.message = '';
    this.getJob(this.route.snapshot.paramMap.get('id'));
  }

  getJob(id): void {
    this.jobService.get(id).subscribe(
      (data) => {
        this.currentJob = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updatePublished(status): void {
    const data = {
      title: this.currentJob.title,
      description: this.currentJob.description,
      published: status,
    };

    this.jobService.update(this.currentJob.id, data).subscribe(
      (response) => {
        this.currentJob.published = status;
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateJob(): void {
    this.jobService.update(this.currentJob.id, this.currentJob).subscribe(
      (response) => {
        console.log(response);
        this.message = 'The job was updated successfully!';
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteJob(): void {
    this.jobService.delete(this.currentJob.id).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['/jobs']);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
