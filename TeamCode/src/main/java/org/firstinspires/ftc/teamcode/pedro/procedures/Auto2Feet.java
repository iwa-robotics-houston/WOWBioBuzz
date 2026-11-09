package org.firstinspires.ftc.teamcode.pedro.procedures;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Auto 2 Feet")
public class Auto2Feet extends LinearOpMode {

    private DcMotor leftMotor, rightMotor;

    // ---- FILL THESE IN ----
    static final double TICKS_PER_REV = 28 * 13.7;     // 5203 312 RPM motor. Check your motor's spec
    static final double WHEEL_DIAMETER_IN = 3.5;   // measure your wheel
    // -----------------------

    static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER_IN);
    static final double DRIVE_POWER = 0.4;

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftDrive");
        rightMotor = hardwareMap.get(DcMotor.class, "rightDrive");

        // same directions as your TeleOp
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        // forward 2 feet (24 inches)
        moveInches(24, 24);
    }

    private void moveInches(double leftInches, double rightInches) {
        leftMotor.setTargetPosition(leftMotor.getCurrentPosition() + (int) (leftInches * TICKS_PER_INCH));
        rightMotor.setTargetPosition(rightMotor.getCurrentPosition() + (int) (rightInches * TICKS_PER_INCH));

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(DRIVE_POWER);
        rightMotor.setPower(DRIVE_POWER);

        while (opModeIsActive() && (leftMotor.isBusy() || rightMotor.isBusy())) {
            telemetry.addData("Left", leftMotor.getCurrentPosition());
            telemetry.addData("Right", rightMotor.getCurrentPosition());
            telemetry.update();
        }

        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}