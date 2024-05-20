/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio;

/**
 *
 * @author frank
 */
public abstract class MixingTank {

    public abstract boolean heatPower(int percentage);

    public abstract boolean mixerPower(int percentage);

    public abstract boolean purge();

    public abstract double upTo();

    public abstract double temperature();

    public abstract void updateElapsedTime(long time);
}
