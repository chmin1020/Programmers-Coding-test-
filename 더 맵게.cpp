#include <string>
#include <vector>
#include <queue>
#include <functional>

using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0, cnt = scoville.size();
    priority_queue<int, vector<int> ,greater<int>> q;

    for(int i = 0; i < cnt; i++)
        q.push(scoville[i]);

    int tp1, tp2;
    while(1){
        if(cnt <= 1){
            if(cnt == 0 || q.top() < K) answer = -1;
            break;  
        }

        if(q.top() < K){
            tp1 = q.top();
            q.pop();    
            tp2 = q.top();
            q.pop();

            q.push(tp1+ 2 * tp2);
            cnt--;
            answer++;
        }
        else break;
    }
    return answer;
}
