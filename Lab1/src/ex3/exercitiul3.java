package ex3;

import java.util.Scanner;

public class exercitiul3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, i=0,k=0;
        System.out.println("da un n");
        n = sc.nextInt();
        for(i=1;i<=n;i++)
        {
            if(n%i==0) {
                System.out.println(i);
                k++;
            }
        }
        if(k==2)
            System.out.println("numarul n este prim");
    }
}
