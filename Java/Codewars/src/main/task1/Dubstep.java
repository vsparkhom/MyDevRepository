package main.task1;

import java.util.regex.Pattern;

public class Dubstep {

    public static final boolean IS_DEBUG_ENABLED = true;
    public static final String WUB_PATTERN = "(WUB)+";

    public static String SongDecoder(String song) {
        Pattern pattern = Pattern.compile(WUB_PATTERN);
        String[] words = pattern.split(song);

        debug("---- WORDS OF THE SONG ----");
        StringBuilder builder = new StringBuilder();
        for(String word : words) {
            if (!"".equals(word)) {
                debug("- " + word);
                builder.append(word).append(' ');
            }
        }

        return builder.toString().trim();
    }

    public static void main(String[] args) {
        String test1 = "WUBWUBIWUBAMWUBWUBX";
        String test2 = "WUBWUBAWUBBWUBWUBCWUBWUBDWUB";
        String decodedResult = Dubstep.SongDecoder(test2);
        debug("Decoded Result: <" + decodedResult + ">");
    }

    public static void debug(String msg) {
        if (IS_DEBUG_ENABLED) {
            System.out.println(msg);
        }
    }


//    Test10(SongTests)
//    ✘ expected:&lt;A [B ]C D&gt; but was:&lt;A []C D&gt;
//    Test11(SongTests)
//    ✘ expected:&lt;W U[ B]&gt; but was:&lt;W U[]&gt;
//    Test12(SongTests)
//    ✘ expected:&lt;WU [B]W UB&gt; but was:&lt;WU []W UB&gt;
}
