package com.vlpa.spring.expenseimporter.cmd;

public class ExportCmdRunner implements CommandRunner {

    @Override
    public void execute(String command) throws Exception {
        CommandExecutor cmdExecutor = new CommandExecutor();
        cmdExecutor.executeExport(command);
    }
}
