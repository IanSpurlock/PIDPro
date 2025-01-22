package sim;

import params.ControllerParameter;
import ui.ChartHandler;

/**
 * A class for handling the PID controller and its calculations.
 */
public class PID {
    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;
    public static double errorSumThreshold = 0;
    public static boolean errorSumReset = false;
    public static int setpoint = 0;
    public static double maxOutput = 0;
    public static boolean propOutput = false;

    public static double errorSum = 0;
    public static double errorRate = 0;
    public static double lastError = 0;

    /**
     * Retrieves and updates the controller's characteristics from its corresponding parameters.
     */
    public static void setPIDValues() {
        kP = ControllerParameter.getDouble("kP");
        kI = ControllerParameter.getDouble("kI");
        kD = ControllerParameter.getDouble("kD");
        errorSumThreshold = ControllerParameter.getDouble("iLimit");
        errorSumReset = ControllerParameter.getBoolean("errSumRes");
        setpoint = ControllerParameter.getInt("setpoint");
        maxOutput = ControllerParameter.getDouble("maxForce");
        propOutput = ControllerParameter.getBoolean("propOutput");

        errorSum = 0;
        errorRate = 0;
        lastError = 0;
    }

    /**
     * Runs the PID calculation.
     * @return the output "power"
     */
    public static double calculate(double position, double time) {
        double error = setpoint - position;
        double dt = ControlledObject.getDeltaTime();

        // I:
        if (Math.abs(error) < errorSumThreshold) errorSum += error * dt;
        if (errorSumReset && errorSum != 0 && Math.signum(error) != Math.signum(errorSum)) errorSum = 0;

        // D:
        if (lastError != 0) errorRate = (error - lastError) / dt;
        lastError = error;

        // Limit output:
        double pValue = kP * error;
        double iValue = kI * errorSum;
        double dValue = kD * errorRate;
        if (ChartHandler.showPID) {
            ChartHandler.pSeries.add(time, pValue);
            ChartHandler.iSeries.add(time, iValue);
            ChartHandler.dSeries.add(time, dValue);
        }

        double output = pValue + iValue + dValue;
        if (propOutput) output *= maxOutput;
        if (Math.abs(output) > maxOutput) output = Math.signum(output) * maxOutput;

        return output;
    }
}
