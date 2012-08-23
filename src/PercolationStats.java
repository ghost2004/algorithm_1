import java.util.Random;
import java.lang.Math;
import java.lang.IllegalArgumentException;

public class PercolationStats {
	private Percolation model;
	private double record[];
	private int tried_times;
	private double mean_value;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) throws Exception
	{
		if (N<=0 || T<=0)
			throw new java.lang.IllegalArgumentException();
		tried_times=T;
		model= new Percolation(N);
		Random rd1 = new Random();
		record=new double[T];
		int array_size=N*N;
		
		mean_value=0;
		
		for (int c=0;c<T;c++)
		{
			model= new Percolation(N);
			int times=0;
			while (!model.percolates())
			{
				boolean gotit=false;
				
				while (!gotit)
				{
					int idx=rd1.nextInt(array_size);
					int i=idx/N;
					int j=idx%N;
					if (!model.isOpen(i, j))
					{
						gotit=true;
						model.open(i, j);
					}
					
				}
				
				times++;
				
			}
			
			record[c]=(double)times/array_size;
			mean_value+=record[c];
		}
		
		mean_value/=T;
	}
	
	// sample mean of percolation threshold
	public double mean()
	{
		return mean_value;
	}
	
	 // sample standard deviation of percolation threshold
	public double stddev()
	{
		double out=0;
		for (int i=0;i<tried_times;i++)
		{
			out+=(record[i]-mean_value)*(record[i]-mean_value);
		}
		out= Math.sqrt(out/(tried_times-1));
		return out;
	}
	
	public static void main(String[] args)
	{
		int N = StdIn.readInt();
		int T = StdIn.readInt();
		try
		{
			PercolationStats percol=new PercolationStats(N,T);
			double mean=percol.mean();
			double stddev_vaule=percol.stddev();
			double intval=(1.96*stddev_vaule)/Math.sqrt(T);
			double intval1=mean-intval;
			double intval2=mean+intval;
			StdOut.println("mean                    ="+mean);
			StdOut.println("stddev                  ="+stddev_vaule);
			StdOut.println("95% confidence interval ="+intval1+", "+intval2);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
