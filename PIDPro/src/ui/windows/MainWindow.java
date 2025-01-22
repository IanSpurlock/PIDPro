package ui.windows;

import core.Constants.ParameterConstants;
import core.Constants.WindowConstants;
import params.CheckBoxParameter;
import sim.Simulator;
import params.ControllerParameter;
import params.SliderParameter;

import javax.swing.*;

public class MainWindow extends ControllerWindow {
    private JPanel mainPanel;
    private JSlider kpSlider;
    private JButton runSimButton;
    private JToolBar toolbar;
    private JSlider kiSlider;
    private JSlider kdSlider;
    private JLabel kpLabel;
    private JLabel kiLabel;
    private JLabel kdLabel;
    private JTextField kpText;
    private JTextField kiText;
    private JTextField kdText;
    private JSlider setpointSlider;
    private JLabel setpointLabel;
    private JTextField setpointText;
    private JSlider runtimeSlider;
    private JLabel runtimeLabel;
    private JTextField runtimeText;
    private JLabel pidParamsLabel;
    private JLabel simParamsLabel;
    private JLabel controlledObjParamsLabel;
    private JSlider iLimitSlider;
    private JLabel iLimitLabel;
    private JTextField iLimitText;
    private JButton allParamResetButton;
    private JButton paramHelp;
    private JSlider minForceSlider;
    private JLabel minForceLabel;
    private JTextField minForceText;
    private JSlider maxForceSlider;
    private JLabel maxForceLabel;
    private JTextField maxForceText;
    private JSlider massSlider;
    private JLabel massLabel;
    private JTextField massText;
    private JSlider frictionSlider;
    private JLabel frictionLabel;
    private JTextField frictionText;
    private JButton pidParamResetButton;
    private JButton simParamResetButton;
    private JButton objParamResetButton;
    private JLabel sensorDelayLabel;
    private JSlider sensorDelaySlider;
    private JTextField sensorDelayText;
    private JLabel propOutLabel;
    private JCheckBox propOutBox;
    private JLabel keepGraphLabel;
    private JCheckBox keepGraphBox;
    private JLabel errSumResLabel;
    private JCheckBox errSumResBox;
    private JLabel showPIDOutputLabel;
    private JCheckBox showPIDOutputBox;
    private JLabel bgForceLabel;
    private JSlider bgForceSlider;
    private JTextField bgForceText;
    private JButton createBlockButton;
    private JToolBar mainToolbar;

    public MainWindow() {
        super(WindowConstants.WINDOW_TITLE);

        createParameters();
        toolbarButtonsInit();
        resetParameterButtonsInit();

        finishWindowSetup(WindowConstants.WINDOW_DIMS, mainPanel);
        setLocationRelativeTo(null);
    }

    /**
     * Initializes all the {@link ControllerParameter}s that are designed to be changed in the main window before running the simulation.
     */
    private void createParameters() {
        // Add parameters here:
        // Default values should be initialized in Constants.java.
        // Parameter resetting should be implemented in the corresponding reset method.
        // Parameter values should be queried and stored before being used in the simulation.

        new SliderParameter("kP", kpSlider, kpText, ParameterConstants.DEFAULT_KP, true);
        new SliderParameter("kI", kiSlider, kiText, ParameterConstants.DEFAULT_KI, true);
        new SliderParameter("kD", kdSlider, kdText, ParameterConstants.DEFAULT_KD, true);
        new SliderParameter("iLimit", iLimitSlider, iLimitText, ParameterConstants.DEFAULT_I_LIMIT, true);
        new CheckBoxParameter("errSumRes", errSumResBox, ParameterConstants.DEFAULT_ERROR_SUM_RESET);
        new SliderParameter("setpoint", setpointSlider, setpointText, ParameterConstants.DEFAULT_SETPOINT, false);
        new CheckBoxParameter("propOutput", propOutBox, ParameterConstants.DEFAULT_PROP_OUTPUT);

        new SliderParameter("runtime", runtimeSlider, runtimeText, ParameterConstants.DEFAULT_RUNTIME, false);
        new CheckBoxParameter("keepGraph", keepGraphBox, ParameterConstants.DEFAULT_KEEP_GRAPH);
        new CheckBoxParameter("showPID", showPIDOutputBox, ParameterConstants.DEFAULT_SHOW_PID);

        new SliderParameter("minForce", minForceSlider, minForceText, ParameterConstants.DEFAULT_MIN_FORCE, true);
        new SliderParameter("maxForce", maxForceSlider, maxForceText, ParameterConstants.DEFAULT_MAX_FORCE, true);
        new SliderParameter("mass", massSlider, massText, ParameterConstants.DEFAULT_MASS, true);
        new SliderParameter("friction", frictionSlider, frictionText, ParameterConstants.DEFAULT_FRICTION, true);
        new SliderParameter("bgForce", bgForceSlider, bgForceText, ParameterConstants.DEFAULT_BG_FORCE, true);
        new SliderParameter("sensorDelay", sensorDelaySlider, sensorDelayText, ParameterConstants.DEFAULT_SENSOR_DELAY, true);
    }

    private void toolbarButtonsInit() {
        runSimButton.addActionListener(e -> Simulator.startSimulation());
        allParamResetButton.addActionListener(e -> resetAllParams());
        paramHelp.addActionListener(e -> new ParameterExplanationWindow());
    }

    private void resetParameterButtonsInit() {
        pidParamResetButton.addActionListener(e -> resetPIDParams());
        simParamResetButton.addActionListener(e -> resetSimParams());
        objParamResetButton.addActionListener(e -> resetObjParams());
    }
    private void resetAllParams() {
        resetPIDParams();
        resetSimParams();
        resetObjParams();
    }
    private void resetPIDParams() {
        resetParams("kP", "kI", "kD", "iLimit", "errSumRes", "setpoint", "propOutput");
    }
    private void resetSimParams() {
        resetParams("runtime", "keepGraph", "showPID");
    }
    private void resetObjParams() {
        resetParams("minForce", "maxForce", "mass", "friction", "bgForce", "sensorDelay");
    }

    /**
     * Resets all the parameters specified by the keys.
     * @param params parameter keys
     */
    private void resetParams(String... params) {
        for (String param : params) {
            ControllerParameter.parameters.get(param).resetValue();
        }
    }

    /**
     * Displays an exit confirmation window.
     */
    @Override
    public void onWindowClose() {
        int confirm = JOptionPane.showOptionDialog(
                null, "Are you sure you want to close the demo?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null
        );
        if (confirm == 0) System.exit(0);
    }
}