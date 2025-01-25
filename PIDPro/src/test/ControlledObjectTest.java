package test;

import org.junit.jupiter.api.Test;
import sim.ControlledObject;

import static org.junit.jupiter.api.Assertions.*;

class ControlledObjectTest extends SimulatorTest {
    /**
     * When the object has a mass and friction of 1, an applied force of 1 should always
     * result in a change in position equal to deltaTime squared, assuming the minimum
     * force is less than or equal to 1.
     */
    @Test
    void forceResponse() {
        objectInit();

        testObjectPosition(
                ControlledObject.toThreeDecimalPlaces(Math.pow(ControlledObject.getDeltaTime(), 2)),
                1
        );
    }

    /**
     * If the absolute value of the applied force is less than the minimum force, then
     * the only force acting on the object should be friction, but in this case the
     * velocity should be 0 and thus there should be no friction either. Thus, there
     * should be no change in position. Also, two different forces are tested to make
     * sure that friction isn't somehow creating a false positive.
     */
    @Test
    void lessThanMinimumForceResponse() {
        objectInit();
        ControlledObject.minForce.value = 2.0;

        testObjectPosition(0, 1);
        testObjectPosition(0, 1.5);
    }

    private void testObjectPosition(double expected, double force) {
        assertEquals(expected, ControlledObject.processPhysics(force));
    }

    private void objectInit() {
        initializeParametersForTests();

        ControlledObject.minForce.value = 0.2;
        ControlledObject.mass.value = 1.0;
        ControlledObject.friction.value = 1.0;

        ControlledObject.resetObjectKinematics();
    }
}