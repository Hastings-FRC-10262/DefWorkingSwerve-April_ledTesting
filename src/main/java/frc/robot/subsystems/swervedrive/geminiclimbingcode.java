package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
  
  // Define your CAN ID here (Change 5 to your actual ID)
  private static final int CLIMBER_MOTOR_ID = 5;
  
  private final CANSparkMax climberMotor;

  public ClimberSubsystem() {
    // Initialize the motor
    climberMotor = new CANSparkMax(CLIMBER_MOTOR_ID, MotorType.kBrushless);

    // RESTORE FACTORY DEFAULTS
    // This is important to ensure no old settings persist on the controller
    climberMotor.restoreFactoryDefaults();

    // 1. SET IDLE MODE TO BRAKE
    // CRITICAL: This attempts to hold the robot up when power is cut (end of match).
    // Without this, the robot will slide down immediately.
    climberMotor.setIdleMode(IdleMode.kBrake);

    // 2. SET CURRENT LIMITS
    // Smart Current Limit prevents the motor from drawing too much power 
    // if the mechanism jams or reaches the physical stop.
    // 60 Amps is standard for NEOs, but you might lower this to 40A or 50A for a climber
    // depending on your gearing to play it safe.
    climberMotor.setSmartCurrentLimit(60);

    // 3. INVERT SETTING
    // Check the direction of your climber. If positive power makes it go DOWN,
    // change this to true.
    climberMotor.setInverted(false);
    
    // 4. SOFT LIMITS (Highly Recommended)
    // If you know the max rotations your climber can extend, set this up 
    // to prevent the mechanism from tearing itself apart.
    // climberMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 50.0f); 
    // climberMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);

    // Save configuration to flash (prevents reset on brownout)
    climberMotor.burnFlash();
  }

  /**
   * Sets the speed of the climber motor.
   * @param speed -1.0 to 1.0. Positive usually means extend/up (check inversion).
   */
  public void setSpeed(double speed) {
    climberMotor.set(speed);
  }

  /**
   * Stops the motor specifically.
   */
  public void stop() {
    climberMotor.set(0);
  }

  @Override
  public void periodic() {
    // Put telemetry here if needed (e.g., SmartDashboard.putNumber(...))
  }
}
