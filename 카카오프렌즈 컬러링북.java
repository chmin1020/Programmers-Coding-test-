import java.util.LinkedList;
import java.util.Queue;
import static java.lang.Math.max;

class Solution {
    //이동 경로
    private static final int[] directX = {-1, 1, 0, 0};
    private static final int[] directY = {0, 0, -1, 1};

    //위치 데이터 클래스
    private static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        boolean[][] visited = new boolean[m][n];

        int areaCnt = 0;
        int maxAreaSize = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    maxAreaSize = max(maxAreaSize, measureCurrentArea(picture, visited, new Pos(i, j)));
                    areaCnt++;
                }
            }
        }

        return new int[]{areaCnt, maxAreaSize};
    }

    //-- 각 구역 넓이 측정 --//
    private int measureCurrentArea(int[][] picture, boolean[][] visited, Pos start) {
        int size = 0;
        int areaNum = picture[start.x][start.y];
        Queue<Pos> qu = new LinkedList<>();
        qu.add(start);
        visited[start.x][start.y] = true;
        
        //bfs
        while (!qu.isEmpty()){
            Pos cur = qu.poll();
            size++;
            
            //4방향
            for(int d = 0; d < 4; d++){
                int nx = cur.x + directX[d];
                int ny = cur.y + directY[d];
                
                if(nx < 0 || nx >= picture.length || ny < 0 || ny >= picture[0].length)
                    continue;
                if(visited[nx][ny] || picture[nx][ny] != areaNum)
                    continue;
                
                qu.add(new Pos(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return size;
    }
}
