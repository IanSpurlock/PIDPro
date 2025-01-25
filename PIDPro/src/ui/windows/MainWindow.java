package ui.windows;

import core.Constants.WindowConstants;
import params.ParameterBuilder;
import sim.Simulator;
import params.ControllerParameter;

import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class MainWindow extends ControllerWindow {
    private JPanel mainPanel;
    private JButton runSimButton;
    private JToolBar toolbar;
    private JLabel kpLabel;
    private JLabel kiLabel;
    private JLabel kdLabel;
    private JLabel setpointLabel;
    private JLabel runtimeLabel;
    private JLabel pidParamsLabel;
    private JLabel simParamsLabel;
    private JLabel controlledObjParamsLabel;
    private JLabel iLimitLabel;
    private JLabel minForceLabel;
    private JLabel maxForceLabel;
    private JLabel massLabel;
    private JLabel frictionLabel;
    private JLabel sensorDelayLabel;
    private JLabel propOutLabel;
    private JLabel keepGraphLabel;
    private JLabel errSumResLabel;
    private JLabel showPIDOutputLabel;
    private JLabel bgForceLabel;
    private JLabel hwLabel;
    private JButton allParamResetButton;
    private JButton pidParamResetButton;
    private JButton simParamResetButton;
    private JButton objParamResetButton;
    private JButton hdwParamResetButton;
    private JLabel outputMultLabel;
    private JLabel pendulumLabel;
    private JLabel startPosLabel;
    private JButton paramHelp;

    public JSlider kpSlider;
    public JSlider kiSlider;
    public JSlider kdSlider;
    public JTextField kpText;
    public JTextField kiText;
    public JTextField kdText;
    public JSlider setpointSlider;
    public JTextField setpointText;
    public JSlider runtimeSlider;
    public JTextField runtimeText;
    public JSlider iLimitSlider;
    public JTextField iLimitText;
    public JSlider minForceSlider;
    public JTextField minForceText;
    public JSlider maxForceSlider;
    public JTextField maxForceText;
    public JSlider massSlider;
    public JTextField massText;
    public JSlider frictionSlider;
    public JTextField frictionText;
    public JSlider sensorDelaySlider;
    public JTextField sensorDelayText;
    public JCheckBox propOutBox;
    public JCheckBox keepGraphBox;
    public JCheckBox errSumResBox;
    public JCheckBox showPIDOutputBox;
    public JSlider bgForceSlider;
    public JTextField bgForceText;
    public JSlider outputMultSlider;
    public JTextField outputMultText;
    public JCheckBox pendulumBox;
    public JSlider startPosSlider;
    public JTextField startPosText;

    public static ArrayList<ControllerParameter<?>> pidParameters = new ArrayList<>();
    public static ArrayList<ControllerParameter<?>> hdwParameters = new ArrayList<>();
    public static ArrayList<ControllerParameter<?>> objParameters = new ArrayList<>();
    public static ArrayList<ControllerParameter<?>> simParameters = new ArrayList<>();

    public MainWindow(boolean visible) {
        super(WindowConstants.WINDOW_TITLE);

        ParameterBuilder.buildAllParameters(this);
        toolbarButtonsInit();
        parameterResetButtonsInit();

        finishWindowSetup(WindowConstants.WINDOW_DIMS, mainPanel, visible);
        setLocationRelativeTo(null);
    }

    private void toolbarButtonsInit() {
        runSimButton.addActionListener(e -> Simulator.startSimulation());
        allParamResetButton.addActionListener(e -> resetAllParams());
        paramHelp.addActionListener(e -> new ParameterExplanationWindow());
    }

    private void parameterResetButtonsInit() {
        pidParamResetButton.addActionListener(e -> resetPIDParams());
        hdwParamResetButton.addActionListener(e -> resetHdwParams());
        objParamResetButton.addActionListener(e -> resetObjParams());
        simParamResetButton.addActionListener(e -> resetSimParams());
    }

    private void resetAllParams() {
        resetPIDParams();
        resetHdwParams();
        resetObjParams();
        resetSimParams();
    }
    private void resetPIDParams() {
        resetParams(pidParameters);
    }
    private void resetHdwParams() {
        resetParams(hdwParameters);
    }
    private void resetObjParams() {
        resetParams(objParameters);
    }
    private void resetSimParams() {
        resetParams(simParameters);
    }

    private void resetParams(ArrayList<ControllerParameter<?>> params) {
        params.forEach(ControllerParameter::resetValue);
    }

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