package test;

import org.junit.jupiter.api.Test;
import sim.ControlledObject;
import sim.PID;

import static org.junit.jupiter.api.Assertions.*;

class PIDTest extends BaseSimulatorTest {
    /**
     * If the error, errorSum, and errorRate are all 0, then the PID output should be 0.
     */
    @Test
    void zeroErrorCalculation() {
        pidInit();
        ControlledObject.position = 1;

        checkPIDOutput(0);
    }

    private void checkPIDOutput(double expected) {
        assertEquals(expected, PID.calculate(ControlledObject.position, 0));
    }

    private void pidInit() {
        initializeParametersForTests();

        PID.kP.value = 1.0;
        PID.kI.value = 1.0;
        PID.kD.value = 1.0;
        PID.errorSumThreshold.value = 1.0;
        PID.setpoint.value = 1.0;
        PID.maxOutput.value = 1.0;

        PID.resetState();
    }
}