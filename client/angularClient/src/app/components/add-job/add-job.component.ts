import { Component, OnInit } from '@angular/core';
import { JobService } from '../../_services/job.service';

@Component({
  selector: 'app-add-job',
  templateUrl: './add-job.component.html',
  styleUrls: ['./add-job.component.css'],
})
export class AddJobComponent implements OnInit {
  job = {
    title: '',
    description: '',
    published: false,
  };
  submitted = false;

  constructor(private jobService: JobService) {}

  ngOnInit(): void {}

  saveJob(): void {
    const data = {
      title: this.job.title,
      description: this.job.description,
    };

    this.jobService.create(data).subscribe(
      (response) => {
        console.log(response);
        this.submitted = true;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  newJob(): void {
    this.submitted = false;
    this.job = {
      title: '',
      description: '',
      published: false,
    };
  }
}
