#include <string>
#include <vector>
#include <cmath>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    int xpy = brown/2 + 2;
    int xy = brown + yellow;
    int xmy = (int)sqrt(pow(xpy,2) - 4 * xy); 
    
    answer.push_back((xpy + xmy) / 2);
    answer.push_back(xpy - answer[0]);
    return answer;
}
