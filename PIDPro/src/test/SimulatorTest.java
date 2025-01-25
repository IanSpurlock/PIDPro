package test;

import params.ParameterBuilder;
import ui.windows.MainWindow;

class SimulatorTest {
    static void initializeParametersForTests() {
        ParameterBuilder.buildAllParameters(new MainWindow(false));
    }
}