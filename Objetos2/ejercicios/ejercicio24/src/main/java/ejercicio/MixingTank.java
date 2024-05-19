/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio;

/**
 *
 * @author frank
 */
public class MixingTank {
    private int heatPower;
    private int mixerPower;
    private double volume;
    private double temperature;

    public MixingTank() {
        this.heatPower = 0;
        this.mixerPower = 0;
        this.volume = 0;
        this.temperature = 0;
    }

    public boolean heatPower(int percentage){
        this.heatPower = percentage;
        return true;
    }

    public boolean mixerPower(int percentage){
        this.mixerPower = percentage;
        return true;
    }

    public boolean purge(){
        this.volume = 0;
        return true;
    }

    public double upTo(){
        return this.volume;
    }

    public double temperature(){
        return this.temperature;
    }
}
