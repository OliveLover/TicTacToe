package module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static manager.GameUtil.fontColor;
import static manager.GameUtil.waitSecond;

public class TicTacToe implements Playable {
    private final int w = 4;
    private final int h = 4;
    private int countTimes;
    private boolean flag = true;
    private final String[][] board = new String[w][h];

    public TicTacToe() {
        this.countTimes = 9;
    }

    @Override
    public void init() {
        initializeBoard();
        playGame();
    }

    @Override
    public void playGame() {
        try {
            System.out.println("게임 모드를 선택하세요.");
            System.out.println("1. BasicMode");
            System.out.println("2. InfiniteMode");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int selectNum = Integer.parseInt(br.readLine());

            if (selectNum == 2) {
                System.out.println("InifinitMode를 시작합니다.");
                ready();
                showBoard();
                playInfiniteTicTacToe();
            } else {
                System.out.println("BasicMode를 시작합니다.");
                ready();
                showBoard();
                playTicTacToe();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0) {
                    board[i][j] = String.valueOf(j);
                    continue;
                }
                if (j == 0) {
                    board[i][j] = String.valueOf(i);
                    continue;
                }

                board[i][j] = " ";
            }
        }
    }

    private void showBoard() {
        for (int i = 0; i < h; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        if (!isWin() || countTimes != 0) {
            showCurrentTurn();
        }
    }

    private void playTicTacToe() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int i;
        int j;
        do {
            try {
                System.out.println("좌표를 입력해 주세요.");
                System.out.print(": ");
                st = new StringTokenizer(br.readLine());
                i = Integer.parseInt(st.nextToken());
                j = Integer.parseInt(st.nextToken());


                if (isAlreadyDraw(i, j)) {
                    throw new IllegalArgumentException("유효 하지 않은 입력 입니다. 다시 입력 해주세요.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                showBoard();
                continue;
            } catch (NoSuchElementException | ArrayIndexOutOfBoundsException e) {
                System.out.println("유효 하지 않은 입력 입니다. 다시 입력 해주세요.");
                showBoard();
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            draw_OX(i, j);

            showBoard();

            if (--countTimes == 0) break;

        } while (!isWin());

        if (isWin() || (isWin() && isCountEnd())) {
            showWinner();
        } else {
            System.out.println("Draw!!");
        }
    }

    private void playInfiniteTicTacToe() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Deque<int[]> cache = new ArrayDeque<>();
        int i;
        int j;
        do {
            try {
                System.out.println("좌표를 입력해 주세요.");
                System.out.print(": ");
                st = new StringTokenizer(br.readLine());
                i = Integer.parseInt(st.nextToken());
                j = Integer.parseInt(st.nextToken());

                if (isAlreadyDraw(i, j)) {
                    throw new IllegalArgumentException("유효 하지 않은 입력 입니다. 다시 입력 해주세요.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                showBoard();
                continue;
            } catch (NoSuchElementException | ArrayIndexOutOfBoundsException e) {
                System.out.println("유효 하지 않은 입력 입니다. 다시 입력 해주세요.");
                showBoard();
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int[] pos = {i, j};

            cache.push(pos);

            if (cache.size() == 7) {
                int[] check = cache.pollLast();
                int del_i = check[0];
                int del_j = check[1];
                erase_OX(del_i, del_j);
            }

            if (cache.size() >= 6) {
                int[] check = cache.peekLast();
                int mod_i = check[0];
                int mod_j = check[1];

                mod_OX(mod_i, mod_j);
            }

            draw_OX(i, j);

            showBoard();

        } while (!isWin());

        if (isWin()) {
            showWinner();
        }
    }

    private void draw_OX(int i, int j) {
        this.board[i][j] = this.flag ? "O" : "X";
        this.flag = !flag;
    }

    private void erase_OX(int i, int j) {
        this.board[i][j] = " ";
    }

    private void mod_OX(int i, int j) {
        this.board[i][j] = this.flag ? fontColor("red", "X") : fontColor("blue", "O");
    }

    private boolean isWin() {
        String o = "O";
        String x = "X";
        if (o.equals(board[1][1]) || x.equals(board[1][1])) {
            if (board[1][1].equals(board[1][2]) && board[1][2].equals(board[1][3])) return true;
            if (board[1][1].equals(board[2][1]) && board[2][1].equals(board[3][1])) return true;
            if (board[1][1].equals(board[2][2]) && board[2][2].equals(board[3][3])) return true;
        }
        if (o.equals(board[2][1]) || x.equals(board[2][1])) {
            if (board[2][1].equals(board[2][2]) && board[2][2].equals(board[2][3])) return true;
        }
        if (o.equals(board[3][1]) || x.equals(board[3][1])) {
            if (board[3][1].equals(board[3][2]) && board[3][2].equals(board[3][3])) return true;
        }
        if (o.equals(board[1][2]) || x.equals(board[1][2])) {
            if (board[1][2].equals(board[2][2]) && board[2][2].equals(board[3][2])) return true;
        }
        if (o.equals(board[1][3]) || x.equals(board[1][3])) {
            if (board[1][3].equals(board[2][3]) && board[2][3].equals(board[3][3])) return true;
            if (board[1][3].equals(board[2][2]) && board[2][2].equals(board[3][1])) return true;
        }

        return false;
    }

    public boolean isCountEnd() {
        if (this.countTimes == 0) {
            return true;
        }
        return false;
    }

    private void showWinner() {
        waitSecond();
        String winner = !this.flag ? "< O >" : "< X >";
        System.out.println("----------------------");
        System.out.println(winner + " is Winner!!");
        System.out.println("----------------------");
        waitSecond();
    }

    private void showCurrentTurn() {
        if (!isWin()) {
            String nowPlayer = this.flag ? "< O >" : "< X >";
            System.out.println(nowPlayer + "'s turn.");
        } else {
            waitSecond();
            System.out.println("Game End!!");
        }
    }

    private boolean isAlreadyDraw(int i, int j) {
        if (board[i][j].contains("O") || board[i][j].contains("X")) return true;
        return false;
    }

    private void ready() {
        int count = 3;
        waitSecond();
        for (int i = count; i > 0; i--) {
            System.out.println(i + "!");
            waitSecond();
        }
        System.out.println("Battle!");
        waitSecond();
    }
}
