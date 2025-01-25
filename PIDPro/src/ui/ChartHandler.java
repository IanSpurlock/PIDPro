package ui;

import core.Constants.ParameterConstants;
import core.Constants.ChartConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import params.ControllerParameter;
import params.ParameterBuilder;
import ui.windows.MainWindow;

import java.awt.*;

public class ChartHandler extends ParameterBuilder {
    public static ControllerParameter<Boolean> showPID;

    public static XYSeries positionSeries;
    public static XYSeries pidOutputSeries;
    public static XYSeries setpointSeries;
    public static XYSeries pSeries;
    public static XYSeries iSeries;
    public static XYSeries dSeries;

    public static void buildParameters(MainWindow window) {
        showPID = buildBooleanParameter(window.showPIDOutputBox, ParameterConstants.DEFAULT_SHOW_PID, C_SIM);
    }

    public static void resetData() {
        positionSeries = new XYSeries(ChartConstants.POSITION_SERIES_KEY);
        pidOutputSeries = new XYSeries(ChartConstants.PID_OUTPUT_SERIES_KEY);
        setpointSeries = new XYSeries(ChartConstants.SETPOINT_SERIES_KEY);
        if (showPID.value) {
            pSeries = new XYSeries(ChartConstants.P_SERIES_KEY);
            iSeries = new XYSeries(ChartConstants.I_SERIES_KEY);
            dSeries = new XYSeries(ChartConstants.D_SERIES_KEY);
        }
    }

    public static JFreeChart createChart() {
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        seriesCollection.addSeries(positionSeries);
        seriesCollection.addSeries(pidOutputSeries);
        seriesCollection.addSeries(setpointSeries);
        if (showPID.value) {
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

    private static void setChartRendererStyle(JFreeChart chart) {
        XYItemRenderer renderer = chart.getXYPlot().getRenderer();

        for (int i = 0; i < (showPID.value ? 6 : 3); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(ChartConstants.LINE_THICKNESS));
            renderer.setSeriesPaint(i, ChartConstants.LINE_CHART_COLORS[i]);
        }
    }
}
