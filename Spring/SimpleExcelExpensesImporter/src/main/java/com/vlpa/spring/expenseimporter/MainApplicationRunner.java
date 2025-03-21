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
                /**
                 * IMPORT
                 *
                 *     -- Syntax
                 *     import -m [1-12] -b [td|pcf|cibc] -t [credit|debit]
                 *
                 *     -- Examples
                 *     import -m 12 -b td -t credit
                 *     import -m 12 -b pcf -t credit
                 *     import -m 02 -b pcf -t credit
                 *
                 *     import -m 02 -y 2025 -b pcf -t credit
                 *
                 * EXPORT
                 *
                 *     -- Syntax
                 *     export -m [1-12]
                 *
                 *     -- Examples
                 *     export -m 12
                 *
                 *
                 * EXEC ALL (import + export)
                 *
                 *     -- Syntax
                 *     execAll -m [1-12]
                 *
                 *     -- Examples
                 *     execAll -m 12
                 *
                 * CONFIG
                 *
                 *     -- Syntax
                 *
                 *         -- re-import categories from excel file to database
                 *         config [categories|-c]
                 *
                 *         -- ead patterns from excel file and display them
                 *         config [patterns|-p]
                 *
                 *     -- Examples
                 *
                 *         config -c
                 *         config -p
                 */
                commandToRun = CommandEnum.resolveCommandByKey(cmdParts[0]);
                commandToRun.getCmdRunner().execute(command);
            } catch (Exception e) {
                warning("Could not run command. Reason: " + e.getMessage());
                e.printStackTrace();
            }

        } while (commandToRun != CommandEnum.EXIT);

        input.close();
    }

}
