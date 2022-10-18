package jandy.krystian.lab1.command;

import jandy.krystian.lab1.service.Service;

import java.util.List;

public class Command {

    private static final int SERVICE = 1;
    private static final int METHOD = 0;

    public static void execute(String command) {
        Service currentService = getService(command);
        runMethod(currentService, command);
    }

    private static void runMethod(Service service, String command) {
        CommandOptions method = getCommandOption(command, METHOD);
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
            case SELECT:
                System.out.println(service.find());
                break;
            default:
                throw new IllegalArgumentException(String.join("Not found method", method.toString()));
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
        return CommandOptions.getOption(List.of(command.split(" ")).get(option));
    }
}
