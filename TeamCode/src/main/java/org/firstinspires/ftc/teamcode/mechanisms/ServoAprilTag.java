package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoAprilTag {

    private Servo servo;

    public void init(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "limelight_servo");
    }

    public void setServo(double angle) {
        servo.setPosition(angle);
    }

}
