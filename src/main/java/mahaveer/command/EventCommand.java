package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Event;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

/**
 * The EventCommand class is responsible for adding a new event task.
 * <p>
 * It encapsulates the event's description along with its start and end times.
 * When executed, it creates an Event task, adds it to the task list, and saves the task in storage.
 * </p>
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add a new event task.
     * <p>
     * This method validates the event details, creates an Event task, adds it to the task list,
     * displays a confirmation message to the user, and updates the storage.
     * </p>
     *
     * @param tasks   the current task list
     * @param ui      the user interface for displaying messages
     * @param storage the storage system for persisting tasks
     * @throws MaheveerException if the event details are invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        if (description.isEmpty() || from == null || from.isEmpty() || to == null || to.isEmpty()) {
            throw new MaheveerException("An 'event' requires a description plus '/from' and '/to'.\n" +
                    "For example:\n"
                    + "  event Conference /from Monday /to Wednesday\n"
                    + "  event Birthday party /from 2pm /to 6pm");
        }
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.showToUser("Got it. I've added this task:");
        ui.showToUser("  " + task);
        storage.addEvent(description, from, to);
    }
}
