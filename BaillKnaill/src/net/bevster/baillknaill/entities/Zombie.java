package net.bevster.baillknaill.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Zombie extends Player {

	int				id;

	static int		amount		= 0;

	public boolean	move		= true;
	public boolean	draw		= true;
	public boolean	collision	= true;

	public Zombie(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub

		if (Zombie.amount == 0) Zombie.amount = 1;
		Zombie.amount++;
		this.id = amount;

	}

	@Override
	public void Paint(Graphics2D g) {
		// TODO Auto-generated method stub
		super.Paint(g);

		g.setColor(Color.black);
		g.fillOval(x - 1, y - 1, width + 2, height + 2);

		g.setColor(Color.red);
		g.fillOval(x, y, width, height);

		g.setColor(Color.black);
		g.fillOval(x + (width / 2) - (width / 3) - 1, y + (height / 2) - (height / 3) - 1, (width / 3) + 2, (height / 3) + 2);

		g.fillOval(x + (width / 3) + (width / 3) - 1, y + (height / 2) - (height / 3) - 1, (width / 3) + 2, (height / 3) + 2);

		g.setColor(Color.WHITE);
		g.fillOval(x + (width / 2) - (width / 3), y + (height / 2) - (height / 3), width / 3, height / 3);

		g.fillOval(x + (width / 3) + (width / 3), y + (height / 2) - (height / 3), width / 3, height / 3);

		g.drawString(this.name, x + (width / 2) / 2, y - 5);
	}

	public void setMoveZombie(Boolean bol) {

		if (move != bol) {

			move = bol;
		}

	}

	public boolean getMoveZombie() {

		return move;
	}

	public void setDrawZombie(Boolean bol) {

		if (draw != bol) {

			draw = bol;
		}

	}

	public boolean getDrawZombie() {

		return draw;
	}

	public void setColZombie(Boolean bol) {

		if (collision != bol) {

			collision = bol;
		}

	}

	public boolean getColZombie() {

		return collision;
	}

	public int getID() {

		return this.id;
	}

	int getAmount() {

		return amount;
	}

}
