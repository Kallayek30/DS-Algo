package graph.traversal;


/*You are given an n x m binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Find the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

        Example 1:

Input:
grid[][] = {{0, 0, 0, 0},
        {1, 0, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}}
Output:
        3
Explanation:
        0 0 0 0
        1 0 1 0
        0 1 1 0
        0 0 0 0
The highlighted cells represents the land cells.
        Example 2:

Input:
grid[][] = {{0, 0, 0, 1},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 1},
        {0, 1, 1, 0}}
Output:
        4
Explanation:
        0 0 0 1
        0 1 1 0
        0 1 1 0
        0 0 0 1
        0 1 1 0
The highlighted cells represents the land cells.
        */


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NumberOfEnclaves {

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {

        int [][] matrix = new int [][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };

        int [][] matrix1 ={
                {0, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 1, 1, 0}};


        NumberOfEnclaves  numberOfEnclaves = new NumberOfEnclaves();
        System.out.println("Number of enclaves via BFS : " +numberOfEnclaves.numberOfEnclavesViaBFS(matrix));
        System.out.println("Number of enclaves via DFS : " +numberOfEnclaves.numberOfEnclavesViaDFS(matrix1));
    }

    public int numberOfEnclavesViaBFS(int [][] grid){

        int rows = grid.length;;
        int cols = grid[0].length;

        boolean [][] visited = new boolean[grid.length][grid[0].length];

        Queue<Pair> onesCoOrdinates = new LinkedList<>();
        for(int i = 0 ; i < cols; i++){
            if(!visited[0][i] && grid[0][i] == 1){
                visited[0][i] = true;
                onesCoOrdinates.add(new Pair(0, i));
            }
            if(!visited[rows -1][i] && grid[rows -1][i] == 1){
                visited[rows -1][i] = true;
                onesCoOrdinates.add(new Pair(rows -1 , i));
            }
        }

        for(int j = 0 ; j < rows; j++){
            if(!visited[j][0] && grid[j][0] == 1){
                visited[j][0] = true;
                onesCoOrdinates.add(new Pair(j, 0));
            }
            if(!visited[j][cols -1] && grid[j][cols -1] == 1){
                visited[j][cols -1] = true;
                onesCoOrdinates.add(new Pair(j, cols -1));
            }
        }

        int[] deltaRow = {-1, 1, 0, 0};
        int[] deltaCol = {0, 0, -1, 1};

        while(!onesCoOrdinates.isEmpty()){
            Pair coOrdinate = onesCoOrdinates.poll();

            for(int i = 0 ; i < deltaRow.length; i++){
                int newRow = coOrdinate.row + deltaRow[i];
                int newCol = coOrdinate.col + deltaCol[i];

                if(newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                        && !visited[newRow][newCol] && grid[newRow][newCol] == 1){
                  visited[newRow][newCol] = true;
                  onesCoOrdinates.add(new Pair(newRow, newCol));
                }
            }
        }

        int enclaveCounter = 0;
        for(int i = 0 ; i< rows ; i++){
            for (int j = 0; j< cols; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    enclaveCounter++;
                }
            }
        }
        return enclaveCounter;
    }

    public int numberOfEnclavesViaDFS(int [][] grid){

        int rows = grid.length;;
        int cols = grid[0].length;

        boolean [][] visited = new boolean[grid.length][grid[0].length];

        Stack<Pair> onesCoOrdinates = new Stack<>();
        int[] deltaRow = {-1, 1, 0, 0};
        int[] deltaCol = {0, 0, -1, 1};
        for(int i = 0 ; i < cols; i++){
            if(!visited[0][i] && grid[0][i] == 1){
                visited[0][i] = true;
                onesCoOrdinates.add(new Pair(0, i));
                doDFS(grid, onesCoOrdinates, visited, deltaRow, deltaCol);
            }
            if(!visited[rows -1][i] && grid[rows -1][i] == 1){
                visited[rows -1][i] = true;
                onesCoOrdinates.add(new Pair(rows -1 , i));
                doDFS(grid, onesCoOrdinates, visited, deltaRow, deltaCol);
            }
        }

        for(int j = 0 ; j < rows; j++){
            if(!visited[j][0] && grid[j][0] == 1){
                visited[j][0] = true;
                onesCoOrdinates.add(new Pair(j, 0));
                doDFS(grid, onesCoOrdinates, visited, deltaRow, deltaCol);
            }
            if(!visited[j][cols -1] && grid[j][cols -1] == 1){
                visited[j][cols -1] = true;
                onesCoOrdinates.add(new Pair(j, cols -1));
                doDFS(grid, onesCoOrdinates, visited, deltaRow, deltaCol);
            }
        }

        int enclaveCounter = 0;
        for(int i = 0 ; i< rows ; i++){
            for (int j = 0; j< cols; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    enclaveCounter++;
                }
            }
        }
        return enclaveCounter;
    }

    private void doDFS(int[][] grid, Stack<Pair> onesCoOrdinates, boolean[][] visited, int[] deltaRow, int[] deltaCol) {

        while (!onesCoOrdinates.isEmpty()) {
            Pair coOrdinate = onesCoOrdinates.pop();

            for (int i = 0; i < deltaRow.length; i++) {
                int newRow = coOrdinate.row + deltaRow[i];
                int newCol = coOrdinate.col + deltaCol[i];

                if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                        && !visited[newRow][newCol] && grid[newRow][newCol] == 1) {
                    visited[newRow][newCol] = true;
                    onesCoOrdinates.add(new Pair(newRow, newCol));
                }
            }
        }
    }
}
