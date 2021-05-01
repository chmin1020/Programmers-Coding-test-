#include <string>
#include <vector>
#include <set>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    multiset<string> ms;
    
    for(int i = 0; i < participant.size(); i++)
        ms.insert(participant[i]);
    for(int i = 0; i < completion.size(); i++)
        ms.erase(ms.find(completion[i]));
  
    answer = *ms.begin();
    return answer;
}
