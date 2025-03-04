package mahaveer.command;

import mahaveer.Parser.ParsedCommand;
import mahaveer.exception.MaheveerException;

public class CommandFactory {
    public static Command getCommand(ParsedCommand parsedCommand) throws MaheveerException {
        switch (parsedCommand.command()) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (parsedCommand.number() == null)
                throw new MaheveerException("Please provide a valid task number.");
            return new MarkCommand(parsedCommand.number());
        case "unmark":
            if (parsedCommand.number() == null)
                throw new MaheveerException("Please provide a valid task number.");
            return new UnmarkCommand(parsedCommand.number());
        case "todo":
            return new TodoCommand(parsedCommand.task());
        case "deadline":
            return new DeadlineCommand(parsedCommand.task(), parsedCommand.extra());
        case "event":
            return new EventCommand(parsedCommand.task(), parsedCommand.extra(), parsedCommand.extra2());
        case "delete":
            if (parsedCommand.number() == null)
                throw new MaheveerException("Please provide a valid task number.");
            return new DeleteCommand(parsedCommand.number());
        default:
            throw new MaheveerException("I'm sorry, I don't understand what you want me to do :c\nPlease refer to Mahaveer Manual! (COMING SOON ON README.md)");
        }
    }
}
