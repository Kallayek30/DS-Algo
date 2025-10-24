package graph.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class DistanceOfNearestCellHavingOne {

    static class DistanceTracker {
        int i;
        int j;
        int distance;

        public DistanceTracker(int i, int j,int distance) {
            this.i = i;
            this.j = j;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 0, 1}};
        DistanceOfNearestCellHavingOne nearestCellHavingOne =
                new DistanceOfNearestCellHavingOne();
        int[][] distance = nearestCellHavingOne.getDistance(grid);
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                System.out.print(distance[i][j] + "\t");
            }
            System.out.println();
        }

    }

    public int[][] getDistance(int[][] grid) {

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] distance = new int[grid.length][grid[0].length];

        Queue<DistanceTracker> distanceTrackers = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    distanceTrackers.add(new DistanceTracker(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int[] deltaRow = {-1, 1, 0, 0};
        int[] deltaCol = {0, 0, -1, 1};
        while (!distanceTrackers.isEmpty()) {
            DistanceTracker distanceTracker = distanceTrackers.poll();
            for (int i = 0; i < deltaCol.length; i++) {
                int newRow = distanceTracker.i + deltaRow[i];
                int newCol = distanceTracker.j + deltaCol[i];
                int dist = distanceTracker.distance;
                distance[distanceTracker.i][distanceTracker.j] = dist;
                if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                        && !visited[newRow][newCol]) {
                    System.out.println("Visiting :" + newRow + "_"+ newCol);
                    distanceTrackers.add(new DistanceTracker(newRow, newCol, 1 + distanceTracker.distance));
                    visited[newRow][newCol] = true;
                }
            }
        }
        return distance;
    }

}
