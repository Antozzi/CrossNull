import java.util.Random;
import java.util.Scanner;

public class TicTacToe_default {

    public static void main(String[] args) {
        playGame();
    }

    static void playGame() {
        char[][] field = createField();

        drawField(field);

        while (true) {
            if (!checkNextPlayerMove(field)) {
                return;
            }

            if (!checkNextAIMove(field)) {
                return;
            }
        }
    }

    static boolean checkNextAIMove(char[][] field) {
        doAIMove(field);
        drawField(field);
        return isNextMoveAvailable(field, 'O', "Sorry, AI is winner!");
    }

    static boolean checkNextPlayerMove(char[][] field) {
        doPlayerMove(field);
        drawField(field);
        return isNextMoveAvailable(field, 'X', "Congrats!!! You are winner!");
    }

    static boolean isNextMoveAvailable(char[][] field, char sign, String winMessage) {
        if (isDraw(field)) {
            System.out.println("There is draw detected. Finish!");
            return false;
        }
        if (isWin(field, sign)) {
            System.out.println(winMessage);
            return false;
        }
        return true;
    }

    static boolean isWin(char[][] field, char sign) {
        for (int i = 0; i < field.length; i++) {
            if (field[i][0] == sign && field[i][1] == sign && field[i][2] == sign) {
                return true;
            }
        }

        for (int i = 0; i < field.length; i++) {
            if (field[0][i] == sign && field[1][i] == sign && field[2][i] == sign) {
                return true;
            }
        }

        if (field[0][0] == sign && field[1][1] == sign && field[2][2] == sign) {
            return true;
        }
        if (field[0][2] == sign && field[1][1] == sign && field[2][0] == sign) {
            return true;
        }

        return false;
    }

    static boolean isDraw(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    static void doAIMove(char[][] field) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(field.length);
            y = random.nextInt(field.length);
        } while (isCellFree(field, x, y));
        field[x][y] = 'O';
    }

    static void doPlayerMove(char[][] field) {
        Scanner scanner = new Scanner(System.in);
        int x, y;

        do {
            x = checkCoordinateRange(scanner, 'X');
            y = checkCoordinateRange(scanner, 'Y');
        } while (isCellFree(field, x, y));

        field[x][y] = 'X';
    }

    static int checkCoordinateRange(Scanner scanner, char coordName) {
        int val;
        do {
            System.out.printf("Please input %s-coordinate in range [1-3]...%n", coordName);
            val = scanner.nextInt() - 1;
        } while (val < 0 || val > 2);
        return val;
    }

    static boolean isCellFree(char[][] field, int x, int y) {
        return field[x][y] != '-';
    }

    static char[][] createField() {
        return new char[][]{
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };
    }

    static void drawField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
