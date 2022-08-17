package Window.Util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

public class RoundedButton extends JButton {

  private int radius;
  private boolean showBorder;
  
  public RoundedButton () {
    super();
  }

  public RoundedButton (String text) {
    super(text);
  }

  public RoundedButton (String text, int radius, boolean showBorder) {
    super(text); 

    this.radius = radius;
    this.showBorder = showBorder;
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (!isOpaque() && getBorder() instanceof RoundedBorder) {

      Graphics2D g2 = (Graphics2D) g.create();
      g2.setPaint( getBackground() );
      g2.fill( ( (RoundedBorder) getBorder() )
          .getBorderShape(0, 0, getWidth() - 1, getHeight() - 1, radius) );

      g2.dispose();
    }

    super.paintComponent(g);
  }

  @Override
  public void updateUI() {
    super.updateUI();
    setOpaque(false);
    setBorder( new RoundedBorder(radius, showBorder) );
  }
}
