package mahaveer.command;

import mahaveer.Parser.ParsedCommand;
import mahaveer.exception.MaheveerException;

/**
 * The CommandFactory class is responsible for creating concrete Command objects
 * based on the parsed user input.
 */
public class CommandFactory {

    /**
     * Creates and returns a specific Command instance based on the provided
     * ParsedCommand details.
     *
     * @param parsedCommand the parsed command containing the command type and arguments
     * @return a concrete Command instance corresponding to the parsed command
     * @throws MaheveerException if the parsed command is invalid or unrecognized
     */
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
            case "find":
                return new FindCommand(parsedCommand.task());
            default:
                throw new MaheveerException("I'm sorry, I don't understand what you want me to do :c\nPlease refer to Mahaveer Manual! (COMING SOON ON README.md)");
        }
    }
}
