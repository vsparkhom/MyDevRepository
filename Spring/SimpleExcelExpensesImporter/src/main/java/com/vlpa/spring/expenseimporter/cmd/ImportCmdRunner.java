package com.vlpa.spring.expenseimporter.cmd;

public class ImportCmdRunner implements CommandRunner {

    @Override
    public void execute(String command) throws Exception {
        CommandExecutor cmdExecutor = new CommandExecutor();
        cmdExecutor.executeImport(command);
    }
}
