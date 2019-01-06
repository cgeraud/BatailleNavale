package fr.ensma.a3.ia.bataille_navale;

import fr.ensma.a3.ia.bataille_navale.game_elements.Cruiser;
import fr.ensma.a3.ia.bataille_navale.map.Map;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Map mapPlayer1 = new Map();
        Map mapPlayer2 = new Map();
        Cruiser nimitz = new Cruiser(Direction.Horizontal, new Coordinates(0,0));
        
    }
}
