package coding_problems.sedgewick.coursera.course1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE = 1.96;
    private final int n;
    private final int trials;
    private final double mean;
    private final double stddev;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
        validate(n);
        validate(trials);

        double[] results = new double[trials];
        for (int i = 0; i < trials; i++) {
            results[i] = makeExperiment();
        }

        mean = StdStats.mean(results);
        stddev = StdStats.stddev(results);
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, t);

        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println(
                "95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }

    private double makeExperiment() {
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int col = StdRandom.uniform(1, n + 1);
            int row = StdRandom.uniform(1, n + 1);
            percolation.open(row, col);
        }

        return percolation.numberOfOpenSites() / (double) (n * n);
    }

    private void validate(int val) {
        if (val <= 0) throw new IllegalArgumentException();
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE * stddev() / Math.sqrt(trials);
    }
}