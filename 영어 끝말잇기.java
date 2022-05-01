import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {1,1};
        int cnt = 0;
        boolean wrong = true;
        HashSet<String> mentioned = new HashSet<String>();
        
        if(words[0].length() <= 1) return answer;
        String before = words[0];
        mentioned.add(words[0]);        
        for(cnt = 1; cnt < words.length; cnt++){
            if(words[cnt].charAt(0) != before.charAt(before.length() - 1)
               ||words[cnt].length() <= 1 || mentioned.contains(words[cnt])){
                wrong = false;
                break;
            }
            mentioned.add(words[cnt]);
            before = words[cnt];
        }
        
        answer = new int[2];    
        if(wrong){
            answer[0] = answer[1] = 0;
        }
        else{
            answer[0] = (cnt%n) + 1;
            answer[1] = (cnt/n) + 1;
        }
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다. 
        System.out.println("Hello Java");

        return answer;
    }
}
