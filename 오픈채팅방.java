import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        ArrayList<String> idSeq = new ArrayList<String>();
        ArrayList<String> contentSeq = new ArrayList<String>();
        Map<String, String> users = new TreeMap<String, String>();
        
        for(int i = 0; i < record.length; i++){
            String[] msg = record[i].split(" ");
            
            if(!msg[0].equals("Change")){
                if(msg[0].equals("Enter"))
                    contentSeq.add("님이 들어왔습니다.");
                else
                    contentSeq.add("님이 나갔습니다.");
                
                idSeq.add(msg[1]);
            }
            
            if(msg.length > 2)
                users.put(msg[1], msg[2]);
        }
        
        String[] answer = new String[idSeq.size()];
        
        for(int i = 0; i < answer.length; i++)
            answer[i] = users.get(idSeq.get(i)) + contentSeq.get(i);
        return answer;
    }
}
