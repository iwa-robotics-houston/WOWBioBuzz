package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class Turret {

    private DcMotorEx motor;

    // ---- TUNE THESE ----
    static final double kP = 0.004;        // more power per tick of error
    static final double kD = 0.0001;       // smooths out overshoot
    static final double MAX_POWER = 0.4;   // keep low while testing
    static final int MIN_TICKS = -400;     // left limit (measure on your robot)
    static final int MAX_TICKS = 400;      // right limit (measure on your robot)
    static final double NUDGE_TICKS = 6;   // how far full stick moves the target each loop
    static final int DEADBAND = 5;         // close enough, stop moving
    // --------------------

    private double target = 0;
    private double lastError = 0;
    private final ElapsedTime timer = new ElapsedTime();

    public void init(HardwareMap hwMap) {
        motor = hwMap.get(DcMotorEx.class, "turret");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        target = 0;
        timer.reset();
    }

    // stick value from -1 to 1
    public void aim(double stick) {
        if (Math.abs(stick) < 0.1) {
            return; // ignore stick drift
        }
        target = Range.clip(target + stick * NUDGE_TICKS, MIN_TICKS, MAX_TICKS);
    }

    public void center() {
        target = 0;
    }

    // call this every loop
    public void update() {
        double error = target - motor.getCurrentPosition();
        double dt = timer.seconds();
        timer.reset();

        double derivative = (dt > 0) ? (error - lastError) / dt : 0;
        lastError = error;

        double power = kP * error + kD * derivative;
        if (Math.abs(error) < DEADBAND) {
            power = 0;
        }
        motor.setPower(Range.clip(power, -MAX_POWER, MAX_POWER));
    }

    public int getPosition() {
        return motor.getCurrentPosition();
    }

    public double getTarget() {
        return target;
    }
}