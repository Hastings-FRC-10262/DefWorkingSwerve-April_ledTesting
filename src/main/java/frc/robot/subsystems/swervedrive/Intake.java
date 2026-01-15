package frc.robot.subsystems.swervedrive;

import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class Intake extends SubsystemBase {
    SparkMax intakeMotorTop, intakeMotorBottom;
    SparkMaxConfig intakeMotorTopConfig, intakeMotorBottomConfig;

    public Intake() {
        intakeMotorTop = new SparkMax(5, MotorType.kBrushless);
        intakeMotorBottom = new SparkMax(4, MotorType.kBrushless);

        
        intakeMotorTopConfig = new SparkMaxConfig();
        intakeMotorBottomConfig = new SparkMaxConfig();

        intakeMotorTop.configure(intakeMotorTopConfig.
            inverted(false).
            idleMode(IdleMode.kBrake), 
            ResetMode.kNoResetSafeParameters, 
            PersistMode.kPersistParameters);

        intakeMotorBottom.configure(intakeMotorBottomConfig.
            inverted(false).
            follow(intakeMotorTop).
            idleMode(IdleMode.kBrake), 
            ResetMode.kNoResetSafeParameters, 
            PersistMode.kPersistParameters);
    }

    public Command moveIntake(Double velocity) {
        return run(
            () -> {
              intakeMotorTop.set(velocity);
            });
    }


    @Override
    public void periodic() {
        
    }
}
