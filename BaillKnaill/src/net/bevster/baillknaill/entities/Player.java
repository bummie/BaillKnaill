package net.bevster.baillknaill.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {

	String	name;
	int		x, y;
	int		width, height;
	int		velocity;
	int		score	= 0;
	Color	col		= new Color(50, 205, 50);
	boolean	show	= false;

	public Player(String name, int x, int y, int width, int height) {

		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public void Paint(Graphics2D g) {

		g.setColor(Color.black);
		g.fillOval(x - 1, y - 1, width + 2, height + 2);

		g.setColor(col);
		g.fillOval(x, y, width, height);

		g.setColor(Color.black);
		g.fillOval(x + (width / 2) - (width / 3) - 1, y + (height / 2) - (height / 3) - 1, (width / 3) + 2, (height / 3) + 2);

		g.fillOval(x + (width / 3) + (width / 3) - 1, y + (height / 2) - (height / 3) - 1, (width / 3) + 2, (height / 3) + 2);

		g.setColor(Color.WHITE);
		g.fillOval(x + (width / 2) - (width / 3), y + (height / 2) - (height / 3), width / 3, height / 3);

		g.fillOval(x + (width / 3) + (width / 3), y + (height / 2) - (height / 3), width / 3, height / 3);

		g.drawString(this.name, x + (width / 2) / 2, y - 5);

	}

	public String getName() {

		return name;
	}

	public void setName(String nam) {

		this.name = nam;
	}

	public void setName(int nam) {

		this.name = Integer.toString(nam);
	}

	public void setScore(int scor) {

		this.score = scor;
	}

	public int getScore() {

		return this.score;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setVelocity(int vel) {

		this.velocity = vel;
	}

	public int getVelocity() {

		return this.velocity;
	}

	public void setColor(Color col) {

		this.col = col;

	}

	public Color getColor() {

		return col;
	}

	public void move(int x, int y) {

		this.x += x;
		this.y += y;

	}

	public void setPos(int x, int y) {

		this.x = (x + (this.width / 2));
		this.y = (y + (this.height / 2));

	}

	public int getX() {

		return (this.x + (this.width / 2));

	}

	public int getY() {

		return (this.y + (this.height / 2));
	}

	public void show(boolean show) {

		this.show = show;

	}

	public boolean isShowing() {

		return show;

	}

	public void setColor(int nextInt, int nextInt2, int nextInt3) {

		col = new Color(nextInt, nextInt2, nextInt3);

	}

	public int returnDistance() {

		double svar = getX() + getY();

		return (int) svar;
	}
}
