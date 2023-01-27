class Solution {
    //--숫자를 포화 트리형 이진수로 만드는 함수
    private fun toTreeBinary(num: Long): String{
        val binaryMaker = StringBuilder()

        //이진수 만들기
        var tmp = num
        while(tmp > 0){
            binaryMaker.append((tmp % 2).toString())
            tmp /= 2
        }

        //포화 이진 트리를 위해 0 붙이기
        var goodLen = 1
        while(goodLen - 1 < binaryMaker.length)
            goodLen *= 2

        binaryMaker.append("0".repeat(goodLen - 1 - binaryMaker.length))

        return binaryMaker.reverse().toString()
    }

    //--가능한 트리인지 확인하는 함수
    private fun treeCheck(tree: String): Boolean{
        if(tree.length == 1) return true

        val root = tree.length / 2

        //부모가 dummy -> 불가능
        return if(tree[root] == '0' && tree.contains("1")) false
               else treeCheck(tree.substring(0, root)) && treeCheck(tree.substring(root + 1))
    }

    fun solution(numbers: LongArray): IntArray {
        val answers = mutableListOf<Int>()

        numbers.forEach {
            val result = if(treeCheck(toTreeBinary(it))) 1 else 0
            answers.add(result)
        }

        return answers.toIntArray()
    }
}
