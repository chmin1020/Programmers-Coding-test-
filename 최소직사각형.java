class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int bigs = 0, smalls = 0;
        
        int bigIdx;
        for(int i = 0; i < sizes.length; i++){
            bigIdx = (sizes[i][0] > sizes[i][1])? 0 : 1;
            
            bigs = Math.max(sizes[i][bigIdx], bigs);
            smalls = Math.max(sizes[i][1 - bigIdx], smalls);
        }
        
        return bigs * smalls;
    }
}
