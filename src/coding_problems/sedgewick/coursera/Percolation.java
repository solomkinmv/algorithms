package coding_problems.sedgewick.coursera;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP_CELL_ID = 0;
    private final WeightedQuickUnionUF ufTop;
    private final WeightedQuickUnionUF ufTopBottom;
    private final int n;
    private final boolean[] opened;
    private final int bottomCellId;
    private boolean percolates;
    private int numberOfOpen;
    private int nSquare;

    public Percolation(int n) {
        this.n = n;                 // create n-by-n grid, with all sites blocked
        if (n <= 0) throw new IllegalArgumentException();

        nSquare = n * n;
        bottomCellId = nSquare + 1;
        opened = new boolean[nSquare + 2];
        ufTop = new WeightedQuickUnionUF(nSquare + 2);
        ufTopBottom = new WeightedQuickUnionUF(nSquare + 2);
        opened[TOP_CELL_ID] = true;
        opened[bottomCellId] = true;
    }

    private void connect(int idOrigin, int rowDest, int colDest) {
        if (colDest < 1 || colDest > n) {
            return;
        }

        int idDest = toId(rowDest, colDest);

        if (ufTop.connected(idOrigin, idDest) || !isOpenWithoutValidation(rowDest, colDest))
            return;

        if (rowDest <= n) {
            ufTop.union(idOrigin, idDest);
        }
        ufTopBottom.union(idOrigin, idDest);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int id = toId(row, col);


        if (opened[id]) return;
        opened[id] = true;
        numberOfOpen++;

        connect(id, row - 1, col);
        connect(id, row + 1, col);
        connect(id, row, col - 1);
        connect(id, row, col + 1);
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);

        return isOpenWithoutValidation(row, col);
    }

    private boolean isOpenWithoutValidation(int row, int col) {
        return opened[toId(row, col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return isOpen(row, col) && ufTop.connected(toId(row, col), TOP_CELL_ID);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (!percolates) {
            recalculatePercolation();
        }
        return percolates;
    }

    private void recalculatePercolation() {
        percolates = ufTopBottom.connected(TOP_CELL_ID, bottomCellId);
    }

    private void validate(int row, int col) {
        validate(row);
        validate(col);
    }

    private void validate(int val) {
        if (val < 1 || val > n) throw new IllegalArgumentException();
    }

    private int toId(int row, int col) {
        if (row == 0) return TOP_CELL_ID;
        if (row == n + 1) return bottomCellId;
        return (row - 1) * n + col;
    }
}