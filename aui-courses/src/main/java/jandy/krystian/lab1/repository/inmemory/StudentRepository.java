//package jandy.krystian.lab1.repository.inmemory;
//
//
//import jandy.krystian.lab1.datastore.DataStore;
//import jandy.krystian.lab1.entity.Student;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Optional;
//
//@org.springframework.stereotype.Repository
//public class StudentRepository implements Repository<Student, Long> {
//
//    private final DataStore store;
//
//    @Autowired
//    public StudentRepository(DataStore store) {
//        this.store = store;
//    }
//
//    @Override
//    public Optional<Student> find(Long id) {
//        return store.findStudentById(id);
//    }
//
//    @Override
//    public List<Student> findAll() {
//        return store.findAllStudents();
//    }
//
//    @Override
//    public void delete(Long id) {
//        store.deleteStudent(id);
//    }
//
//    @Override
//    public void create(Student entity) {
//        store.createStudent(entity);
//    }
//
//    @Override
//    public void update(Student entity) {
//        store.updateStudent(entity);
//    }
//}
