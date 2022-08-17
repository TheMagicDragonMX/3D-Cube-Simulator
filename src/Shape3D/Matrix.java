package Shape3D;

public class Matrix {

  double matrix[][];

  public Matrix () {
    matrix = new double [4][4];
  }

  public Matrix (double sourceMatrix[][]) {
    matrix = sourceMatrix;
  }

  /**
   * Multiplies the current matrix by the given
   * matrix
   * 
   * @param otherMatrix the one to multiply with
   * @return A new matrix product of the operation
   */
  public Matrix multiply (Matrix otherMatrix) {
    Matrix result = new Matrix();

    for (int row = 0; row < 4; row++) 
      for (int column = 0; column < 4; column++) {
        double sum = 0;

        for (int i = 0; i < 4; i++) 
          sum += matrix[row][i] * otherMatrix.getCell(i, column);

        result.setCell(row, column, sum);
      }

    return result;
  }

  /**
   * Returns the cell at the given position
   * 
   * @param row of the cell
   * @param column of the cell
   * @return number in the given cell
   */
  public double getCell (int row, int column) {
    return matrix[row][column];
  }


  /**
   * Sets the given cell position with the given value
   * 
   * @param row of the cell
   * @param column of the cell
   * @param newValue that the cell will get
   */
  public void setCell (int row, int column, double newValue) {
    matrix[row][column] = newValue;
  }

  /**
   * Returns the 2D array of the matrix
   * @return a 2D double array
   */
  public double[][] getMatrix () {
    return matrix;
  }
}
