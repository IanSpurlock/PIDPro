package sim;

import org.jfree.data.xy.XYDataItem;
import params.ControllerParameter;
import ui.ChartHandler;
import params.SliderParameter;
import ui.windows.DataOutputFrame;

/**
 * A class for running the PID simulation, which involves initializing all the simulation
 * classes, running the simulation loop, and sending all the data to the {@link ChartHandler}
 * to be displayed.
 */
public class Simulator {
    private static boolean runningSimulation = false;
    public static DataOutputFrame activeChartFrame = null;
    private static int runtime;
    private static int sensorDelay;

    /**
     * Retrieves and updates the simulation's characteristics from its corresponding parameters.
     */
    public static void setSimulationValues() {
        runtime = ControllerParameter.getInt("runtime");
        sensorDelay = (int) (ControllerParameter.getDouble("sensorDelay") / ControlledObject.getDeltaTime());
    }

    public static void startSimulation() {
        if (runningSimulation) return;
        runningSimulation = true;
        if (activeChartFrame != null) activeChartFrame.onWindowClose(); // Close the previous chart window

        ChartHandler.init();
        PID.setPIDValues();
        setSimulationValues();
        ControlledObject.setObjectValues();

        runSimulation();

        DataOutputFrame frame = new DataOutputFrame(ChartHandler.createChart());
        if (!ControllerParameter.getBoolean("keepGraph")) activeChartFrame = frame;
        runningSimulation = false;
    }

    private static void runSimulation() {
        // Initial data points
        ChartHandler.positionSeries.add(0, 0);
        ChartHandler.pidOutputSeries.add(0, 0);
        ChartHandler.setpointSeries.add(0, PID.setpoint);
        ChartHandler.setpointSeries.add(runtime, PID.setpoint);

        // Simulation loop
        for (int i = 1; i < runtime / ControlledObject.getDeltaTime(); i++) {
            double time = i * ControlledObject.getDeltaTime();
            double pidOutput = PID.calculate(getDelayedObjectPosition(i));

            ChartHandler.positionSeries.add(time, ControlledObject.processPhysics(pidOutput));
            ChartHandler.pidOutputSeries.add(time, pidOutput);
        }
    }

    private static double getDelayedObjectPosition(int simulationTime) {
        if (sensorDelay == 0) return ControlledObject.position;

        int retrieveTime = simulationTime - sensorDelay;
        if (retrieveTime < 0) return 0;
        return ((XYDataItem) ChartHandler.positionSeries.getItems().get(retrieveTime)).getYValue();
    }
}
