public class Q开n次方根 {
    public static void main(String[] args)
    {
        System.out.println(getNthRoot(10,4, 0.0001));
    }

    public static double getNthRoot(int a, int b, double error){
        double start = 0;
        double end = a;
        double mid = start + (end - start) / 2.0;

        while (Math.abs(a - Math.pow(mid, b)) > error){
            if (a < Math.pow(mid, b))
                end = mid;
            else
                start = mid;

            mid = start + (end - start) / 2.0;
        }
        return mid;
    }
}
