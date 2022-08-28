class Solution {
    
    public String solution(String new_id) {
        String answer = new_id;
        
        answer = answer.toLowerCase();
        answer = answer.replaceAll("[^a-z0-9[-][_][.]]","");
        answer = answer.replaceAll("[.]+",".");
        
        if(answer.charAt(0) == '.')
            answer = answer.substring(1, answer.length());
    
        if(answer.length() >= 1)
            if(answer.charAt(answer.length() - 1) == '.')
                answer = answer.substring(0, answer.length() - 1);
        
        if(answer.length() == 0)
            answer = "a";
        
        if(answer.length() >= 15)
            answer = answer.substring(0, 15); 

        if(answer.charAt(answer.length() - 1) == '.')
            answer = answer.substring(0, answer.length() - 1);
        
        int lack = 3 - answer.length();
        if(lack > 0){
            String extra = "" + answer.charAt(answer.length() - 1);
            extra = extra.repeat(lack);
            answer += extra;
        }
        
        return answer;
    }
}
