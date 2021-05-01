#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<vector<string>> clothes) {
    int answer = 1;
    unordered_map<string,int> kinds;
    
    for(int i = 0; i < clothes.size(); i++){
        if(kinds.find(clothes[i][1]) == kinds.end()) 
            kinds.insert(make_pair(clothes[i][1], 2));
        else
            kinds[clothes[i][1]]++;
    }
    
    for(unordered_map<string,int>::iterator it = kinds.begin(); it != kinds.end(); it++)
        answer*=(*it).second;
    answer -= 1;
    
    return answer;
}
