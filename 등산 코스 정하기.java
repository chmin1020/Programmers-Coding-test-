import java.util.*;

class Solution {
    private static final int INF = 1000000000;
    //way 정보 클래스
    class Info implements Comparable<Info>{
        int dest, dist;
        
        Info(int dest, int dist){
            this.dest = dest;
            this.dist = dist;
        }
        
        public int compareTo(Info o){
            return this.dist - o.dist;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {0, INF};
        ArrayList<Info>[] edges = new ArrayList[n + 1];
        int[] intensity = new int[n + 1];
        boolean isGate[] = new boolean[n + 1];
        boolean isSummit[] = new boolean[n + 1];
        
        
        //초기화
        Arrays.fill(intensity, INF);
        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<Info>();
        
        //gate 등록
        for(int gate: gates)
            isGate[gate] = true;
        
        //summit 등록
        for(int summit: summits)
            isSummit[summit] = true;
    
        //path 등록
        for(int[] each: paths){
            if(!isGate[each[1]])
                edges[each[0]].add(new Info(each[1], each[2]));
            if(!isGate[each[0]])
                edges[each[1]].add(new Info(each[0], each[2]));
        }
        
        //dijkstra로 경로 찾기
        PriorityQueue<Info> heap = new PriorityQueue<Info>();
        for(int gate: gates){
            heap.offer(new Info(gate, 0));
            intensity[gate] = 0;
        }
        
        while(!heap.isEmpty()){
            Info cur = heap.poll();
            
            if(cur.dist > intensity[cur.dest])
                continue;
            
            //다른 산봉우리는 지나치지 않는다.
            if(isSummit[cur.dest])
                continue;
                
            // 각 가능한 경로에 대하여
            for(Info each: edges[cur.dest]){
                int weight = each.dist;
                int to = each.dest;
                int curMax = Math.max(weight, intensity[cur.dest]);                
           
                // 이미 결정 완료한 곳이 아니면 결정 시도
                if((intensity[to] > curMax)){
                    intensity[to] = curMax;
                    heap.offer(new Info(to, intensity[to]));
                }
            }
        }
        
        //summit intensity 값 갱신
        Arrays.sort(summits);
        for(int summit: summits){    
            if(answer[1] > intensity[summit]){
                answer[0] = summit;
                answer[1] = intensity[summit];
            }
        }
        return answer;
    }
}
