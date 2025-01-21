package ui;

import core.Constants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

/**
 * A class for displaying the simulation data.
 */
public class ChartHandler {
    public static XYSeries positionSeries;
    public static XYSeries pidOutputSeries;
    public static XYSeries setpointSeries;

    /**
     * Reset the data.
     */
    public static void init() {
        positionSeries = new XYSeries(Constants.ChartConstants.POSITION_SERIES_KEY);
        pidOutputSeries = new XYSeries(Constants.ChartConstants.PID_OUTPUT_SERIES_KEY);
        setpointSeries = new XYSeries(Constants.ChartConstants.SETPOINT_SERIES_KEY);
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

        JFreeChart chart = ChartFactory.createXYLineChart(
                Constants.ChartConstants.CHART_TITLE,
                Constants.ChartConstants.X_AXIS_LABEL,
                Constants.ChartConstants.Y_AXIS_LABEL,
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

        for (int i = 0; i < 3; i++) {
            renderer.setSeriesStroke(i, new BasicStroke(3.0f));
            renderer.setSeriesPaint(i, Constants.ChartConstants.LINE_CHART_COLORS[i]);
        }
    }
}
