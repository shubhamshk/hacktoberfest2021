import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PuzzleGame {

    private static final int SIZE = 3; // Size of the puzzle (3x3 in this case)
    private static final Integer[][] puzzle = new Integer[SIZE][SIZE];
    private static final Integer[][] solvedPuzzle = new Integer[SIZE][SIZE];

    public static void main(String[] args) {
        initializePuzzle();
        shufflePuzzle();

        System.out.println("Welcome to the Puzzle Game!");
        printPuzzle();

        while (!isPuzzleSolved()) {
            moveTile();
            printPuzzle();
        }

        System.out.println("Congratulations! You solved the puzzle!");
    }

    private static void initializePuzzle() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0); // 0 represents the blank space

        Collections.shuffle(numbers);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                puzzle[i][j] = numbers.get(i * SIZE + j);
                solvedPuzzle[i][j] = i * SIZE + j + 1;
            }
        }

        solvedPuzzle[SIZE - 1][SIZE - 1] = 0;
    }

    private static void shufflePuzzle() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0); // 0 represents the blank space

        Collections.shuffle(numbers);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                puzzle[i][j] = numbers.get(i * SIZE + j);
            }
        }
    }

    private static void printPuzzle() {
        System.out.println("-------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(puzzle[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean isPuzzleSolved() {
        return Arrays.deepEquals(puzzle, solvedPuzzle);
    }

    private static void moveTile() {
        Scanner scanner = new Scanner(System.in);

        int tileToMove;
        do {
            System.out.print("Enter the number of the tile to move (0 to quit): ");
            tileToMove = scanner.nextInt();
        } while (tileToMove < 0 || tileToMove > 8);

        if (tileToMove == 0) {
            System.out.println("Quitting the game. Goodbye!");
            System.exit(0);
        }

        int[] tilePosition = findTile(tileToMove);
        int emptyPosition[] = findTile(0);

        if (isAdjacent(tilePosition, emptyPosition)) {
            puzzle[emptyPosition[0]][emptyPosition[1]] = puzzle[tilePosition[0]][tilePosition[1]];
            puzzle[tilePosition[0]][tilePosition[1]] = 0;
        } else {
            System.out.println("Invalid move. Tiles must be adjacent.");
        }
    }

    private static int[] findTile(int tile) {
        int[] position = new int[2];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (puzzle[i][j] == tile) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }

        return null; // Tile not found (should not happen in a valid puzzle)
    }

    private static boolean isAdjacent(int[] position1, int[] position2) {
        int rowDiff = Math.abs(position1[0] - position2[0]);
        int colDiff = Math.abs(position1[1] - position2[1]);

        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1);
    }
}
