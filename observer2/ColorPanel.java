package observer2;

import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {
    private Color color;

    public ColorPanel(Color initialColor) {
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
    
    public void update(float newHue, float newSaturation, float newBrightness,
    		boolean isComplementary) {
        if(isComplementary)
        {
        	newHue = newHue - (float) 0.5;
            if (newHue < 0) {
                newHue++;
            }
        }
        this.setColor(Color.getHSBColor(newHue, newSaturation, newBrightness));
    }
}
