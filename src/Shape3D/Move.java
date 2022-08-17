package Shape3D;


public class Move {
  
  public int x;
  public int y;
  public int z;

  /**
   * Creates a new move with the coord set to origin
   * which is (0, 0, 0)
   */
  public Move () {
    x = y = z = 0;
  }

  /**
   * Creates a new move with the given coords
   * @param toX x coord
   * @param toY y coord
   * @param toZ z coord
   */
  private Move (int toX, int toY, int toZ) {
    x = toX;
    y = toY;
    z = toZ;
  }

  /**
   * Creates a new move with the given coords
   * @param toX x coord
   * @param toY y coord
   * @param toZ z coord
   * @return A new Move object
   */
  public static Move createWithDestiny (int toX, int toY, int toZ) {
    return new Move(toX, toY, toZ);
  }
}
