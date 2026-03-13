package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class found {

    public void sort(int[] arrays){
        if(Objects.isNull(arrays)||arrays.length<=1){
            return;
        }

        int start=0;
        int end=arrays.length-1;
     doSort(arrays,start,end);

    }


    private void doSort(int[] arrays , int start, int end){
        int mid=(start+end)/2;

        int oldMid= mid;
        int curS=start;
        int cure=end;

        if(start>=end){
            return;
        }


        while(cure>mid||curS<mid){
            if(curS<mid){
                for (int i = curS; i <= mid; i++) {

                    if(arrays[i]>arrays[mid]){
                        swat(arrays,i,mid);
                        curS=i+1;
                        mid=i;
                        break;
                    }

                    curS=i;

                }

            }

            if(cure>mid){
                for (int i = cure; i >=mid; i--) {

                    if(arrays[i]<arrays[mid]){
                        swat(arrays,mid,i);
                        cure=i-1;
                        mid=i;
                    }

                    cure=i;
                }
            }

        }


        doSort(arrays,start, oldMid-1);
        doSort(arrays,oldMid+1,end);

        System.out.println("fdsfsd");
    }


    public void swat(int[] arrays, int index, int target){
        int temp= arrays[target];
        arrays[target]=arrays[index];
        arrays[index]=temp;
    }



    public static void main(){
//int[] arrays=new int[5];
//        for (int i = 0; i <5 ; i++) {
//            arrays[i]=4-i;
//        }
//
//
//        found found=new found();
//
//        found.sort(arrays);
//
//
//       Arrays.stream(arrays).forEach(x-> System.out.println(x));



        found found =new found();
////      boolean reuslt=  found.canPartition(new int[]{3,3,3,4,5});
//        int result= found.maxProfit(2,new int[]{3,3,5,0,0,3,1,4});
//        System.out.println(result);


      found.threadExe();
    }


    public boolean canPartition(int[] nums) {

        int sum= Arrays.stream(nums).sum();
        if (sum%2!=0){
            return false;
        }

        int target=sum/2;


        Set<Integer> result = new HashSet<>();
        AtomicBoolean hasFound=new AtomicBoolean(false);

        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>target){
                return false;
            }
            if(nums[i]==target){
                return true;
            }
            Set<Integer> needAdd=new HashSet<>();
            int cur=nums[i];
            result.forEach(x->{
                if(x+cur<target){
                    needAdd.add(x+cur);
                }
                if(x+cur==target){
                    hasFound.set(true);
                }

            });

            if(hasFound.get()){
                return true;
            }

            needAdd.add(cur);
            result.addAll(needAdd);

        }
       return false;
    }


    public int maxProfit(int k, int[] prices) {
        int[][] result=new int [prices.length][k*2];
        for (int i = 0; i <2*k; i++) {
            result[0][i]=Integer.MIN_VALUE/2;
        }


        result[0][0]=-prices[0];
        result[0][1]=0;

//        result[0]=-prices[0];
        for (int i = 1; i < prices.length; i++) {

            for (int j = 0; j < k; j++) {
                if(j>0){
                    result[i][j*2]=Math.max(result[i-1][j*2], result[i-1][j*2-1]-prices[i]);
                    result[i][j*2+1]=Math.max(result[i-1][j*2+1], result[i-1][j*2]+prices[i]);
                }else {

                    result[i][j*2]=Math.max(result[i-1][j*2],  -prices[i]);
                    result[i][j*2+1]=Math.max(result[i-1][j*2+1],result[i-1][0] +prices[i]);
                }
            }
        }
        return Arrays.stream(result[prices.length-1]).max().getAsInt();
    }





    public void threadExe()   {
        int num=2;
        Thread[] threads = new Thread[num];
        AtomicInteger value= new AtomicInteger(1);
        for (int i = 0; i < num; i++) {

            RunLoic temp= new RunLoic();
            temp.threads=threads;
            temp.index=i;
            temp.value=value;
            threads[i]= new Thread(temp);
        }

        for (int i = 0; i < num; i++) {
            threads[i].start();

        }


       LockSupport.unpark( threads[0]);
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public static class RunLoic implements   Runnable{

        private Thread[] threads;
        private int index;

        private AtomicInteger value;

        @Override
        public void run() {
            while(true){

                if(value.get()>100){
                    break;
                }
                LockSupport.park();
                System.out.println(value.getAndIncrement()+"------"+Thread.currentThread().threadId());
                LockSupport.unpark(threads[(index+1)%(threads.length)]);


            }


        }
    }
}
