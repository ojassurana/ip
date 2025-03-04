package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size())
            throw new MaheveerException("Task number does not exist.");
        Task task = tasks.getTask(index);
        if (task.isDone()) {
            ui.showToUser("This task is already marked as done!");
        } else {
            storage.markTask(index);
            task.setDone(true);
            ui.showToUser("Nice! I've marked this task as done:");
            ui.showToUser("  [" + task.getStatusIcon() + "] " + task.getDescription());
        }
    }
}
