import java.util.*;

class Solution {
    private boolean[] visited;
    private Set<String> redunChk = new HashSet<String>();
    private ArrayList<String> candidate = new ArrayList<String>();
    private int answer = 0;
    private int peopleCnt;
    private int keyCnt;
    
    private void dfs(int n, int cnt, String[][] relation){
        redunChk.clear();
        visited[n] = true;
        
        int[] keys = new int[cnt];
        
        int idx = 0;
        for(int i = 0; i < keyCnt; i++)
            if(visited[i])
                keys[idx++] = i;
        
        for(int i = 0; i < peopleCnt; i++){
            String str = "";
            
            for(int j = 0; j < cnt; j++)
                str = str + relation[i][keys[j]] + "@";
            redunChk.add(str);
        }
        
        if(redunChk.size() == peopleCnt){
            String cand = "";
            for(int i = 0; i < cnt; i++)
                cand = cand + Integer.toString(keys[i]);
            candidate.add(cand);
        }
        else{
            for(int i = n + 1; i < keyCnt; i++){
                if(!visited[i])
                    dfs(i, cnt + 1, relation);
            }
        }
        
        visited[n] = false;
    } 
    
    public int solution(String[][] relation) {
        peopleCnt = relation.length;
        keyCnt = relation[0].length;
        
        visited = new boolean[keyCnt];
        
        //유일성
        for(int i = 0; i < keyCnt; i++)
            dfs(i, 1, relation);
        
        //최소성
        boolean[] itCan = new boolean[candidate.size()];
        Arrays.fill(itCan, true);
        
        Collections.sort(candidate, Comparator.comparing(String::length));
        for(int i = 0; i < candidate.size(); i++){
            if(!itCan[i])
                continue;
            
            String cur = candidate.get(i);
            for(int j = i + 1; j < candidate.size(); j++){
                String each = candidate.get(j);
                int same = 0;
                for(int k = 0; k < cur.length(); k++)
                    if(each.contains(String.valueOf(cur.charAt(k))))
                        same++;
                if(same == cur.length())
                    itCan[j] = false;
            }   
        }
        
        for(boolean b: itCan)
            if(b) answer++;
    
        return answer;
    }
}
