package jandy.krystian.lab1.command;

import jandy.krystian.lab1.model.Student;
import jandy.krystian.lab1.service.CourseService;
import jandy.krystian.lab1.service.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Command {

    private static final int SERVICE = 1;
    private static final int METHOD = 0;

    private static List<String> options;

    public static void execute(String command) {
        extractOption(command);
        run(command);
    }

    private static void extractOption(String command) {
        options = List.of(command.split(" "));
    }

    private static void run(String command) {
        try {
            CommandOptions method = getCommandOption(command, METHOD);
            Service service = null;
            if (options.size() > 1) {
                service = getService(command);
            }
            switch (method) {
                case EXIT:
                    System.exit(0);
                    break;
                case SELECT_ALL:
                    System.out.println(service.findAll());
                    break;
                case CREATE:
                    service.create();
                    break;
                case COMMANDS:
                    CommandLine.showCommands();
                    break;
                case DELETE:
                    service.delete();
                    break;
                case UPDATE:
                    service.update();
                    break;
                case ADD_TO_COURSE:
                    CourseService courseService = InitializeServices.courseService;
                    courseService.addToCourse();
                    break;
                case SELECT:
                    System.out.println(service.find());
                    break;
                default:
                    throw new IllegalArgumentException(String.join("Not found method", method.toString()));
            }
        }
        catch (Exception e) {
            log.error("Cannot run command: " + command);
        }
    }

    private static Service getService(String command) {
        CommandOptions service = getCommandOption(command, SERVICE);
        switch (service) {
            case STUDENT:
                return InitializeServices.studentService;
            case COURSE:
                return InitializeServices.courseService;
            default:
                throw new IllegalArgumentException(String.join("Not found repository", service.toString()));
        }
    }

    private static CommandOptions getCommandOption(String command, int option) {
        return CommandOptions.getOption(options.get(option));
    }
}
