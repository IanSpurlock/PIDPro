package params;

import core.Constants;

import javax.swing.*;

public class DirectSliderParameter extends SliderParameter<Integer> {
    public DirectSliderParameter(JSlider slider, JTextField text, Integer defaultValue, int parameterCategory) {
        super(slider, text, defaultValue, parameterCategory);
    }

    @Override
    protected void updateValue() {
        value = slider.getValue();
    }

    @Override
    protected int getValueForSlider() {
        return value;
    }
}
