package graph.traversal;

public class FloodFill {

    public static void main(String[] args) {

        int[][] grid = new int[][]{
                {1, 1, 1, 1},
                {2, 2, 0, 2},
                {2, 2, 2, 2}
        };

        FloodFill floodFill = new FloodFill();
        int[][] result = floodFill.doFloodFill(grid, 2, 0, 3, true);

        for (int[] array : result) {
            for (int val : array) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    public int[][] doFloodFill(int[][] grid, int sr, int sc, int newColor, boolean isDFS) {
        int initColor = grid[sr][sc];
        int[][] ans = grid;
        int[] delRow = {0, 0, -1, 1};
        int[] delCol = {-1, 1, 0, 0};
        if (isDFS) {
            doFloodFillViaDFS(grid, ans, delRow, delCol, sr, sc, initColor, newColor);
        }
        return ans;
    }

    private void doFloodFillViaDFS(int[][] grid, int[][] ans, int[] delRow, int[] delCol, int sr, int sc, int initColor, int newColor) {

        ans[sr][sc] = newColor;

        for (int i = 0; i < delRow.length; i++) {
            int newRow = sr + delRow[i];
            int newCol = sc + delCol[i];

            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length &&
                    grid[newRow][newCol] == initColor && ans[newRow][newCol] != newColor) {
                doFloodFillViaDFS(grid, ans, delRow, delCol, newRow, newCol, initColor, newColor);
            }
        }

    }


}
