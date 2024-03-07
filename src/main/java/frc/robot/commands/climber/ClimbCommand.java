// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.climber.ClimberSubsystem;

/** An example command that uses an example subsystem. */
public class ClimbCommand extends Command {
  private final ClimberSubsystem m_ClimberSubsystem;
  private final XboxController m_driverXboxController;
  private final CommandGenericHID m_operatorController;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ClimbCommand(ClimberSubsystem climberSubsystem, 
                      CommandGenericHID operatorController, 
                      XboxController driverXboxController ) {
    m_ClimberSubsystem = climberSubsystem;
    m_driverXboxController = driverXboxController;
    m_operatorController = operatorController;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if( m_operatorController.getHID().getRawButton(ControllerConstants.ButtonRedUpper1) )
    {
      m_ClimberSubsystem.extendClimber( CANConstants.LeftClimberID);  
    }
    else if( m_operatorController.getHID().getRawButton(ControllerConstants.ButtonRedLower1) )
    {
      m_ClimberSubsystem.retractClimber( CANConstants.LeftClimberID);  
    }
    else 
    {
      m_ClimberSubsystem.stopClimber( CANConstants.LeftClimberID);  
    } 

    if( m_operatorController.getHID().getRawButton(ControllerConstants.ButtonRedUpper2) )
    {
      m_ClimberSubsystem.extendClimber( CANConstants.RightClimberID);  
    }
    else if( m_operatorController.getHID().getRawButton(ControllerConstants.ButtonRedLower2) )
    {
      m_ClimberSubsystem.retractClimber( CANConstants.RightClimberID);  
    }
    else 
    {
      m_ClimberSubsystem.stopClimber( CANConstants.RightClimberID);  
    } 

    
    if( m_ClimberSubsystem.areClimbersStalled() )
    {
      m_driverXboxController.setRumble(RumbleType.kLeftRumble, 1);  
    }
    else
    {
      m_driverXboxController.setRumble(RumbleType.kLeftRumble, 0);  
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ClimberSubsystem.stopClimbers(); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  
    return false;
  }
}
