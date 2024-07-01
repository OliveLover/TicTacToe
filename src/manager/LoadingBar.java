package manager;

import static manager.GameUtil.fontColor;
import static manager.GameUtil.waitSecond;

public class LoadingBar {
    public static void main(String[] args) {
        int total = 100;
        int delay = 25;

        System.out.println(
                "████████████████████████████████████████████████████████████████████████████\n" +
                        "█▌ _____ ______   _______   ________  ________               ________     ▐█\n" +
                        "█▌|\\   _ \\  _   \\|\\  ___ \\ |\\   ____\\|\\   __  \\             |\\_____  \\    ▐█\n" +
                        "█▌\\ \\  \\\\\\__\\ \\  \\ \\   __/|\\ \\  \\___|\\ \\  \\|\\  \\  ___________\\|___/  /|   ▐█\n" +
                        "█▌ \\ \\  \\\\|__| \\  \\ \\  \\_|/_\\ \\  \\  __\\ \\   __  \\|\\____________\\ /  / /   ▐█\n" +
                        "█▌  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\ \\  \\|\\  \\ \\  \\ \\  \\|____________|/  /_/__  ▐█\n" +
                        "█▌   \\ \\__\\    \\ \\__\\ \\_______\\ \\_______\\ \\__\\ \\__\\            |\\________\\▐█\n" +
                        "█▌    \\|__|     \\|__|\\|_______|\\|_______|\\|__|\\|__|             \\|_______|▐█\n" +
                        "████████████████████████████████████████████████████████████████████████████"
        );

        System.out.println(
                        "                   ██████╗  █████╗ ███╗   ███╗███████╗\n" +
                        "                   ██╔════╝ ██╔══██╗████╗ ████║██╔════╝\n" +
                        "                   ██║  ███╗███████║██╔████╔██║█████╗  \n" +
                        "                   ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝  \n" +
                        "                   ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗\n" +
                        "                   ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝");

        System.out.println("Loading: ");
        System.out.print("[");

        for (int i = 0; i <= total; i++) {

            if (i % 2 == 0) {
                System.out.print(fontColor("green","▓"));
            }

            System.out.print("\r[");
            for (int j = 0; j <= total; j++) {
                if (j <= i) {
                    System.out.print(fontColor("green","▓"));
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("] " + i + "%");

            GameUtil.waitSecond(delay);
        }

        System.out.println("\nLoading complete!");
    }
}