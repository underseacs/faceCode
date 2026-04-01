package list;

import node.LinkNode;

import java.util.Objects;
import java.util.Stack;

public class list {

    public LinkNode revert(LinkNode root,int k){

        Stack<LinkNode> stack= new Stack<>();

        int count=0;
        LinkNode temp= root;
        LinkNode result=null;
        LinkNode resultTemp=null;

        while(temp!=null){
            count++;
            stack.push(temp);
            temp=temp.getNext();

            if(k==count){
                count=0;
                while(!stack.isEmpty()) {
                    if(Objects.isNull(resultTemp)){
                        resultTemp=stack.pop();
                        result=resultTemp;
                    }else{
                        resultTemp.setNext(stack.pop());
                        resultTemp=resultTemp.getNext();
                    }

                }
            }
        }

        if(!stack.isEmpty()){
            resultTemp.setNext( stack.getFirst());
            stack.getLast().setNext(null);

        }

        if(Objects.isNull(result)){
            return root;
        }else {
            return result;
        }
    }


    static void main() {

        LinkNode root=new LinkNode();
        root.setValue(0);

        LinkNode temp=root;
        for (int i = 1; i <=10 ; i++) {
           LinkNode values=  new LinkNode();
            values.setValue(i);
            temp.setNext(values);
            temp=values;

        }
        list a=new list();

      LinkNode vv=   a.revert(root,3);


      StringBuffer sb=new StringBuffer();
      temp=vv;
      while(Objects.nonNull(temp)){

          sb.append(temp.getValue());
          sb.append(",");

          temp=temp.getNext();
      }
      sb.substring(0,sb.length()-1);

        System.out.println(sb.toString());


    }
}
