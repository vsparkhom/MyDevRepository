package vlpa.spring.expman.controller;

import org.springframework.ui.Model;
import vlpa.spring.expman.controller.period.PeriodHolder;

public class ControllerUtils {

    private static boolean isDebugEnabled = true;//TODO: move logging setting to Log4J

    public static void initPeriodParameters(Model model) {
        model.addAttribute("currentPeriodIndex", PeriodHolder.getInstance().getCurrentPeriod().getIndex());
        model.addAttribute("periodsList", PeriodHolder.getInstance().getPeriodsList());
    }

    public static void debug(Object message) {
        if (isDebugEnabled) {
            System.out.println("[DEBUG] " + message);
        }
    }

}
