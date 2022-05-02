import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int before = 1;
        int maximum = 2 * w + 1;
        int dis = 0;
        for(int i = 0; i < stations.length; i++){
            dis = ((stations[i] - w) - before);
            if(dis >= 0){
                answer += (dis / maximum);
                if(dis % maximum != 0) answer++;
            }
            before = stations[i] + w + 1;
        }
        
        dis = n + 1 - before;
        if(dis > 0){
            answer += (dis / maximum);
            if(dis % maximum != 0) answer++;
        }
        return answer;
    }
}
