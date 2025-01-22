package ui;

import core.Constants.ChartConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import params.ControllerParameter;

import java.awt.*;

/**
 * A class for displaying the simulation data.
 */
public class ChartHandler {
    public static boolean showPID;

    public static XYSeries positionSeries;
    public static XYSeries pidOutputSeries;
    public static XYSeries setpointSeries;
    public static XYSeries pSeries;
    public static XYSeries iSeries;
    public static XYSeries dSeries;

    /**
     * Reset the data.
     */
    public static void init() {
        showPID = ControllerParameter.getBoolean("showPID");

        positionSeries = new XYSeries(ChartConstants.POSITION_SERIES_KEY);
        pidOutputSeries = new XYSeries(ChartConstants.PID_OUTPUT_SERIES_KEY);
        setpointSeries = new XYSeries(ChartConstants.SETPOINT_SERIES_KEY);
        if (showPID) {
            pSeries = new XYSeries(ChartConstants.P_SERIES_KEY);
            iSeries = new XYSeries(ChartConstants.I_SERIES_KEY);
            dSeries = new XYSeries(ChartConstants.D_SERIES_KEY);
        }
    }

    /**
     * Create a line chart based on the simulation data.
     * @return the line chart
     */
    public static JFreeChart createChart() {
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        seriesCollection.addSeries(positionSeries);
        seriesCollection.addSeries(pidOutputSeries);
        seriesCollection.addSeries(setpointSeries);
        if (showPID) {
            seriesCollection.addSeries(pSeries);
            seriesCollection.addSeries(iSeries);
            seriesCollection.addSeries(dSeries);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                ChartConstants.CHART_TITLE,
                ChartConstants.X_AXIS_LABEL,
                ChartConstants.Y_AXIS_LABEL,
                seriesCollection,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        setChartRendererStyle(chart);

        return chart;
    }

    /**
     * Sets up the line chart's style (i.e. color and line thickness).
     * @param chart the chart to set up the style for
     */
    private static void setChartRendererStyle(JFreeChart chart) {
        XYItemRenderer renderer = chart.getXYPlot().getRenderer();

        for (int i = 0; i < (showPID ? 6 : 3); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(ChartConstants.LINE_THICKNESS));
            renderer.setSeriesPaint(i, ChartConstants.LINE_CHART_COLORS[i]);
        }
    }
}
