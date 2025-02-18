package com.vlpa.spring.expenseimporter.cmd;

public class ExecuteAllImportExportCmdRunner implements CommandRunner {

    @Override
    public void execute(String command) throws Exception {
        CommandExecutor cmdExecutor = new CommandExecutor();
        cmdExecutor.executeAll(command);
    }
}
