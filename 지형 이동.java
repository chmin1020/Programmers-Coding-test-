import java.util.*;

class Pos{
    int x, y;
	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}
	
class Edge implements Comparable<Edge>{
	int from, to, len;
	Edge(int from, int to, int len){
		this.from = from;
		this.to = to;
		this.len = len;
	}
	@Override
	public int compareTo(Edge o) {
        if(this.len == o.len){
            return (this.from > o.from)? 1:-1;
        }
		return (this.len > o.len)? 1:-1;
	}
}

class Solution{
    public int[] parent;
    public boolean union(int a, int b) {
        int ap = find(a), bp = find(b);
        
        if(ap == bp) return false;
        
        if(ap < bp)
            parent[bp] = ap;
        else
            parent[ap] = bp;
        return true;
    }

    public int find(int a) {
        if (a == parent[a]){
            return a;
          }
          else {
            return parent[a] = find(parent[a]);
          }
    }

    public int solution(int[][] land, int height) {
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};
        int[][] group = new int[land.length][land.length];
        for(int i = 0; i < land.length; i++) {
            Arrays.fill(group[i], -1);
        }

        Queue<Pos> qu = new LinkedList<Pos>();

        int gNum = 0;
        for(int i = 0; i < land.length; i++) {
            for(int j = 0; j < land.length; j++) {
                if(group[i][j] == - 1) {
                    qu.add(new Pos(i, j));
                    group[i][j] = gNum;

                    Pos tmp;
                    while(!qu.isEmpty()) {
                        tmp = qu.poll();
                        
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = tmp.x + dx[dir];
                            int ny = tmp.y + dy[dir];
                        
                            if (nx < 0 || ny < 0 || nx == land.length || ny == land.length) continue;
                            if (group[nx][ny] != -1) continue;
                            if (Math.abs(land[nx][ny] - land[tmp.x][tmp.y]) > height) continue;

                            group[nx][ny] = gNum;
                            qu.add(new Pos(nx,ny));
                        }
                    }
                    gNum++;
                }
            }
        }

        PriorityQueue<Edge> heap = new PriorityQueue<Edge>();
        for(int i = 0; i < land.length; i++) {
            for(int j = 0; j < land.length; j++) {
                 
                for (int dir = 0; dir < 4; dir++) {    
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    
                    if (nx < 0 || ny < 0 || nx == land.length || ny == land.length) continue;
                    if (group[i][j] >= group[nx][ny]) continue;
                    heap.add(new Edge(group[i][j], group[nx][ny], Math.abs(land[i][j]-land[nx][ny])));
                }
            }
        }

        parent = new int[gNum];
        for(int i = 0; i < gNum; i++)
            parent[i] = i;

        int answer = 0, cnt = 0;
        while(!heap.isEmpty()) {
            Edge tp = heap.poll();
            if(union(tp.from, tp.to)){
                answer += tp.len;
                if(++cnt == gNum) break;
            }
        }

        return answer;
    }
}
