class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        boolean[] onTree = new boolean[26];
        
        for(int i = 0; i < skill.length(); i++)
            onTree[skill.charAt(i)-'A'] = true;
        
        for(int i = 0; i < skill_trees.length; i++){
            int ptr = 0;
            String cur = skill_trees[i];
            boolean possible = true;
            
            for(int j = 0; j < cur.length(); j++){
                if(ptr == skill.length()) break;
                
                if(cur.charAt(j) == skill.charAt(ptr))
                    ptr++;
                else{
                    if(onTree[cur.charAt(j) - 'A']){
                        possible = false;
                        break;
                    }
                }
            }
            if(possible) answer++;
        }
        return answer;
    }
}
