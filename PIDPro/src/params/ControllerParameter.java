package params;

import java.util.HashMap;

/**
 * This class is designed to handle a given parameter's value, default value, slider, and slider text.
 * <p> It contains a non-static method for resetting the parameter's value and a static method for retrieving
 * a parameter's value based off of its key in a static HashMap (parameters).
 */
public abstract class ControllerParameter {
    public static final HashMap<String, ControllerParameter> parameters = new HashMap<>();

    public final Object defaultValue;
    public Object value;

    /**
     * Creates a {@link ControllerParameter}.
     */
    public ControllerParameter(String parameterKey, Object defaultValue) {
        parameters.put(parameterKey, this);
        this.defaultValue = defaultValue;
    }

    /**
     * Gets the value of the parameter with the given key.
     * @param parameterKey the parameter's key
     * @return the parameter's value
     */
    public static double getDouble(String parameterKey) {
        return (double) parameters.get(parameterKey).value;
    }

    /**
     * Gets the value of the parameter with the given key.
     * @param parameterKey the parameter's key
     * @return the parameter's value
     */
    public static int getInt(String parameterKey) {
        return (int)(double)parameters.get(parameterKey).value;
    }

    /**
     * Gets the value of the parameter with the given key.
     * @param parameterKey the parameter's key
     * @return the parameter's value
     */
    public static boolean getBoolean(String parameterKey) {
        return (boolean) parameters.get(parameterKey).value;
    }

    public abstract void resetValue();
}
