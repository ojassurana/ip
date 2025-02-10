import java.util.Scanner;

public class Mahaveer {

    private static void printSeparator() {
        System.out.println("----");
    }

    private static void exceptionManager(String userInput, String commandWord) throws MaheveerException {
        userInput = userInput.trim();
        int minLengthRequired;
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
                minLengthRequired = 9;
                yield """
                        A 'deadline' requires a description and '/by' time.
                        For example:
                          deadline Submit assignment /by tonight
                          deadline Finish reading /by next Monday
                        """;
            }
            case "event" -> {
                minLengthRequired = 6;
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
        if (userInput.length() <= minLengthRequired) {
            throw new MaheveerException(
                    "The description of '" + commandWord + "' cannot be empty :c\n"
                            + commandHint
            );
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        String toEcho;
        int counter = 0;
        String line = "____________________________________________________________\n";
        System.out.println(line + " Hello! I'm Mahaveer\n What can I do for you?\n" + line);
        boolean notBye = true;

        while (notBye) {
            toEcho = in.nextLine();
            printSeparator();
            if (toEcho.equals("bye")) {
                notBye = false;
            } else if (toEcho.equals("list")) {
                if (counter == 0) {
                    System.out.println("No tasks available.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println(" " + (i + 1) + ". " + taskList[i]);
                    }
                }
                printSeparator();
            } else if (toEcho.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(toEcho.substring(5)) - 1;
                    if (taskNumber >= 0 && taskNumber < counter) {
                        Task task = taskList[taskNumber];
                        task.setDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  [" + task.getStatusIcon() + "] " + task.description);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid task number.");
                }
                printSeparator();
            } else if (toEcho.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(toEcho.substring(7)) - 1;
                    if (taskNumber >= 0 && taskNumber < counter) {
                        Task task = taskList[taskNumber];
                        task.setDone(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  [" + task.getStatusIcon() + "] " + task.description);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid task number.");
                }
                printSeparator();
            } else if (toEcho.startsWith("todo ")) {
                try {
                    exceptionManager(toEcho, "todo");
                    String description = toEcho.substring(5).trim();
                    taskList[counter] = new Task(description);
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [T][ ] " + description);
                } catch (MaheveerException e) {
                    System.out.println(e.getMessage());
                }
            } else if (toEcho.startsWith("deadline ")) {
                try {
                    exceptionManager(toEcho, "deadline");
                    String[] parts = toEcho.substring(9).split(" /by ");
                    if (parts.length < 2) {
                        throw new MaheveerException("Invalid deadline format. Use: deadline <description> /by <time>");
                    } else {
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        taskList[counter] = new Deadline(description, by);
                        counter++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + taskList[counter - 1].toString());
                    }
                } catch (MaheveerException e) {
                    System.out.println(e.getMessage());
                }
            } else if (toEcho.startsWith("event ")) {
                try {
                    exceptionManager(toEcho, "event");
                    String[] parts = toEcho.substring(6).split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new MaheveerException("Invalid event format. Use: event <description> /from <start> /to <end>");
                    } else {
                        String description = parts[0].trim();
                        String start = parts[1].trim();
                        String end = parts[2].trim();
                        taskList[counter] = new Event(description, start, end);
                        counter++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + taskList[counter - 1].toString());
                    }
                } catch (MaheveerException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new MaheveerException("I'm sorry, I don't understand what you want me to do :c\nPlease refer to Mahaveer Manual! (COMING SOON ON README.md)");
                } catch (MaheveerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println(line);
        System.out.println("Jai Jinendra! Till we meet next time :)");
    }
}
