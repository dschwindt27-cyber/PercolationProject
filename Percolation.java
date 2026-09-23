import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation  {

    private final int n;
    private final boolean[] openSites;
    private final WeightedQuickUnionUF percolationUF;
    private final WeightedQuickUnionUF fullnessUF;
    private final int virtualTop;
    private final int virtualBottom;
    private int openCount;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
        this.openSites = new boolean[n * n];
        this.virtualTop = n * n;
        this.virtualBottom = n * n + 1;
        this.percolationUF = new WeightedQuickUnionUF(n * n + 2);
        this.fullnessUF = new WeightedQuickUnionUF(n * n + 1);
        this.openCount = 0;
    }

    public void open(int row, int col) {
        validate(row, col);
        int site = index(row, col);
        if (openSites[site]) {
            return;
        }
        openSites[site] = true;
        openCount++;

        if (row == 1) {
            percolationUF.union(site, virtualTop);
            fullnessUF.union(site, virtualTop);
        }
        if (row == n) {
            percolationUF.union(site, virtualBottom);
        }

        connectIfOpen(site, row - 1, col);
        connectIfOpen(site, row + 1, col);
        connectIfOpen(site, row, col - 1);
        connectIfOpen(site, row, col + 1);
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int site = index(row, col);
        return openSites[site] && fullnessUF.find(site) == fullnessUF.find(virtualTop);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return percolationUF.find(virtualTop) == percolationUF.find(virtualBottom);
    }

    private void connectIfOpen(int site, int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            return;
        }
        int neighbor = index(row, col);
        if (openSites[neighbor]) {
            percolationUF.union(site, neighbor);
            fullnessUF.union(site, neighbor);
        }
    }

    private int index(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException(
                    "row and col must be between 1 and " + n + ": (" + row + ", " + col + ")");
        }
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(3);
        perc.open(1, 2);
        perc.open(2, 2);
        StdOut.println("percolates after (1,2),(2,2): " + perc.percolates());
        perc.open(3, 2);
        StdOut.println("percolates after (3,2):       " + perc.percolates());
        perc.open(3, 1);
        StdOut.println("isFull(3,1) (backwash check): " + perc.isFull(3, 1));
        StdOut.println("open sites: " + perc.numberOfOpenSites());
    }
}
