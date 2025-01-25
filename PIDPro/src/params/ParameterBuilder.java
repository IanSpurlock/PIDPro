package params;

import sim.ControlledObject;
import sim.PID;
import sim.Simulator;
import ui.ChartHandler;
import ui.windows.MainWindow;

import javax.swing.*;
import java.util.ArrayList;

public abstract class ParameterBuilder {
    protected final static int C_PID = 0;
    protected final static int C_HDW = 1;
    protected final static int C_OBJ = 2;
    protected final static int C_SIM = 3;

    public static void buildAllParameters(MainWindow window) {
        ChartHandler.buildParameters(window);
        ControlledObject.buildParameters(window);
        PID.buildParameters(window);
        Simulator.buildParameters(window);
    }

    protected static ControllerParameter<Double> buildDoubleParameter(
            JSlider slider,
            JTextField text,
            double defaultValue,
            int parameterCategory
    ) {
        return new ScaledSliderParameter(slider, text, defaultValue, parameterCategory);
    }
    protected static ControllerParameter<Integer> buildIntegerParameter(
            JSlider slider,
            JTextField text,
            int defaultValue,
            int parameterCategory
    ) {
        return new DirectSliderParameter(slider, text, defaultValue, parameterCategory);
    }

    protected static ControllerParameter<Boolean> buildBooleanParameter(
            JCheckBox checkBox,
            boolean defaultValue,
            int parameterCategory
    ) {
        return new CheckBoxParameter(checkBox, defaultValue, parameterCategory);
    }

    public static void addToResetList(ControllerParameter<?> parameter, int paramCategory) {
        ArrayList<ControllerParameter<?>> parameters = switch (paramCategory) {
            case C_PID -> MainWindow.pidParameters;
            case C_HDW -> MainWindow.hdwParameters;
            case C_OBJ -> MainWindow.objParameters;
            case C_SIM -> MainWindow.simParameters;
            default    -> null;
        };
        if (parameters == null) {
            System.out.println("Invalid parameter category");
            return;
        }

        parameters.add(parameter);
    }
}
