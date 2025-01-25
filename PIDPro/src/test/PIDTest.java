package test;

import org.junit.jupiter.api.Test;
import sim.ControlledObject;
import sim.PID;

import static org.junit.jupiter.api.Assertions.*;

class PIDTest {
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

    /**
     * Sets the controller's characteristics to some preset values and resets the
     * controller's errorSum, errorRate, and lastError.
     */
    private void pidInit() {
        PID.kP.value = 1.0;
        PID.kI.value = 1.0;
        PID.kD.value = 1.0;
        PID.errorSumThreshold.value = 1.0;
        PID.setpoint.value = 1;
        PID.maxOutput.value = 1.0;

        PID.errorSum = 0;
        PID.errorRate = 0;
        PID.lastError = 0;
    }
}