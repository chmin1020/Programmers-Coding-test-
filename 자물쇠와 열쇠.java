import java.util.*;

class Solution {   
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;
        int holeCnt = 0;
            
        for(int[] row: lock)
            for(int part: row)
                if(part == 0) holeCnt++;
        
        int[][] newLock = new int[3 * n][3 * n];
        for(int[] row: newLock)
            Arrays.fill(row, 0);
        
        for(int i = n; i < 2 * n; i++)
            for(int j = n; j < 2 * n; j++)
                if(lock[i - n][j - n] == 1)
                    newLock[i][j]++;
        
        int[][][] keys = new int[4][m][m];
        keys[0] = key;
        for(int a = 1; a < 4; a++){
            keys[a] = new int[m][m];
            
            for(int i = 0; i < m; i++)
                for(int j = 0; j < m; j++)
                    keys[a][i][j] = keys[a - 1][m - j - 1][i];        
        }
        
        for(int j = 0; j < 3 * n; j++){
                for(int k = 0; k < 3* n; k++)
                    System.out.print(newLock[j][k]);
                System.out.println();
            }
        System.out.println();
        
        for(int i = 0 ; i < 4; i++){
            for(int j = 0; j < m; j++){
                for(int k = 0; k < m; k++)
                    System.out.print(keys[i][j][k]);
                System.out.println();
            }
            System.out.println();
        }
        
        
        for(int i = 0; i < 3 * n - m; i++){            
            for(int j = 0; j < 3 * n - m; j++){
                for(int a = 0; a < 4; a++){
                    int cnt = 0;
                    boolean noMore = false;
                    for(int x = 0; x < m; x++){
                        for(int y = 0; y < m; y++){
                            if(n <= i + x && 2 * n > i + x &&
                               n <= j + y && 2 * n > j + y){
                                if(keys[a][x][y] == newLock[i + x][j + y]){
                                    noMore = true;
                                    break;
                                }
                                if(keys[a][x][y] == 1)
                                    cnt++;
                            }
                        }
                        if(noMore) break;
                    }
                    if(!noMore && cnt == holeCnt)
                        return true;
                }
            }
        }
        return false;
    }
}
