package com.vlpa.spring.expenseimporter;

import com.vlpa.spring.expenseimporter.cmd.CommandEnum;
import java.util.Scanner;

import static com.vlpa.spring.expenseimporter.LoggerUtils.*;

public class MainApplicationRunner {

    public static void main(String[] args) {

        LoggerUtils.setCurrentLevel(LoggerUtils.LogLevel.ALL);

        String command;
        Scanner input = new Scanner(System.in);

        CommandEnum commandToRun = null;

        do {
            info("\nCommand: ");
            command = input.nextLine().trim();

            String[] cmdParts = command.split(" ");

            try {
                /* Examples of usage:

                   IMPORT

                    import -m 12 -b=td -t credit
                    import -m 12 -b=td -t debit
                    import -m 12 -b=pcf -t credit
                    import -m 12 -b=cibc -t credit

                   EXPORT

                     TBD

                   EXEC ALL

                     execAll -m 12

                   CONFIG

                    config -c
                    config -p

                 */
                commandToRun = CommandEnum.resolveCommandByKey(cmdParts[0]);
                commandToRun.getCmdRunner().execute(command);
            } catch (Exception e) {
                warning("Could not run command. Reason: " + e.getMessage());
            }

        } while (commandToRun != CommandEnum.EXIT);

        input.close();
    }

}
