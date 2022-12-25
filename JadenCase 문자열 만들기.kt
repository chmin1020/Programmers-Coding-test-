import java.lang.StringBuilder

class Solution {
  fun solution(s: String): String {
        //띄어쓰기 기준으로 문단 나누기
        val sb = StringBuilder()

        //변환 작업
        var isFirst = true
        for(i in 0 until s.length){
            if(isFirst && s[i] != ' ') { //첫번째 글자
                sb.append(s[i].toUpperCase())
                isFirst = false
            }
            else { //나머지
                sb.append(s[i].toLowerCase())
                if(s[i] == ' ') isFirst = true //공백 다음은 변환
            }
        }

        return sb.toString()
    }
}
