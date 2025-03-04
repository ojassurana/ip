package mahaveer;

import java.util.Scanner;

/**
 * The Ui class handles all user interactions, including reading user input
 * and displaying messages to the console.
 */
public class Ui {
    private final Scanner scanner;
    private static final String SEPARATOR_LINE = "____________________________________________________________\n";

    /**
     * Constructs a new Ui instance and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a simple line separator to the console.
     */
    public void showLine() {
        System.out.println("----");
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(SEPARATOR_LINE + " Hello! I'm Mahaveer!\n What can I do for you?\n" + SEPARATOR_LINE);
    }

    /**
     * Displays the specified message to the user.
     *
     * @param message the message to be displayed
     */
    public void showToUser(String message) {
        System.out.println(message);
    }

    /**
     * Closes the input scanner.
     */
    public void close() {
        scanner.close();
    }
}
