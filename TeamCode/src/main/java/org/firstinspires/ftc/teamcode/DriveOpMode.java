package org.firstinspires.ftc.teamcode;

//import com.qualcomm.hardware.limelightvision.LLResultTypes;
//import com.qualcomm.hardware.limelightvision.LLStatus;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
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
    //ServoAprilTag cameraServo = new ServoAprilTag();

    DriveTrain drive = new DriveTrain();

    double throttle, spin, getstuff;
    double tx, ty, ta;

    double servo_pos = 0.50;

    double distanceToGoalInches;

    @Override
    public void init() {


        drive.init(hardwareMap);

//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        telemetry.setMsTransmissionInterval(11);
//        limelight.pipelineSwitch(7);


//        cameraServo.init(hardwareMap);
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


//        LLResult result = limelight.getLatestResult();
//        if (result != null && result.isValid()) {
//
//                Pose3D botpose = result.getBotpose();
//                telemetry.addData("tx", result.getTx());
//                telemetry.addData("ty", result.getTy());
//                telemetry.addData("Botpose", botpose.toString());
//            }
//        else {
//            telemetry.addData("Camera", "no data");
//        }
//
        if (gamepad2.left_trigger_pressed) {
            if(servo_pos < 0.41) {
                servo_pos = 0.40;
            } else {
                servo_pos -= 0.02;
            }

        }

        if (gamepad1.xWasPressed()) {
            if(servo_pos > 0.70) {
                servo_pos = 0.70;
            } else {
                servo_pos += 0.02;
            }
        }
//        cameraServo.setServo(servo_pos);

    }
}

