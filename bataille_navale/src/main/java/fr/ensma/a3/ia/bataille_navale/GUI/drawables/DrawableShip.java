package fr.ensma.a3.ia.bataille_navale.GUI.drawables;

import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.AircraftCarrier;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.Cruiser;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.Destroyer;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.SailBoat;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.ShipType;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.Submarine;
import fr.ensma.a3.ia.bataille_navale.game_elements.Ships.TorpedoBoat;
import fr.ensma.a3.ia.bataille_navale.utils.Coordinates;
import fr.ensma.a3.ia.bataille_navale.utils.Direction;
import fr.ensma.a3.ia.bataille_navale.utils.Shape;

public class DrawableShip {
	
	private ShipType type = null;
	private String name = null;
	private Coordinates origin = null;
	private Direction direction = null;

	public DrawableShip() {}

	public ShipType getType() {
		return type;
	}

	public void setType(ShipType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coordinates getOrigin() {
		return origin;
	}

	public void setOrigin(Coordinates origin) {
		this.origin = origin;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Shape getShape() {
		Shape shape = null;
		switch(this.type) {
		case AircraftCarrier:
			shape = AircraftCarrier.getShape();
			break;
		case Cruiser:
			shape = Cruiser.getShape();
			break;
		case Destroyer:
			shape = Destroyer.getShape();
			break;
		case Submarine:
			shape = Submarine.getShape();
			break;
		case TorpedoBoat:
			shape = TorpedoBoat.getShape();
			break;
		case SailBoat:
			shape = SailBoat.getShape();
			break;
		}
		return shape;
	}
	
	public Shape getCoordinates() {
		Shape retShape = new Shape();
		for(Coordinates coord : this.getShape().getRelativeTiles()) {
			if(this.direction==Direction.Horizontal)
				retShape.addRelativeTile(new Coordinates(coord.getX()+getOrigin().getX(),
						coord.getY()+getOrigin().getY()));
			else
				retShape.addRelativeTile(new Coordinates(coord.getY()+getOrigin().getX(),
						getOrigin().getY()-coord.getX()));
		}
		return retShape;
	}
}
