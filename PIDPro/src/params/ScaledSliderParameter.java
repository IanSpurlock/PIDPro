package params;

import core.Constants.ParameterConstants;

import javax.swing.*;

public class ScaledSliderParameter extends SliderParameter<Double> {
    public ScaledSliderParameter(JSlider slider, JTextField text, double defaultValue, int parameterCategory) {
        super(slider, text, defaultValue, parameterCategory);
    }

    @Override
    protected void updateValue() {
        value = slider.getValue() / ParameterConstants.SLIDER_SCALE;
    }

    @Override
    protected int getValueForSlider() {
        return (int) (value * ParameterConstants.SLIDER_SCALE);
    }
}
