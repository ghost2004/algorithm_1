
public class Board {
    
    private int N;
    private int[][] block;
    
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        block = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                block[i][j] = blocks[i][j];
    }
    
    // board dimension N
    public int dimension() {
        return N;
    }
    
    // number of blocks out of place
    public int hamming() {
        int out = 0;
        
        
        
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int idealNum = i*N + j +1;
                if (block[i][j] != idealNum 
                        && block[i][j] != 0)
                    out++;

            }
        
        
        return out;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int out = 0;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int idealNum = i*N + j +1;
                if (block[i][j] != idealNum 
                        && block[i][j] != 0) {
                    int row = block[i][j] / N;
                    int col = block[i][j] % N;
                    out += Math.abs(row - i) + Math.abs(col -j);
                    
                }
            }
        
        return out;
    }
    
    // is this board the goal board?
    public boolean isGoal() {
        boolean out = true;
        
        for (int i = 0; i < N*N -1; i++) {
            int row = i / N;
            int col = i % N;
            if (block[row][col] != i+1)
                return false;
        }
        
        return out;
    }

}
