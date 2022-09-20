import java.util.*;

class Solution {
    private class StageInfo implements Comparable<StageInfo>{
        int num;
        double failRate;
        
        StageInfo(int n, double f){
            num = n;
            failRate = f;
        }
        
        public int compareTo(StageInfo o){
            if(o.failRate > this.failRate) return 1;
            else if(o.failRate < this.failRate) return -1;
            else return 0;
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        int[] inStage = new int[N + 1];
        int[] aboveStage = new int[N + 1];
        
        Arrays.fill(inStage, 0);
        Arrays.fill(aboveStage, 0);
        
        for(int state: stages){
            if(state < N + 1) {
                inStage[state]++;
                for(int i = 1; i <= state; i++)
                    aboveStage[i]++;
            }
            else
                for(int i = 1; i < state; i++)
                    aboveStage[i]++;
        }
        
        StageInfo[] sortArr = new StageInfo[N];
        for(int i = 0; i < N; i++)
            sortArr[i] = new StageInfo(i + 1, inStage[i + 1]/(double)aboveStage[i + 1]);
        Arrays.sort(sortArr);
        
        for(int i = 0; i < N; i++)
            answer[i] = sortArr[i].num;
        
        return answer;
    }
}
