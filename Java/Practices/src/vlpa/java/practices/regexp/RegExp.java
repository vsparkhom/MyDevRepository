package vlpa.java.practices.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
    public static void main(String[] args) {
        String newWoUrl = "workOrder";
        String cancelWoUrl = "workOrder/00000677/cancel";

        System.out.println("test1 (newWoUrl): " + getWOId(newWoUrl));
        System.out.println("test2 (cancelWoUrl): " + getWOId(cancelWoUrl));

    }

    private static String getWOId(String url) {
        Pattern pattern = Pattern.compile("/[0-9]*/");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            String matchString = matcher.group();
            return matchString.substring(1, matchString.length() - 1);
        }
        return null;
    }
}
