package test;

import params.ParameterBuilder;
import ui.windows.MainWindow;

class BaseSimulatorTest {
    static void initializeParametersForTests() {
        ParameterBuilder.buildAllParameters(new MainWindow(false));
    }
}