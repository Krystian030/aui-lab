package jandy.krystian.lab1.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Scanner;

@Component
@Slf4j
public class CommandLine implements CommandLineRunner {

    public static Scanner scanner;

    @Override
    public void run(String... args) throws Exception {
        showCommands();
        System.out.println();
        scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                String command = scanner.nextLine().toUpperCase(Locale.ROOT);
                Command.execute(command);
            } catch (Exception e) {
                log.error("Exception was thrown", e);
            }
        }
    }

    public static void showCommands() {
        System.out.println("======= COMMANDS =======");
        System.out.println(">>> CREATE [STUDENT][COURSE] - create student / course");
        System.out.println(">>> UPDATE [STUDENT][COURSE] - update student / course");
        System.out.println(">>> DELETE [STUDENT][COURSE] - delete student / course");
        System.out.println(">>> SELECT [STUDENT][COURSE] - find student / course");
        System.out.println(">>> SELECT_ALL [STUDENT][COURSE] - find all students/ courses");
        System.out.println(">>> COMMANDS - show all commands");
        System.out.println(">>> EXIT - exit");
    }
}
