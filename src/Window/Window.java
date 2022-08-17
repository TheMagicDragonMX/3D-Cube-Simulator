package Window;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Shape3D.Shape3D;
import Shape3D.Shape3DModel;

import Window.Util.Colors;
import Window.Util.RoundedButton;
import Window.Util.RoundedLabel;

public class Window extends JFrame {

  private Container workspace = getContentPane();

  private RoundedLabel controlsContainer;

  private JLabel cardinalMovementLabel;
  private RoundedButton upButton;
  private RoundedButton downButton;
  private RoundedButton leftButton;
  private RoundedButton rightButton;
  
  private JLabel depthMovementLabel;
  private RoundedButton toBackButton;
  private RoundedButton toFrontButton;
  
  private JLabel zoomMovementLabel;
  private RoundedButton zoomInButton;
  private RoundedButton zoomOutButton;
  
  private JLabel rotationMovementLabel;
  private JLabel xLabel;
  private JLabel yLabel;
  private JLabel zLabel;

  private RoundedButton rotateXForwardButton;
  private RoundedButton rotateXBackwardButton;
  private RoundedButton rotateYForwardButton;
  private RoundedButton rotateYBackwardButton;
  private RoundedButton rotateZForwardButton;
  private RoundedButton rotateZBackwardButton;

  private RoundedLabel xDegreesLabel;
  private RoundedLabel yDegreesLabel;
  private RoundedLabel zDegreesLabel;

  private Shape3D cube;

