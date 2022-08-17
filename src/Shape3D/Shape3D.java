package Shape3D;

import java.util.ArrayList;

public class Shape3D {  
  private double zAxisAngle;

  private int x;
  private int y;
  private int z;

  private float zoom;

  private double xRotation;
  private double yRotation;
  private double zRotation;
  
  private Matrix rootMatrix;
  private ArrayList<Matrix> movementMatrices;
  private ArrayList<Matrix> coords3DMatrices;

  private ArrayList<Integer> xCoords2D;
  private ArrayList<Integer> yCoords2D;

  /**
   * Initializes a new 3D shape
   */
  public Shape3D () {
    movementMatrices = new ArrayList<>();
    coords3DMatrices = new ArrayList<>();

    xCoords2D = new ArrayList<>();
    yCoords2D = new ArrayList<>();
  }

  /**
   * Gives a 3D shape model to the current shape
   * and updates its movement matrices
   * 
   * @param model a model for the shape, which must be defined elsewhere
   */
  public void setModel (Shape3DModel model) {
    movementMatrices.clear();

    for (Move move : model.getMoves()) // Convert every move into a matrix 
      movementMatrices.add( new Matrix( new double [][] {
        { 1, 0, 0, move.x },
        { 0, 1, 0, move.y },
        { 0, 0, 1, move.z },
        { 0, 0, 0,   1    }
      }));
  }
  
  /**
   * Sets the angle of the simulated Z axis
   * @param angle
   */
  public void setZAxisAngle (double angle) {
    this.zAxisAngle = angle;
  }

