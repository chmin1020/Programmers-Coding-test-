import java.util.*

class Solution {
    //--110 제거 문자열 반환 함수--//
    private fun remove110s(str: String): Pair<String, Int>{
        val stack = Stack<Char>()
        var cnt = 0
        str.forEach {
            stack.add(it)
            if(stack.size >= 3 && it == '0'){
                var test = ""
                for(i in 0 until 3)
                    test = stack.pop()!! + test

                //110 아니면 다시 넣기
                if(test != "110")
                    for(i in test.indices)
                        stack.push(test[i])
                else cnt++
            }
        }

        //남은 것 문자열로 반환
        val rest = StringBuilder("")
        while(stack.isNotEmpty())
            rest.append(stack.pop())

        return Pair(rest.reverse().toString(), cnt)
    }

    //--답 문자열 제작 함수--//
    private fun makeNewStr(str: String, cnt: Int): String{
        //0을 기준으로 문자열 나누기
        val sb = StringBuilder("")

        //마지막 0 있는 인덱스 탐색
        var zeroIdx = str.lastIndex
        while(zeroIdx >= 0 && str[zeroIdx] != '0')
            zeroIdx--
        
        //0이 존재하면 추가
        if(zeroIdx >= 0)
            sb.append(str.substring(0..zeroIdx))

        //110 추가
        for(i in 0 until cnt)
            sb.append("110")

        //나머지 뒷부분 추가
        sb.append(str.substring(zeroIdx + 1 until str.length))

        return sb.toString()
    }

    fun solution(s: Array<String>): Array<String> {
        val answer = mutableListOf<String>()

        s.forEach {
            val (str,cnt) = remove110s(it)
            answer.add(makeNewStr(str, cnt))
        }

        return answer.toTypedArray()
    }
}
