package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.LauncherTest.LauncherUtils;
import org.firstinspires.ftc.teamcode.mechanisms.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.ServoAprilTag;

//import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraCompatibilityManager;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.imgproc.Moments;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvPipeline;
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

@TeleOp
public class DriveOpMode extends OpMode {

    private Limelight3A limelight;
    ServoAprilTag pulley = new ServoAprilTag();
    //this is just the pulley, can fix later

    DriveTrain drive = new DriveTrain();

    GoBildaPinpointDriver imu;

    DcMotorEx turret;

    CRServo passThrough;
    CRServo passUp;

    double lateral, axial, intake;
    double tx, ty, ta;

    double servo_pos = 0.50;

    double f = 13.9;
    double p = 167.0;

    private VoltageSensor myControlHubVoltageSensor;

    int newVelocity = 0;
    double adj_motor_ratio = 0.75;

    LauncherUtils launcher = new LauncherUtils();

    double distanceToGoalInches;

    @Override
    public void init() {


        drive.init(hardwareMap);


        launcher.init(hardwareMap);

        //limelight = hardwareMap.get(Limelight3A.class, "limelight");
        telemetry.setMsTransmissionInterval(11);
        //limelight.pipelineSwitch(7);
        //hi what is this pipeline for --Carys

        pulley.init(hardwareMap);
        pulley.servoStop();

        turret = hardwareMap.get(DcMotorEx.class, "turret");
        passThrough = hardwareMap.get(CRServo.class, "passthrough");
        passUp = hardwareMap.get(CRServo.class, "passup");
        turret.setPositionPIDFCoefficients(0);
        imu = hardwareMap.get(GoBildaPinpointDriver.class, "imu");

//
    }

    public void start() {
        //limelight.start();
        turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //just in case
        //limelight.startTracking();
    }


    @Override
    public void loop() {
        axial = gamepad1.left_stick_y;
        lateral = -gamepad1.right_stick_x;
        intake = gamepad2.right_stick_y;

        drive.drive(axial, lateral);
        drive.intake(intake);
        imu.update();

        telemetry.addData("axial: ", axial);
        telemetry.addData("lateral: ", lateral);
        //telemetry.addData("Turret Angle", turret.getTargetPosition());

        if (gamepad2.left_stick_y > 0.5) {
            pulley.servoSpinForward();
        } else if (gamepad2.left_stick_y < 0.0)  {
            pulley.servoSpinBackward();
        } else {
            pulley.servoStop();
        }
        //cameraServo.setServo(servo_pos);

        //pass-through & pass-up
        //can be combined together or combined with launcher
        if (gamepad2.dpad_up) {
            passThrough.setPower(.5);
        } else if (gamepad2.dpad_down) {
            passThrough.setPower(-.5);
        } else {
            passThrough.setPower(0);
        }

        if (gamepad2.dpad_left) {
            passUp.setPower(1);
        } else if (gamepad2.dpad_right) {
            passUp.setPower(-.5);
        } else {
            passUp.setPower(0);
        }

        /*
        //this is straight up last season's code, was hoping to use skeleton
        limelight.uploadPipeline();
        limelight.scanGoal();
        if (limelight.resultWorks() && limelight.getTa() > 1.7) {

        } else if (limelight.resultWorks() && limelight.getTa() < 1.7) {

        */
    }
}

