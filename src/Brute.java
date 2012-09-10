import java.util.Arrays;

public class Brute {
    private static Point[] pointArray;
    private static final int POINTNUM = 4;
    public Brute() {
        
    }


    public static void findPoints() {
        int[] flag = new int[POINTNUM];
        combine(flag, 0, 0, POINTNUM);
    }
    
    private static void checkPoints(int[] flag) {
        Point[] p = new Point[4];
        for (int i = 0; i < POINTNUM; i++)
            p[i] = pointArray[flag[i]];
        if (p[0].SLOPE_ORDER.compare(p[1], p[2]) == 0 
            && p[0].SLOPE_ORDER.compare(p[2], p[3]) == 0) {
            StdOut.print(POINTNUM+": ");
            for (int j = 0; j < POINTNUM -1; j++)
                StdOut.print(p[j].toString()+" -> ");
            StdOut.println(p[POINTNUM -1]);
        }
    }
    
    private static void combine(int[] flag, int index, int start, int length) {
        if (length <= 0) {
            checkPoints(flag);
            return;
        }
        if (start >= pointArray.length)
            return;
        //StdOut.println("index:"+index+" start:"+start+" length:"+length);    
        flag[index] = start;
        combine(flag, index+1, start+1, length-1);
        combine(flag, index, start+1, length);
        
        return;    
    }
    
    public static void main(String[] args) {
        if (args.length < 1)
            return;
        
        String filename = args[0];
        
        In in = new In(filename);
        int N = in.readInt();
        Point[] pArray = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            pArray[i] = p;
        }

        
        if (pArray == null || pArray.length < 4)
            return;
        
        //StdOut.println("Total "+pointArray.length);
        
        Arrays.sort(pArray);
        
        pointArray = pArray;
        
       
        findPoints();
       

    }
}
