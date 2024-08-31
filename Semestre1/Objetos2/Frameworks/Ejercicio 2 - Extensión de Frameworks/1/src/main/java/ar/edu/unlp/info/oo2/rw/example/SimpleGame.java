package ar.edu.unlp.info.oo2.rw.example;

import ar.edu.unlp.info.oo2.rw.model.GameBoard;
import ar.edu.unlp.info.oo2.rw.model.NuclearCaterpillarRobotWithBombs;
import ar.edu.unlp.info.oo2.rw.model.NuclearOvercraftRobotWithBombs;
import ar.edu.unlp.info.oo2.rw.model_extension.SolarCaterpillarRobotWithBombs;

public class SimpleGame {

    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        board.add(new NuclearOvercraftRobotWithBombs("Twonky"));
        board.add(new NuclearCaterpillarRobotWithBombs("Hammer Bot"));
        board.add(new SolarCaterpillarRobotWithBombs("Jacinto"));
        board.runForCicles(5);
    }
}
