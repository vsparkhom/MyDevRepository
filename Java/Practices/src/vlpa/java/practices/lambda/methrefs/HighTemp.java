package vlpa.java.practices.lambda.methrefs;

public class HighTemp {

    int temp;

    HighTemp(int temp) {
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    boolean sameTemp(HighTemp ht) {
        return temp == ht.getTemp();
    }

    boolean lessThenTemp(HighTemp ht) {
        return temp < ht.getTemp();
    }

}
