import java.util.Scanner;

public class Mahaveer {

    private static void printSeparator() {
        System.out.println("----");
    }

    private static void checkEmptyAfterCommand(String userInput, String commandWord) throws MaheveerException {
        userInput = userInput.trim();
        if (userInput.length() <= commandWord.length()) {
            throw new MaheveerException(
                    "I'm sorry by the description of " + commandWord + " cannot be empty.\nInstead, type something like: e.g. " + commandWord + " Make a bread."
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
                String description = toEcho.substring(5).trim();
                taskList[counter] = new Task(description);
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  [T][ ] " + description);
            } else if (toEcho.startsWith("deadline ")) {
                String[] parts = toEcho.substring(9).split(" /by ");
                if (parts.length < 2) {
                    System.out.println("Invalid deadline format. Use: deadline <description> /by <time>");
                } else {
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    taskList[counter] = new Deadline(description, by);
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + taskList[counter - 1].toString());
                }
            } else if (toEcho.startsWith("event ")) {
                String[] parts = toEcho.substring(6).split(" /from | /to ");
                if (parts.length < 3) {
                    System.out.println("Invalid event format. Use: event <description> /from <start> /to <end>");
                } else {
                    String description = parts[0].trim();
                    String start = parts[1].trim();
                    String end = parts[2].trim();
                    taskList[counter] = new Event(description, start, end);
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + taskList[counter - 1].toString());
                }
            } else {
                System.out.println("Stored: " + toEcho);
                printSeparator();
                taskList[counter] = new Task(toEcho);
                counter++;
            }
        }
        System.out.println(line);
        System.out.println("Jai Jinendra! Till we meet next time :)");
    }
}
