package p4.warmongers;

public class Logger {
    public static void logEvent(String details) {
        System.err.println(new Exception().getStackTrace()[1]+"\n"+details);
        System.exit(1);
    }
}
