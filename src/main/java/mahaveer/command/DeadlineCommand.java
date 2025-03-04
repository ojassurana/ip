package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Deadline;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        if (description.isEmpty() || by == null || by.isEmpty())
            throw new MaheveerException("A 'deadline' requires a description and '/by' time.\nFor example:\n  deadline Submit assignment /by tonight\n  deadline Finish reading /by next Monday");
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showToUser("Got it. I've added this task:");
        ui.showToUser("  " + task);
        storage.addDeadline(description, by);
    }
}
