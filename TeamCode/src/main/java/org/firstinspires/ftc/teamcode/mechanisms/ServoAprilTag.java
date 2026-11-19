package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ServoAprilTag {

    private CRServo pulley;

    public void init(HardwareMap hwMap) {
        pulley = hwMap.get(CRServo.class, "pulleyServo");
        pulley.setDirection(DcMotorSimple.Direction.FORWARD);
        pulley.setPower(0);
    }

    public void servoSpinForward() {
        pulley.setPower(0.5);
    }

    public void servoSpinBackward() {
        pulley.setPower(-0.5);
    }

    public void servoStop() {
        pulley.setPower(0.0);
    }
}