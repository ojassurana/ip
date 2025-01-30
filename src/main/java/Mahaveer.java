import java.util.Scanner;

public class Mahaveer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] taskList = new String[100];
        String toEcho;
        int counter = 0;
        String line = "____________________________________________________________\n";
        System.out.println(line+" Hello! I'm Mahaveer\n What can I do for you?\n"+line);
        boolean notBye = true;
        while (notBye){
            toEcho = in.nextLine();
            System.out.println("----");
            if (toEcho.equals("bye")){
                notBye = false;
            }
            else if (toEcho.equals("list")){
                if (counter == 0) {
                    System.out.println("No tasks available.");
                } else {
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + ". " + taskList[i]);
                    }
                }
                System.out.println("----");
            }
            else {
                System.out.println("Stored: " + toEcho);
                System.out.println("----");
                taskList[counter] = toEcho;
                counter++;
            }
        }
        System.out.println(line);
        System.out.println("Jai Jinendra! Till we meet next time :)");
    }
}
