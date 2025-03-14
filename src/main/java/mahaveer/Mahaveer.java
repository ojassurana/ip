package mahaveer;

import mahaveer.exception.MaheveerException;
import mahaveer.task.Task;
import mahaveer.command.Command;
import mahaveer.command.CommandFactory;

import java.util.List;

/**
 * The main class for the Mahaveer chatbot application.
 * <p>
 * This class initializes the UI, storage, and task list,
 * then continuously processes user commands until an exit command is issued.
 * </p>
 */
public class Mahaveer {

    /**
     * Main entry point of the application.
     *
     * <p>
     * Initializes the user interface, loads stored tasks, and starts the command processing loop.
     * This loop continues until an exit command is received.
     * </p>
     *
     * @param args Command-line arguments (not used)
     */

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        List<Task> loadedTasks = storage.loadTasks();
        TaskList taskList = new TaskList(loadedTasks);

        ui.showWelcome();
        while (!processCommand(taskList, ui, storage)) {
            ui.showLine();
        }
    }

    /**
     * Processes a single user command.
     * <p>
     * This method parses the user input, executes the corresponding command,
     * and returns a boolean indicating whether the exit command was issued.
     * </p>
     *
     * @param taskList the current task list
     * @param ui       the user interface for displaying messages
     * @param storage  the storage for persisting task changes
     * @return {@code true} if the executed command is an exit command, {@code false} otherwise
     */
    private static boolean processCommand(TaskList taskList, Ui ui, Storage storage) {
        Parser.ParsedCommand parsedCommand;
        try {
            parsedCommand = Parser.getCommandDetails();
        } catch (MaheveerException e) {
            ui.showToUser(e.getMessage());
            return false;
        }

        ui.showLine();

        Command command;
        try {
            command = CommandFactory.getCommand(parsedCommand);
            command.execute(taskList, ui, storage);
        } catch (MaheveerException e) {
            ui.showToUser(e.getMessage());
            return false;
        }
        return command.isExit();
    }
}
