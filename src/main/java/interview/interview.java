package interview;

import java.util.Stack;

public class interview {

    static void main() {



interview interview= new interview();

       int result=  interview.calculate("(1+(4+5+2)-3)+(6+8)");


        System.out.println(result
        );

    }


    public int calculate(String s) {

        Stack<Character> operatorStack= new Stack<>();
        Stack<Integer> values= new Stack<>();
       char v=s.charAt(0);
       Integer temp=0;

       int hasK=0;
    for (int i=0;i<s.length();i++)
    {
        v= s.charAt(i);

        switch (v) {

            case ' ':
                break;

            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                for (;i<s.length();i++){
                    v=s.charAt(i);
                    if (v >= '0' && v <= '9') {
                        temp = temp * 10 + (v - '0');
                    } else {
                        i--;
                        break;
                    }
                }
                values.push(temp);
                temp=0;

                if(hasK==0&&!operatorStack.isEmpty()){
                    while(  !operatorStack.isEmpty()){
                        char op=    operatorStack.pop();
                        if(op=='('){
                            break;
                        }
                        switch (op){
                            case '+':
                                int right=values.pop();
                                int left=values.pop();
                                values.push(right+left);
                                break;
                            case '-':
                                right=values.pop();
                                left=values.pop();
                                values.push(left-right);
                                break;
                        }

                    }


                }


                break;

            case '+':
                operatorStack.push('+');

                break;

            case '-':
                operatorStack.push('-');
                break;


            case '(':
                operatorStack.push('(');
                hasK++;
                break;

            case ')':
                hasK--;



                while(  !operatorStack.isEmpty()){
                 char op=    operatorStack.pop();
                    if(op=='('){
                        break;
                    }
                    switch (op){
                        case '+':
                            int right=values.pop();
                            int left=values.pop();
                            values.push(right+left);
                            break;
                        case '-':
                             right=values.pop();
                             left=values.pop();
                            values.push(left-right);
                            break;
                    }

                }



                break;

        }
    }

        while(  !operatorStack.isEmpty()){
            char op=    operatorStack.pop();
            if(op=='('){
                break;
            }
            switch (op){
                case '+':
                    int right=values.pop();
                    int left=values.pop();
                    values.push(right+left);
                    break;
                case '-':
                    right=values.pop();
                    left=values.pop();
                    values.push(left-right);
                    break;
            }

        }


    if(values.isEmpty()){
        return -1;
    }else {
        return values.pop();
    }

    }


}
