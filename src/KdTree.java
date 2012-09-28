
public class KdTree {
    
    private static class KDNode {
        private Point2D p;      // the point
        // the axis-aligned rectangle corresponding to this node
        private RectHV rect;
        private KDNode lb;        // the left/bottom subtree
        private KDNode rt;        // the right/top subtree
        private int level;      // the level of node;
    }
    
    private KDNode root;
    private int totalNum;
    
    // construct an empty set of points
    public KdTree() {
        root = null;
        totalNum = 0;
        
    }
    
    // is the set empty?
    public boolean isEmpty() {
        return (totalNum == 0);
    }
    
    // number of points in the set
    public int size() {
        return totalNum;
    }
    

    
    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        
    }
    
    // does the set contain the point p?
    public boolean contains(Point2D p) {
        
    }
    
    // draw all of the points to standard draw
    public void draw() {
        
    }
    
    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        
    }
    
    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        
    }
}
