package utils.logger;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {

    private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
    private final static String BOLD = "\u001B[1m";

    private MyLogger() {
        LOGGER.setLevel(Level.INFO);

    }

    public static void log(String text) {
        LOGGER.info(BOLD + "[Current Method] " + text);
    }

    public static void logFormat(String text, Object ...args) {
        final Object[] arguments = Arrays.stream(args).toArray();
        String message = String.format(text, arguments);
        log(message);
    }
}