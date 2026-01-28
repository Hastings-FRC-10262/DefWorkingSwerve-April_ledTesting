package frc.robot.subsystems.swervedrive;

import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

//conveyrrrrr import
import frc.robot.subsystems.swervedrive.Conveyor;

public class Intake extends SubsystemBase {
    SparkFlex IntakeMotor;
    SparkFlexConfig IntakeMotorConfig = new SparkFlexConfig();
    Conveyor m_conveyor;
    public Intake(Conveyor conveyor) {
        IntakeMotor = new SparkFlex(2, MotorType.kBrushless);
        IntakeMotorConfig = new SparkFlexConfig();
        //put tthe thinging from the constructor inside the other thingy i put at the top
        m_conveyor = conveyor;

        IntakeMotorConfig.inverted(true);
        IntakeMotorConfig.idleMode(IdleMode.kCoast);
        IntakeMotorConfig.openLoopRampRate(0.5);
        IntakeMotorConfig.smartCurrentLimit(40);

        IntakeMotor.configure(IntakeMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }
    //rember its power not veolicty lock in
    public Command runIntake(Double intakePower, double conveyorPower) {
        return run(
            () -> {
              IntakeMotor.set(intakePower);
              m_conveyor.setPower(conveyorPower);
            });
    }

    public Command stopIntake() {
        return run(
            () -> {
              IntakeMotor.set(0);
              m_conveyor.setPower(0.0);
            });
    }

    public Command testIntakeMotor(Double power) {
        return run(
            () -> {
              IntakeMotor.set(power);
            });
    }


    @Override
    public void periodic() {
        
    }
}