  public Window () {
    super("3D Cube");

    setup();
    createCube();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


  /**
   * Setups multiple aspects of the window
   */
  public void setup () {
    setupDimensions();
    setupStyle();

    setupComponents();
    setupLayout();

    setupListeners();
  }

  /**
   * Determines the size of the window and some window
   * properties
   */
  public void setupDimensions () {
    setSize(1400, 700);
    setResizable(false);
  }

  /**
   * Determines the style of the window
   */
  public void setupStyle () {
    workspace.setBackground(Colors.deepPurple);
  }

  /**
   * Initializes every component that will be displayed
   * on the window
   */
  public void setupComponents () {

    // Controls container
    controlsContainer = new RoundedLabel("", 20, false);
    controlsContainer.setBackground(Colors.grapePurple);

    // Cardinal movement label
    cardinalMovementLabel = new JLabel("X & Y");
    cardinalMovementLabel.setForeground( Colors.yellow );
    cardinalMovementLabel.setFont( new Font("Carter One", Font.BOLD, 24) );

    // Depth movement label
    depthMovementLabel = new JLabel("Move");
    depthMovementLabel.setForeground( Colors.yellow );
    depthMovementLabel.setFont( new Font("Carter One", Font.BOLD, 24) );

    // Zoom movement label
    zoomMovementLabel = new JLabel("Zoom");
    zoomMovementLabel.setForeground( Colors.yellow );
    zoomMovementLabel.setFont( new Font("Carter One", Font.BOLD, 24) );

    // Rotation movement label
    rotationMovementLabel = new JLabel("Rotate");
    rotationMovementLabel.setForeground( Colors.yellow );
    rotationMovementLabel.setFont( new Font("Carter One", Font.BOLD, 24) );

    // UP button
    upButton = new RoundedButton("UP", 20, false);
    upButton.setFont( new Font("Recursive", Font.BOLD, 18) );
    upButton.setBackground( Colors.yellow );
    upButton.setForeground( Colors.red );
    
    // DOWN button
    downButton = new RoundedButton("DOWN", 20, false);
    downButton.setFont( new Font("Recursive", Font.BOLD, 18) );
    downButton.setBackground( Colors.yellow );
    downButton.setForeground( Colors.red );

    // LEFT button
    leftButton = new RoundedButton("LEFT", 20, false);
    leftButton.setFont( new Font("Recursive", Font.BOLD, 18) );
    leftButton.setBackground( Colors.yellow );
    leftButton.setForeground( Colors.red );

    // RIGHT button
    rightButton = new RoundedButton("RIGHT", 20, false);
    rightButton.setFont( new Font("Recursive", Font.BOLD, 18) );
    rightButton.setBackground( Colors.yellow );
    rightButton.setForeground( Colors.red );
    
    // TO BACK button
    toBackButton = new RoundedButton("BACK", 20, false);
    toBackButton.setFont( new Font("Recursive", Font.BOLD, 18) );
    toBackButton.setBackground( Colors.yellow );
    toBackButton.setForeground( Colors.red );
    
    // TO FRONT button
    toFrontButton = new RoundedButton("FRONT", 20, false);
    toFrontButton.setFont( new Font("Recursive", Font.BOLD, 18) );
    toFrontButton.setBackground( Colors.yellow );
    toFrontButton.setForeground( Colors.red );
    
    // ZOOM IN button
    zoomInButton = new RoundedButton("+", 20, false);
    zoomInButton.setFont( new Font("Recursive", Font.BOLD, 26) );
    zoomInButton.setBackground( Colors.yellow );
    zoomInButton.setForeground( Colors.red );
    
    // ZOOM OUT button
    zoomOutButton = new RoundedButton("-", 20, false);
    zoomOutButton.setFont( new Font("Recursive", Font.BOLD, 26) );
    zoomOutButton.setBackground( Colors.yellow );
    zoomOutButton.setForeground( Colors.red );

    // ROTATE X FORWARD button
    rotateXForwardButton = new RoundedButton("+", 30, false);
    rotateXForwardButton.setFont( new Font("Recursive", Font.BOLD, 22) );
    rotateXForwardButton.setBackground( Colors.yellow );
    rotateXForwardButton.setForeground( Colors.red );
    
    // ROTATE Y FORWARD button
    rotateYForwardButton = new RoundedButton("+", 30, false);
    rotateYForwardButton.setFont( new Font("Recursive", Font.BOLD, 22) );
    rotateYForwardButton.setBackground( Colors.yellow );
    rotateYForwardButton.setForeground( Colors.red );

    // ROTATE Z FORWARD button
    rotateZForwardButton = new RoundedButton("+", 30, false);
    rotateZForwardButton.setFont( new Font("Recursive", Font.BOLD, 22) );
    rotateZForwardButton.setBackground( Colors.yellow );
    rotateZForwardButton.setForeground( Colors.red );

    // ROTATE X BACKWARD button
    rotateXBackwardButton = new RoundedButton("-", 30, false);
    rotateXBackwardButton.setFont( new Font("Recursive", Font.BOLD, 22) );
    rotateXBackwardButton.setBackground( Colors.yellow );
    rotateXBackwardButton.setForeground( Colors.red );
    
    // ROTATE Y BACKWARD button
    rotateYBackwardButton = new RoundedButton("-", 30, false);
    rotateYBackwardButton.setFont( new Font("Recursive", Font.BOLD, 22) );
    rotateYBackwardButton.setBackground( Colors.yellow );
    rotateYBackwardButton.setForeground( Colors.red );

    // ROTATE Z BACKWARD button
    rotateZBackwardButton = new RoundedButton("-", 30, false);
    rotateZBackwardButton.setFont( new Font("Recursive", Font.BOLD, 22) );
    rotateZBackwardButton.setBackground( Colors.yellow );
    rotateZBackwardButton.setForeground( Colors.red );

    // X DEGREES label
    xDegreesLabel = new RoundedLabel("0 °", 30, false);
    xDegreesLabel.setFont( new Font("Recursive", Font.ITALIC, 20) );
    xDegreesLabel.setHorizontalAlignment( SwingConstants.CENTER );
    xDegreesLabel.setBackground( Colors.magenta );
    xDegreesLabel.setForeground( Colors.white );

    // Y DEGREES label
    yDegreesLabel = new RoundedLabel("0 °", 30, false);
    yDegreesLabel.setFont( new Font("Recursive", Font.ITALIC, 20) );
    yDegreesLabel.setHorizontalAlignment( SwingConstants.CENTER );
    yDegreesLabel.setBackground( Colors.magenta );
    yDegreesLabel.setForeground( Colors.white );
    
    // Z DEGREES label
    zDegreesLabel = new RoundedLabel("0 °", 30, false);
    zDegreesLabel.setFont( new Font("Recursive", Font.ITALIC, 20) );
    zDegreesLabel.setHorizontalAlignment( SwingConstants.CENTER );
    zDegreesLabel.setBackground( Colors.magenta );
    zDegreesLabel.setForeground( Colors.white );
    
    // X label
    xLabel = new JLabel("X :");
    xLabel.setFont( new Font("Carter One", Font.BOLD, 20) );
    xLabel.setForeground( Colors.white );
    xLabel.setOpaque(false);
    
    // Y label
    yLabel = new JLabel("Y :");
    yLabel.setFont( new Font("Carter One", Font.BOLD, 20) );
    yLabel.setForeground( Colors.white );
    yLabel.setOpaque(false);

    // Z label
    zLabel = new JLabel("Z :");
    zLabel.setFont( new Font("Carter One", Font.BOLD, 20) );
    zLabel.setForeground( Colors.white );
    zLabel.setOpaque(false);

  }

  /**
   * Determines the layout of the window
   * Layout: ABSOLUTE LAYOUT
   */
  public void setupLayout () {
    setLayout(null);

    // Cardinal movement label
    add(cardinalMovementLabel);
    cardinalMovementLabel.setBounds(100, 35, 100, 40);

    // UP button
    add(upButton);
    upButton.setBounds(105, 85, 55, 40);
    
    // LEFT button
    add(leftButton);
    leftButton.setBounds(40, 130, 75, 40);
    
    // RIGHT button
    add(rightButton);
    rightButton.setBounds(150, 130, 75, 40);
    
    // DOWN button
    add(downButton);
    downButton.setBounds(97, 177, 70, 40);
    
    // Cardinal movement label
    add(depthMovementLabel);
    depthMovementLabel.setBounds(285, 35, 100, 40);
    
    // TO BACK button
    add(toBackButton);
    toBackButton.setBounds(285, 95, 70, 40);

    // TO FRONT button
    add(toFrontButton);
    toFrontButton.setBounds(275, 155, 90, 40);
    
    // Zoom movement label
    add(zoomMovementLabel);
    zoomMovementLabel.setBounds(100, 270, 100, 40);
    
    // ZOOM IN button
    add(zoomInButton);
    zoomInButton.setBounds(220, 240, 90, 40);
    
    // ZOOM OUT button
    add(zoomOutButton);
    zoomOutButton.setBounds(220, 300, 90, 40);

    // Rotation movement label
    add(rotationMovementLabel);
    rotationMovementLabel.setBounds(170, 380, 100, 40);
  
    // ROTATE X FORWARD button
    add(rotateXForwardButton);
    rotateXForwardButton.setBounds(140, 430, 40, 40);
    
    // ROTATE Y FORWARD button
    add(rotateYForwardButton);
    rotateYForwardButton.setBounds(140, 480, 40, 40);

    // ROTATE Z FORWARD button
    add(rotateZForwardButton);
    rotateZForwardButton.setBounds(140, 530, 40, 40);

    // ROTATE X BACKWARD button
    add(rotateXBackwardButton);
    rotateXBackwardButton.setBounds(190, 430, 40, 40);
    
    // ROTATE Y BACKWARD button
    add(rotateYBackwardButton);
    rotateYBackwardButton.setBounds(190, 480, 40, 40);

    // ROTATE Z BACKWARD button
    add(rotateZBackwardButton);
    rotateZBackwardButton.setBounds(190, 530, 40, 40);

    // X label
    add(xLabel);
    xLabel.setBounds(100, 430, 30, 40);

    // Y label
    add(yLabel);
    yLabel.setBounds(100, 480, 30, 40);

    // Z label
    add(zLabel);
    zLabel.setBounds(100, 530, 30, 40);

    // X DEGREES label
    add(xDegreesLabel);
    xDegreesLabel.setBounds(240, 430, 100, 40);
    
    // Y DEGREES label
    add(yDegreesLabel);
    yDegreesLabel.setBounds(240, 480, 100, 40);

    // Z DEGREES label
    add(zDegreesLabel);
    zDegreesLabel.setBounds(240, 530, 100, 40);

    // Controls container
    add(controlsContainer);
    controlsContainer.setBounds(20, 20, 400, 625);
  }


  /**
   * Gives listeners to buttons
   */
  public void setupListeners () {
    upButton.addActionListener( e -> { cube.moveUp(20); repaint(); } );
    leftButton.addActionListener( e -> { cube.moveToLeft(20); repaint(); } );
    rightButton.addActionListener( e -> { cube.moveToRight(20); repaint(); } );
    downButton.addActionListener( e -> { cube.moveDown(20); repaint(); } );

    toFrontButton.addActionListener( e -> { cube.moveFront(20); repaint(); } );
    toBackButton.addActionListener( e -> { cube.moveBack(20); repaint(); } );
    
    zoomInButton.addActionListener( e -> { cube.zoomIn(0.1f); repaint(); } );
    zoomOutButton.addActionListener( e -> { cube.zoomOut(0.1f); repaint(); } );

    rotateXForwardButton.addActionListener( e -> rotateForwardX() );
    rotateYForwardButton.addActionListener( e -> rotateForwardY() );
    rotateZForwardButton.addActionListener( e -> rotateForwardZ() );

    rotateXBackwardButton.addActionListener( e -> rotateBackwardX() );
    rotateYBackwardButton.addActionListener( e -> rotateBackwardY() );
    rotateZBackwardButton.addActionListener( e -> rotateBackwardZ() );
  }


  /**
   * Rotates the cube forward in X axis and displays its 
   * rotation angle in the label
   */
  private void rotateForwardX () {
    cube.increaseXRotation(10);
    xDegreesLabel.setText( cube.getXRotation() + "" );

    repaint();
  }

  /**
   * Rotates the cube backward in X axis and displays its 
   * rotation angle in the label
   */
  private void rotateBackwardX () {
    cube.decreaseXRotation(10);
    xDegreesLabel.setText( cube.getXRotation() + "" );

    repaint();
  }

  /**
   * Rotates the cube forward in Y axis and displays its 
   * rotation angle in the label
   */
  private void rotateForwardY () {
    cube.increaseYRotation(10);
    yDegreesLabel.setText( cube.getYRotation() + "" );

    repaint();
  }

  /**
   * Rotates the cube backward in X axis and displays its 
   * rotation angle in the label
   */
  private void rotateBackwardY () {
    cube.decreaseYRotation(10);
    yDegreesLabel.setText( cube.getYRotation() + "" );

    repaint();
  }

  /**
   * Rotates the cube forward in Z axis and displays its 
   * rotation angle in the label
   */
  private void rotateForwardZ () {
    cube.increaseZRotation(10);
    zDegreesLabel.setText( cube.getZRotation() + "" );

    repaint();
  }

  /**
   * Rotates the cube backward in Z axis and displays its 
   * rotation angle in the label
   */
  private void rotateBackwardZ () {
    cube.decreaseZRotation(10);
    zDegreesLabel.setText( cube.getZRotation() + "" );

    repaint();
  }

  /**
   * Initializes the cube with its model
   */
  public void createCube () {
    cube = new Shape3D();

    cube.setZAxisAngle(-45);
    cube.setPosition(600, 250, 0);
    cube.setZoom(1);
    cube.setXRotation(0);
    cube.setYRotation(0);
    cube.setZRotation(0);

    Shape3DModel cubeModel = Shape3DModel.create();
    int cubeSideLength = 100;

    cubeModel
      // Coco
      // .toUp(100)
      // .toRight(50)
      // .toLeft(50)
      // .toDown(100)
      // .toRight(50)

      // .toRight(30)
      // .toUp(100)
      // .toRight(50)
      // .toDown(100)
      // .toLeft(50)
      // .toRight(50)

      // .toRight(30)
      
      // .toUp(100)
      // .toRight(50)
      // .toLeft(50)
      // .toDown(100)
      // .toRight(50)

      // .toRight(30)
      // .toUp(100)
      // .toRight(50)
      // .toDown(100)
      // .toLeft(50)
      // .toRight(50)

      // The Game
      // .toUp(100)
      // .toLeft(50)
      // .toRight(140)

      // .toDown(100)
      // .toUp(50)
      // .toRight(60)
      // .toUp(50)
      // .toDown(100)
      
      // .toRight(110)
      // .toLeft(70)
      // .toUp(50)
      // .toRight(30)
      // .toLeft(30)
      // .toUp(50)
      // .toRight(60)
      // .toLeft(60)

      // .toDown(300)
      // .toUp(100)
      // .toRight(70)
      // .toLeft(70)
      // .toDown(100)
      // .toRight(70)
      // .toUp(50)
      // .toLeft(50)
      // .toRight(50)
      // .toDown(50)

      // .toRight(20)
      // .toDiagonal2D(50, 100)
      // .toDiagonal2D(50, -100)
      // .toDiagonal2D(-25, 50)
      // .toLeft(20)
      // .toRight(20)
      // .toDiagonal2D(25, -50)
      // .toRight(20)

      // .toUp(100)
      // .toDiagonal2D(30, -40)
      // .toDiagonal2D(30, 40)
      // .toDown(100)
      
      // .toRight(110)
      // .toLeft(70)
      // .toUp(50)
      // .toRight(30)
      // .toLeft(30)
      // .toUp(50)
      // .toRight(60)
      // .toLeft(60)

      // Triangular prism

      // .toDiagonal2D(60, 100)
      // .toDiagonal2D(60, -100)
      // .toLeft(120)

      // .toBack(140)

      // .toDiagonal2D(60, 100)
      // .toDiagonal2D(60, -100)
      // .toLeft(120)
      
      // .toDiagonal2D(60, 100)
      // .toFront(140)
      
      // .toDiagonal2D(60, -100)
      // .toBack(140)
    
      // Cube

      .toRight(cubeSideLength)
      .toUp(cubeSideLength)
      .toLeft(cubeSideLength)
      .toDown(cubeSideLength)
      
      .toBack(cubeSideLength)
      
      .toRight(cubeSideLength)
      .toUp(cubeSideLength)
      .toLeft(cubeSideLength)
      .toDown(cubeSideLength)
      
      .toUp(cubeSideLength)
      .toFront(cubeSideLength)
      
      .toRight(cubeSideLength)
      .toBack(cubeSideLength)

      .toDown(cubeSideLength)
      .toFront(cubeSideLength)
      ;

    cube.setModel(cubeModel);
  }


  /**
   * Draws the cube
   */
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    
    Graphics2D pencil = (Graphics2D) g;
    pencil.setColor( Colors.yellow );
    pencil.setStroke( new BasicStroke(3) );
    
    cube.calculateCoords();
    pencil.drawPolyline(cube.getX2DCoords(), cube.getY2DCoords(), cube.getAmountOfPoints());
  }
}
