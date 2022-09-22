import java.util.Arrays;

class Solution {
    int N, maxDiff = -0;
    int[] INFO;
    int[] answer = {-1}, tmp;
    
    private void backTracking(int cnt, int shot){
        tmp[shot]++;
        
        if(cnt == N){
            int myScore = 0, enemyScore = 0;
            
            for(int i = 0; i <= 10; i++){
                if(tmp[i] > INFO[i])
                    myScore += (10 - i);
                else if(INFO[i] != 0)
                    enemyScore += (10 - i);
            }
            
            int diff = myScore - enemyScore;
            if(myScore > enemyScore && diff > maxDiff){
                maxDiff = diff;    
                answer = tmp.clone();
            }
        }
        else{
            for(int i = shot; i >= 0; i--)
                if(INFO[i] >= tmp[i])
                    backTracking(cnt + 1, i);   
        }
        tmp[shot]--;
    }
    
    public int[] solution(int n, int[] info) {
        N = n;
        INFO = info;
        
        tmp = new int[11];
        Arrays.fill(tmp, 0);
        
        for(int i = 10; i >= 0; i--)
            backTracking(1, i);
        
        return answer;
    }
}
