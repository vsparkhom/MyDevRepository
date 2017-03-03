package main.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LineBestSolutions {

    private static boolean isDebugEnabled = true;

    public static String Tickets1(int[] peopleInLine) {
        int bill25 = 0, bill50 = 0;
        for (int payment : peopleInLine) {
            debug("payment: " + payment);
            if (payment == 25) {
                debug("   - v1");
                bill25++;
            } else if (payment == 50) {
                debug("   - v2");
                bill25--;
                bill50++;
            } else if (payment == 100) {
                debug("   - v3");
                if (bill50 > 0) {
                    debug("      - v3.1");
                    bill50--;
                    bill25--;
                } else {
                    debug("      - v3.1");
                    bill25 -= 3;
                }
            }
            debug("bill25: " + bill25 + " - bill50: " + bill50);
            if (bill25 < 0 || bill50 < 0) {
                return "NO";
            }
        }
        return "YES";
    }

    public static String Tickets2(int[] bills)
    {
        Till till = new Till();

        for (int bill : bills) {
            till.addBill(bill);
            if (!till.removeCash(bill - 25)) return "NO";
        }

        return "YES";
    }

    private static class Till extends HashMap<Integer, Integer> {
        public int getBillCount(int bill) {
            if (!containsKey(bill)) put(bill, 0);
            return get(bill);
        }

        public void addBill(int bill) {
            put(bill, getBillCount(bill) + 1);
        }

        public boolean removeBill(int bill) {
            if (getBillCount(bill) == 0) return false;
            put(bill, getBillCount(bill) - 1);
            return true;
        }

        public boolean removeCash(int amount) {
            List<Integer> denominations = new ArrayList<Integer>(keySet());
            Collections.sort(denominations);
            Collections.reverse(denominations);

            for (int denomination : denominations) {
                while (denomination <= amount && getBillCount(denomination) > 0) {
                    removeBill(denomination);
                    amount -= denomination;
                }
            }

            return amount == 0;
        }
    }

    public static String Tickets3(final int[] peopleInLine) {
        int k25=0, k50=0;
        for (int n : peopleInLine) {
            if (n==25) k25++; // correct
            else if (n==50 && k25>0) { k50++; k25--; } // $25 change for $50
            else if (n==100 && k50>0 && k25 > 0) { k50--; k25--; } // $50+$25 change for $100
            else if (n==100 && k25>2) k25-=3; // 3x$25 change for $100
            else return "NO";
        }
        return "YES";
    }

    private static void debug(String msg) {
        if (isDebugEnabled) {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        debug("RESULT1 (EXPECTED: NO): " + LineBestSolutions.Tickets3(new int[]{25, 50, 50}));
        debug("RESULT2 (EXPECTED: YES): " + LineBestSolutions.Tickets3(new int[]{25, 50, 25, 25, 100, 50}));
        debug("RESULT3 (EXPECTED: YES): " + LineBestSolutions.Tickets3(new int[]{25, 25, 50, 100}));
        debug("RESULT3 (EXPECTED: NO): " + LineBestSolutions.Tickets3(new int[]{25, 50, 50, 100}));
    }

}
