package ui.windows;

import core.Constants;

import javax.swing.*;
public class ParameterExplanationWindow extends ControllerWindow {
    private JPanel mainPanel;
    private JTextArea text;

    public ParameterExplanationWindow() {
        super(Constants.WindowConstants.PARAM_WINDOW_TITLE);

        finishWindowSetup(Constants.WindowConstants.WINDOW_DIMS, mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    /**
     * Does nothing. This window's default close operation is overridden to dispose.
     */
    @Override
    public void onWindowClose() {}
}
