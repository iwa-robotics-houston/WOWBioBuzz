package org.firstinspires.ftc.teamcode.LauncherTest;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor;




@TeleOp
public class LauncherOpMode extends OpMode {

    double f = 13.9;
    double p = 167.0;

    private VoltageSensor myControlHubVoltageSensor;

    int newvelocity = 0;
    double adj_motor_ratio = 1.75;

    LauncherUtils launcher = new LauncherUtils();


    @Override
    public void init() {

        launcher.init(hardwareMap);

        // initialize voltage sensor
        myControlHubVoltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");

    }

    @Override
    public void loop() {

        // adjust velocity based on button pushed
        if (gamepad1.yWasPressed()) {
            newvelocity = 1400;
        } else if (gamepad1.bWasPressed()) {
            newvelocity = 0;
        } else if (gamepad1.xWasPressed()) {
            newvelocity = (newvelocity + 28);
        } else if (gamepad1.aWasPressed()) {
            newvelocity = (newvelocity - 28);
        }

        // adjust ratio of small wheel velocity to big wheel velocity
        if (gamepad1.leftTriggerWasPressed()) {
            adj_motor_ratio += 0.05;

        } else if (gamepad1.leftBumperWasPressed()) {
            adj_motor_ratio -= 0.05;
        }

        if (gamepad1.dpadUpWasPressed()) {
            f += 1;
        } else if (gamepad1.dpadRightWasPressed()) {
            f += 0.1;
        } else if (gamepad1.dpadDownWasPressed()) {
            f -= 1;
        } else if (gamepad1.dpadLeftWasPressed()) {
            f -= 0.1;
        }


        PIDFCoefficients newCoefficients = new PIDFCoefficients(p, 0, 0, f);


        launcher.shooter(newvelocity, adj_motor_ratio, newCoefficients);


        telemetry.addData("Main target velocity: ", newvelocity);
        telemetry.addData("Main actual velocity: ", launcher.actualVelocity1);
        telemetry.addData("Adjusted target velocity: ", -1*newvelocity/adj_motor_ratio);
        telemetry.addData("Actual adjusted velocity: ", launcher.actualvelocity2);
        telemetry.addData("Wheel ratio: ", adj_motor_ratio);
        telemetry.addData("Voltage: ", myControlHubVoltageSensor.getVoltage());
        telemetry.addData("F value: ", f);
        telemetry.addData("P value: ", p);

    }
}