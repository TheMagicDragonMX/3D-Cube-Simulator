package Shape3D;

import java.util.ArrayList;

public class Shape3DModel {
  
  private ArrayList<Move> model;

  /**
   * Initializes a new 3D shape model
   */
  public Shape3DModel () {
    model = new ArrayList<>();
    // model.add( Move.createWithDestiny(0, 0, 0) );
  }

  /**
   * Creates a new 3D shape model
   * 
   * @return A new 3D shape model 
   */
  public static Shape3DModel create () {
    return new Shape3DModel();
  }

  /**
   * Returns the array of moves of the model
   * @return
   */
  public ArrayList<Move> getMoves () {
    return model;
  }

  /**
   * Adds one movement to the left
   * 
   * @param units amount of units the move will have of length 
   * @return The current object so the moves can be defined in a chain
   */
  public Shape3DModel toLeft (int units) {
    model.add( Move.createWithDestiny(-units, 0, 0) );
    return this;
  }

  /**
   * Adds one movement to the right
   * 
   * @param units amount of units the move will have of length
   * @return The current object so the moves can be defined in a chain
   */
  public Shape3DModel toRight (int units) {
    model.add( Move.createWithDestiny(units, 0, 0) );
    return this;
  }

  /**
   * Adds one movement to up
   * 
   * @param units amount of units the move will have of length
   * @return The current object so the moves can be defined in a chain
   */
  public Shape3DModel toDown (int units) {
    model.add( Move.createWithDestiny(0, units, 0) );
    return this;
  }

  /**
   * Adds one movement to down
   * 
   * @param units amount of units the move will have of length
   * @return The current object so the moves can be defined in a chain
   */
  public Shape3DModel toUp (int units) {
    model.add( Move.createWithDestiny(0, -units, 0) );
    return this;
  }

  /**
   * Adds one movement to back
   * 
   * @param units amount of units the move will have of length
   * @return The current object so the moves can be defined in a chain
   */
  public Shape3DModel toBack (int units) {
    model.add( Move.createWithDestiny(0, 0, units) );
    return this;
  }

  /**
   * Adds one movement to front
   * 
   * @param units amount of units the move will have of length
   * @return The current object so the moves can be defined in a chain
   */
  public Shape3DModel toFront(int units) {
    model.add( Move.createWithDestiny(0, 0, -units) );
    return this;
  }

  public Shape3DModel toDiagonal2D(int unitsX, int unitsY) {
    model.add( Move.createWithDestiny(unitsX, -unitsY, 0) );
    return this;
  }
}
