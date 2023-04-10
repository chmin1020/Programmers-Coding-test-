import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static class Pos{
        int x, y, step;

        Pos(int x, int y, int step){
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    private final int[] directX = {-1, 1, 0, 0};
    private final int[] directY = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        return bfs(maps, maps.length, maps[0].length);
    }

    private int bfs(int[][] maps, int n, int m){
        Queue<Pos> qu = new LinkedList<Pos>();
        boolean[][] visited = new boolean[n][m];
        qu.add(new Pos(0, 0, 1));
        visited[0][0] = true;

        while (!qu.isEmpty()){
            Pos cur = qu.poll();

            if(cur.x == n - 1 && cur.y == m - 1)
                return cur.step;

            //4방향 탐색
            for(int d = 0; d < 4; d++){
                int nx = cur.x + directX[d];
                int ny = cur.y + directY[d];

                //범위 벗어남
                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                //못 감
                if(visited[nx][ny] || maps[nx][ny] == 0)
                    continue;

                visited[nx][ny] = true;
                qu.add(new Pos(nx, ny, cur.step + 1));
            }
        }
        return -1;
    }
}
