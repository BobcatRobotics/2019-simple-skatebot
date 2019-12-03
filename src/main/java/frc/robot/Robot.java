/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private WPI_TalonSRX leftTalon = new WPI_TalonSRX(0);
  private WPI_VictorSPX rightVictor = new WPI_VictorSPX(1);
  private WPI_TalonSRX rightTalon = new WPI_TalonSRX(2);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftTalon, rightVictor);
  private final Joystick m_stick = new Joystick(0);
  public static Solenoid solenoid1 = new Solenoid(4);
  public static Solenoid solenoid2 = new Solenoid(7);
  private double leftVelocity = 0.0;
  private double leftDistance = 0.0;

  // Flip the phase of the encoder for use with SRX motion magic, etc.
  // and set current position to 0.0;
  //leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,0,0);
  //leftTalon.setSensorPhase(true);
  //leftTalon.setSelectedSensorPosition(0,0,0);

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // Flip the phase of the encoder for use with SRX motion magic, etc.
    // and set current position to 0.0;
      leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,0,0);
      leftTalon.setSensorPhase(true);
      leftTalon.setSelectedSensorPosition(0,0,0);
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    //m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
    SmartDashboard.putNumber("The left stick value is:", m_stick.getRawAxis(Joystick.AxisType.kY.value));
    m_robotDrive.tankDrive(m_stick.getRawAxis(Joystick.AxisType.kY.value), m_stick.getRawAxis(Joystick.AxisType.kTwist.value));

    // Try to drive the solenoids
    solenoid1.set(m_stick.getRawButton(1));
    solenoid2.set(m_stick.getRawButton(2));
    leftDistance = -1.0*leftTalon.getSelectedSensorPosition(0);
    leftVelocity = leftTalon.getSelectedSensorVelocity(0);
  }

   /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void disabledPeriodic() {
    //m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
    SmartDashboard.putNumber("Current gamepad left stick value:", m_stick.getRawAxis(Joystick.AxisType.kY.value));
    SmartDashboard.putNumber("Current gamepad right stick value:", m_stick.getRawAxis(Joystick.AxisType.kTwist.value));
    leftDistance = -1.0*leftTalon.getSelectedSensorPosition(0);
    leftVelocity = leftTalon.getSelectedSensorVelocity(0);
    SmartDashboard.putNumber("left distance value:", leftDistance);
    SmartDashboard.putNumber("right velocity value:", leftVelocity);
}

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
