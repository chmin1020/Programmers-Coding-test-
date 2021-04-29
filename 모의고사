#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> answers) {
    vector<int> answer;
    int people[3] = {0,0,0};
    int maxNum = -1;
    
    int pattern1[5] = {1,2,3,4,5};
    int pattern2[8] = {2,1,2,3,2,4,2,5};
    int pattern3[10] = {3,3,1,1,2,2,4,4,5,5};
    
    for(int i = 0; i < answers.size(); i++){
        if(pattern1[i%5] == answers[i]) people[0]++;
        if(pattern2[i%8] == answers[i]) people[1]++;
        if(pattern3[i%10] == answers[i]) people[2]++;
        
        if(people[0] > maxNum) maxNum = people[0];
        if(people[1] > maxNum) maxNum = people[1]; 
        if(people[2] > maxNum) maxNum = people[2];        
    }
    
    for(int i = 0; i < 3; i++)
        if(people[i] == maxNum) answer.push_back(i+1);
    
    return answer;
}
