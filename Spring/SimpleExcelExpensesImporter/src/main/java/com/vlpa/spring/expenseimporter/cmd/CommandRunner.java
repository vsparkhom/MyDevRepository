package com.vlpa.spring.expenseimporter.cmd;

public interface CommandRunner {

    void execute(String command) throws Exception;
}
