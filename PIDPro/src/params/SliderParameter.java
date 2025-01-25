package params;

import javax.swing.*;

public abstract class SliderParameter<T> extends ControllerParameter<T> {
    protected final JSlider slider;
    protected final JTextField text;

    public SliderParameter(JSlider slider, JTextField text, T defaultValue, int parameterCategory) {
        super(defaultValue, parameterCategory);
        this.slider = slider;
        this.text = text;

        slider.addChangeListener(e -> {
            updateValue();
            updateText();
        });
        resetValue();
    }

    private void updateText() {
        text.setText(String.valueOf(value));
    }

    @Override
    protected void onResetValue() {
        slider.setValue(getValueForSlider());
        updateText();
    };

    protected abstract int getValueForSlider();
    protected abstract void updateValue();
}
