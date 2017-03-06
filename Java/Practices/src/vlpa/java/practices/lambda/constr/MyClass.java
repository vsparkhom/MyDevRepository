package vlpa.java.practices.lambda.constr;

public class MyClass<T> {

    private T value;

    MyClass(T value) {
        this.value = value;
    }

    MyClass() {
        this.value = null;
    }

    T getValue() {
        return value;
    }

}
