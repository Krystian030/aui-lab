package jandy.krystian.lab1;

import jandy.krystian.lab1.service.CourseService;
import jandy.krystian.lab1.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Scanner;

@Component
@Slf4j
public class CommandLine implements CommandLineRunner {

    private final CourseService courseService;

    private final StudentService studentService;

    @Autowired
    public CommandLine(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        showCommands();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                String option = scanner.nextLine().toUpperCase(Locale.ROOT);
                if (option.equals("EXIT")) {
                    System.exit(0);
                } else if (option.startsWith("CREATE")) {
                    if (option.contains("STUDENT")) {

                    } else if (option.contains("COURSE")) {

                    }
                } else if (option.startsWith("DELETE")) {
                    System.out.println("Enter id: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    if (option.contains("STUDENT")) {
                        studentService.delete(id);
                    } else if (option.contains("COURSE")) {
                        courseService.delete(id);
                    }
                } else if (option.startsWith("UPDATE")) {
                    if (option.contains("STUDENT")) {

                    } else if (option.contains("COURSE")) {

                    }
                } else if (option.startsWith("SELECT")) {
                    System.out.println("Enter id: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    if (option.contains("STUDENT")) {
                        System.out.println(studentService.find(id));
                    } else if (option.contains("COURSE")) {
                        System.out.println(courseService.find(id));
                    }
                } else {
                    System.out.println("Unknown option: " + option);
                }
            } catch (Exception e) {
                log.error("Exception was thrown", e);
            }
        }
    }

    private void showCommands() {
        System.out.println("======= COMMANDS =======");
        System.out.println(">>> CREATE [STUDENT][COURSE] - create student / course");
        System.out.println(">>> UPDATE [STUDENT][COURSE] - update student / course");
        System.out.println(">>> DELETE [STUDENT][COURSE] - delete student / course");
        System.out.println(">>> SELECT [STUDENT][COURSE] - find student / course");
        System.out.println(">>> SELECT ALL [STUDENT][COURSE] - find all students/ courses");
        System.out.println(">>> COMMANDS - show all commands");
        System.out.println(">>> EXIT - exit");
    }
}
