package mahaveer;

import mahaveer.exception.MaheveerException;
import mahaveer.task.Task;
import mahaveer.command.Command;
import mahaveer.command.CommandFactory;

import java.util.List;

public class Mahaveer {

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
