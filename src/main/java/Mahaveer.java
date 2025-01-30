import java.util.Scanner;

public class Mahaveer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String toEcho;
        String line = "____________________________________________________________\n";
        System.out.println(line+" Hello! I'm Mahaveer\n What can I do for you?\n"+line);
        boolean notBye = true;
        while (notBye){
            toEcho = in.nextLine();
            System.out.println("----");
            if (toEcho.equals("bye")){
                notBye = false;
            }
            else {
                System.out.println(toEcho);
                System.out.println("----");
            }
        }
        System.out.println(line);
        System.out.println("Jai Jinendra! Till we meet next time :)");
    }
}
