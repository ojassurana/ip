package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Event;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        if (description.isEmpty() || from == null || from.isEmpty() || to == null || to.isEmpty())
            throw new MaheveerException("An 'event' requires a description plus '/from' and '/to'.\nFor example:\n  event Conference /from Monday /to Wednesday\n  event Birthday party /from 2pm /to 6pm");
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.showToUser("Got it. I've added this task:");
        ui.showToUser("  " + task);
        storage.addEvent(description, from, to);
    }
}
