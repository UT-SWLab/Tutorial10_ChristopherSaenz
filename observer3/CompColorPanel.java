package observer3;

import javax.swing.*;
import java.awt.*;

public class CompColorPanel extends JPanel {
    private Color color;

    public CompColorPanel(Color initialColor) {
        this.color = initialColor;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        this.setBackground(color);
        super.paintComponent(g);
    }
    
    public void update(float newHue, float newSaturation, float newBrightness) {
    	newHue = newHue - (float) 0.5;
        if (newHue < 0) {
            newHue++;
        }
        this.setColor(Color.getHSBColor(newHue, newSaturation, newBrightness));
    }
}
