package mahaveer;

public class Parser {
    private static final Ui ui = new Ui();

    public static String getCommandDeets() {
        return ui.readCommand().trim();
    }
}