  /**
   * Sets the position of the shape in a 3D environment
   * @param x coord
   * @param y coord
   * @param z coord
   */
  public void setPosition(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Sets how closer the shape looks
   * @param zoomMultiplier amount of zoom
   */
  public void setZoom (float zoomMultiplier) {
    this.zoom = zoomMultiplier;
  }

  /**
   * Set the rotation of the shape in the X axis 
   * @param xRotationAngle rotation angle in degrees
   */  
  public void setXRotation (double xRotationAngle) {
    this.xRotation = xRotationAngle;
  }

  /**
   * Returns the X rotation in degrees
   * @return a double number
   */
  public double getXRotation() {
    return xRotation;
  }

  /**
   * Set the rotation of the shape in the Y axis 
   * @param yRotationAngle rotation angle in degrees
   */  
  public void setYRotation (double yRotationAngle) {
    this.yRotation = yRotationAngle;
  }

  /**
   * Returns the Y rotation in degrees
   * @return a double number
   */
  public double getYRotation() {
    return yRotation;
  }

  /**
   * Set the rotation of the shape in the Z axis 
   * @param zRotationAngle rotation angle in degrees
   */  
  public void setZRotation (double zRotationAngle) {
    this.zRotation = zRotationAngle;
  }

  /**
   * Returns the Z rotation in degrees
   * @return a double number
   */
  public double getZRotation() {
    return zRotation;
  }

  /**
   * Takes the position, zoom and rotation values
   * to calculate the coords that will be used 
   * to draw the shape
   */
  public void calculateCoords () {
    calculateRootMatrix();
    
    calculate3DCoords();
    calculate2DCoords();
  }

  /**
   * Calculates the root matrix depending on
   * - The position in X, Y and Z
   * - Zoom
   * - X rotation angle
   * - Y rotation angle
   * - Z rotation angle
   */
  private void calculateRootMatrix () {
    Matrix movementMatrix = new Matrix( new double[][] {
      { 1, 0, 0, x },
      { 0, 1, 0, y },
      { 0, 0, 1, z },
      { 0, 0, 0, 1 },
    });
    
    Matrix zoomMatrix = new Matrix( new double[][] {
      { zoom,  0,   0,  0 },
      {   0, zoom,  0,  0 },
      {   0,   0, zoom, 0 },
      {   0,   0,   0,  1 },
    });
    
    Matrix xRotationMatrix = new Matrix( new double [][] {
      { 1,                 0,                                     0,                   0 },
      { 0, Math.cos(Math.toRadians(xRotation)), - Math.sin(Math.toRadians(xRotation)), 0 },
      { 0, Math.sin(Math.toRadians(xRotation)),   Math.cos(Math.toRadians(xRotation)), 0 },
      { 0,                 0,                                     0,                   1 },
    });

    Matrix yRotationMatrix = new Matrix( new double [][] {
      {   Math.cos(Math.toRadians(yRotation)), 0, Math.sin(Math.toRadians(yRotation)), 0 },
      {                   0,                   1,                 0,                   0 },
      { - Math.sin(Math.toRadians(yRotation)), 0, Math.cos(Math.toRadians(yRotation)), 0 },
      {                   0,                   0,                 0,                   1 },
    });

    Matrix zRotationMatrix = new Matrix( new double [][] {
      {  Math.cos(Math.toRadians(zRotation)), - Math.sin(Math.toRadians(zRotation)), 0, 0 },
      {  Math.sin(Math.toRadians(zRotation)),   Math.cos(Math.toRadians(zRotation)), 0, 0 },
      {                  0,                                     0,                   1, 0 },
      {                  0,                                     0,                   0, 1 },
    });

    rootMatrix = 
      movementMatrix
        .multiply(zoomMatrix)
        .multiply(xRotationMatrix)
        .multiply(yRotationMatrix)
        .multiply(zRotationMatrix);
  }

  /**
   * Takes the root matrix and the movement matrices
   * to calculate the coords that will be used to 
   * draw the shape
   */
  private void calculate3DCoords () {
    coords3DMatrices.clear();
    coords3DMatrices.add( rootMatrix ); // Set initial coord from the root matrix

    for (Matrix matrix : movementMatrices) 
      coords3DMatrices.add(
        coords3DMatrices.get( coords3DMatrices.size() - 1 ) // Previous calculated matrix
        .multiply(matrix) // Current movement matrix
      );
  }

  /**
   * Takes the 3D coords matrices, extracts their 3D coords
   * and convert them into 2D coords
   */
  private void calculate2DCoords () {
    yCoords2D.clear();
    xCoords2D.clear();

    for (Matrix coord3DMatrix : coords3DMatrices) {

      // Get 3D coords from the matrices
      float x3DCoord = (float) coord3DMatrix.getCell(0, 3);
      float y3DCoord = (float) coord3DMatrix.getCell(1, 3);
      float z3DCoord = (float) coord3DMatrix.getCell(2, 3);

      // Convert 3D coords to 2D coords by using the formula
      // X2D = X3D - Z3D * cos(alpha)
      // Y2D = Y3D - Z3D * sin(alpha)
      int x2DCoord = Math.round(x3DCoord - z3DCoord * (float) Math.cos(zAxisAngle));
      int y2DCoord = Math.round(y3DCoord - z3DCoord * (float) Math.sin(zAxisAngle));

      // Add coords to array
      xCoords2D.add(x2DCoord);
      yCoords2D.add(y2DCoord);
    }
  }

  /**
   * Returns Y 2D coords in an array of integers
   * @return a 2D int array
   */
  public int [] getX2DCoords () {
    int [] xCoords = new int [xCoords2D.size()];

    for (int i = 0; i < xCoords.length; i++)
      xCoords[i] = xCoords2D.get(i);

    return xCoords;
  }

  /**
   * Returns X 2D coords in an array of integers
   * @return a 2D int array
   */
  public int [] getY2DCoords () {
    int [] yCoords = new int [yCoords2D.size()];

    for (int i = 0; i < yCoords.length; i++)
      yCoords[i] = yCoords2D.get(i);

    return yCoords;
  }

  /**
   * Returns the amount of vertices that the shape has
   * @return
   */
  public int getAmountOfPoints () {
    return coords3DMatrices.size();
  }
  
  /**
   * Moves shape to the left
   * @param units
   */
  public void moveToLeft(int units) {
    x -= units;
  }
  
  /**
   * Moves shape to the right
   * @param units
   */
  public void moveToRight(int units) {
    x += units;
  }
  
  /**
   * Moves shape up
   * @param units
   */
  public void moveUp(int units) {
    y -= units;
  }
  
  /**
   * Moves shape down
   * @param units
   */
  public void moveDown(int units) {
    y += units;
  }
  
  /**
   * Moves shape to front
   * @param units
   */
  public void moveFront(int units) {
    z += units;
  }
  
  /**
   * Moves shape to back
   * @param units
   */
  public void moveBack(int units) {
    z -= units;
  }
  
  /**
   * Increases zoom
   * @param units
   */
  public void zoomIn(float multiplierIncrease) {
    zoom += multiplierIncrease;
  }
  
  /**
   * Decreases zoom
   * @param units
   */
  public void zoomOut(float multiplierDecrease) {
    zoom -= multiplierDecrease;
  }
  
  /**
   * Increases X angle
   * @param units
   */
  public void increaseXRotation(int units) {
    xRotation += units;
    xRotation %= 360;
  }
  
  /**
   * Decreases X angle
   * @param units
   */
  public void decreaseXRotation(int units) {
    xRotation -= units;
    xRotation %= 360;
  }
  /**
   * Increases Y angle
   * @param units
   */
  public void increaseYRotation(int units) {
    yRotation += units;
    yRotation %= 360;
  }
  
  /**
   * Decreases Y angle
   * @param units
   */
  public void decreaseYRotation(int units) {
    yRotation -= units;
    yRotation %= 360;
  }
  /**
   * Increases Z angle
   * @param units
   */
  public void increaseZRotation(int units) {
    zRotation += units;
    zRotation %= 360;
  }
  
  /**
   * Decreases Z angle
   * @param units
   */
  public void decreaseZRotation(int units) {
    zRotation -= units;
    zRotation %= 360;
  }

}
