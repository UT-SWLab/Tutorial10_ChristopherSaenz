package observer3;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ColorPanel extends JPanel implements PropertyChangeListener {
    private Color color;
    private float hue, saturation, brightness;
    private CompColorPanel companel;

    public ColorPanel(Color initialColor) {
        this.color = initialColor;
    }
    
    public void setCompanel(CompColorPanel companel)
    {
    	this.companel = companel;
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
        Color prevValue = this.color;
        hue = newHue;
        saturation = newSaturation;
        brightness = newBrightness;
    	this.setColor(Color.getHSBColor(newHue, newSaturation, newBrightness));
        this.propertyChange(new PropertyChangeEvent(this, "color", prevValue, color));
//        propertyChange("color", prevValue, color);
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		companel.update(hue, saturation, brightness);
	}
}
