package com.juk.algo.A0723;

//Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
//Note: You can only move either down or right at any point in time.
//
//Example:
//Input:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//Output: 7
//Explanation: Because the path 1¡æ3¡æ1¡æ1¡æ1 minimizes the sum.
public class MinPathSum {
    public static void main(String[] args) {
        int grid[][] = {
          {1,3,1},
          {1,5,1},
          {4,2,1}
        };
        //int grid[][] = {{9,1,4,8}};
        
        int minPath = (new MinPathSum()).minPathSum(grid);
        System.out.println("minPath:" + minPath);
    }
    
    public int minPathSum(int[][] grid) {    
        int m = grid.length;
        int n = grid[0].length;
            
        for(int y=0; y<m; y++) {
            for(int x=0; x<n; x++) {
                if(y==0 && x>=1) {
                    grid[0][x] = grid[0][x]+grid[0][x-1];
                }
                if(x==0 && y>=1) {
                    grid[y][0] = grid[y][0]+grid[y-1][0]; 
                }      
                if(x==0) continue;
                if(y==0) continue;
                
                grid[y][x] = grid[y][x]+ Math.min(grid[y-1][x], grid[y][x-1]);
                //System.out.println("a grid["+y+"]["+x+"]:" + grid[y][x]);                     
            }
        }
                
        return grid[m-1][n-1];
    }
}
