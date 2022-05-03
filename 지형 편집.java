public class Solution {
    public long calculate(int [][] land, long height, int P, int Q){
        long tmp, res = 0;
        for(int i = 0; i < land.length; i++){
            for(int j = 0; j < land.length; j++){
                tmp = land[i][j] - height;
                if(tmp > 0) res += (Q * tmp);
                else res += (-P * tmp);
            }
        }
        return res;
    }
    
    public long solution(int[][] land, int P, int Q) {
        long answer = calculate(land, 0, P, Q);
        long start = land[0][0], end = land[0][0];
        
        for(int i = 0; i < land.length; i++){
            for(int j = 0; j < land.length; j++){
                start = Math.min(start, land[i][j]);
                end = Math.max(end, land[i][j]);
            }
        }
        long mid, mc, moc;
        while(start <= end){
            mid = (start + end) / 2;
            mc = calculate(land, mid, P, Q);
            moc = calculate(land, mid + 1, P, Q);
            
            if(mc < moc)
                end = mid - 1;
            else
                start = mid + 1;
            
            mid = Math.min(mc, moc);
            answer = Math.min(mid, answer);
        }
        
        return answer;
    } 
}
