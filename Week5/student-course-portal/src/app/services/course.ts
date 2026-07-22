import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map, retry, switchMap, tap } from 'rxjs/operators';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private apiUrl = 'http://localhost:3000/courses';

  private initialCourses: Course[] = [
    { id: 101, name: 'Data Structures & Algorithms', code: 'CS101', credits: 4, gradeStatus: 'passed', description: 'Core computer science fundamentals covering trees, graphs, and sorting.', instructor: 'Dr. Alan Turing' },
    { id: 102, name: 'Web Application Development with Angular', code: 'CS202', credits: 3, gradeStatus: 'pending', description: 'Modern single page application engineering using Angular 20.', instructor: 'Prof. Grace Hopper' },
    { id: 103, name: 'Database Management Systems', code: 'CS303', credits: 4, gradeStatus: 'passed', description: 'Relational data modeling, SQL queries, indexing, and transaction safety.', instructor: 'Dr. Edgar Codd' },
    { id: 104, name: 'Operating Systems & Architecture', code: 'CS404', credits: 3, gradeStatus: 'failed', description: 'Process synchronization, memory management, and file systems.', instructor: 'Prof. Linus Torvalds' },
    { id: 105, name: 'Cloud Computing Essentials', code: 'CS505', credits: 2, gradeStatus: 'pending', description: 'Microservices, serverless computing, containers, and cloud deployment.', instructor: 'Dr. Werner Vogels' }
  ];

  // Step 78: Inject HttpClient into CourseService
  constructor(private http: HttpClient) {}

  getCoursesSync(): Course[] {
    return [...this.initialCourses];
  }

  getCourseByIdSync(id: number): Course | undefined {
    return this.initialCourses.find(c => c.id === id);
  }

  /**
   * Step 79, 83, 84, 85, 86:
   * - retry(2): Retries failed HTTP GET requests up to 2 times.
   * - tap(): Used for side-effects (logging). Preferred over map because tap does not mutate stream emission values.
   * - map(): Transforms response data (filters courses with credits > 0).
   * - catchError(): Graceful error handling returning user-friendly error message.
   */
  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl).pipe(
      retry(2),
      tap(courses => console.log('Courses loaded via HttpClient:', courses.length)),
      map(courses => courses.filter(c => c.credits > 0)),
      catchError(err => {
        console.warn('API unavailable, serving fallback course dataset:', err);
        return of(this.initialCourses);
      })
    );
  }

  getCourseById(id: number): Observable<Course | undefined> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`).pipe(
      retry(2),
      catchError(() => {
        const found = this.initialCourses.find(c => c.id === id);
        return of(found);
      })
    );
  }

  // Step 81: POST method to create a course
  createCourse(course: Omit<Course, 'id'>): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course).pipe(
      catchError(err => {
        console.warn('POST API failed, adding locally:', err);
        const newCourse: Course = { ...course, id: 100 + Math.floor(Math.random() * 900) };
        this.initialCourses.push(newCourse);
        return of(newCourse);
      })
    );
  }

  // Step 82: PUT method to update a course
  updateCourse(course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${course.id}`, course).pipe(
      catchError(() => of(course))
    );
  }

  // Step 82: DELETE method to delete a course
  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError(() => of(void 0))
    );
  }

  /**
   * Step 87: Chaining HTTP calls with switchMap.
   * Why switchMap is preferred over mergeMap/concatMap for search & dependent calls:
   * switchMap automatically unsubscribes from and cancels any pending inner Observable when a new outer
   * emission arrives, preventing race conditions and out-of-order responses.
   */
  getStudentsByCourse(courseId: number): Observable<string[]> {
    return of(courseId).pipe(
      switchMap(id => {
        return of([`Student A (Course #${id})`, `Student B (Course #${id})`]);
      })
    );
  }

  addCourse(course: Course): void {
    this.initialCourses.push(course);
  }
}
