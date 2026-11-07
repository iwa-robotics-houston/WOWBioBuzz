package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class ServoAprilTag {

    private CRServo servo;

    public void init(HardwareMap hwMap) {
        //servo = hwMap.get(Servo.class, "pulleyServo");
        servo = hwMap.get(CRServo.class, "pulleyServo");
    }

//    public void setServo(double angle) {
//        servo.setPosition(angle);
//    }

    public void servoSpinForward() {
        servo.setDirection(DcMotorSimple.Direction.FORWARD);
        servo.setPower(0.5);
    }

    public void servoSpinBackward() {
        servo.setDirection(DcMotorSimple.Direction.FORWARD);
        servo.setPower(0.5);
    }

    public void servoStop() {
        servo.setDirection(DcMotorSimple.Direction.FORWARD);
        servo.setPower(0.5);
    }

}
