package frc.robot.subsystems.swervedrive;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Define your CAN ID here (Change 5 to your actual ID)
private static final int CLIMBER_MOTOR_ID = 5;

public class ClimberSubsystem extends SubsystemBase {

    private final SparkMax climberMotor = new SparkMax(CLIMBER_MOTOR_ID, MotorType.kBrushless);

    public init() {
       
        SparkMaxConfig config = new SparkMaxConfig();

        // 1. Set Brake Mode: Holds position when power is 0
        config.idleMode(IdleMode.kBrake);

        // 2. Smart Current Limit: 40A-60A is safe for most climbers
        config.smartCurrentLimit(50);

        // 3. Optional: Invert motor if it pulls when it should push
        config.inverted(false);

        // Apply configuration
        climberMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        
    }
    //Negative speed retracts
    
    public void runWinch(double speed) {
            climberMotor.set(speed);
        }

    
    public void stop() {
            climberMotor.set(0);
        }
}
