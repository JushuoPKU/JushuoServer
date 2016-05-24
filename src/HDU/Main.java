package HDU;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by MebiuW on 16/5/21.
 */
public class Main {
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n=input.nextInt();
        int counter=1;
        while(n-->0){
            int x = input.nextInt();
            long m = input.nextLong();
            int k = input.nextInt();
            int c = input.nextInt();
            boolean flag=false;
            int tmp=x;
            int i=0;
            while(i++<m && tmp< k){
                tmp=tmp*10+x;
            }
            if(k==0) flag= false;
            else flag = (tmp%k)==c;
            out.println("Case #"+counter+":");
            if(flag)
                out.println("Yes");
            else out.println("No");
            counter++;

        }
        //这里一定要关闭
        input.close();
        out.close();

    }
}
