package graph.traversal;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island. Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).
 * <p>
 * Example 1:
 * <p>
 * Input:
 * grid[][] = [[1, 1, 0, 0, 0],
 * [1, 1, 0, 0, 0],
 * [0, 0, 0, 1, 1],
 * [0, 0, 0, 1, 1]]
 * Output: 1
 * Explanation:
 * grid[][] = [[1, 1, 0, 0, 0],
 * [1, 1, 0, 0, 0],
 * [0, 0, 0, 1, 1],
 * [0, 0, 0, 1, 1]]
 * Same colored islands are equal. We have 2 equal islands, so we have only 1 distinct island.
 * <p>
 * Example 2:
 * <p>
 * Input:
 * grid[][] = [[1, 1, 0, 1, 1],
 * [1, 0, 0, 0, 0],
 * [0, 0, 0, 0, 1],
 * [1, 1, 0, 1, 1]]
 * Output: 3
 * Explanation:
 * grid[][] = [[1, 1, 0, 1, 1],
 * [1, 0, 0, 0, 0],
 * [0, 0, 0, 0, 1],
 * [1, 1, 0, 1, 1]]
 * Same colored islands are equal.
 * We have 4 islands, but 2 of them
 * are equal, So we have 3 distinct islands.
 */
public class NumberOfDistinctIslands {

    public static void main(String[] args) {

        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}};

        NumberOfDistinctIslands distinctIslands = new NumberOfDistinctIslands();
        System.out.println("number of distinct islands: " +distinctIslands.getNumberOfDistinctIslandsViaDFS(grid));
    }

    public int getNumberOfDistinctIslandsViaDFS(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Set<List<String>> uniquePatternTracker = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    List<String> distances = new ArrayList<>();
                    doDFS(i, j, grid, visited, distances, i, j);
                    uniquePatternTracker.add(distances);
                }
            }
        }
        return uniquePatternTracker.size();
    }

    private void doDFS(int row, int col, int[][] grid, boolean[][] visited, List<String> distances, int newRow, int newCol) {
        visited[row][col] = true;
        distances.add(Math.abs(row - newRow) + "_" + Math.abs(col - newCol));

        int[] deltaRow = {-1, 1, 0, 0};
        int[] deltaCol = {0, 0, -1, 1};

        for (int i = 0; i < deltaRow.length; i++) {
            int nextRow = row + deltaRow[i];
            int nextCol = col + deltaCol[i];

            if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length
                    && !visited[nextRow][nextCol] && grid[nextRow][nextCol] == 1) {
                doDFS(nextRow, nextCol, grid, visited, distances, row, col);
            }
        }
    }
}
