package com.vlpa.spring.expenseimporter.cmd;

public enum CommandEnum {

    IMPORT("import", new ImportCmdRunner()),
    EXPORT("export", new ExportCmdRunner()),
    EXEC_ALL("execAll", new ExecuteAllImportExportCmdRunner()),
    CONFIG("config", new ConfigCmdRunner()),
    EXIT("exit", new ExitCmdRunner());

    private String cmdKey;
    private CommandRunner cmdRunner;

    CommandEnum(String cmdKey, CommandRunner cmdRunner) {
        this.cmdKey = cmdKey;
        this.cmdRunner = cmdRunner;
    }

    public String getCmdKey() {
        return cmdKey;
    }

    public CommandRunner getCmdRunner() {
        return cmdRunner;
    }

    public static CommandEnum resolveCommandByKey(String cmdKey) {
        for (CommandEnum cmd : values()) {
            if (cmd.cmdKey.equalsIgnoreCase(cmdKey)) {
                return cmd;
            }
        }
        throw new RuntimeException("UNKNOWN CMD");//TODO: Create an exception class
    }
}
