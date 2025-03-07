import java.util.Scanner;

public class E6_2_b {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter size of array");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        int i,coun1=0,count2=0;

        for (i = 0; i < n; i++)
        {
            System.out.println("enter array elements");
            arr[i] = scanner.nextInt();
        }

        for(i=0;i<n;i++)
        {
           if(arr[i]%2==0) coun1++;
           if(arr[i]%2!=0) count2++;
        }
        System.out.println("enen count is"+ coun1);
        System.out.println("odd count is"+ count2);
    }
}
