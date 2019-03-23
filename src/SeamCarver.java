import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture picture;

    public SeamCarver(Picture picture) { // create a seam carver object based on the given picture
        if (picture == null) throw new IllegalArgumentException();
        this.picture = new Picture(picture);
    }

    public Picture picture() { // current picture
        return picture;
    }

    public int width() { // width of current picture
        return picture().width();
    }

    public int height() { // height of current picture
        return picture().height();
    }

    public double energy(int x, int y) { // energy of pixel at column x and row y
        validateValue(x, width());
        validateValue(y, height());

        return pixelEnergy(x, y);
    }

    public int[] findHorizontalSeam() { // sequence of indices for horizontal seam
        // sequence of indices for vertical seam
        EdgeWeightedDigraph graph = picToWeightedDigraph(true);
        AcyclicSP acyclicSP = new AcyclicSP(graph, getFakeLeftVertexIndex());

        int[] result = new int[width()];
        int index = 0;
        for (DirectedEdge edge : acyclicSP.pathTo(getFakeRightVertexIndex())) {
            if (index < result.length) {
                result[index++] = indexToY(edge.to());
            }
        }

        return result;
    }

    public int[] findVerticalSeam() { // sequence of indices for vertical seam
        EdgeWeightedDigraph graph = picToWeightedDigraph(false);
        AcyclicSP acyclicSP = new AcyclicSP(graph, getFakeTopVertexIndex());

        int[] result = new int[height()];
        int index = 0;
        for (DirectedEdge edge : acyclicSP.pathTo(getFakeBottomVertexIndex())) {
            if (index < result.length) {
                result[index++] = indexToX(edge.to());
            }
        }

        return result;
    }

    public void removeHorizontalSeam(int[] seam) { // remove horizontal seam from current picture
        validateSeam(seam, height(), width());
        Picture newPicture = new Picture(width(), height() - 1);
        for (int col = 0; col < width(); col++) {
            int seamY = seam[col];
            for (int row = 0; row < height() - 1; row++) {
                if (row < seamY) {
                    newPicture.set(col, row, picture.get(col, row));
                } else {
                    newPicture.set(col, row, picture.get(col, row + 1));
                }
            }
        }

        picture = newPicture;
    }

    public void removeVerticalSeam(int[] seam) { // remove vertical seam from current picture
        validateSeam(seam, width(), height());
        Picture newPicture = new Picture(width() - 1, height());
        for (int row = 0; row < height(); row++) {
            int seamX = seam[row];
            for (int col = 0; col < width() - 1; col++) {
                if (col < seamX) {
                    newPicture.set(col, row, picture.get(col, row));
                } else {
                    newPicture.set(col, row, picture.get(col + 1, row));

                }
            }
        }

        picture = newPicture;
    }

    private void validateSeam(int[] seam, int max, int length) {
        if (seam == null) throw new IllegalArgumentException();
        if (seam.length != length) throw new IllegalArgumentException();
        validateValue(seam[0], max);
        for (int i = 1; i < seam.length; i++) {
            if (Math.abs(seam[i - 1] - seam[i]) > 1) throw new IllegalArgumentException();
            validateValue(seam[i], max);
        }
    }

    private void validateValue(int value, int max) {
        if (value < 0 || value >= max) throw new IllegalArgumentException();
    }

    // dual-gradient energy function
    private double pixelEnergy(int x, int y) {
        int gradOnBorder = 1000;
        if (x <= 0 || x >= width() - 1) return gradOnBorder;
        if (y <= 0 || y >= height() - 1) return gradOnBorder;

        return Math.sqrt(gradientX(x, y) + gradientY(x, y));
    }

    private double gradientX(int x, int y) {
        int x1 = x - 1;
        int x2 = x + 1;
        return gradient(x1, y, x2, y);
    }

    private double gradientY(int x, int y) {
        int y1 = y - 1;
        int y2 = y + 1;
        return gradient(x, y1, x, y2);
    }

    private double gradient(int x1, int y1, int x2, int y2) {
        int rgb1 = picture.getRGB(x1, y1);
        int rgb2 = picture.getRGB(x2, y2);

        return pow2(toRed(rgb1) - toRed(rgb2)) +
                pow2(toGreen(rgb1) - toGreen(rgb2)) +
                pow2(toBlue(rgb1) - toBlue(rgb2));
    }

    private EdgeWeightedDigraph picToWeightedDigraph(boolean horizontal) {
        int maxIndex = height() * width();
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(maxIndex + 4); // 4 fake vertices

        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                double pixelEnergy = energy(col, row);
                int currentPixelIndex = posToIndex(col, row);
                if (horizontal) { // left-to-right seam
                    if (col != width() - 1) {
                        graph.addEdge(new DirectedEdge(currentPixelIndex, posToIndex(col + 1, row), pixelEnergy)); // right
                        if (row != 0) {
                            graph.addEdge(new DirectedEdge(currentPixelIndex, posToIndex(col + 1, row - 1), pixelEnergy)); // top
                        }
                        if (row != height() - 1) {
                            graph.addEdge(new DirectedEdge(currentPixelIndex, posToIndex(col + 1, row + 1), pixelEnergy)); // bottom
                        }
                    }
                } else { // top-to-bottom seam
                    if (row != height() - 1) {
                        graph.addEdge(new DirectedEdge(currentPixelIndex, posToIndex(col, row + 1), pixelEnergy)); // bottom
                        if (col != 0) {
                            graph.addEdge(new DirectedEdge(currentPixelIndex, posToIndex(col - 1, row + 1), pixelEnergy)); // left
                        }
                        if (col != width() - 1) {
                            graph.addEdge(new DirectedEdge(currentPixelIndex, posToIndex(col + 1, row + 1), pixelEnergy)); // right
                        }
                    }
                }

                // fake vertices
                if (horizontal) {
                    if (col == 0) {
                        graph.addEdge(new DirectedEdge(getFakeLeftVertexIndex(), currentPixelIndex, 0));
                    }
                    if (col == width() - 1) {
                        graph.addEdge(new DirectedEdge(currentPixelIndex, getFakeRightVertexIndex(), 0));
                    }
                } else {
                    if (row == 0) { // add fake top vertex
                        graph.addEdge(new DirectedEdge(getFakeTopVertexIndex(), currentPixelIndex, 0));
                    }
                    if (row == height() - 1) { // add fake bottom vertex
                        graph.addEdge(new DirectedEdge(currentPixelIndex, getFakeBottomVertexIndex(), 0));
                    }
                }
            }
        }

        return graph;
    }

    private int toRed(int argb) {
        return (argb >> 16) & 0xFF;
    }

    private int toGreen(int argb) {
        return (argb >> 8) & 0xFF;
    }

    private int toBlue(int argb) {
        return (argb) & 0xFF;
    }

    private int getFakeTopVertexIndex() {
        return height() * width();
    }

    private int getFakeBottomVertexIndex() {
        return getFakeTopVertexIndex() + 1;
    }

    private int getFakeLeftVertexIndex() {
        return getFakeTopVertexIndex() + 2;
    }

    private int getFakeRightVertexIndex() {
        return getFakeTopVertexIndex() + 3;
    }

    private int posToIndex(int x, int y) {
        return y * width() + x;
    }

    private int indexToX(int index) {
        return index % width();
    }

    private int indexToY(int index) {
        return index / width();
    }

    private double pow2(double value) {
        return Math.pow(value, 2);
    }
}
