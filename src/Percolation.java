import java.lang.IndexOutOfBoundsException;
public class Percolation {
	
	private boolean matrix[][];
	private WeightedQuickUnionUF union;
	private int virtual_top;
	private int virtual_bottom;
	private int side;
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int N)
	{
		
		int i,j;
		matrix=new boolean[N][N];

		for (i=0;i<N;i++)
			for (j=0;j<N;j++)
				matrix[i][j]=false;
				
		union=new WeightedQuickUnionUF(N*N+2);
		
		virtual_top=N*N;
		virtual_bottom=N*N+1;
		
		j=N*(N-1);
		
		for (i=0;i<N;i++)
		{
			union.union(i, virtual_top);
			union.union(j+i, virtual_bottom);
		}
		side=N;
		
	}
	
	private int getIndex(int i, int j)
	{
		return side*i+j;
	}
	
	// open site (row i, column j) if it is not already
	public void open(int i, int j) throws java.lang.IndexOutOfBoundsException
	{
		if (i>=side || j >=side)
			throw new java.lang.IndexOutOfBoundsException();
		matrix[i][j]=true;
		// left
		if(i>0 && matrix[i-1][j])
			union.union(getIndex(i-1,j), getIndex(i,j));
		// right
		if(i<side-1 && matrix[i+1][j])
			union.union(getIndex(i+1,j), getIndex(i,j));
		// up
		if(j>0 && matrix[i][j-1])
			union.union(getIndex(i,j-1), getIndex(i,j));
		// down
		if(j<side-1 && matrix[i][j+1])
			union.union(getIndex(i,j+1), getIndex(i,j));		
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j)throws java.lang.IndexOutOfBoundsException
	{
		if (i>=side || j >=side)
			throw new java.lang.IndexOutOfBoundsException();
		return matrix[i][j];
	}
	
	// is site (row i, column j) full?
	// A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
	public boolean isFull(int i, int j) throws java.lang.IndexOutOfBoundsException
	{
		if (i>=side || j >=side)
			throw new java.lang.IndexOutOfBoundsException();		
		return (isOpen(i,j) && union.connected(getIndex(i,j), virtual_top));
	}
	
	
	public boolean percolates()
	{
		return union.connected(virtual_top, virtual_bottom);
	}

}
