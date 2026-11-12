package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Mechanisms.Intake;

@TeleOp(name = "MainTeleOp", group = "LinearOpMode")
public class MainTeleOp extends LinearOpMode {
 
    public DcMotor  leftDrive   = null;
    public DcMotor  rightDrive  = null;


    Intake intake = new Intake();
    Controls controls = new Controls(gamepad1, gamepad2);



    public void runOpMode() {

        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left and right sticks forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        intake.init(hardwareMap);
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();



        waitForStart();

        while (opModeIsActive()) {

            double left;
            double right;

            // Run the wheels in tank mode (note: The joystick goes negative when pushed forward, so negate it)
            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;

            leftDrive.setPower(left);
            rightDrive.setPower(right);

            if (gamepad2.left_trigger > 0) {
                intake.intake();

            } else if (gamepad2.left_bumper) {
                intake.release();
            } else {
                intake.stop();

            }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }

    }
}