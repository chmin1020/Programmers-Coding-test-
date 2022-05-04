import java.util.ArrayList;

class Solution {
    public boolean isAlphabet(char t){
        if((t >= 'A' && t <= 'Z') ||
          (t >= 'a' && t <= 'z'))
            return true;
        return false;
    }
    
    public int solution(String str1, String str2) {
        int all = 0, part = 0;
        ArrayList<String> list = new ArrayList<String>();
        String tmp;
        
        for(int i = 0; i < str1.length() - 1; i++){
            tmp = str1.substring(i, i + 2);
            if(!isAlphabet(tmp.charAt(0)) || !isAlphabet(tmp.charAt(1)))
                continue;
            all++;
            list.add(tmp.toLowerCase());
        }
        
        for(int i = 0; i < str2.length() - 1; i++){
            tmp = str2.substring(i, i + 2);
            if(!isAlphabet(tmp.charAt(0)) || !isAlphabet(tmp.charAt(1)))
                continue;
            
            tmp = tmp.toLowerCase();
            if(list.contains(tmp)){
                part++;
                list.remove(tmp);
            }
            else
                all++;
        }
        
        if(all == 0){
            all = 1;
            if(part == 0)
                part = 1;
        }
        
        double res = (double)part/(double)all * 65536;
        return (int)Math.floor(res);    
    }
}
