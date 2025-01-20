import com.vlpa.leetcode.medium.task1.TwoSumTask;
import com.vlpa.leetcode.medium.task2.AddTwoNumbersTask;
import com.vlpa.leetcode.medium.task2.ListNode;
import com.vlpa.leetcode.medium.task3.LongestSubstringTask;
import com.vlpa.leetcode.medium.task5.LongestPalindromicSubstringTask;
import com.vlpa.leetcode.medium.task7.ReverseIntegerTask;

public class Main {

    public static void main(String[] args) {

//        runTask1();
//        runTask2();
//        runTask3();
        runTask5();
//        runTask7();
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

    private static void runTask5() {
        LongestPalindromicSubstringTask task = new LongestPalindromicSubstringTask();
//        task.longestPalindrome("babad");
//        task.longestPalindrome("cbbd");
        task.longestPalindrome("mqizdjrfqtmcsruvvlhdgzfrmxgmmbguroxcbhalzggxhzwfznfkrdwsvzhieqvsrbyedqxwmnvovvnesphgddoikfwuujrhxwcrbttfbmlayrlmpromlzwzrkjkzdvdkpqtbzszrngczvgspzpfnvwuifzjdrmwfadophxscxtbavrhfkadhxrmvlmofbzqshqxazzwjextdpuszwgrxirmmlqitjjpijptmqfbggkwaolpbdglmsvlwdummsrdyjhmgrasrblpjsrpkkgknsucsshjuxunqiouzrdwwooxclutkrujpfebjpoodvhknayilcxjrvnykfjhvsikjabsdnvgguoiyldshbsmsrrlwmkfmyjbbsylhrusubcglaemnurpuvlyyknbqelmkkyamrcmjbncpafchacckhymtasylyfjuribqxsekbjkgzrvzjmjkquxfwopsbjudggnfbuyyfizefgxamocxjgkwxidkgursrcsjwwyeiymoafgyjlhtcdkgrikzzlenqgtdukivvdsalepyvehaklejxxmmoycrtsvzugudwirgywvsxqapxyjedbdhvkkvrxxsgifcldkspgdnjnnzfalaslwqfylmzvbxuscatomnmgarkvuccblpoktlpnazyeazhfucmfpalbujhzbykdgcirnqivdwxnnuznrwdjslwdwgpvjehqcbtjljnxsebtqujhmteknbinrloregnphwhnfidfsqdtaexencwzszlpmxjicoduejjomqzsmrgdgvlrfcrbyfutidkryspmoyzlgfltclmhaeebfbunrwqytzhuxghxkfwtjrfyxavcjwnvbaydjnarrhiyjavlmfsstewtxrcifcllnugldnfyswnsewqwnvbgtatccfeqyjgqbnufwttaokibyrldhoniwqsflvlwnjmffoirzmoxqxunkuepj");
    }

    private static void runTask3() {
        LongestSubstringTask task = new LongestSubstringTask();
        task.lengthOfLongestSubstring("abcabcbb"); // The answer is "abc", with the length of 3.
        task.lengthOfLongestSubstring("bbbbb");
        task.lengthOfLongestSubstring("pwwkew");
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

    private static void runTask1() {
        TwoSumTask task = new TwoSumTask();
        task.twoSum(new int[]{2,7,11,15}, 9);
        task.twoSumImproved(new int[]{2,7,11,15}, 9);
    }
}
