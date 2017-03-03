package main.task3;

public class SumBestSolutions {

    //Сумма арифметической прогрессии
    public int GetSum(int a, int b) {
        return (a + b) * (Math.abs(a - b) + 1) / 2;
    }
}
