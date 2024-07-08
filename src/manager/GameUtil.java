package manager;

import java.util.HashMap;
import java.util.Map;

public class GameUtil {
    private static final Map<String, String> colorList = new HashMap<>(){
        {
            put("black", "\u001B[30m");
            put("red", "\u001B[31m");
            put("green", "\u001B[32m");
            put("yellow", "\u001B[33m");
            put("blue", "\u001B[34m");
            put("purple", "\u001B[35m");
            put("cyan", "\u001B[36m");
            put("white", "\u001B[37m");
        }
    };

    public static String fontColor(String color, String words) {
        return colorList.get(color) + words + "\u001B[0m";
    }

    public static void waitSecond(int Time) {
        try {
            for (int i = Time; i > 0; i--) {
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
