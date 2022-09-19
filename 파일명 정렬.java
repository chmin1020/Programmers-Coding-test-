import java.util.*;

class Solution {
    private class FileName implements Comparable<FileName>{
        String origin;
        String head;
        int number;
        
        FileName(String str){
            origin = str;
            
            int cur = 0, nStart = 0;
            
            char ch = str.charAt(cur);
            while(ch < '0' || ch > '9')
                ch = str.charAt(++cur);
            head = str.substring(0, cur).toLowerCase();
            nStart = cur;
            
            for(; cur < str.length(); cur++){
                ch = str.charAt(cur);
                if(ch < '0' || ch > '9')
                    break;
            }            
            number = Integer.parseInt(str.substring(nStart, cur));
        }
        
        public int compareTo(FileName o){
            int compTry = this.head.compareTo(o.head);
            if(compTry == 0)
                compTry = this.number - o.number;    
            return compTry;
        }
    }
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        ArrayList<FileName> fns = new ArrayList<FileName>();
        
        for(String name: files)
            fns.add(new FileName(name));
        
        Collections.sort(fns);
     
        for(int i = 0; i < files.length; i++)
            answer[i] = fns.get(i).origin;
        return answer;
    }
}
