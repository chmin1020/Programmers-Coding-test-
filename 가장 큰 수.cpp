#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(pair<int,int> a, pair<int,int> b){
    if(a.first != b.first) return (a.first > b.first);
    else return a.second < b.second;
}

string solution(vector<int> numbers) {
    string answer = "";
    int tmp1, tmp2;
    bool onlyZero = true;
    vector<pair<int,int>> list;
    
    for(int i = 0; i < numbers.size(); i++){
        tmp1 = 0;
        if(numbers[i] != 0) onlyZero = false;
            
        if(numbers[i] <= 9) {
            for(int j = 0; j < 6; j++){
                tmp1 *= 10;
                tmp1 += numbers[i];
            }
            tmp2 = 1;
        }
        else if(numbers[i] <= 99){
            for(int j = 0; j < 3; j++){
                tmp1 *= 100; 
                tmp1 += numbers[i];
            }
            tmp2 = 2;
        }
        else if(numbers[i] <= 999){
            for(int j = 0; j < 2; j++){
                tmp1 *= 1000; 
                tmp1 += numbers[i];
            }
            tmp2 = 3;
        }
        else{
            tmp1 = 100000; tmp2 = 4;
        }
        list.push_back(make_pair(tmp1, tmp2));
    }   
    sort(list.begin(), list.end(), compare);
    
    if(onlyZero) answer = "0";
    else{
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < 6 - list[i].second; j++)
                list[i].first /= 10;
            answer.append(to_string(list[i].first));
        }
    }
    return answer;
}
