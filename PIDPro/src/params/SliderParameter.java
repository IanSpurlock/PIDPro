package params;

import core.Constants.ParameterConstants;

import javax.swing.*;

public class SliderParameter extends ControllerParameter {
    private final JSlider slider;
    private final JTextField text;
    private final boolean isScaled;

    /**
     * Creates a {@link ControllerParameter} that correlates to a parameter controlled by a JSlider.
     *
     * @param parameterKey this parameter's key
     * @param slider the slider ui element
     * @param text the text ui element
     * @param defaultValue the parameter's default value
     * @param isScaled whether the parameter's value is scaled when retrieved from the slider.
     */
    public SliderParameter(String parameterKey, JSlider slider, JTextField text, double defaultValue, boolean isScaled) {
        super(parameterKey, defaultValue);
        this.slider = slider;
        this.text = text;
        this.isScaled = isScaled;

        slider.addChangeListener(e -> text.setText(updateSliderValue()));
        resetValue();
    }

    /**
     * Updates the stored parameter value when the slider's value is updated by the user.
     * @return the new value as a string.
     */
    private String updateSliderValue() {
        value = (double)slider.getValue();
        if (isScaled) value = (double)value / ParameterConstants.SLIDER_SCALE;
        return String.valueOf(value);
    }

    /**
     * Sets the parameter's value to its default and updates the slider along with its text.
     */
    @Override
    public void resetValue() {
        value = defaultValue;
        slider.setValue((int) ((double)value * (isScaled ? ParameterConstants.SLIDER_SCALE : 1)));
        text.setText(String.valueOf(value));
    }
}
