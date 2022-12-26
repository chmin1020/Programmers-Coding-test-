class Solution {
    private fun gcd(a: Int, b: Int): Int{
        var n1 = a
        var n2 = b

        //순서는 오름차순으로
        if(n1 > n2) n1 = n2.also { n2 = n1 }

        while(n2 != 0){
            val rest = n1 % n2
            n1 = n2
            n2 = rest
        }
        return n1
    }

    fun solution(arr: IntArray): Int {
        return arr.reduce{ ans, it -> (ans * it)/ gcd(ans, it)}
    }
}
