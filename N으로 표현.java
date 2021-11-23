import java.util.*;

class Solution {
    private final int MAX = 32000;
    public int solution(int N, int number) {
        int contNum, cur1, cur2, match;
        int[] nums = new int[MAX + 1];
        ArrayList<ArrayList<Integer>> dp = new ArrayList<ArrayList<Integer>>();
        
        Arrays.fill(nums, 100);
        for(int i = 0; i <= 8; i++)
        	dp.add(new ArrayList<Integer>());
                
        for(int i = 1; i < 8; i++){
            contNum = 0;

            for(int j = 0; j < i; j++)
                contNum += Math.pow(10, j) * N;
            
            if(contNum <= MAX){
                dp.get(i).add(contNum);
                nums[contNum] = i;
            }
            
            for(int j = 1; j <= i; j++){
                for(int k = 0; k < dp.get(j).size(); k++){
                    cur1 = dp.get(j).get(k);
                    match = (i + 1) - j;
                    for(int l = 0; l < dp.get(match).size(); l++){
                        cur2 = dp.get(match).get(l);
                        if(cur1 + cur2 <= MAX && nums[cur1 + cur2] > nums[cur1] + nums[cur2]){
                            nums[cur1 + cur2] = nums[cur1] + nums[cur2];
                            dp.get(i + 1).add(cur1 + cur2);
                        }
                        if(cur1 - cur2 > 0 && nums[cur1 - cur2] > nums[cur1] + nums[cur2]){
                            nums[cur1 - cur2] = nums[cur1] + nums[cur2];
                            dp.get(i + 1).add(cur1 - cur2);
                        }
                        if(cur1 * cur2 <= MAX && nums[cur1 * cur2] > nums[cur1] + nums[cur2]){
                            nums[cur1 * cur2] = nums[cur1] + nums[cur2];
                            dp.get(i + 1).add(cur1 * cur2);
                        }
                        if(cur1 / cur2 > 0 && nums[cur1 / cur2] > nums[cur1] + nums[cur2]){
                            nums[cur1 / cur2] = nums[cur1] + nums[cur2];
                            dp.get(i + 1).add(cur1 / cur2);
                        }
                        
                    }
                }
            }
        }
       if(nums[number] > 8) return -1;
       else return nums[number];
    }
}
