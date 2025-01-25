package params;

public abstract class ControllerParameter<T> {
    public T value;
    public T defaultValue;

    public ControllerParameter(T defaultValue, int parameterCategory) {
        this.defaultValue = defaultValue;
        ParameterBuilder.addToResetList(this, parameterCategory);
    }

    public void resetValue() {
        value = defaultValue;
        onResetValue();
    }

    protected abstract void onResetValue();
}
