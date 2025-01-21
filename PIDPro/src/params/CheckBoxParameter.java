package params;

import javax.swing.*;

public class CheckBoxParameter extends ControllerParameter {
    private final JCheckBox checkBox;

    /**
     * Creates a {@link ControllerParameter} that correlates to a parameter controlled by a JCheckBox.
     *
     * @param parameterKey the parameter's key
     * @param checkBox the checkbox ui element
     * @param defaultValue the parameter's default value
     */
    public CheckBoxParameter(String parameterKey, JCheckBox checkBox, boolean defaultValue) {
        super(parameterKey, defaultValue);
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
    public void resetValue() {
        value = defaultValue;
        checkBox.setSelected((boolean) value);
    }
}
