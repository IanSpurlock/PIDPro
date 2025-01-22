package sim;

import params.ControllerParameter;

/**
 * This class handles the "controlled object", which can best be thought of as an
 * object (with velocity, friction, etc.) that is moved based on the PID controller's
 * output. However, its behavior is similar to that of other PID-controlled systems.
 */
public class ControlledObject {
    public static double minForce = 0;
    public static double mass = 0;
    public static double frictionCoefficient = 0;
    public static double backgroundForce = 0;

    public static double position = 0;
    public static double velocity = 0;

    /**
     * Retrieves and updates the object's characteristics from its corresponding parameters.
     */
    public static void setObjectValues() {
        minForce = ControllerParameter.getDouble("minForce");
        mass = ControllerParameter.getDouble("mass");
        frictionCoefficient = ControllerParameter.getDouble("friction");
        backgroundForce = ControllerParameter.getDouble("bgForce");

        position = 0;
        velocity = 0;
    }

    /**
     * Applies a force to the object and accordingly updates its velocity and position.
     * @param force the force to apply
     * @return the new position of the object rounded to three decimal places for simplicity and possible external use.
     */
    public static double processPhysics(double force) {
        force += backgroundForce;
        if (Math.abs(force) < minForce) force = 0;

        velocity += getDeltaTime() * calculateAcceleration(force);
        position += getDeltaTime() * velocity;
        return toThreeDecimalPlaces(position);
    }

    private static double calculateAcceleration(double force) {
        return force / mass - frictionCoefficient * velocity;
    }

    public static double toThreeDecimalPlaces(double value) {
        return Math.round((float)value * 1000f) / 1000d;
    }

    public static double getDeltaTime() {
        return 0.05; // in seconds
    }
}