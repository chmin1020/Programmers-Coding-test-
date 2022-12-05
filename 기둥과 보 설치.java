import java.util.*;

class Solution {
    //설치된 자재들 
    Set<Integer> pillars = new HashSet<Integer>();
    Set<Integer> roofs = new HashSet<Integer>();
    
    //set에 보관하기 위한 좌표 변환 및 역변환 함수 
    private int axisToInt(int x, int y){
        return x * 1000 + y; 
    }
    
    //바닥에 있거나, 기둥 위에 있거나, 보 위에 있으면 가능
    private boolean canBuildPillar(int axis){
        int x = axis / 1000;
        int y = axis % 1000;
        
        if(y == 0)
            return true;
        else if(pillars.contains(axisToInt(x, y - 1)))
            return true;
        else if(roofs.contains(axis) ||
                (x > 0 && roofs.contains(axisToInt(x - 1, y))))
            return true;
        
        return false;
    }
    
    //기둥 위에 있거나, 양 옆에 보가 있으면 가능
    private boolean canBuildRoof(int axis, int n){
        int x = axis / 1000;
        int y = axis % 1000;
        
        if(y != 0 && 
           (pillars.contains(axisToInt(x, y - 1)) ||
            pillars.contains(axisToInt(x + 1, y - 1))))
            return true;
        else if((x != 0 && roofs.contains(axisToInt(x - 1, y))) &&
            (x != n - 1 && roofs.contains(axisToInt(x + 1, y))))
            return true;
        
        return false;
    }
    
    //지워도 위 기둥, 보가 영향 안 받으면 가능
    private void tryRemovePillar(int x, int y, int axis, int n){   
        boolean still = true;
        
        pillars.remove(axis);
        for(int each: pillars){
            if(!canBuildPillar(each)){
                still = false;
                break;
            }
        }
        
        if(still){
            for(int each: roofs){
                if(!canBuildRoof(each, n)){
                    still = false;
                    break;
                }
            }
        }
        
        if(!still)
            pillars.add(axis);
    }
    
    //지워도 양 옆 보, 양 옆 기둥이 영향 안 받으면 가능
    private void tryRemoveRoof(int x, int y, int axis, int n){ 
        boolean still = true;
        
        roofs.remove(axis);
        for(int each: pillars){
            if(!canBuildPillar(each)){
                still = false;
                break;
            }
        }
        
        if(still){
            for(int each: roofs){
                if(!canBuildRoof(each, n)){
                    still = false;
                    break;
                }
            }
        }
        
        if(!still)
            roofs.add(axis);
    }
    
    public int[][] solution(int n, int[][] build_frame) {        
        //각 명령 수행
        for(int[] order: build_frame){
            int x = order[0];
            int y = order[1];
            int axis = axisToInt(x, y);
            int a = order[2]; 
            int b = order[3]; 
            
            if(a == 0){ //기둥
                if(b == 0)
                    tryRemovePillar(x, y, axis, n);
                if(b == 1 && canBuildPillar(axis))
                    pillars.add(axis);
            }
            else{ //보
                if(b == 0)
                    tryRemoveRoof(x, y, axis, n);
                if(b == 1 && canBuildRoof(axis, n))
                    roofs.add(axis);
            }
        }
        
        //answer에 담기
        int[][] answer = new int[pillars.size() + roofs.size()][3];
        int idx = 0;
        for(Integer each: pillars){
            answer[idx][0] = each / 1000;
            answer[idx][1] = each % 1000;
            answer[idx++][2] = 0;
        }
        for(Integer each: roofs){
            answer[idx][0] = each / 1000;
            answer[idx][1] = each % 1000;
            answer[idx++][2] = 1;
        }
        
        //정렬
        Arrays.sort(answer, new Comparator<int[]>(){
            public int compare(int[] s1, int[] s2){
                if(s1[0] > s2[0])
                    return 1;
                else if(s1[0] == s2[0]){
                    if(s1[1] > s2[1])
                        return 1;
                    else if(s1[1] == s2[1]){
                        if(s1[2] > s2[2]) return 1;
                        else return -1;                
                    }
                    else
                        return -1;
                }
                else return -1;
            }
        });
        
        return answer;
    }
}
