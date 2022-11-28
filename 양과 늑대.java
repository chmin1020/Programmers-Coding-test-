import java.util.*;

class Solution {
    //경로 저장할 리스트 배열
    private ArrayList<Integer>[] routes;
    
    //answer
    private int answer = 0;
    
    //백트래킹 함수
    private void findWay
        (int[] info, int cur, int sheep, int wolf, ArrayList<Integer> possible){
        
        //늑대가 더 많아지면
        if(sheep <= wolf)
            return;
        
        //양의 최댓값을 발견했으면
        if(sheep > answer)
            answer = sheep;
        
        //다음으로 갈 수 있는 루트 갱신
        for(int cand: routes[cur])
            possible.add(cand);
        
        //실제 경로 탐색
        for(int i = 0; i < possible.size(); i++){
            //가능 경로 갱신
            ArrayList<Integer> tmp = (ArrayList<Integer>)possible.clone();
            tmp.remove(i);
            
            //실제 탐색
            int next = possible.get(i);  
            if(info[next] == 0)
                findWay(info, next, sheep + 1, wolf, tmp);
            else
                findWay(info, next, sheep, wolf + 1, tmp);
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        // routes 배열 초기화
        routes = new ArrayList[info.length];
        for(int i = 0; i < info.length; i++)
            routes[i] = new ArrayList<Integer>();
        
        ArrayList<Integer> possible = new ArrayList<Integer>();
        
        // 실제 edge 정보 저장
        for(int[] pair: edges)    
            routes[pair[0]].add(pair[1]);
                
        //루트에서 backtracking 시작
        findWay(info, 0, 1, 0, possible);
        
        return answer;
    }
}
