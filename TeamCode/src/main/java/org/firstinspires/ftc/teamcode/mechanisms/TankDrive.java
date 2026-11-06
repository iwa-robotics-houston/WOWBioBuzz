package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class TankDrive {

    private DcMotor leftMotor, rightMotor, intakeMotor;

    public void init(HardwareMap hwMap) {
        leftMotor = hwMap.get(DcMotor.class, "leftDrive");
        rightMotor = hwMap.get(DcMotor.class, "rightDrive");
        intakeMotor = hwMap.get(DcMotor.class, "intake");


        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

//        servo = hwMap.get(Servo.class, "_servo");
    }

    public void drive(double throttle, double spin) {

        double leftPower = throttle + spin;
        double rightPower = throttle - spin;

        double largest = Math.max(Math.abs(leftPower), Math.abs(rightPower));

        if (largest > .85) {
            leftPower /= largest;
            rightPower /= largest;
        }

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }

    public void intake(double getstuff) {
        intakeMotor.setPower(getstuff);

    }
}