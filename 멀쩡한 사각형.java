class Solution {
    public long solution(int w, int h) {
        long count = 0;
        long len, cur = 0;
        for(long i = 0; i < h; i++){
            len = i + 1;
            count += ((long)(Math.ceil(len * (double)w / h)) - cur);
            cur = (long)Math.floor(len * (double)w / h);
        }
        return (long)h*(long)w - count;
    }
}
