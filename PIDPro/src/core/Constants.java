package core;

import java.awt.*;

public final class Constants {
    public static class WindowConstants {
        public static final String WINDOW_TITLE = "PIDPro - PID Control Simulator - v1.0";
        public static final String PARAM_WINDOW_TITLE = WINDOW_TITLE + " - Parameter Explanations";
        public static final Dimension WINDOW_DIMS = new Dimension(800, 700);
    }

    public static class ParameterConstants {
        public static final double SLIDER_SCALE = 20;

        // Add default parameter values here:
        public static final double DEFAULT_KP = 0;
        public static final double DEFAULT_KI = 0;
        public static final double DEFAULT_KD = 0;
        public static final double DEFAULT_I_LIMIT = 1;
        public static final boolean DEFAULT_ERROR_SUM_RESET = false;
        public static final int DEFAULT_SETPOINT = 4;
        public static final boolean DEFAULT_PROP_OUTPUT = false;
        public static final int DEFAULT_RUNTIME = 20;
        public static final boolean DEFAULT_KEEP_GRAPH = false;
        public static final boolean DEFAULT_SHOW_PID = false;
        public static final double DEFAULT_MIN_FORCE = 0.2;
        public static final double DEFAULT_MAX_FORCE = 10;
        public static final double DEFAULT_MASS = 1;
        public static final double DEFAULT_FRICTION = 1;
        public static final double DEFAULT_SENSOR_DELAY = 0;
    }

    public static class ChartConstants {
        public static final String CHART_WINDOW_TITLE = "Position Output";
        public static final Dimension CHART_WINDOW_DIMS = new Dimension(800, 700);
        public static final String POSITION_SERIES_KEY = "Position Data";
        public static final String PID_OUTPUT_SERIES_KEY = "PID Output Data";
        public static final String SETPOINT_SERIES_KEY = "Setpoint";
        public static final String P_SERIES_KEY = "kP Value Data";
        public static final String I_SERIES_KEY = "kI Value Data";
        public static final String D_SERIES_KEY = "kD Value Data";
        public static final String CHART_TITLE = "Position v. Time:";
        public static final String X_AXIS_LABEL = "Time";
        public static final String Y_AXIS_LABEL = "Object Position / PID Output";
        public static final float LINE_THICKNESS = 2.5f;
        public static final Color[] LINE_CHART_COLORS = {
                new Color(240, 40, 40),
                new Color(40, 110, 240),
                new Color(20, 20, 20),
                new Color(21, 188, 21),
                new Color(255, 115, 0),
                new Color(170, 40, 210)
        };
    }
}
