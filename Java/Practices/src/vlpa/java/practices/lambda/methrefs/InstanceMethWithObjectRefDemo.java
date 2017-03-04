package vlpa.java.practices.lambda.methrefs;

public class InstanceMethWithObjectRefDemo {

    public static <T> int counter(T[] vals, MyFunc<T> f, T v) {
        int count = 0;
        for (T currentValue : vals) {
            if (f.func(currentValue, v)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        HighTemp[] weekDayHighs = {
                new HighTemp(89), new HighTemp(82), new HighTemp(90), new HighTemp(89),
                new HighTemp(89), new HighTemp(91), new HighTemp(84), new HighTemp(83)};

        int count;

        count = counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(89));
        System.out.println("Same temperatures count: " + count);

        count = counter(weekDayHighs, HighTemp::lessThenTemp, new HighTemp(83));
        System.out.println("Less then temperatures count: " + count);

    }

}
