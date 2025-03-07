import java.util.Scanner;

public class E6_5 {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("enter size of array");
        int n=scanner.nextInt();

        float[] arr=new float[n];
        for (int i=0;i< arr.length;i++)
        {
            System.out.println("enter elements of array");
            arr[i]=scanner.nextFloat();
        }

        float result1=average(arr);
        System.out.println("average is "+ result1);
        float result2=smallest(arr);
        System.out.println("smallest is "+ result2);
        float result3=largest(arr);
        System.out.println("largest is "+ result3);
        float result4=range(arr);
        System.out.println("range is "+ result4);
    }

    static float average(float[] arr)
    {
        float sum=0,average=0;
        for (int i=0;i< arr.length;i++)
        {
            sum+=arr[i];
        }
        average=sum/ arr.length;
        return average;
    }

    static float smallest(float[] arr)
    {
        float min=arr[0];
        for (int i=0;i< arr.length;i++)
        {
            if (min>arr[i]) min=arr[i];
        }
        return min;
    }

    static float largest(float[] arr)
    {
        float max=arr[0];
        for (int i=0;i< arr.length;i++)
        {
            if (max<arr[i]) max=arr[i];
        }
        return max;
    }

    static float range(float[] arr)
    {
        float max=largest(arr);
        float min=smallest(arr);
        float range=max-min;
        return range;
    }
}
