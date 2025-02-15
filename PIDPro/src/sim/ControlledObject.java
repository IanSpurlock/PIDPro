package sim;

import core.Constants.ParameterConstants;
import params.ControllerParameter;
import ui.windows.MainWindow;
import params.ParameterBuilder;

/**
 * This class handles the "controlled object", which can best be thought of as an
 * object (with velocity, friction, etc.) that is moved based on the PID controller's
 * output. However, its behavior is similar to that of other PID-controlled systems.
 */
public class ControlledObject extends ParameterBuilder {
    public static ControllerParameter<Boolean> isPendulum;
    public static ControllerParameter<Double> startPosition;
    public static ControllerParameter<Double> minForce;
    public static ControllerParameter<Double> mass;
    public static ControllerParameter<Double> friction;
    public static ControllerParameter<Double> backgroundForce;

    public static double trueStartPosition = 0;
    public static double position = 0;
    public static double velocity = 0;

    public static void buildParameters(MainWindow window) {
        isPendulum = buildBooleanParameter(window.pendulumBox, ParameterConstants.IS_PENDULUM, C_OBJ);
        startPosition = buildDoubleParameter(window.startPosSlider, window.startPosText, ParameterConstants.START_POSITION, C_OBJ);
        minForce = buildDoubleParameter(window.minForceSlider, window.minForceText, ParameterConstants.DEFAULT_MIN_FORCE, C_OBJ);
        mass = buildDoubleParameter(window.massSlider, window.massText, ParameterConstants.DEFAULT_MASS, C_OBJ);
        friction = buildDoubleParameter(window.frictionSlider, window.frictionText, ParameterConstants.DEFAULT_FRICTION, C_OBJ);
        backgroundForce = buildDoubleParameter(window.bgForceSlider, window.bgForceText, ParameterConstants.DEFAULT_BG_FORCE, C_OBJ);
    }

    public static void resetObjectKinematics() {
        trueStartPosition = Simulator.wrapPositionForPendulum(startPosition.value);
        position = trueStartPosition;
        velocity = 0;
    }

    public static double processPhysics(double force) {
        force += backgroundForce.value + calculatePendulumForce();
        if (Math.abs(force) < minForce.value) force = 0;

        velocity += getDeltaTime() * calculateAcceleration(force);
        position += getDeltaTime() * velocity;
        return toThreeDecimalPlaces(position);
    }

    private static double calculateAcceleration(double force) {
        return force / mass.value - friction.value * velocity;
    }

    private static double calculatePendulumForce() {
        return isPendulum.value ? -Math.sin(position) : 0;
    }

    public static double toThreeDecimalPlaces(double value) {
        return Math.round((float)value * 1000f) / 1000d;
    }

    public static double getDeltaTime() {
        return 0.05; // in seconds
    }
}