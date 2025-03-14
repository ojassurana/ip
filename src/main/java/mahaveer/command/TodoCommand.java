package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

/**
 * The TodoCommand class handles adding a new todo task.
 * <p>
 * It creates a new Task with the provided description, adds it to the task list,
 * displays a confirmation message to the user, and updates the storage.
 * </p>
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a new TodoCommand with the given task description.
     *
     * @param description the description of the todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command.
     * <p>
     * This method validates the task description, creates a new Task, adds it to the task list,
     * displays a confirmation to the user, and saves the task in storage.
     * </p>
     *
     * @param tasks   the current task list
     * @param ui      the user interface for displaying messages
     * @param storage the storage system for persisting tasks
     * @throws MaheveerException if the description is empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        if (description.isEmpty()) {
            throw new MaheveerException("A 'todo' requires a short description.\nFor example:\n"
                    + "  todo Bake a cake\n"
                    + "  todo Walk the dog");
        }
        Task task = new Task(description);
        tasks.addTask(task);
        ui.showToUser("Got it. I've added this task:");
        ui.showToUser("  [T][ ] " + description);
        storage.addTodo(description);
    }
}
