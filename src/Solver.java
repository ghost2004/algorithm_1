import java.util.Vector;

public class Solver {
    private Board initial;
    private Board twin;
    private int minMoves = -1;
    private boolean solvable = false;
    private Vector<Board> vector = null;
    private MinPQ<PQKey> mainMinPQ;
    private MinPQ<PQKey> twinMinPQ;
    
    private class PQKey implements Comparable<PQKey>
    {
        private Board state;
        private int moves;
        
        
        public int compareTo(PQKey that) {
            return (this.moves + this.state.manhattan())
                    - (that.moves + that.state.manhattan());
        }
    }
    
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
        twin = initial.twin();

    }
    
    // is the initial board solvable? 
    public boolean isSolvable() {
        return solvable;
        
    }
    
    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        return minMoves;
    }
    
    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution()  {
        return vector;
    }
    
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
