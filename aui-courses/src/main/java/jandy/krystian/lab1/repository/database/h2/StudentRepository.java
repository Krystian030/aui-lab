package jandy.krystian.lab1.repository.database.h2;

import jandy.krystian.lab1.entity.Course;
import jandy.krystian.lab1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByCourse(Course course);
    List<Student> findAllByCourseId(Long courseId);
}
