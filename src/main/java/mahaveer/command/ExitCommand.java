package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showToUser("Jai Jinendra! Till we meet next time :)");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
