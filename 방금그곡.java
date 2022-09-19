import java.util.*;

class Solution {
    private class PlayedMusic{
        ArrayList<String> contents;
        String name;
        int len;
        
        PlayedMusic(String info){
            String[] eachInfo = info.split(",");
            int startHour = Integer.parseInt(eachInfo[0].substring(0, 2));
            int startMinute = Integer.parseInt(eachInfo[0].substring(3, 5));
            int endHour = Integer.parseInt(eachInfo[1].substring(0, 2));
            int endMinute = Integer.parseInt(eachInfo[1].substring(3, 5));
            
            len = (endHour - startHour) * 60 + (endMinute - startMinute);
            name = eachInfo[2];
            
            ArrayList<String> melody = toMelody(eachInfo[3]);
            
            int idx = 0, curLen = 0;
            contents = new ArrayList<String>();
            
            while(curLen < len){
                contents.add(melody.get(idx));
                idx = (idx + 1) % melody.size();
                curLen++;
            }
        }
        
        public boolean contains(String m){
            ArrayList<String> msg = toMelody(m);
            
            for(int i = 0; i <= contents.size() - msg.size(); i++){
                if(contents.get(i).equals(msg.get(0))){
                    boolean isMatched = true;
                    
                    for(int j = i; j < i + msg.size(); j++){
                        if(!contents.get(j).equals(msg.get(j - i))){
                            isMatched = false;
                            break;
                        }
                    }
                    if(isMatched)
                        return true;
                }
            }
            return false;
        } 
    }
    
    private ArrayList<String> toMelody(String str){
        ArrayList<String> melody = new ArrayList<String>();
        int idx = 0;
        while(idx < str.length()){
            int far = 1;
                
            if(idx + 1 < str.length() && str.charAt(idx + 1) == '#')
                far++;
            melody.add(str.substring(idx, idx + far));
            idx += far;
        }
        return melody;
    }
    
    public String solution(String m, String[] musicinfos) {
        int answerIdx = -1;
        
        PlayedMusic[] list = new PlayedMusic[musicinfos.length];
        
        for(int i = 0; i < musicinfos.length; i++){
            list[i] = new PlayedMusic(musicinfos[i]);
            
            if(list[i].contains(m)){
                if(answerIdx == -1)
                    answerIdx = i;
                else{
                    if(list[answerIdx].len < list[i].len)
                        answerIdx = i;
                }
            }
        }
        
        if(answerIdx == -1)
            return "(None)";
        else
            return list[answerIdx].name;        
    }
}
