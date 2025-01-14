import com.vlpa.leetcode.medium.task2.AddTwoNumbersTask;
import com.vlpa.leetcode.medium.task2.ListNode;
import com.vlpa.leetcode.medium.task7.ReverseIntegerTask;

public class Main {

    public static void main(String[] args) {

//        runTask2();
        runTask7();
    }

    private static void runTask2() {
        AddTwoNumbersTask addTwoNumbersTask = new AddTwoNumbersTask();

        ListNode ln1 = addTwoNumbersTask.convertArrayIntoList(new int[]{2, 4, 3});
        ListNode ln2 = addTwoNumbersTask.convertArrayIntoList(new int[]{5, 6, 4});

        ListNode ln5 = addTwoNumbersTask.convertArrayIntoList(new int[]{9,9,9,9,9,9,9});
        ListNode ln6 = addTwoNumbersTask.convertArrayIntoList(new int[]{9,9,9,9});

        addTwoNumbersTask.addTwoNumbers(ln1, ln2);
//        addTwoNumbersTask.addTwoNumbers(ln3, ln4);
        addTwoNumbersTask.addTwoNumbers(ln5, ln6);
    }

    private static void runTask7() {
        ReverseIntegerTask task = new ReverseIntegerTask();

        int reversedNumber = task.reverse(234);
        System.out.println("reversedNumber: " + reversedNumber);

        reversedNumber = task.reverse(-1005);
        System.out.println("reversedNumber: " + reversedNumber);

        reversedNumber = task.reverse(1534236469);
        System.out.println("reversedNumber: " + reversedNumber);

    }
}
