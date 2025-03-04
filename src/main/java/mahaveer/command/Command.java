package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.exception.MaheveerException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException;

    public boolean isExit() {
        return false;
    }
}
