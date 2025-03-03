package mahaveer;

import mahaveer.exception.MaheveerException;
import mahaveer.task.Deadline;
import mahaveer.task.Event;
import mahaveer.task.Task;

import java.util.List;
import java.util.ArrayList;


public class Mahaveer {

    private static void emptyDescription(String userInput, String commandWord) throws MaheveerException {
        userInput = userInput.trim();
        int minLengthRequired;
        String expectedPrefix = commandWord + " ";

        // Determine the minimum length to allow for actual description text
        String commandHint = switch (commandWord) {
            case "todo" -> {
                minLengthRequired = 5;
                yield """
                        A 'todo' requires a short description.
                        For example:
                          todo Bake a cake
                          todo Walk the dog
                        """;
            }
            case "deadline" -> {
                minLengthRequired = 9; // "deadline " -> 9 chars
                yield """
                        A 'deadline' requires a description and '/by' time.
                        For example:
                          deadline Submit assignment /by tonight
                          deadline Finish reading /by next Monday
                        """;
            }
            case "event" -> {
                minLengthRequired = 6; // "event " -> 6 chars
                yield """
                        An 'event' requires a description plus '/from' and '/to'.
                        For example:
                          event Conference /from Monday /to Wednesday
                          event Birthday party /from 2pm /to 6pm
                        """;
            }
            default -> {
                minLengthRequired = commandWord.length() + 1;
                yield "Please provide more details for the command '" + commandWord + "'.";
            }
        };

        // If the input doesn't start with <command> + space
        // OR the user typed no additional characters, throw exception
        if (!userInput.startsWith(expectedPrefix) || userInput.length() <= minLengthRequired) {
            throw new MaheveerException(
                    "The description of '" + commandWord + "' cannot be empty :c\n" + commandHint
            );
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ArrayList<Task> taskList = new ArrayList<>();
        Storage storage = new Storage();
        List<Task> loadedTasks = storage.loadTasks();
        for (int i = 0; i < loadedTasks.size() && i < 100; i++) {
            taskList.add(i, loadedTasks.get(i));
        }
        ui.showWelcome();
        boolean notBye = true;
        while (notBye) {
            String toEcho = ui.readCommand();
            ui.showLine();
            if (toEcho.equals("bye")) {
                notBye = false;

            } else if (toEcho.equals("list")) {
                if (taskList.isEmpty()) {
                    ui.showToUser("No tasks available.");
                } else {
                    ui.showToUser("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        ui.showToUser(" " + (i + 1) + ". " + taskList.get(i));
                    }
                }
                ui.showLine();

            } else if (toEcho.startsWith("mark")) {
                try {
                    int taskNumber = Integer.parseInt(toEcho.substring(5).trim()) - 1;
                    if (taskNumber >= 0 && taskNumber < taskList.size()) {
                        Task task = taskList.get(taskNumber);
                        if (task.isDone()) {
                            ui.showToUser("This task is already marked as done!");
                        } else {
                            storage.markTask(taskNumber);
                            task.setDone(true);
                            ui.showToUser("Nice! I've marked this task as done:");
                            ui.showToUser("  [" + task.getStatusIcon() + "] " + task.getDescription());
                        }
                    } else {
                        ui.showToUser("Task number does not exist.");
                    }
                } catch (Exception e) {
                    ui.showToUser("Please provide a valid task number.");
                }
                ui.showLine();

            } else if (toEcho.startsWith("unmark")) {
                try {
                    int taskNumber = Integer.parseInt(toEcho.substring(7).trim()) - 1;
                    if (taskNumber >= 0 && taskNumber < taskList.size()) {
                        Task task = taskList.get(taskNumber);
                        if (!task.isDone()) {
                            ui.showToUser("This task is already marked as not done!");
                        } else {
                            task.setDone(false);
                            ui.showToUser("OK, I've marked this task as not done yet:");
                            ui.showToUser("  [" + task.getStatusIcon() + "] " + task.getDescription());
                            storage.unmarkTask(taskNumber);
                        }
                    } else {
                        ui.showToUser("Task number does not exist.");
                    }
                } catch (Exception e) {
                    ui.showToUser("Please provide a valid task number.");
                }
                ui.showLine();

            } else if (toEcho.startsWith("todo")) {
                try {
                    emptyDescription(toEcho, "todo");
                    String description = toEcho.substring(5).trim();
                    taskList.add(new Task(description));
                    ui.showToUser("Got it. I've added this task:");
                    ui.showToUser("  [T][ ] " + description);
                    storage.addTodo(description);
                } catch (MaheveerException e) {
                    ui.showToUser(e.getMessage());
                }

            } else if (toEcho.startsWith("deadline")) {
                try {
                    emptyDescription(toEcho, "deadline");
                    String[] parts = toEcho.substring(9).split(" /by ");
                    if (parts.length < 2) {
                        throw new MaheveerException("Invalid deadline format. Use: deadline <description> /by <time>");
                    } else {
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        taskList.add(new Deadline(description, by));
                        ui.showToUser("Got it. I've added this task:");
                        ui.showToUser("  " + taskList.get(taskList.size() - 1).toString());
                        storage.addDeadline(description, by);
                    }
                } catch (MaheveerException e) {
                    ui.showToUser(e.getMessage());
                }

            } else if (toEcho.startsWith("event")) {
                try {
                    emptyDescription(toEcho, "event");
                    String[] parts = toEcho.substring(6).split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new MaheveerException("Invalid event format. Use: event <description> /from <start> /to <end>");
                    } else {
                        String description = parts[0].trim();
                        String start = parts[1].trim();
                        String end = parts[2].trim();
                        taskList.add(new Event(description, start, end));
                        ui.showToUser("Got it. I've added this task:");
                        ui.showToUser("  " + taskList.get(taskList.size() - 1).toString());
                        storage.addEvent(description, start, end);
                    }
                } catch (MaheveerException e) {
                    ui.showToUser(e.getMessage());
                }
            } else if (toEcho.startsWith("delete")) {
                try {
                    int taskNumber = Integer.parseInt(toEcho.substring(7).trim()) - 1;
                    if (taskNumber >= 0 && taskNumber < taskList.size()) {
                        // Get the task before removing, so we can show it to the user
                        Task removedTask = taskList.remove(taskNumber);
                        ui.showToUser("Noted. I've removed this task:");
                        ui.showToUser("  " + removedTask);
                        ui.showToUser("Now you have " + taskList.size() + " tasks in the list.");
                        storage.deleteTask(taskNumber);
                    } else {
                        ui.showToUser("Task number does not exist.");
                    }
                } catch (Exception e) {
                    ui.showToUser("Please provide a valid task number.");
                }
            } else {
                try {
                    throw new MaheveerException(
                            "I'm sorry, I don't understand what you want me to do :c\n"
                                    + "Please refer to mahaveer.Mahaveer Manual! (COMING SOON ON README.md)"
                    );
                } catch (MaheveerException e) {
                    ui.showToUser(e.getMessage());
                }
            }
        }

        ui.showLine();
        ui.showToUser("Jai Jinendra! Till we meet next time :)");
    }
}
