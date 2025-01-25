package sim;

import core.Constants.ParameterConstants;
import org.jfree.data.xy.XYDataItem;
import params.ControllerParameter;
import ui.ChartHandler;
import ui.windows.DataOutputFrame;
import params.ParameterBuilder;
import ui.windows.MainWindow;

/**
 * A class for running the PID simulation, which involves initializing all the simulation
 * classes, running the simulation loop, and sending all the data to the {@link ChartHandler}
 * to be displayed.
 */
public class Simulator extends ParameterBuilder {
    private static ControllerParameter<Integer> runtime;
    private static ControllerParameter<Boolean> keepGraph;
    private static ControllerParameter<Double> sensorDelay;

    private static boolean runningSimulation = false;
    public static DataOutputFrame activeChartFrame = null;
    private static int scaledSensorDelay = 0;

    public static void buildParameters(MainWindow window) {
        runtime = buildIntegerParameter(window.runtimeSlider, window.runtimeText, ParameterConstants.DEFAULT_RUNTIME, C_SIM);
        keepGraph = buildBooleanParameter(window.keepGraphBox, ParameterConstants.DEFAULT_KEEP_GRAPH, C_SIM);
        sensorDelay = buildDoubleParameter(window.sensorDelaySlider, window.sensorDelayText, ParameterConstants.DEFAULT_SENSOR_DELAY, C_HDW);
    }

    public static void resetCalculations() {
        scaledSensorDelay = (int) (sensorDelay.value / ControlledObject.getDeltaTime());
    }

    public static void startSimulation() {
        if (runningSimulation) return;
        runningSimulation = true;
        if (activeChartFrame != null) activeChartFrame.onWindowClose();

        resetAllSimulatorStates();
        runSimulation();

        DataOutputFrame frame = new DataOutputFrame(ChartHandler.createChart());
        if (!keepGraph.value) activeChartFrame = frame;
        runningSimulation = false;
    }

    private static void runSimulation() {
        ChartHandler.positionSeries.add(0, 0);
        ChartHandler.pidOutputSeries.add(0, 0);
        ChartHandler.setpointSeries.add(0, PID.setpoint.value);
        ChartHandler.setpointSeries.add(runtime.value, PID.setpoint.value);

        for (int i = 1; i <= runtime.value / ControlledObject.getDeltaTime(); i++) {
            double time = i * ControlledObject.getDeltaTime();
            double pidOutput = PID.calculate(getDelayedObjectPosition(i), time);

            ChartHandler.positionSeries.add(time, ControlledObject.processPhysics(pidOutput));
            ChartHandler.pidOutputSeries.add(time, pidOutput);
        }
    }

    private static double getDelayedObjectPosition(int simulationTime) {
        if (scaledSensorDelay == 0) return ControlledObject.position;

        int retrieveTime = simulationTime - scaledSensorDelay;
        if (retrieveTime < 0) return 0;
        return getPositionDataAtTime(retrieveTime).getYValue();
    }
    private static XYDataItem getPositionDataAtTime(int time) {
        return (XYDataItem) ChartHandler.positionSeries.getItems().get(time);
    }

    private static void resetAllSimulatorStates() {
        ChartHandler.resetData();
        ControlledObject.resetObjectKinematics();
        PID.resetState();
        resetCalculations();
    }
}
