/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio;

/**
 *
 * @author frank
 */
public class MixingTankMock extends MixingTank {

    private int heatPower;
    private int mixerPower;
    private double volume;
    private double temperature;
    private long timeSince;

    public MixingTankMock() {
        this.heatPower = 0;
        this.mixerPower = 0;
        this.volume = 0;
        this.temperature = 0;
    }

    @Override
    public boolean heatPower(int percentage) {
        if (percentage >= 0 && percentage <= 100) {
            this.heatPower = percentage;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean mixerPower(int percentage) {
        if (percentage >= 0 && percentage <= 100) {
            this.mixerPower = percentage;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean purge() {
        if (this.volume == 0) {
            return false;
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.volume = 0;
        return true;
    }

    @Override
    public double upTo() {
        return this.volume;
    }

    @Override
    public double temperature() {
        if (this.heatPower == 100) {
            return this.timeSince * 5.0;
        }
        if (this.heatPower == 75) {
            return this.timeSince * 4.0;
        }
        if (this.heatPower == 50) {
            return this.timeSince * 2.0;
        }
        if (this.heatPower == 25) {
            return this.timeSince * 1.0;
        }

        return 0.0;
    }

    public void updateElapsedTime(long time) {
        this.timeSince += time;
    }
}
