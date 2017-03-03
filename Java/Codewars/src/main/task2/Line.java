package main.task2;

import java.util.HashMap;
import java.util.Map;

public class Line {

    private static int TICKET_PRICE = 25;
    private static int MAX_AMOUNT = 100;

    private static String YES_RESULT = "YES";
    private static String NO_RESULT  = "NO";

    private static Map<Integer, Integer> cashbox = new HashMap<>();

    private static boolean isDebugEnabled = false;

    public static String Tickets(int[] peopleInLine) {

        clearCashbox();

        int totalAmount = 0;

        for (int currentBill : peopleInLine) {
            int change = currentBill - TICKET_PRICE;
            debug("currentBill = " + currentBill + "  change = " + change);
            if (currentBill == TICKET_PRICE) {
                putAmount(TICKET_PRICE);
                totalAmount += TICKET_PRICE;
            } else if (change <= totalAmount) {
                if (check(change, MAX_AMOUNT)) {
                    withdrawAmount(currentBill - TICKET_PRICE, MAX_AMOUNT);
                    putAmount(currentBill);
                    totalAmount = totalAmount + TICKET_PRICE - change;
                } else {
                    return NO_RESULT;
                }
            } else {
                return NO_RESULT;
            }
        }

        return YES_RESULT;
    }

    private static boolean check(int change, int currentAmount) {
        debug("   <check> change: " + change + " - startAmount: " + currentAmount);
        if (currentAmount < TICKET_PRICE) {
            return (change == 0) ? true : false;
        }
        if (change < currentAmount && currentAmount > TICKET_PRICE) {
            return check(change, currentAmount / 2);
        }
        int currentBillsCount = cashbox.get(currentAmount);
        debug("   <check> currentBillsCount: " + currentBillsCount);
        if (change > currentBillsCount * currentAmount) {
            return check(change - currentBillsCount * currentAmount, currentAmount / 2);
        } else {
            return check(change - currentAmount * (change / currentAmount), currentAmount / 2);
        }
    }

    private static void clearCashbox() {
        cashbox.put(TICKET_PRICE, 0);
        cashbox.put(TICKET_PRICE * 2, 0);
        cashbox.put(TICKET_PRICE * 4, 0);
    }

    private static void putAmount(int amount) {
        cashbox.put(amount, cashbox.get(amount) + 1);
    }

    private static void withdrawAmount(int totalAmount, int currentBill) {
        debug("   <withdraw> totalAmount: " + totalAmount + " - startAmount: " + currentBill);
        if (totalAmount < TICKET_PRICE) {
            return;
        }

        int currentBillsCount = cashbox.get(currentBill);
        int withdrawalBillsCount = totalAmount/currentBill;
        debug("   <withdraw> currentBillsCount: " + currentBillsCount + " - withdrawalBillsCount: " + withdrawalBillsCount);

        if (withdrawalBillsCount != 0 && currentBillsCount >= withdrawalBillsCount) {
            currentBillsCount = currentBillsCount - withdrawalBillsCount;
            totalAmount = totalAmount - withdrawalBillsCount * currentBill;
            cashbox.put(currentBill, currentBillsCount);
        }

        withdrawAmount(totalAmount, currentBill / 2);
    }

    private static void debug(String msg) {
        if (isDebugEnabled) {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        debug("RESULT: " + Line.Tickets(new int[]{25, 50, 50}));

        debug("Final KASSA/VASYA: ");
        for (Integer key : cashbox.keySet()) {
            debug(key + " - " + cashbox.get(key));
        }
    }

}