package ejercicio;

import ejercicio.LightMix;
import ejercicio.MixingTank;
import ejercicio.Purge;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessTankTest {

    private LightMix lightmix;
    private Purge purge;
    private MixingTank tank;

    @BeforeEach
    void setUp(){
        this.lightmix = new LightMix();
        this.purge = new Purge();
        this.tank = new MixingTank();
    }

    @Test 
    public void testExecute(){
        this.lightmix.execute(tank);
        this.purge.execute(tank);
        assertTrue(this.lightmix.isDone());
        assertTrue(this.purge.isDone());
    }


}
