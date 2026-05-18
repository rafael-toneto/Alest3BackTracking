import java.util.Arrays;
import java.util.Optional;

public class NQueensSolver {
    public Optional<int[]> solve(int n) {
        if (n < 2) {
            throw new IllegalArgumentException("O valor de n deve ser maior ou igual a 2.");
        }

        int[] queenColumns = new int[n];
        Arrays.fill(queenColumns, -1);

        boolean[] usedColumns = new boolean[n];
        boolean[] usedMainDiagonals = new boolean[2 * n - 1];
        boolean[] usedSecondaryDiagonals = new boolean[2 * n - 1];

        boolean solved = placeQueen(
                0,
                n,
                queenColumns,
                usedColumns,
                usedMainDiagonals,
                usedSecondaryDiagonals
        );

        if (!solved) {
            return Optional.empty();
        }

        return Optional.of(queenColumns);
    }

    private boolean placeQueen(
            int row,
            int n,
            int[] queenColumns,
            boolean[] usedColumns,
            boolean[] usedMainDiagonals,
            boolean[] usedSecondaryDiagonals
    ) {
        if (row == n) {
            return true;
        }

        for (int column = 0; column < n; column++) {
            int mainDiagonal = row - column + n - 1;
            int secondaryDiagonal = row + column;

            if (usedColumns[column]
                    || usedMainDiagonals[mainDiagonal]
                    || usedSecondaryDiagonals[secondaryDiagonal]) {
                continue;
            }

            queenColumns[row] = column;
            usedColumns[column] = true;
            usedMainDiagonals[mainDiagonal] = true;
            usedSecondaryDiagonals[secondaryDiagonal] = true;

            if (placeQueen(
                    row + 1,
                    n,
                    queenColumns,
                    usedColumns,
                    usedMainDiagonals,
                    usedSecondaryDiagonals
            )) {
                return true;
            }

            queenColumns[row] = -1;
            usedColumns[column] = false;
            usedMainDiagonals[mainDiagonal] = false;
            usedSecondaryDiagonals[secondaryDiagonal] = false;
        }

        return false;
    }

    public String formatBoard(int[] queenColumns) {
        StringBuilder board = new StringBuilder();

        for (int row = 0; row < queenColumns.length; row++) {
            for (int column = 0; column < queenColumns.length; column++) {
                board.append(queenColumns[row] == column ? "Q " : ". ");
            }
            board.append(System.lineSeparator());
        }

        return board.toString();
    }

    public String complexity() {
        return "Tempo: O(n!) no pior caso; espaco: O(n), alem do tabuleiro de saida.";
    }
}
