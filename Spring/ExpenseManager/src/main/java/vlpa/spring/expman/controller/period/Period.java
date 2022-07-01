package vlpa.spring.expman.controller.period;

import java.util.Date;

public interface Period {

    Date getStartDate();

    Date getEndDate();

    int getNumberOfUnits();

    void setNumberOfUnits(int numberOfUnits);

}
