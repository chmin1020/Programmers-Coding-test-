import java.util.*;

class Solution {
    private class Trie{
        Node start = new Node();
        
        public void insert(String word){
            start.insert(word);
        }
        
        public int search(String word){
            Node cur = start;
            int idx = 0;
            while(idx < word.length() && cur != null && !cur.isOneLeft()){
                cur = cur.getChild(word.charAt(idx++));
           }
            return (idx);
        }
    }
    
    private class Node{
        Map<Character, Node> childMap;
        int childNum;
    
        Node(){
            childMap = new TreeMap<Character, Node>();
            childNum = 0;
        }
        
        public boolean isOneLeft(){
            if(childNum > 1)
                return false;
            return true;
        }
        
        public Node getChild(char c){
            return childMap.get(c);
        }
        
        public void insert(String word){
            childNum++;
            if(word.length() == 0)
                return;
            
            char ch = word.charAt(0);
            
            if(childMap.get(ch) == null)
                childMap.put(ch, new Node());
            

            childMap.get(ch).insert(word.substring(1));
        }
    }
    
    public int solution(String[] words) {
        int answer = 0;
        Trie trie = new Trie();
        
        for(String word: words)
            trie.insert(word);
                
        for(String word: words)
            answer += trie.search(word);
        
        return answer;
    }
}
