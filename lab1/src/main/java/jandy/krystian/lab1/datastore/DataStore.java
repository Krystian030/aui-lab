package jandy.krystian.lab1.datastore;

import jandy.krystian.lab1.entity.Course;
import jandy.krystian.lab1.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class DataStore {

    List<Course> courses = new ArrayList<>();

    List<Student> students = new ArrayList<>();

    public synchronized List<Course> findAllCourses() {
        return this.courses;
    }

    public synchronized Optional<Course> findCourse(Long id) {
        return courses.stream()
                .filter(course -> id.equals(course.getId()))
                .findFirst();
    }

    public synchronized void createCourse(Course course) {
        findCourse(course.getId()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(String.format("The course %s is already in the list", course.getTitle()));
                },
                () -> courses.add(course)
        );
    }

    public synchronized void updateCourse(Course course) {
        findCourse(course.getId()).ifPresentOrElse(
                original -> {
                    courses.remove(original);
                    courses.add(course);
                },
                () -> {
                    throw new IllegalArgumentException(String.format("The course with id %d does not exists", course.getId()));
                }
        );
    }

    public synchronized void deleteCourse(Long id) {
        findCourse(id).ifPresentOrElse(
                original -> {
                    courses.remove(original);
                },
                () -> {
                    throw new IllegalArgumentException(String.format("The course with id %d does not exists", id));
                }
        );
    }

    public synchronized List<Student> findAllStudents() {
        return this.students;
    }

    public synchronized Optional<Student> findStudentById(Long id) {
        return students.stream()
                .filter(student -> id.equals(student.getId()))
                .findFirst();
    }

    public synchronized void createStudent(Student student) {
        findStudentById(student.getId()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(String.format("The student with id %d is not unique", student.getId()));
                },
                () -> students.add(student)
        );
    }

    public synchronized void updateStudent(Student student) {
        findStudentById(student.getId()).ifPresentOrElse(
                original -> {
                    students.remove(original);
                    students.add(student);
                },
                () -> {
                    throw new IllegalArgumentException(String.format("The student with id %d does not exists", student.getId()));
                }
        );
    }

    public synchronized void deleteStudent(Long id) {
        findStudentById(id).ifPresentOrElse(
                original -> {
                    students.remove(original);
                },
                () -> {
                    throw new IllegalArgumentException(String.format("The student with id %d does not exists", id));
                }
        );
    }
}
