package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ServoAprilTag {

    private CRServo pulley;

    public void init(HardwareMap hwMap) {
        //servo = hwMap.get(Servo.class, "pulleyServo");
        pulley = hwMap.get(CRServo.class, "pulleyServo");
        pulley.setPower(0);
    }

//    public void setServo(double angle) {
//        servo.setPosition(angle);
//    }

    public void servoSpinForward() {
        pulley.setDirection(DcMotorSimple.Direction.FORWARD);
        pulley.setPower(0.5);
    }

    public void servoSpinBackward() {
        pulley.setDirection(DcMotorSimple.Direction.FORWARD);
        pulley.setPower(0.5);
    }

    public void servoStop() {
        pulley.setDirection(DcMotorSimple.Direction.FORWARD);
        pulley.setPower(0.0);
    }

}
