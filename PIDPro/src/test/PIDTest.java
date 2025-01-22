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
        PID.kP = 1;
        PID.kI = 1;
        PID.kD = 1;
        PID.errorSumThreshold = 1;
        PID.setpoint = 1;
        PID.maxOutput = 1;

        PID.errorSum = 0;
        PID.errorRate = 0;
        PID.lastError = 0;
    }
}