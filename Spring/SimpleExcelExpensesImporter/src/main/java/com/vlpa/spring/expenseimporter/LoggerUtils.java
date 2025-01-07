package com.vlpa.spring.expenseimporter;

//TODO: replace with LOG4J
public class LoggerUtils {

    private static LogLevel currentLevel = LogLevel.ALL;

    enum LogLevel {
        ALL,
        DEBUG,
        INFO,
        NONE
    }

    public static LogLevel getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(LogLevel currentLevel) {
        LoggerUtils.currentLevel = currentLevel;
    }

    public static void info() {
        info("");
    }

    public static void info(String msg) {
        LogLevel currentLevel = getCurrentLevel();
        if (LogLevel.ALL.equals(currentLevel) || LogLevel.INFO.equals(currentLevel)) {
            System.out.println("[INFO] " + msg);
        }
    }

    public static void debug() {
        debug("");
    }

    public static void debug(String msg) {
        LogLevel currentLevel = getCurrentLevel();
        if (LogLevel.ALL.equals(currentLevel) || LogLevel.DEBUG.equals(currentLevel)) {
            System.out.println("[DEBUG] " + msg);
        }
    }

    public static void warning(String msg) {
        criticalMessage("WARNING", msg);
    }

    public static void error(String msg) {
        criticalMessage("ERROR", msg);
    }

    public static void criticalMessage(String type, String msg) {
        if (!LogLevel.NONE.equals(getCurrentLevel())) {
            System.out.println("[" + type + "] " + msg);
        }
    }
}
