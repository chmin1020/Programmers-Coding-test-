import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    //조건 데이터 클래스
    private static class Condition{
        char friend, con;
        int distance;

        Condition(char friend, char con, int distance){
            this.friend = friend;
            this.con = con;
            this.distance = distance;
        }
    }

    //연산을 위한 컬렉션들
    private static final char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private static final Map<Character, Integer> positions = new HashMap<Character, Integer>();
    private static final Map<Character, ArrayList<Condition>> conditions = new HashMap<Character, ArrayList<Condition>>();

    //경우의 수 확인 백트래킹 함수 및 답 변수
    private int answer = 0;
    private void backTracking(int cur, int pos){
        if(pos == 7){
            answer++;
            return;
        }

        positions.put(friends[cur], pos);

        for(int i = 0; i < 8; i++){
            if(positions.get(friends[i]) != -1)
                continue;

            //배치 안한 프렌즈 배치 가능 여부 체크
            boolean isPossible = true;
            for(Condition each: conditions.get(friends[i])){
                if(positions.get(each.friend) != -1) {
                    int dis = pos - positions.get(each.friend);

                    //조건 만족 안하면 불가
                    if (each.con == '<' && dis >= each.distance)
                        isPossible = false;
                    if (each.con == '>' && dis <= each.distance)
                        isPossible = false;
                    if(each.con == '=' && dis != each.distance)
                        isPossible = false;
                }
            }

            //다 통과하면 포지션에 넣기
            if(isPossible)
                backTracking(i, pos + 1);
        }

        positions.put(friends[cur], -1);
    }

    public int solution(int n, String[] data) {
        //맵 초기화
        for(char each: friends) {
            positions.put(each, -1);
            conditions.put(each, new ArrayList<Condition>());
        }

        //조건 분석
        for(String each: data){
            char one = each.charAt(0);
            char other = each.charAt(2);
            char op = each.charAt(3);
            int distance = each.charAt(4) - '0';

            conditions.get(one).add(new Condition(other, op, distance));
            conditions.get(other).add(new Condition(one, op, distance));
        }

        //경우의 수 확인
        for(int i = 0; i < 8; i++)
            backTracking(i, 0);

        return answer;
    }
}
