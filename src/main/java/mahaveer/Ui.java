package mahaveer;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private static final String SEPARATOR_LINE = "____________________________________________________________\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("----");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(SEPARATOR_LINE + " Hello! I'm Mahaveer!\n What can I do for you?\n" + SEPARATOR_LINE);
    }

    public void showToUser(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }
}