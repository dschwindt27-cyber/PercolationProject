import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private WeightedQuickUnionUF[] uf;
    private boolean[] openSites;
    private int n;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n <= 0) {throw new IllegalArgumentException("n must be greater than 0");}
        this.n = n;
        this.uf = new WeightedQuickUnionUF[n*n+2];
        this.openSites = new boolean[n*n+2];
        openSites[0] = true;
        openSites[n*n+2] = true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!openSites[((n - 1) * row) + col]){openSites[((n-1)* row) + col] = true;}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return openSites[((n - 1) * row) + col];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return !openSites[((n - 1) * row) + col];
    }


    // returns the number of open sites
    public int numberOfOpenSites(){
        for (int i = 0; i < n*n+2; i++){

        }
    }

    /*
    // does the system percolate?
    public boolean percolates()

    // test client (optional)
    public static void main(String[] args)
     */
}
