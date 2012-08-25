import java.util.Random;


/**
 *
 * @author Eric
 *
 */
public class PercolationStats {
    private Percolation model;
    private double[] record;
    private int triedTimes;
    private double meanValue;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) throws Exception
    {
        
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
            
        triedTimes = T;
        model = new Percolation(N);
        Random rd1 = new Random();
        record = new double[T];
        int arraySize = N * N;

        meanValue = 0;
        
        for (int c = 0; c < T; c++)
        {
            model = new Percolation(N);
            int times = 0;
            while (!model.percolates()) {
                boolean gotit = false;

                while (!gotit) {
                    int idx = rd1.nextInt(arraySize);
                    int i = idx / N +1;
                    int j = idx % N +1;
                    if (!model.isOpen(i, j))
                    {
                        gotit = true;
                        model.open(i, j);
                    }
                    
                }
                
                times++;
                
            }
            
            record[c] = (double) times/arraySize;
            meanValue += record[c];
        }
        
        meanValue /= T;
    }
    
    // sample mean of percolation threshold
    public double mean()
    {
        return meanValue;
    }
    
     // sample standard deviation of percolation threshold
    public double stddev()
    {
        if (triedTimes == 1)
            return Double.NaN;
        double out = 0;
        for (int i = 0; i < triedTimes; i++)
        {
            out += (record[i]-meanValue)*(record[i]-meanValue);
        }
        out = Math.sqrt(out/(triedTimes-1));
        return out;
    }
    
    /**
     *
     * @param args String
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        int num = StdIn.readInt();
        int times = StdIn.readInt();
        try {
            PercolationStats percol = new PercolationStats(num, times);
            double mean = percol.mean();
            double stdValue = percol.stddev();
            double intval = (1.96*stdValue)/Math.sqrt(times);
            double intval1 = mean - intval;
            double intval2 = mean + intval;
            StdOut.println("mean                    ="+mean);
            StdOut.println("stddev                  ="+stdValue);
            StdOut.println("95% confidence interval ="+intval1+", "+intval2);
            
        }
        catch (java.lang.IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (java.lang.IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        
    }
    
    

}

