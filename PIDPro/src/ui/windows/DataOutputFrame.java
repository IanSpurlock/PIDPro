package ui.windows;

import core.Constants;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import sim.Simulator;

public class DataOutputFrame extends ControllerWindow {
    public DataOutputFrame(JFreeChart chart) {
        super(Constants.ChartConstants.CHART_WINDOW_TITLE);
        finishWindowSetup(Constants.ChartConstants.CHART_WINDOW_DIMS, new ChartPanel(chart));
    }

    /**
     * Set the active chart frame to null and dispose the window.
     */
    @Override
    public void onWindowClose() {
        Simulator.activeChartFrame = null;
        dispose();
    }
}
