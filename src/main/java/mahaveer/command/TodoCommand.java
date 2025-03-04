package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        if (description.isEmpty())
            throw new MaheveerException("A 'todo' requires a short description.\nFor example:\n  todo Bake a cake\n  todo Walk the dog");
        Task task = new Task(description);
        tasks.addTask(task);
        ui.showToUser("Got it. I've added this task:");
        ui.showToUser("  [T][ ] " + description);
        storage.addTodo(description);
    }
}
