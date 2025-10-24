package graph.traversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NumberOfIslandsInGraph {
    
        static class Pair {
            int first;
            int second;
    
            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }
    
        public int getNumOfIslands(int[][] grid, boolean isBFS) {
    
            int row = grid.length;
            int col = grid[0].length;
    
            boolean[][] visited = new boolean[row][col];
            int isLandCounter = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1 && !visited[i][j]) {
                        isLandCounter++;
                        if (isBFS) {
                            doBFS(grid, visited, i, j);
                        } else {
                            doDFS(grid, visited, i, j);
                        }
                    }
                }
            }
            return isLandCounter;
        }
    
        private void doDFS(int[][] grid, boolean[][] visited, int row, int col) {
            visited[row][col] = true;
            Stack<Pair> gridTracker = new Stack<>();
            gridTracker.add(new Pair(row, col));

            while(!gridTracker.isEmpty()){
                int ro = gridTracker.peek().first;
                int co = gridTracker.peek().second;

                gridTracker.pop();

                for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
                    for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                        int nextRow = ro + deltaRow;
                        int nextCol = co + deltaCol;
                        if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length
                                && grid[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                            visited[nextRow][nextCol] = true;
                            gridTracker.add(new Pair(nextRow, nextCol));
                        }
                    }
                }
            }
        }
    
        private void doBFS(int[][] grid, boolean[][] visited, int row, int col) {
            visited[row][col] = true;
            Queue<Pair> gridTracker = new LinkedList<>();
            gridTracker.add(new Pair(row, col));
    
            while (!gridTracker.isEmpty()) {
                int ro = gridTracker.peek().first;
                int co = gridTracker.peek().second;
                gridTracker.poll();
    
                for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
                    for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                        int nextRow = ro + deltaRow;
                        int nextCol = co + deltaCol;
                        if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length
                                && grid[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                            visited[nextRow][nextCol] = true;
                            gridTracker.add(new Pair(nextRow, nextCol));
                        }
                    }
                }
            }
        }
    
    
        public static void main(String[] args) {
            int[][] grid = new int[][]{
                    {0, 1, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 1, 0},
                    {1, 0, 0, 0},
                    {0, 0, 0, 1}
            };

            NumberOfIslandsInGraph numberOfIslandsInGraph = new NumberOfIslandsInGraph();
            System.out.println("number of an island is via DFS :" + numberOfIslandsInGraph.getNumOfIslands(grid, false));
            System.out.println("number of an island is via BFS :" + numberOfIslandsInGraph.getNumOfIslands(grid, true));
        }
    
    
    }