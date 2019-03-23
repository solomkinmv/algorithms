import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Picture;

import java.awt.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class SeamCarver {

    private Picture picture;

    public SeamCarver(Picture picture) {// create a seam carver object based on the given picture
        this.picture = new Picture(picture);
    }

    public Picture picture() {// current picture
        return picture;
    }

    public int width() {// width of current picture
        return picture().width();
    }

    public int height() {// height of current picture
        return picture().height();
    }

    public double energy(int x, int y) {// energy of pixel at column x and row y
        return pixelEnergy(x, y);
    }

    public int[] findHorizontalSeam() {// sequence of indices for horizontal seam
        // sequence of indices for vertical seam
        EdgeWeightedDigraph graph = picToWeightedDigraph(true);
        AcyclicSP acyclicSP = new AcyclicSP(graph, getFakeLeftVertexIndex());

        int[] result = new int[width()];
        int index = 0;
        for (DirectedEdge edge : acyclicSP.pathTo(getFakeRightVertexIndex())) {
            if (++index < result.length) {
                result[index] = indexToY(edge.to());
            }
        }

        return result;
    }

    public int[] findVerticalSeam() {// sequence of indices for vertical seam
        EdgeWeightedDigraph graph = picToWeightedDigraph(false);
        AcyclicSP acyclicSP = new AcyclicSP(graph, getFakeTopVertexIndex());

        int[] result = new int[height()];
        int index = 0;
        for (DirectedEdge edge : acyclicSP.pathTo(getFakeBottomVertexIndex())) {
            if (++index < result.length) {
                result[index++] = indexToX(edge.to());
            }
        }

        return result;
    }

    public void removeHorizontalSeam(int[] seam) {// remove horizontal seam from current picture
        Picture newPicture = new Picture(width(), height() - 1);
        int[] horizontalSeam = seam;
        for (int col = 0; col < width(); col++) {
            int seamY = horizontalSeam[col];
            for (int row = 0; row < height() - 1; row++) {
                if (row <= seamY) {
                    newPicture.set(col, row, picture.get(col, row));
                } else {
                    newPicture.set(col, row, picture.get(col, row + 1));
                }
            }
        }

        picture = newPicture;
    }

    public void removeVerticalSeam(int[] seam) {// remove vertical seam from current picture
        Picture newPicture = new Picture(width() - 1, height());
        int[] verticalSeam = seam;
        for (int row = 0; row < height(); row++) {
            int seamX = verticalSeam[row];
            for (int col = 0; col < width() - 1; col++) {
                if (col <= seamX) {
                    newPicture.set(col, row, picture.get(col, row));
                } else {
                    newPicture.set(col, row, picture.get(col + 1, row));

                }
            }
        }

        picture = newPicture;
    }

    // dual-gradient energy function
    private double pixelEnergy(int x, int y) {
        int gradOnBorder = 1000;
        if (x <= 0 || x >= width() - 1) return gradOnBorder;
        if (y <= 0 || y >= height() - 1) return gradOnBorder;

        return sqrt(gradientX(x, y) + gradientY(x, y));
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
        return pow2(rDif(x1, y1, x2, y2)) + pow2(gDif(x1, y1, x2, y2)) + pow2(bDif(x1, y1, x2, y2));
    }

    private double rDif(int x1, int y1, int x2, int y2) {
        // todo: optimize
        Color color1 = picture.get(x1, y1);
        Color color2 = picture.get(x2, y2);

        return color1.getRed() - color2.getRed();
    }

    private double gDif(int x1, int y1, int x2, int y2) {
        Color color1 = picture.get(x1, y1);
        Color color2 = picture.get(x2, y2);

        return color1.getGreen() - color2.getGreen();
    }

    private double bDif(int x1, int y1, int x2, int y2) {
        Color color1 = picture.get(x1, y1);
        Color color2 = picture.get(x2, y2);

        return color1.getBlue() - color2.getBlue();
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
        return pow(value, 2);
    }
}
