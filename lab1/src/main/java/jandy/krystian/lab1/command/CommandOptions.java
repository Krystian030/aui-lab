package jandy.krystian.lab1.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum CommandOptions {

    EXIT("EXIT"),
    STUDENT("STUDENT"),
    COURSE("COURSE"),
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    SELECT("SELECT"),
    SELECT_ALL("SELECT_ALL"),
    COMMANDS("COMMANDS"),
    UNDEFINED("UNDEFINED"),
    ADD_TO_COURSE("ADD_TO_COURSE");

    private final String option;

    CommandOptions(String option) {
        this.option = option;
    }

    public static CommandOptions getOption(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            log.error("Undefined option {}", value);
            return CommandOptions.UNDEFINED;
        }
    }

    @Override
    public String toString() {
        return option;
    }
}
