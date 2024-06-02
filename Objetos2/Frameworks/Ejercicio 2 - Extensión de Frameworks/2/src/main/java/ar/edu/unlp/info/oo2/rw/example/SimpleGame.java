package ar.edu.unlp.info.oo2.rw.example;

import ar.edu.unlp.info.oo2.rw.model.BombsSystem;
import ar.edu.unlp.info.oo2.rw.model.Caterpillar;
import ar.edu.unlp.info.oo2.rw.model.GameBoard;
import ar.edu.unlp.info.oo2.rw.model.LasersSystem;
import ar.edu.unlp.info.oo2.rw.model.NuclearPlant;
import ar.edu.unlp.info.oo2.rw.model.Overcraft;
import ar.edu.unlp.info.oo2.rw.model.Robot;
import ar.edu.unlp.info.oo2.rw.model_extension.FourWheeler;

public class SimpleGame {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        board.add(new Robot("Twonky", new Caterpillar(), new NuclearPlant(), new BombsSystem()));
        board.add(new Robot("Hammer Bot", new Overcraft(), new NuclearPlant(), new LasersSystem()));
        board.add(new Robot("Hammer Bot", new FourWheeler(), new NuclearPlant(), new LasersSystem()));
        board.runForCicles(5);
    }
}
