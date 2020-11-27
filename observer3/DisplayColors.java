package observer3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Observable;

public class DisplayColors extends Observable implements ChangeListener {

	public static void main(String[] args) {
        SwingFacade.launch(new DisplayColors().mainPanel(), "Compute Complementary Colors");
    }

    protected ColorPanel originalColorPanel;
    protected CompColorPanel complementaryColorPanel;

    protected JSlider hueSlider;
    protected JSlider saturationSlider;
    protected JSlider brightnessSlider;

    protected JLabel hueValueLabel;
    protected JLabel saturationValueLabel;
    protected JLabel brightnessValueLabel;

    protected JPanel colorsPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));
        originalColorPanel = createColorPanel(Color.getHSBColor(0, (float) .5, (float) .5));
        p.add(SwingFacade.createTitledPanel("Original Color", originalColorPanel));
        complementaryColorPanel = createCompColorPanel(Color.getHSBColor((float) .5, (float) .5, (float) .5));
        p.add(SwingFacade.createTitledPanel("Complementary Color", complementaryColorPanel));
        originalColorPanel.setCompanel(complementaryColorPanel);
        return p;
    }

    protected JPanel mainPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        JPanel colorsPanel = colorsPanel();
        JPanel subP = new JPanel();
        subP.setLayout(new GridLayout(3, 1));
        hueSlider = slider();
        subP.add(sliderBox("H", hueSlider, hueValueLabel));
        saturationSlider = slider();
        saturationSlider.setValue(50);
        subP.add(sliderBox("S", saturationSlider, saturationValueLabel));
        brightnessSlider = slider();
        brightnessSlider.setValue(50);
        subP.add(sliderBox("B", brightnessSlider, brightnessValueLabel));
        p.add(subP);
        p.add(colorsPanel);
        return p;
    }

    private JSlider slider() {
        JSlider slider = new JSlider();
        
        // WHAT GOES HERE?
        // You need to make it possible for the app to get the slider values out.
        slider.addChangeListener(this);
        
        slider.setValue(slider.getMinimum());
        return slider;
    }

    private Box sliderBox(String sliderLabel, JSlider slider, JLabel valueLabel) {
        if (valueLabel == null) {
            valueLabel = new JLabel();
            valueLabel.setFont(SwingFacade.getStandardFont());
            valueLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            valueLabel.setForeground(java.awt.Color.black);
        }
        Box b = Box.createHorizontalBox();
        JLabel label = new JLabel(sliderLabel);
        label.setFont(SwingFacade.getStandardFont());
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        label.setForeground(java.awt.Color.black);
        b.add(label);
        b.add(valueLabel);
        b.add(slider);
        b.setPreferredSize(new Dimension(600, 50));
        return b;
    }

    protected ColorPanel createColorPanel(Color initialColor) {
        ColorPanel colorPanel = new ColorPanel(initialColor);
        colorPanel.setPreferredSize(new Dimension(300, 200));
        return colorPanel;
    }
    
    protected CompColorPanel createCompColorPanel(Color initialColor) {
        CompColorPanel colorPanel = new CompColorPanel(initialColor);
        colorPanel.setPreferredSize(new Dimension(300, 200));
        return colorPanel;
    }


    public void stateChanged(ChangeEvent e) {
        if (hueSlider != null && saturationSlider != null && brightnessSlider != null) {
            float newHue = (float) hueSlider.getValue() / 100;
            float newSaturation = (float) saturationSlider.getValue() / 100;
            float newBrightness = (float) brightnessSlider.getValue() / 100;
            
            originalColorPanel.update(newHue, newSaturation, newBrightness);
        }
    }
}
    
