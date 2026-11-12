package org.firstinspires.ftc.teamcode.LauncherTest;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class LauncherUtils {

    private DcMotorEx topMotor, botMotor;
    double velocity;
    double adj_ratio;

    public double actualVelocity1, actualvelocity2;



    public void init(HardwareMap hwMap) {
        topMotor = hwMap.get(DcMotorEx.class, "topMotor");
        botMotor = hwMap.get(DcMotorEx.class, "bottomMotor");

        topMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        botMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);


    }

    public void shooter(int velocity, double adj_ratio, PIDFCoefficients pidCoefficients) {

        int adjustedvelocity = (int) (-1*velocity*adj_ratio);
        topMotor.setVelocity(adjustedvelocity);
        botMotor.setVelocity(velocity);

        topMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidCoefficients);
        botMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidCoefficients);
        actualVelocity1 = topMotor.getVelocity();
        actualvelocity2 = botMotor.getVelocity();

    }
}
