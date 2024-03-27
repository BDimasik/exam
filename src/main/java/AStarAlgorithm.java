import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarAlgorithm {
    private static final int ROWS = 5;
    private static final int COLS = 5;

    private static final int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0}
    };

    private static final int[][] directions = {
            {-1, 0}, // Up
            {1, 0},  // Down
            {0, -1}, // Left
            {0, 1}   // Right
    };

    static class Cell {
        int row, col;
        int f, g, h;

        Cell parent;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static boolean isValid(int row, int col) {
        return (row >= 0 && row < ROWS && col >= 0 && col < COLS);
    }

    private static boolean isUnblocked(int row, int col) {
        return (grid[row][col] == 0);
    }

    private static boolean isDestination(int row, int col, Cell dest) {
        return (row == dest.row && col == dest.col);
    }

    private static int calculateHValue(int row, int col, Cell dest) {
        // Using Manhattan distance heuristic
        return Math.abs(row - dest.row) + Math.abs(col - dest.col);
    }

    private static void tracePath(Cell current) {
        System.out.println("Path:");
        while (current != null) {
            System.out.print("[" + current.row + ", " + current.col + "] -> ");
            current = current.parent;
        }
        System.out.println("End");
    }

    public static void aStarSearch(Cell src, Cell dest) {
        if (!isValid(src.row, src.col) || !isValid(dest.row, dest.col)) {
            System.out.println("Invalid source or destination");
            return;
        }

        if (!isUnblocked(src.row, src.col) || !isUnblocked(dest.row, dest.col)) {
            System.out.println("Source or destination is blocked");
            return;
        }

        if (isDestination(src.row, src.col, dest)) {
            System.out.println("Source is the destination");
            return;
        }

        boolean[][] closedList = new boolean[ROWS][COLS];

        PriorityQueue<Cell> pq = new PriorityQueue<>(new Comparator<Cell>() {
            public int compare(Cell a, Cell b) {
                return a.f - b.f;
            }
        });

        src.g = 0;
        src.h = calculateHValue(src.row, src.col, dest);
        src.f = src.g + src.h;

        pq.offer(src);

        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            closedList[current.row][current.col] = true;

            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                if (isValid(newRow, newCol) && isUnblocked(newRow, newCol) && !closedList[newRow][newCol]) {
                    Cell successor = new Cell(newRow, newCol);
                    successor.g = current.g + 1;
                    successor.h = calculateHValue(newRow, newCol, dest);
                    successor.f = successor.g + successor.h;
                    successor.parent = current;

                    if (isDestination(newRow, newCol, dest)) {
                        System.out.println("Destination is reached");
                        tracePath(successor);
                        return;
                    }

                    pq.offer(successor);
                }
            }
        }

        System.out.println("Cannot reach destination");
    }

    public static void main(String[] args) {
        Cell src = new Cell(0, 0);
        Cell dest = new Cell(4, 4);
        aStarSearch(src, dest);
    }
}
