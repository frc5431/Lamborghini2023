package frc.robot;

import edu.wpi.first.wpilibj2.command.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;


public class drivebase extends SubsystemBase{

    public WPI_TalonFX backRightOne;
    public WPI_TalonFX backRightTwo;

    public WPI_TalonFX backLeftOne;
    public WPI_TalonFX backLeftTwo;

    public boolean rightMotorInvert = true;
    public boolean leftMotorInvert = false;
    public boolean isDriving = false;

    public drivebase(WPI_TalonFX backRightOne, WPI_TalonFX backRightTwo,
    WPI_TalonFX backLeftOne, WPI_TalonFX backLeftTwo){
        this.backRightOne = backRightOne;
        this.backRightTwo = backRightTwo;
        this.backLeftOne = backLeftOne;
        this.backLeftTwo = backLeftTwo;

        backRightOne = new WPI_TalonFX(0);
        backRightTwo = new WPI_TalonFX(0);
        
        backLeftOne = new WPI_TalonFX(0);
        backLeftTwo = new WPI_TalonFX(0);

        backRightOne.setInverted(rightMotorInvert);
        backRightOne.setNeutralMode(NeutralMode.Coast);
        backRightTwo.setInverted(rightMotorInvert);
        backRightOne.setNeutralMode(NeutralMode.Coast);
        
        backLeftOne.setInverted(leftMotorInvert);
        backLeftOne.setNeutralMode(NeutralMode.Coast);

        backLeftTwo.setInverted(leftMotorInvert);
        backLeftTwo.setNeutralMode(NeutralMode.Coast);

    }

    public void driveLeft(double val) {
        backLeftOne.set(val);
        backLeftTwo.set(val);
    }

    public void driveRight(final double val) {
        backRightOne.set(val);
        backRightTwo.set(val);
    }

    public void drive(double left, double right) {
        driveLeft(left);
        driveRight(right);
    }

    //con ? 1 : 0

    // Based on arcade drive pseudocode pdf found on CD
    public void driveArcade(final double forward, final double turn) {
        double max = Math.abs(forward);
        max = Math.abs(turn) > max ? max = Math.abs(turn) : Math.abs(forward);

        double sum = forward + turn;
        double dif = forward - turn;

        if (forward > 0) {
            if (turn >= 0) {
                driveLeft(max);
                driveRight(dif);
            } else {
                driveLeft(sum);
                driveRight(max);
            }
            
        

        } else if (turn >= 0) {
            driveLeft(sum);
            driveRight(-max);
        } else {
            driveLeft(-max);
            driveRight(dif);
        }

    }







}