package sim;

import core.Constants.ParameterConstants;
import params.ControllerParameter;
import ui.ChartHandler;
import params.ParameterBuilder;
import ui.windows.MainWindow;

public class PID extends ParameterBuilder {
    public static ControllerParameter<Double> kP;
    public static ControllerParameter<Double> kI;
    public static ControllerParameter<Double> kD;
    public static ControllerParameter<Double> errorSumThreshold;
    public static ControllerParameter<Boolean> errorSumReset;
    public static ControllerParameter<Integer> setpoint;
    public static ControllerParameter<Boolean> propOutput;
    public static ControllerParameter<Double> maxOutput;
    public static ControllerParameter<Double> outputMultiplier;

    public static double trueSetpoint = 0;
    public static double error = 0;
    public static double errorSum = 0;
    public static double errorRate = 0;
    public static double lastError = 0;

    public static double pValue = 0;
    public static double iValue = 0;
    public static double dValue = 0;

    public static void buildParameters(MainWindow window) {
        kP = buildDoubleParameter(window.kpSlider, window.kpText, ParameterConstants.DEFAULT_KP, C_PID);
        kI = buildDoubleParameter(window.kiSlider, window.kiText, ParameterConstants.DEFAULT_KI, C_PID);
        kD = buildDoubleParameter(window.kdSlider, window.kdText, ParameterConstants.DEFAULT_KD, C_PID);
        errorSumThreshold = buildDoubleParameter(window.iLimitSlider, window.iLimitText, ParameterConstants.DEFAULT_I_LIMIT, C_PID);
        errorSumReset = buildBooleanParameter(window.errSumResBox, ParameterConstants.DEFAULT_ERROR_SUM_RESET, C_PID);
        setpoint = buildIntegerParameter(window.setpointSlider, window.setpointText, ParameterConstants.DEFAULT_SETPOINT, C_PID);
        propOutput = buildBooleanParameter(window.propOutBox, ParameterConstants.DEFAULT_PROP_OUTPUT, C_PID);
        maxOutput = buildDoubleParameter(window.maxForceSlider, window.maxForceText, ParameterConstants.DEFAULT_MAX_FORCE, C_HDW);
        outputMultiplier = buildDoubleParameter(window.outputMultSlider, window.outputMultText, ParameterConstants.DEFAULT_OUTPUT_MULTIPLIER, C_HDW);
    }

    public static void resetState() {
        trueSetpoint = wrapSetpointForPendulum();
        errorSum = 0;
        errorRate = 0;
        lastError = 0;
    }

    private static double wrapSetpointForPendulum() {
        return ControlledObject.isPendulum.value ?
                (setpoint.value + Math.PI) % (2 * Math.PI) - Math.PI :
                setpoint.value;
    }

    public static double calculate(double position, double time) {
        error = trueSetpoint - position;

        updateErrorSum();
        updateErrorRate();
        calculatePIDValues();
        updatePIDData(time);

        return calculateOutput();
    }

    private static void updateErrorSum() {
        if (Math.abs(error) < errorSumThreshold.value) errorSum += error * ControlledObject.getDeltaTime();
        if (errorSumReset.value && errorSum != 0 && Math.signum(error) != Math.signum(errorSum)) errorSum = 0;
    }

    private static void updateErrorRate() {
        if (lastError != 0) errorRate = (error - lastError) / ControlledObject.getDeltaTime();
        lastError = error;
    }

    private static void calculatePIDValues() {
        pValue = kP.value * error;
        iValue = kI.value * errorSum;
        dValue = kD.value * errorRate;
    }

    private static void updatePIDData(double time) {
        if (!ChartHandler.showPID.value) return;

        ChartHandler.pSeries.add(time, pValue);
        ChartHandler.iSeries.add(time, iValue);
        ChartHandler.dSeries.add(time, dValue);
    }

    private static double calculateOutput() {
        double output = pValue + iValue + dValue;
        double maxOut = maxOutput.value;

        if (propOutput.value) output *= maxOut;
        if (Math.abs(output) > maxOut) output = Math.signum(output) * maxOut;

        return output * outputMultiplier.value;
    }
}
