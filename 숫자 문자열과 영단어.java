public int solution(String s) {
        long answer = 0;
        String tp = "";
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= 9){
                answer += (int)(s.charAt(i) - '0');
                answer *= 10;
                continue;
            }
            tp = tp + s.charAt(i);
            
            if(tp.equals("zero")){
                answer *= 10;
                tp = "";
            }
            else if(tp.equals("one")){
                answer += 1;
                answer *= 10;
                tp = "";
            }
            else if(tp.equals("two")){
                answer += 2;
                answer *= 10;
                tp = "";
            }
            else if(tp.equals("three")){
                answer += 3;
                answer *= 10;
                tp = "";
            }
            else if(tp.equals("four")){
                answer += 4;
                answer *= 10;
                tp = "";
            }
            else if(tp.equals("five")){
                answer += 5;
                answer *= 10;
                tp = "";
            }
            else if(tp.equals("six")){
                answer += 6;
                answer *= 10;
                tp = "";
            }
            else if(tp.equals("seven")){
                answer += 7;
                answer *= 10;
                tp = "";
            }
            else if(tp.equals("eight")){
                answer += 8;
                answer *= 10;
                tp = "";
            }
            else if(tp.equals("nine")){
                answer += 9;
                answer *= 10;
                tp = "";
            }
        }
        answer /= 10;
        
        return (int)answer;
    }
