#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations) {
    int answer = 0;
    
    sort(citations.begin(), citations.end());
    for(int i = 0, j = 0; i <= 10000; i++){
        while(j < citations.size() && citations[j] < i) j++;
        if(j >= citations.size()) break;
        
        if(j <= i && citations.size() - j >= i)
            answer = i;
    }
    return answer;
}
