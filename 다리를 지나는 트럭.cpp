#include <string>
#include <vector>
#include <queue>
using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 1;
    int sumOfW = 0;
    queue<pair<int, int>> q;

    int i = 1;
    q.push(make_pair(truck_weights[0], answer));
    sumOfW += truck_weights[0];
    while (!q.empty()) {
        answer++;
        while (!q.empty() && answer - q.front().second >= bridge_length) {
            sumOfW -= q.front().first;
            q.pop();
        }       
        if (i < truck_weights.size()) {
            if (weight >= sumOfW + truck_weights[i]) {
                q.push(make_pair(truck_weights[i], answer));
                sumOfW += q.back().first;
                i++;
            }
        }
    }
    return answer;
}
