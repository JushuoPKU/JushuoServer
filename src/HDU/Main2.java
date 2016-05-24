package HDU;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by MebiuW on 16/5/21.
 */
public class Main2 {
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Map<String, Integer> map = new HashMap<String, Integer>();
        int t=input.nextInt();
        int counter=0;
        while(++counter<=t){
            int n=input.nextInt();
            int result[] = new int[n];
            int a[]=new int[n];
            int p[]=new int[n];
            for(int i=0;i<n;i++){
                a[i]=input.nextInt();
                p[i]=input.nextInt();
            }
        }
        //这里一定要关闭
        input.close();
        out.close();

    }
}
