package main.task1;

public class DubstepBestSolutions {

    public static String SongDecoder1(String song) {
        return song.replaceAll("(WUB)+", " ").trim();
    }

    /* Java 8 required */
    /*public static String SongDecoder2(String song) {
        return Arrays.stream(song.split("WUB"))
                .filter(i -> !"".equals(i))
                .collect(Collectors.joining(" "));
    }*/
}
