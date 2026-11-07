package org.firstinspires.ftc.teamcode.LauncherTest;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class LauncherUtils {

    private DcMotorEx motorOne, motorTwo;
    double velocity;
    double adj_ratio;

    public double actualVelocity1, actualvelocity2;



    public void init(HardwareMap hwMap) {
        motorOne = hwMap.get(DcMotorEx.class, "topMotor");
        motorTwo = hwMap.get(DcMotorEx.class, "bottomMotor");

        motorOne.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motorTwo.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);


    }

    public void shooter(int velocity, double adj_ratio, PIDFCoefficients pidCoefficients) {

        int adjustedvelocity = (int) (-1*velocity*adj_ratio);
        motorOne.setVelocity(adjustedvelocity);
        motorTwo.setVelocity(velocity);

        motorOne.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidCoefficients);
        motorTwo.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidCoefficients);
        actualVelocity1 = motorTwo.getVelocity();
        actualvelocity2 = motorOne.getVelocity();

    }
}
