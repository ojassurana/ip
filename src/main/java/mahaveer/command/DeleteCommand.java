package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size())
            throw new MaheveerException("Task number does not exist.");
        Task removedTask = tasks.deleteTask(index);
        ui.showToUser("Noted. I've removed this task:");
        ui.showToUser("  " + removedTask);
        ui.showToUser("Now you have " + tasks.size() + " tasks in the list.");
        storage.deleteTask(index);
    }
}
