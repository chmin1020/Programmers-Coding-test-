class Solution {
    public String solution(String s) {
        String[] nums = s.split(" ");
        int min = Integer.parseInt(nums[0]);
        int max = Integer.parseInt(nums[0]);
        
        for(int i = 1; i < nums.length; i++){
            min = Math.min(min, Integer.parseInt(nums[i]));
            max = Math.max(max, Integer.parseInt(nums[i]));
        }
        
        String answer = min + " " + max;
        return answer;
    }
}
