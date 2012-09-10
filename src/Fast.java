import java.util.Arrays;
import java.util.Vector;
import java.util.Iterator;

public class Fast {
    private static Point[] pointArray;
    
   
    private static final int POINTNUM = 3;
    public Fast() {

    }
    
    
    private static void findPoints() {
        int j;
        
        
        Vector<Point> vector = new Vector<Point>();
        
        for (int i = 1; i <= pointArray.length - POINTNUM; i++) {
            
            Point[] sortArray = new Point[pointArray.length];
            // Copy the point array for sorting
            for (j = 0; j < pointArray.length; j++) {
                sortArray[j] = pointArray[j];
            }

            Point base = pointArray[i-1];
            
            Arrays.sort(sortArray, i, pointArray.length,
                    base.SLOPE_ORDER);

            vector.add(sortArray[i]);
            double slope = base.slopeTo(sortArray[i]);
           
            for (j = i + 1; j < pointArray.length; j++) {
                double curSlope = base.slopeTo(sortArray[j]);
                if (slope == curSlope) {
                    vector.add(sortArray[j]);
                }
                else {
                    if (vector.size() >= POINTNUM) {
                        int num = vector.size() + 1;
                        StdOut.print(num+": ");
                        StdOut.print(base.toString()+" -> ");
                        printPoints(vector);
                    }
                    slope = curSlope;
                    vector.clear();
                    vector.add(sortArray[j]);
                }
                    
            }
            
            if (vector.size() >= POINTNUM) {
                int num = vector.size() + 1;
                StdOut.print(num+": ");
                StdOut.print(base.toString()+" -> ");
                printPoints(vector);
            }
            
            vector.clear();
        }
        
    }
    
    private static void printPoints(Vector<Point> vector) {
    
        Iterator<Point> iter = vector.iterator();
        
        while (iter.hasNext()) {
            StdOut.print(iter.next().toString());
            if (iter.hasNext())
                StdOut.print(" -> ");
        }
        StdOut.println();
        
    }
    
    public static void main(String[] args) {
        if (args.length < 1)
            return;
        
        String filename = args[0];
        
        Point[] pArray;
        
        In in = new In(filename);
        int N = in.readInt();
        pArray = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            pArray[i] = p;
        }

        
        if (pArray == null || pArray.length < 4)
            return;
        
        Arrays.sort(pArray);
        
        pointArray = pArray;
                   
        findPoints();
    }
}
