package com.vlpa.leetcode.medium.task2;

/**
 * https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbersTask {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        System.out.println("list 1: " + l1);
        System.out.println("list 2: " + l2);

        ListNode currentLN1 = l1;
        ListNode currentLN2 = l2;

        ListNode currentSumLN = new ListNode();
        ListNode resultList = currentSumLN;
        ListNode nextSumLN;

        do {
//            System.out.println("currentLN1: " + currentLN1 + ", currentLN2: " + currentLN2);

            int sum;
            if (currentLN1 != null && currentLN2 != null) {
                sum = currentLN1.val + currentLN2.val;
            } else if (currentLN1 != null && currentLN2 == null) {
                sum = currentLN1.val;
            } else {
                sum = currentLN2.val;
            }
//            System.out.println("1 - currentSumLN.val: " + currentSumLN.val);
            sum += currentSumLN.val;
//            System.out.println("sum: " + sum);

            currentSumLN.val = sum % 10;
//            System.out.println("1 - currentSumLN.val: " + currentSumLN.val);

            nextSumLN = new ListNode();
            nextSumLN.val = (sum / 10);
//            System.out.println("nextSumLN.val: " + nextSumLN.val);

            currentSumLN.next = ((currentLN1 != null && currentLN1.next != null) || (currentLN2 != null && currentLN2.next != null) || nextSumLN.val != 0) ? nextSumLN : null;
//            System.out.println("currentSumLN.next is " + currentSumLN.next);
            currentSumLN = nextSumLN;

            currentLN1 = currentLN1 != null ? currentLN1.next : null;
            currentLN2 = currentLN2 != null ? currentLN2.next : null;

        } while (currentLN1 != null|| currentLN2 != null);

//        System.out.println(" / " + (18 / 10)); // 1
//        System.out.println(" % " + (18 % 10)); // 8

        System.out.println("result: " + resultList);

        return null;
    }

    public ListNode convertArrayIntoList(int[] array) {

        ListNode previousLN = new ListNode();
        ListNode firstListNode = previousLN;

        for (int i =0; i<array.length; i++) {
            previousLN.val = array[i];
            previousLN.next = i < array.length-1 ? new ListNode() : null;
            previousLN = previousLN.next;
        }

        return firstListNode;
    }

}
