#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> people, int limit) {
    int answer = 0;
    int left, right;
   
    sort(people.begin(), people.end());
    left = 0; right = (int)people.size() - 1;
    while(left <= right){
        if(people[left] + people[right] <= limit)
            left++; 
        right--;
        answer++;
    }
    return answer;
}
