package main.task3;

public class Sum {

    public int GetSum(int a, int b) {
        if (a == b) {
            return a;
        }
        return a + GetSum((a < b)? a + 1 : a - 1, b); //TODO
    }

    public static void main(String[] args) {
        Sum s = new Sum();
        System.out.println("GetSum: " + s.GetSum(-2, 3));
        System.out.println("GetSum(reverse): " + s.GetSum(3, -2));
        System.out.println("GetSum(reverse): " + s.GetSum(5, 5));
    }
}
