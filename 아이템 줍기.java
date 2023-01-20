import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static class Pos{
        int x, y;

        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    //각 경로의 스텝 정보
    private static class Step {
        Pos pos;
        int time;

        Step(int x, int y, int time){
            pos = new Pos(x, y);
            this.time = time;
        }
    }


    //방문 여부
    private static final boolean[][] visited = new boolean[51][51];

    //방향 상수
    private static final int[] directX = {0, 0, -1, 1};
    private static final int[] directY = {1, -1, 0, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        //50 x 50 맵 (상, 하, 좌, 우)
        boolean[][][] graph = new boolean[51][51][4];


        //----그래프 설정----

        //범위를 정의하고 가능한 경로 표시하기
        for(int[] each: rectangle) {
            int xStart = each[0];
            int xEnd = each[2];
            int yStart = each[1];
            int yEnd = each[3];

            //가로 변 2개 설정
            for(int i = xStart; i < xEnd; i++){
                graph[i][yStart][3] = true;
                graph[i][yEnd][3] = true;
                graph[i + 1][yStart][2] = true;
                graph[i + 1][yEnd][2] = true;
            }

            //세로 변 2개 설정
            for(int i = yStart; i < yEnd; i++){
                graph[xStart][i][0] = true;
                graph[xEnd][i][0] = true;
                graph[xStart][i + 1][1] = true;
                graph[xEnd][i + 1][1] = true;
            }
        }

        //겹쳐서 안되는 경우들도 확인
        for(int[] each: rectangle){
            int xStart = each[0];
            int xEnd = each[2];
            int yStart = each[1];
            int yEnd = each[3];

            //안되는 부분 설정 (사각형 내부)
            for(int i = xStart + 1; i < xEnd; i++){
                graph[i][yStart][0] = false;
                graph[i][yEnd][1] = false;
            }

            for(int i = yStart + 1; i < yEnd; i++){
                graph[xStart][i][3] = false;
                graph[xEnd][i][2] = false;
            }

            for(int i = xStart + 1; i < xEnd; i++){
                for(int j = yStart + 1; j < yEnd; j++)
                    Arrays.fill(graph[i][j], false);
            }
        }


        //----bfs----

        //시작 위치 설정
        Queue<Step> qu = new LinkedList<>();
        qu.add(new Step(characterX, characterY, 0));
        visited[characterX][characterY] = true;

        while (!qu.isEmpty()) {
            Step cur = qu.poll();

            if (cur.pos.x == itemX && cur.pos.y == itemY)
                return cur.time;

            //상하좌우 가능성 확인
            for (int i = 0; i < 4; i++) {
                if (graph[cur.pos.x][cur.pos.y][i]) {
                    int nx = cur.pos.x + directX[i];
                    int ny = cur.pos.y + directY[i];

                    if (!visited[nx][ny]) {
                        qu.add(new Step(nx, ny, cur.time + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}
