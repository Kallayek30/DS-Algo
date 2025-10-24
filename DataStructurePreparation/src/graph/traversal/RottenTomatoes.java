package graph.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class RottenTomatoes {

    static class Tracker {
        int row;
        int col;
        int time;

        public Tracker(int col, int row, int time) {
            this.col = col;
            this.row = row;
            this.time = time;
        }
    }

    public static void main(String[] args) {

        int[][] grid = new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        RottenTomatoes rottenTomatoes = new RottenTomatoes();
        System.out.println("time required to rot the tomatoes in the grid : " + rottenTomatoes.findTimeToRotAllTomatoes(grid));

    }

    public int findTimeToRotAllTomatoes(int[][] grid) {

        int[][] visited = new int[grid.length][grid[0].length];
        Queue<Tracker> rottenTomatoTracker = new LinkedList<>();
        int freshTomatoes = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    rottenTomatoTracker.add(new Tracker(i, j, 0));
                    visited[i][j] = 2;
                } else if (grid[i][j] == 1) {
                    freshTomatoes++;
                }
            }
        }

        int cnt = 0;
        int time = 0;
        int[] deltaRow = {-1, 1, 0, 0};
        int[] deltaCol = {0, 0, -1, 1};
        while (!rottenTomatoTracker.isEmpty()) {
            int row = rottenTomatoTracker.peek().row;
            int col = rottenTomatoTracker.peek().col;
            int tm = rottenTomatoTracker.peek().time;
            time = Math.max(tm, time);
            rottenTomatoTracker.poll();
            for (int index = 0; index < deltaRow.length; index++) {
                int newDeltaRow = row + deltaRow[index];
                int newDeltaCol = col + deltaCol[index];
                //̵System.out.println( newDeltaRow + "_" + newDeltaCol);
                if (newDeltaRow >= 0 && newDeltaRow < grid.length && newDeltaCol >= 0 && newDeltaCol < grid[0].length
                        && grid[newDeltaRow][newDeltaCol] == 1 && visited[newDeltaRow][newDeltaCol] != 2) {
                    rottenTomatoTracker.add(new Tracker(newDeltaRow, newDeltaCol, time + 1));
                    visited[newDeltaRow][newDeltaCol] = 2;
                    cnt++;
                }
            }
        }
        if (cnt != freshTomatoes) {
            System.out.println("Some tomatoes were not rotten !!!");
            return -1;
        }
        return time;
    }


}
