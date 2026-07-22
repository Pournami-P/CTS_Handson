import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Course, Student } from '../../models/course.model';
import * as EnrollmentActions from '../../store/enrollment/enrollment.actions';
import * as EnrollmentSelectors from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-student-profile',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './student-profile.html',
  styleUrl: './student-profile.css'
})
export class StudentProfileComponent implements OnInit {
  student: Student = {
    id: 'STU-98765',
    name: 'Alex Johnson',
    email: 'alex.johnson@university.edu',
    major: 'Computer Science & Engineering',
    semester: '6th Semester',
    gpa: 3.8
  };

  // Step 99: Cross-slice selector Observable joining course and enrollment states
  enrolledCourses$: Observable<Course[]>;

  constructor(private store: Store) {
    this.enrolledCourses$ = this.store.select(EnrollmentSelectors.selectEnrolledCourses);
  }

  ngOnInit(): void {}

  unenroll(courseId: number): void {
    this.store.dispatch(EnrollmentActions.unenrollFromCourse({ courseId }));
  }
}

export { StudentProfileComponent as StudentProfile };
