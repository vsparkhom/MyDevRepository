package vlpa.java.practices.lambda.factory;

public class ConstructorRefDemo3 {

    public static <R, T> R myClassFactory(MyFunc<R, T> f, T v) {
        return f.func(v);
    }

    public static void main(String[] args) {
        MyFunc<MyClass<Double>, Double> f = MyClass<Double>::new;
        MyClass<Double> mc = f.func(10.5);
        System.out.println("Value 1: " + mc.getValue());

        MyFunc<MyClass2, String> f2 = MyClass2::new;
        MyClass2 mc2 = f2.func("Vgsfs1");
        System.out.println("Value 2: " + mc2.getValue());
    }

}
