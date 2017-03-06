package vlpa.java.practices.lambda.constr;

public class ConstructorRefDemo2 {

    public static void main(String[] args) {

        MyFunc<Integer> refInt = MyClass<Integer>::new;

        MyClass<Integer> mc = refInt.func(100);

        System.out.println("Value: " + mc.getValue());

    }
}
