package graph.traversal;

/*
Given a matrix mat[ ][ ] where each cell contains either 'O' or 'X'. You need to replace all 'O's that are completely surrounded by 'X's with 'X'.
An 'O' is considered surrounded if it cannot reach the boundary of the matrix by moving up, down, left, or right through adjacent 'O' cells.

        Examples:

Input: mat[][] =   [ ['X', 'O', 'X', 'X', 'X', 'X'],
        ['X', 'O', 'X', 'X', 'O', 'X'],
        ['X', 'X', 'X', 'O', 'O', 'X'],
        ['O', 'X', 'X', 'X', 'X', 'X'],
        ['X', 'X', 'X', 'O', 'X', 'O'],
        ['O', 'O', 'X', 'O', 'O', 'O'] ];

Output: mat[][] =   [['X', 'O', 'X', 'X', 'X', 'X'],
        ['X', 'O', 'X', 'X', 'X', 'X'],
        ['X', 'X', 'X', 'X', 'X', 'X'],
        ['O', 'X', 'X', 'X', 'X', 'X'],
        ['X', 'X', 'X', 'O', 'X', 'O'],
        ['O', 'O', 'X', 'O', 'O', 'O']];

Explanation: The 'O's at positions (1,4), (2,3), and (2,4) are enclosed by 'X' on all sides, so they get replaced with 'X'. Boundary connected 'O's remain unchanged.

        Input: mat[][] =    [['X', 'X', 'X', 'X']
        ['X', 'O', 'X', 'X']
        ['X', 'O', 'O', 'X']
        ['X', 'O', 'X', 'X']
        ['X', 'X', 'O', 'O'] ];

Output: mat[][] =  [ ['X', 'X', 'X', 'X']
        ['X', 'X', 'X', 'X']
        ['X', 'X', 'X', 'X']
        ['X', 'X', 'X', 'X']
        ['X', 'X', 'O', 'O']];

Explanation: The cluster of 'O's at (1,1), (2,1), (2,2), (3,1) is fully surrounded by 'X' and is converted to 'X'; the 'O's at (4,2) and (4,3) remain because they touch the matrix boundary.

*/


public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'X', 'O', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'O', 'O', 'X'},
                {'O', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'O', 'X', 'O'},
                {'O', 'O', 'X', 'O', 'O', 'O'}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }

        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.fillWithX(grid);
        System.out.println("=======================");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }

    }

    public void fillWithX(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int[] deltaRow = {-1, 1, 0, 0};
        int[] deltaCol = {0, 0, -1, 1};

        for (int i = 0; i < rows; i++) {
            if (!visited[0][i] && grid[0][i] == 'O') {
                doDFS(0, i, grid, visited, deltaRow, deltaCol);
            }
            if (!visited[i][cols - 1] && grid[i][cols - 1] == 'O') {
                doDFS(i, cols - 1, grid, visited, deltaRow, deltaCol);
            }
        }

        for (int j = 0; j < cols; j++) {
            if (!visited[j][0] && grid[j][0] == 'O') {
                doDFS(j, 0, grid, visited, deltaRow, deltaCol);
            }

            if (!visited[j][cols - 1] && grid[j][cols - 1] == 'O') {
                doDFS(j, cols - 1, grid, visited, deltaRow, deltaCol);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 'O') {
                    grid[i][j] = 'X';
                }
            }
        }


    }

    private void doDFS(int row, int col, char[][] grid, boolean[][] visited, int[] deltaRow, int[] deltaCol) {

        visited[row][col] = true;
        for (int index = 0; index < deltaRow.length; index++) {
            int newRow = row + deltaRow[index];
            int newCol = col + deltaCol[index];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length &&
                    !visited[newRow][newCol] && grid[newRow][col] == 'O') {
                doDFS(newRow, newCol, grid, visited, deltaRow, deltaCol);
            }
        }

    }

}
