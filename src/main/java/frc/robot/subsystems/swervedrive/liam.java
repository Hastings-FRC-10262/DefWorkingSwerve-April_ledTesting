package frc.robot.subsystems.swervedrive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.LimelightHelpers;
import frc.robot.subsystems.swervedrive.Leds;

public class Limelight_LED_Test extends SubsystemBase {

    private final Leds ledstrip;
    private final String limelightName;

    public Limelight_LED_Test(Leds led, String limelightName) {
        this.ledstrip = led;
        this.limelightName = limelightName;

        // Set AprilTag pipeline ONCE
        LimelightHelpers.setPipelineIndex(limelightName, 9);
    }

    @Override
    public void periodic() {

        LimelightHelpers.LimelightResults results =
            LimelightHelpers.getLatestResults(limelightName);

        boolean seesAprilTag =
            results != null &&
            results.valid &&
            results.targets_Fiducials != null &&
            results.targets_Fiducials.length > 0;

        Pose2d botPose = LimelightHelpers.getBotPose2d(limelightName);

        if (seesAprilTag) {
                        
            double angle = LimelightHelpers.getTX(limelightName); 

            // Forward distance from CAMERA to AprilTag (meters)
            double distanceMeters =
                LimelightHelpers
                    .getTargetPose3d_CameraSpace(limelightName)
                    .getZ();
        
            System.out.println("AprilTag seen");
            System.out.println("Robot X: " + botPose.getX());
            System.out.println("Robot Y: " + botPose.getY());
            System.out.println("Distance (m): " + distanceMeters);
            System.out.println("Camera Angle: " + angle); 
            
            if (distanceMeters <= 1.0) {
                ledstrip.setYellow();
            } else {
                ledstrip.setWhite();
            }

            LimelightHelpers.setLEDMode_ForceOn(limelightName);

        } else {

            System.out.println("AprilTag NOT seen");

            ledstrip.setOrange();
            LimelightHelpers.setLEDMode_ForceOff(limelightName);
        }
    }
}
