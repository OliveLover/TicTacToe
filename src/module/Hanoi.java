package module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import static manager.GameUtil.fontColor;
import static manager.GameUtil.waitSecond;

public class Hanoi implements Playable {

    private Stack<Integer>[] towers = new Stack[3];
    private int numDisks;
    private int moveCount = 0;

    @Override
    public void init() {
        playGame();
    }

    @Override
    public void playGame() {
        try {
            playHanoi();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();

        return input;
    }

    private void playHanoi() throws IOException {
        numDisks = diskNumInput();
        initializeTowers();
        handleTowerInput();
    }

    private void handleTowerInput() throws IOException {
        while (true) {
            System.out.println("자동 완성을 보려면 'auto'를 입력 해주세요. 하노이 게임을 종료하고 싶으면 '0'을 입력해주세요.");
            System.out.print("옮길 원반의 출발 타워와 도착 타워를 입력하세요 (예: A C): ");
            String input = input().trim().toUpperCase();

            if(input.equals("0")) {
                System.out.println("하노이 게임을 종료합니다.");
                break;
            } else if(input.equals("AUTO")) {
               handleAutoCompletion();
                break;
            } else if (isValidTowerMoveInput(input)) {
                processTowerMove(input);
                if (isGameWon()) {
                    displayGameWonMessage();
                    break;
                }
            } else {
                System.out.println("잘못된 입력입니다. 'A B'와 같은 형식으로 입력해주세요.");
            }
        }
    }

    private void displayGameWonMessage() {
        System.out.println("축하합니다! 하노이탑을 완성하셨습니다!!");
        System.out.println("총 " + moveCount + "번 움직였습니다.");
    }

    private void processTowerMove(String input) {
        char from = input.charAt(0);
        char to = input.charAt(2);

        int source = from - 'A';
        int destination = to - 'A';

        if (isValidMove(source, destination)) {
            moveDisk(source, destination);
            moveCount++;
        } else {
            System.out.println("잘못된 움직임입니다. 다시 시도하세요.");
        }
    }

    private boolean isValidTowerMoveInput(String input) {
        return input.length() == 3 && input.charAt(1) == ' ' &&
                input.charAt(0) >= 'A' && input.charAt(0) <= 'C' &&
                input.charAt(2) >= 'A' && input.charAt(2) <= 'C';
    }

    private void handleAutoCompletion(){
        resetGame();
        initializeTowers();
        autoSolve(numDisks, 0, 2, 1);
        System.out.println("자동으로 하노이탑을 완성하였습니다!");
        System.out.println("총 " + moveCount + "번 움직였습니다. 다시 도전해보세요!");
        resetGame();
        init();
    }

    private int diskNumInput(){
        System.out.print("원반의 갯수를 입력해주세요: ");
        int numDisks;
        while (true) {
            try {
                numDisks = Integer.parseInt(input());
                if (numDisks > 0) {
                    break;
                } else {
                    System.out.print("양의 정수를 입력해주세요: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("유효한 숫자를 입력해주세요: ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return numDisks;
    }

    private void initializeTowers(){
        towers[0] = new Stack<>();
        towers[1] = new Stack<>();
        towers[2] = new Stack<>();

        for (int i = numDisks; i > 0; i--) {
            towers[0].push(i);
        }

        displayTowers();
    }

    private boolean isValidMove(int source, int destination) {
        if (source < 0 || source > 2 || destination < 0 || destination > 2 || towers[source].isEmpty()) {
            return false;
        }
        if (towers[destination].isEmpty() || towers[source].peek() < towers[destination].peek()) {
            return true;
        }
        return false;
    }

    private void moveDisk(int source, int destination) {
        int disk = towers[source].pop();
        towers[destination].push(disk);
        displayTowers();
        System.out.println("원반 [" + disk + "] 을 " + (char)('A' + source) + " 에서 " + (char)('A' + destination) +"로 이동" + "\n");
    }

    private void displayTowers() {
        int maxWidth = numDisks * 2 + 1;
        int boxWidth = maxWidth + 4;

        StringBuilder[] display = new StringBuilder[numDisks + 1];
        for (int i = 0; i <= numDisks; i++) {
            display[i] = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                if (i < towers[j].size()) {
                    int diskSize = towers[j].get(i) * 2 - 1;
                    int padding = (boxWidth - diskSize) / 2;
                    String color = getColorForDisk(towers[j].get(i));
                    display[i].append(" ".repeat(padding))
                            .append(fontColor(color,"█").repeat(diskSize))
                            .append(" ".repeat(padding));
                } else {
                    int pegPadding = (boxWidth - 1) / 2;
                    display[i].append(" ".repeat(pegPadding))
                            .append(fontColor("yellow","║"))
                            .append(" ".repeat(pegPadding));
                }
                display[i].append("   ");
            }
        }

        for (int i = numDisks; i >= 0; i--) {
            System.out.println(display[i].toString());
        }

        String boxLineTop = "┌" + "─".repeat(boxWidth - 2) + "┐";
        String boxLineBottom = "└" + "─".repeat(boxWidth - 2) + "┘";

        System.out.println(boxLineTop + "   " + boxLineTop + "   " + boxLineTop);

        String towerA = "│" + " ".repeat((boxWidth - 3) / 2) + "A" + " ".repeat((boxWidth - 3) / 2) + "│";
        String towerB = "│" + " ".repeat((boxWidth - 3) / 2) + "B" + " ".repeat((boxWidth - 3) / 2) + "│";
        String towerC = "│" + " ".repeat((boxWidth - 3) / 2) + "C" + " ".repeat((boxWidth - 3) / 2) + "│";

        System.out.println(towerA + "   " + towerB + "   " + towerC);

        System.out.println(boxLineBottom + "   " + boxLineBottom + "   " + boxLineBottom);
        System.out.println();
    }

    public static String getColorForDisk(int diskSize) {
        String[] colors = {"blue", "green", "red", "cyan", "purple", "white"};
        return colors[(diskSize - 1) % colors.length];
    }

    private boolean isGameWon() {
        return towers[2].size() == numDisks;
    }

    private void autoSolve(int n, int source, int destination, int tmp) {
        if (n == 1) {
            moveDisk(source, destination);
            moveCount++;
            waitSecond();
            return;
        }

        if (!towers[source].isEmpty()) {
            autoSolve(n - 1, source, tmp, destination);
            moveDisk(source, destination);
            moveCount++;
            waitSecond();
            autoSolve(n - 1, tmp, destination, source);
        }
    }

    private void resetGame() {
        towers[0].clear();
        towers[1].clear();
        towers[2].clear();
        moveCount = 0;
    }

}

