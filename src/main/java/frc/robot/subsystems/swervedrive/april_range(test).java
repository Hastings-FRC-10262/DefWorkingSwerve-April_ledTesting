owpackage frc.robot.subsystems.swervedrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.LimelightHelpers;
import frc.robot.subsystems.swervedrive.Leds.*;

public class Limelight_LED_Test extends SubsystemBase {

    private final Leds ledstrip;
    private final String ledname;
    private double angle;
    private double distance;

    public Limelight_LED_Test(Leds led, String ledname) {
        this.ledstrip = led;
        this.ledname = ledname;
    }

    private void getValue(LimelightHelpers.LimelightResults results) {

        double x = results.targets_Fiducials[0].t6c[0]; 
        double z = results.targets_Fiducials[0].t6c[2]; 

        distance = Math.sqrt(x * x + z * z);
        angle = Math.toDegrees(Math.atan2(x, z));
    }


    @Override
    public void periodic() {
         LimelightHelpers.setPipelineIndex(ledname, 9);
        // Get the correct Limelight table
        NetworkTable limelight =
            NetworkTableInstance.getDefault().getTable(ledname);;

        LimelightHelpers.LimelightResults results =
            LimelightHelpers.getLatestResults(ledname);

        boolean seesAprilTag =
            results != null &&
            results.targets_Fiducials != null &&
            results.targets_Fiducials.length > 0;

        System.out.println("tv = " + LimelightHelpers.getTV(ledname));
        System.out.println("pipeline latency = " +
            LimelightHelpers.getLatency_Pipeline(ledname));

        if (seesAprilTag) {
            getValue(results);
            System.out.println("AprilTag seen!");
            System.out.println("distance = " + this.distance);
            
            if (this.distance <= 1.0) {
                ledstrip.setYellow();
            } else {
                ledstrip.setWhite();
            }
            LimelightHelpers.setLEDMode_ForceOn(ledname);

        } else {

            System.out.println("AprilTag NOT seen!");

            ledstrip.setOrange();
            LimelightHelpers.setLEDMode_ForceOff(ledname);
        }
    }
}
