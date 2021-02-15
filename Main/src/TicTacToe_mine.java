import java.util.Random;
import java.util.Scanner;

public class TicTacToe_mine {

    private static final Random rnd = new Random();
    private static final Scanner sc = new Scanner(System.in);
    private static final int yCell = 5;
    private static final int xCell = 5;
    private static final char[][] field = new char[yCell][xCell];

    public static void main(String[] args) {
        playTTT();
    }

    static char[][] createDesk() {
        for (int i = 0; i < yCell; i++) {
            for (int j = 0; j < xCell; j++) {
                field[i][j] = '-';
            }
        }
        return field;
    }

    static void drawDesk(char[][] field) {
        for (char[] chars : field) {
            for (char aChar : chars) {
                System.out.print(aChar);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void playTTT() {
        char[][] field = createDesk();
        drawDesk(field);
        while (true) {
            if (!checkNextUserGo(field)) {
                return;
            }
            if (!checkNextPCGo(field)) {
                return;
            }
        }
    }
    //*
    private static boolean wonGame(char[][] field, char sign) {
        for (int i = 0; i < yCell; i++) {
            for (int j = 0; j < xCell; j++) {
                if (checkLine(i, j, 0, 1, sign)) return true;
                if (checkLine(i, j, 1, 1, sign)) return true;
                if (checkLine(i, j, 1, 0, sign)) return true;
                if (checkLine(i, j, -1, 1, sign)) return true;
            }
        }
        return false;
    }

    private static boolean checkLine(int y, int x, int vy, int vx, char sign) {
        int wayY = y + (yCell - 1) * vy;
        int wayX = x + (xCell - 1) * vx;
        if (wayX < 0 || wayY < 0 || wayX > xCell - 1 || wayY > yCell - 1) return false;
        for (int i = 0; i < yCell; i++) {
            int itemY = y + i * vy;
            int itemX = x + i * vx;
            if (field[itemY][itemX] != sign) return false;
        }
        return true;
    }

    static boolean hasNoCell(char[][] field) {
        for (int i = 0; i < yCell; i++) {
            for (int j = 0; j < xCell; j++) {
                if (field[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkNextPCGo(char[][] field) {
        doPCGo(field);
        drawDesk(field);
        return isNextGoExist(field, 'O', "Sorry, PC is winner!");
    }

    static boolean checkNextUserGo(char[][] field) {
        doUserGo(field);
        drawDesk(field);
        return isNextGoExist(field, 'X', "Congrats!!! You are winner!");
    }

    static boolean isNextGoExist(char[][] field, char sign, String winMessage) {
        if (hasNoCell(field)) {
            System.out.println("There is draw detected. Finish!");
            return false;
        }
        if (wonGame(field, sign)) {
            System.out.println(winMessage);
            return false;
        }
        return true;
    }

    static void doPCGo(char[][] field) {
        //**if PC is winning:
        for (int i = 0; i < yCell; i++)
            for (int j = 0; j < xCell; j++) {
                if (!emptyCell(field, i, j)) {
                    field[i][j] = 'O';
                    if (wonGame(field, '0')) return;
                    field[i][j] = '-';
                }
            }
        //**if User is winning:
        for (int i = 0; i < yCell; i++)
            for (int j = 0; j < xCell; j++) {
                if (!emptyCell(field, i, j)) {
                    field[i][j] = 'X';
                    if (wonGame(field, 'X')) {
                        field[i][j] = 'O';
                        return;
                    }
                    field[i][j] = '-';
                }
            }

        int x, y;
        do {
            x = rnd.nextInt(xCell);
            y = rnd.nextInt(yCell);
        } while (emptyCell(field, x, y));
        field[x][y] = 'O';
    }

    static void doUserGo(char[][] field) {
        int x, y;
        do {
            x = checkDeskWidth('X', xCell);
            y = checkDeskWidth('Y', yCell);
        } while (emptyCell(field, x, y));

        field[x][y] = 'X';
    }

    static int checkDeskWidth(char coordName, int coordRange) {
        int val;
        do {
            System.out.printf("Please input %s-coordinate in range [1-%d]...%n", coordName, coordRange);
            val = TicTacToe_mine.sc.nextInt() - 1;
        } while (val < 0 || val > 5);
        return val;
    }

    static boolean emptyCell(char[][] field, int x, int y) {
        return field[x][y] != '-';
    }
}
