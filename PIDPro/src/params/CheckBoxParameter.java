package params;

import javax.swing.*;

public class CheckBoxParameter extends ControllerParameter<Boolean>{
    private final JCheckBox checkBox;

    /**
     * Creates a {@link ControllerParameter} that correlates to a parameter controlled by a JCheckBox.
     *
     * @param checkBox the checkbox ui element
     * @param defaultValue the parameter's default value
     */
    public CheckBoxParameter(JCheckBox checkBox, boolean defaultValue, int parameterCategory) {
        super(defaultValue, parameterCategory);
        this.checkBox = checkBox;

        checkBox.addChangeListener(e -> updateCheckBoxValue());
        resetValue();
    }

    /**
     * Updates the stored parameter value when the checkBox's value is updated by the user.
     */
    private void updateCheckBoxValue() {
        value = checkBox.isSelected();
    }

    @Override
    protected void onResetValue() {
        checkBox.setSelected(value);
    }
}
