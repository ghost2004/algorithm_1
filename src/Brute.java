import java.util.Arrays;

public class Brute {
    private Point[] pointArray;
    private final int pointNumber = 4;
    public Brute(Point[] array) {
        this.pointArray = array;
    }
    
    public void findPoints() {
        int[] flag = new int[pointNumber];
        combine(flag, 0, 0, pointNumber);
    }
    
    private void checkPoints(int[] flag) {
        Point[] p = new Point[4];
        for (int i = 0; i < pointNumber; i++)
            p[i] = pointArray[flag[i]];
        if (p[0].SLOPE_ORDER.compare(p[1], p[2]) == 0 
            && p[0].SLOPE_ORDER.compare(p[2], p[3]) == 0) {
            for (int j = 0; j < pointNumber -1; j++)
                StdOut.print(p[j].toString()+" -> ");
            StdOut.println(p[pointNumber -1]);
        }
    }
    
    private void combine(int[] flag, int index, int start, int length) {
        if (length <= 0) {
            checkPoints(flag);
            return;
        }
        StdOut.println(index+" start:"+start+" length"+length);    
        flag[index] = start;
        combine(flag, index+1, start+1, length-1);
        combine(flag, index, start+1, length);
        
        return;    
    }
    
    public static void main(String[] args) {
        if (args.length < 1)
            return;
        
        String filename = args[0];
        
        Point[] pointArray;
        
        pointArray = Point.getFromText(filename);
        
        if (pointArray == null || pointArray.length < 4)
            return;
        
        StdOut.println("Total "+pointArray.length);
        
        Arrays.sort(pointArray);
        
        Brute brute = new Brute(pointArray);
        
        brute.findPoints();
       

    }
}
