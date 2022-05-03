class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];
        int dec, key, power = (int)Math.pow(2, n - 1);
        String tmp;
        
        for(int i = 0; i < arr1.length; i++){
            dec = arr1[i] | arr2[i];
            tmp = "";
            key = power;
            
            while(key > 0){
                if(dec - key >= 0){
                    dec -= key;
                    tmp += "#";
                }
                else
                    tmp += " ";
                key /= 2;
            }
            answer[i] = tmp;
        }
        return answer;
    }
}
