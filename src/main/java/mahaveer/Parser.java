package mahaveer;

import mahaveer.exception.MaheveerException;

public class Parser {
    private static final Ui ui = new Ui();

    public static ParsedCommand getCommandDetails() throws MaheveerException {
        String input = ui.readCommand().trim();
        if (input.isEmpty())
            throw new MaheveerException("No command provided. Please enter a command.");

        String[] tokens = input.split("\\s+", 2);
        String command = tokens[0].toLowerCase();
        String rest = tokens.length > 1 ? tokens[1].trim() : "";

        switch (command) {
        case "bye":
            return new ParsedCommand("bye", null, "", null, null);
        case "list":
            return new ParsedCommand("list", null, "", null, null);
        case "mark":
            try {
                int number = Integer.parseInt(rest);
                return new ParsedCommand("mark", number, "", null, null);
            } catch (NumberFormatException e) {
                throw new MaheveerException("Please provide a valid task number.");
            }
        case "unmark":
            try {
                int number = Integer.parseInt(rest);
                return new ParsedCommand("unmark", number, "", null, null);
            } catch (NumberFormatException e) {
                throw new MaheveerException("Please provide a valid task number.");
            }
        case "delete":
            try {
                int number = Integer.parseInt(rest);
                return new ParsedCommand("delete", number, "", null, null);
            } catch (NumberFormatException e) {
                throw new MaheveerException("Please provide a valid task number.");
            }
        case "todo":
            if (rest.isEmpty())
                throw new MaheveerException("A 'todo' requires a short description.\nFor example:\n  todo Bake a cake\n  todo Walk the dog");
            return new ParsedCommand("todo", null, rest, null, null);
        case "deadline":
            if (rest.isEmpty())
                throw new MaheveerException("A 'deadline' requires a description and '/by' time.\nFor example:\n  deadline Submit assignment /by tonight\n  deadline Finish reading /by next Monday");
            String[] deadlineParts = rest.split(" /by ", 2);
            if (deadlineParts.length < 2 ||
                    deadlineParts[0].trim().isEmpty() ||
                    deadlineParts[1].trim().isEmpty())
                throw new MaheveerException("Invalid deadline format. Use: deadline <description> /by <time>");
            return new ParsedCommand("deadline", null, deadlineParts[0].trim(), deadlineParts[1].trim(), null);
        case "event":
            if (rest.isEmpty())
                throw new MaheveerException("An 'event' requires a description plus '/from' and '/to'.\nFor example:\n  event Conference /from Monday /to Wednesday\n  event Birthday party /from 2pm /to 6pm");
            String[] eventParts = rest.split(" /from ", 2);
            if (eventParts.length < 2 || eventParts[0].trim().isEmpty())
                throw new MaheveerException("Invalid event format. Use: event <description> /from <start> /to <end>");
            String description = eventParts[0].trim();
            String remainder = eventParts[1];
            String[] fromToParts = remainder.split(" /to ", 2);
            if (fromToParts.length < 2 ||
                    fromToParts[0].trim().isEmpty() ||
                    fromToParts[1].trim().isEmpty())
                throw new MaheveerException("Invalid event format. Use: event <description> /from <start> /to <end>");
            return new ParsedCommand("event", null, description, fromToParts[0].trim(), fromToParts[1].trim());
        default:
            throw new MaheveerException("I'm sorry, I don't understand what you want me to do :c\nPlease refer to Mahaveer Manual! (COMING SOON ON README.md)");
        }
    }

    public record ParsedCommand(String command, Integer number, String task, String extra, String extra2) {
    }
}
