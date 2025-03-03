package mahaveer;

import mahaveer.exception.MaheveerException;
import mahaveer.task.Deadline;
import mahaveer.task.Event;
import mahaveer.task.Task;
import java.util.List;

public class Mahaveer {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        // Load tasks from storage and wrap them in a TaskList
        List<Task> loadedTasks = storage.loadTasks();
        TaskList taskList = new TaskList(loadedTasks);

        ui.showWelcome();
        boolean notBye = true;
        while (notBye) {
            try {
                Parser.ParsedCommand commandDetails = Parser.getCommandDetails();
                ui.showLine();
                if (commandDetails.command().equals("bye")) {
                    notBye = false;
                } else {
                    switch (commandDetails.command()) {
                    case "list":
                        if (taskList.size() == 0) {
                            ui.showToUser("No tasks available.");
                        } else {
                            ui.showToUser("Here are the tasks in your list:");
                            for (int i = 0; i < taskList.size(); i++) {
                                ui.showToUser(" " + (i + 1) + ". " + taskList.getAllTasks().get(i));
                            }
                        }
                        break;
                    case "mark":
                        Integer markNumber = commandDetails.number();
                        if (markNumber == null) {
                            ui.showToUser("Please provide a valid task number.");
                        } else {
                            int taskIndex = markNumber - 1;
                            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                                Task task = taskList.getTask(taskIndex);
                                if (task.isDone()) {
                                    ui.showToUser("This task is already marked as done!");
                                } else {
                                    storage.markTask(taskIndex);
                                    task.setDone(true);
                                    ui.showToUser("Nice! I've marked this task as done:");
                                    ui.showToUser("  [" + task.getStatusIcon() + "] " + task.getDescription());
                                }
                            } else {
                                ui.showToUser("Task number does not exist.");
                            }
                        }
                        break;
                    case "unmark":
                        Integer unmarkNumber = commandDetails.number();
                        if (unmarkNumber == null) {
                            ui.showToUser("Please provide a valid task number.");
                        } else {
                            int taskIndex = unmarkNumber - 1;
                            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                                Task task = taskList.getTask(taskIndex);
                                if (!task.isDone()) {
                                    ui.showToUser("This task is already marked as not done!");
                                } else {
                                    task.setDone(false);
                                    ui.showToUser("OK, I've marked this task as not done yet:");
                                    ui.showToUser("  [" + task.getStatusIcon() + "] " + task.getDescription());
                                    storage.unmarkTask(taskIndex);
                                }
                            } else {
                                ui.showToUser("Task number does not exist.");
                            }
                        }
                        break;
                    case "todo":
                        String todoDesc = commandDetails.task();
                        if (todoDesc.isEmpty()) {
                            ui.showToUser("A 'todo' requires a short description.\nFor example:\n  todo Bake a cake\n  todo Walk the dog");
                        } else {
                            Task newTask = new Task(todoDesc);
                            taskList.addTask(newTask);
                            ui.showToUser("Got it. I've added this task:");
                            ui.showToUser("  [T][ ] " + todoDesc);
                            storage.addTodo(todoDesc);
                        }
                        break;
                    case "deadline":
                        String deadlineTask = commandDetails.task();
                        String by = commandDetails.extra();
                        if (deadlineTask.isEmpty() || by == null || by.isEmpty()) {
                            ui.showToUser("A 'deadline' requires a description and '/by' time.\nFor example:\n  deadline Submit assignment /by tonight\n  deadline Finish reading /by next Monday");
                        } else {
                            Task newTask = new Deadline(deadlineTask, by);
                            taskList.addTask(newTask);
                            ui.showToUser("Got it. I've added this task:");
                            ui.showToUser("  " + newTask);
                            storage.addDeadline(deadlineTask, by);
                        }
                        break;
                    case "event":
                        String eventTask = commandDetails.task();
                        String from = commandDetails.extra();
                        String to = commandDetails.extra2();
                        if (eventTask.isEmpty() || from == null || from.isEmpty() || to == null || to.isEmpty()) {
                            ui.showToUser("An 'event' requires a description plus '/from' and '/to'.\nFor example:\n  event Conference /from Monday /to Wednesday\n  event Birthday party /from 2pm /to 6pm");
                        } else {
                            Task newTask = new Event(eventTask, from, to);
                            taskList.addTask(newTask);
                            ui.showToUser("Got it. I've added this task:");
                            ui.showToUser("  " + newTask);
                            storage.addEvent(eventTask, from, to);
                        }
                        break;
                    case "delete":
                        Integer deleteNumber = commandDetails.number();
                        if (deleteNumber == null) {
                            ui.showToUser("Please provide a valid task number.");
                        } else {
                            int taskIndex = deleteNumber - 1;
                            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                                Task removedTask = taskList.deleteTask(taskIndex);
                                ui.showToUser("Noted. I've removed this task:");
                                ui.showToUser("  " + removedTask);
                                ui.showToUser("Now you have " + taskList.size() + " tasks in the list.");
                                storage.deleteTask(taskIndex);
                            } else {
                                ui.showToUser("Task number does not exist.");
                            }
                        }
                        break;
                    default:
                        ui.showToUser("I'm sorry, I don't understand what you want me to do :c\nPlease refer to mahaveer.Mahaveer Manual! (COMING SOON ON README.md)");
                        break;
                    }
                }
            } catch (MaheveerException e) {
                ui.showToUser(e.getMessage());
            }
            ui.showLine();
        }
        ui.showToUser("Jai Jinendra! Till we meet next time :)");
    }
}
