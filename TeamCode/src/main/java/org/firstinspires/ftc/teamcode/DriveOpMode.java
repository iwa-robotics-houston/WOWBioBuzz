package org.firstinspires.ftc.teamcode;

//import com.qualcomm.hardware.limelightvision.LLResultTypes;
//import com.qualcomm.hardware.limelightvision.LLStatus;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
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

//    private Limelight3A limelight;
    ServoAprilTag pulleyServo = new ServoAprilTag();

    DriveTrain drive = new DriveTrain();

    double throttle, spin, getstuff;
    double tx, ty, ta;

    double servo_pos = 0.50;

    double f = 13.9;
    double p = 167.0;

    //private VoltageSensor myControlHubVoltageSensor;

    int newvelocity = 0;
    double adj_motor_ratio = 0.75;

//    LauncherUtils launcher = new LauncherUtils();

    double distanceToGoalInches;

    @Override
    public void init() {


        drive.init(hardwareMap);


//        launcher.init(hardwareMap);

//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        telemetry.setMsTransmissionInterval(11);
//        limelight.pipelineSwitch(7);


        pulleyServo.init(hardwareMap);
        pulleyServo.servoStop();
//
    }

    public void start() {
//        limelight.start();
    }


    @Override
    public void loop() {
        throttle = gamepad1.left_stick_y;
        spin = -gamepad1.right_stick_x;
        getstuff = gamepad2.right_stick_y;

        drive.drive(throttle, spin);
        drive.intake(getstuff);


        telemetry.addData("throttle: ", throttle);
        telemetry.addData("spin: ", spin);

//        if (gamepad2.left_stick_y > 0.5) {
//            pulleyServo.servoSpinForward();
//        } else if (gamepad2.left_stick_y < 0.0)  {
//            pulleyServo.servoSpinBackward();
//        } else {
//            pulleyServo.servoStop();
//        }

//        cameraServo.setServo(servo_pos);

    }
}

