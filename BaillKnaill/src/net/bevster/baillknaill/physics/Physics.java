package net.bevster.baillknaill.physics;

import java.util.Random;

import net.bevster.baillknaill.Main;
import net.bevster.baillknaill.Main.State;
import net.bevster.baillknaill.entities.Player;
import net.bevster.baillknaill.entities.Zombie;

public class Physics implements Runnable {

	Random ra;

	public Physics() {
		// TODO Auto-generated constructor stub

		ra = new Random();

	}

	public void checkCollision(Zombie[] zombieList, Player ply) {

		if (Main.CHECK_COLLISION) {

			for (int i = 0; i < zombieList.length; i++) {

				if (zombieList[i].getColZombie()) {
					if ((Math.sqrt((zombieList[i].getX() - ply.getX()) * (zombieList[i].getX() - ply.getX()) + (zombieList[i].getY() - ply.getY()) * (zombieList[i].getY() - ply.getY())) < (ply.getWidth() / 2 + zombieList[i].getWidth() / 2))) {

						if (zombieList[i].getWidth() > ply.getWidth()) {
							System.out.println("GameOver " + ply.getX() + ", " + ply.getY() + " Zomb: " + zombieList[i].getX() + ", " + zombieList[i].getY());
							Main.state = State.GAMEOVER;
						} else {

							zombieList[i].setDrawZombie(false);
							zombieList[i].setColZombie(false);
							zombieList[i].setMoveZombie(false);

							ply.setWidth((ply.getWidth() + 5));
							ply.setHeight((ply.getHeight() + 5));
						}

					}
				}

			}
		}

	}

	public void checkColissionEnemy(Zombie[] zombieList) {

		for (int i = 0; i < zombieList.length; i++) {

			for (int r = 0; r < zombieList.length; r++) {

				if (i != r && (zombieList[i].getMoveZombie() && zombieList[r].getMoveZombie()) || (!zombieList[i].getMoveZombie() && zombieList[r].getMoveZombie()) || (zombieList[i].getMoveZombie() && !zombieList[r].getMoveZombie())) {

					if (zombieList[i].getDrawZombie() && zombieList[r].getDrawZombie()) {

						if ((Math.sqrt((zombieList[i].getX() - zombieList[r].getX()) * (zombieList[i].getX() - zombieList[r].getX()) + (zombieList[i].getY() - zombieList[r].getY()) * (zombieList[i].getY() - zombieList[r].getY())) < (zombieList[i].getWidth() / 2 + zombieList[r].getWidth() / 2))) {

							if (zombieList[i].getWidth() > zombieList[r].getWidth()) {

								zombieList[r].setColZombie(false);
								zombieList[r].setDrawZombie(false);
								zombieList[r].setMoveZombie(false);

								zombieList[i].setWidth(zombieList[i].getWidth() + 10);
								zombieList[i].setHeight(zombieList[i].getHeight() + 10);

								System.out.println("Endret storelse: " + i + " " + zombieList[i].getDrawZombie());

							} else if (zombieList[i].getWidth() < zombieList[r].getWidth()) {
								zombieList[i].setColZombie(false);
								zombieList[i].setDrawZombie(false);
								zombieList[i].setMoveZombie(false);

								zombieList[r].setWidth(zombieList[r].getWidth() + 10);
								zombieList[r].setHeight(zombieList[r].getHeight() + 10);
								System.out.println("Endret storelse: " + r + " " + zombieList[r].getDrawZombie());

							} else if (zombieList[i].getWidth() == zombieList[r].getWidth()) {

								int ape = ra.nextInt(10);

								if (ape > 5) {
									zombieList[r].setColZombie(false);
									zombieList[r].setDrawZombie(false);
									zombieList[r].setMoveZombie(false);

									zombieList[i].setWidth(zombieList[i].getWidth() + 10);
									zombieList[i].setHeight(zombieList[i].getHeight() + 10);
									System.out.println("Endret st�relse: " + i + " " + zombieList[i].getDrawZombie());

								} else {
									zombieList[i].setColZombie(false);
									zombieList[i].setDrawZombie(false);
									zombieList[i].setMoveZombie(false);

									zombieList[r].setWidth(zombieList[r].getWidth() + 10);
									zombieList[r].setHeight(zombieList[r].getHeight() + 10);
									System.out.println("Endret st�relse: " + r + " " + zombieList[r].getDrawZombie());

								}

							}

							System.out.println("Zombie: " + i + " og Zombie: " + r + " koliderte!");

						}

					}
				}

			}
		}
	}

	public void moveEnemy(Zombie[] zombieList, Player ply) {

		for (int i = 0; i < zombieList.length; i++) {

			if (zombieList[i].getMoveZombie() && zombieList[i].getWidth() < ply.getWidth()) {
				if (this.returnDistance(ply, zombieList[i]) < 150) {
					if (zombieList[i].getX() > ply.getX())
						zombieList[i].move(zombieList[i].getVelocity(), 0);
					if (zombieList[i].getX() < ply.getX())
						zombieList[i].move(-zombieList[i].getVelocity(), 0);
					if (zombieList[i].getY() < ply.getY())
						zombieList[i].move(0, -zombieList[i].getVelocity());
					if (zombieList[i].getY() > ply.getY())
						zombieList[i].move(0, zombieList[i].getVelocity());

				} else {
					// System.out.println("Nope");

					// zombieList[i].setMoveZombie(false);
				}

			} else if (zombieList[i].getMoveZombie() && zombieList[i].getWidth() >= ply.getWidth()) {

				System.out.println("Zombie: " + zombieList[i].getID() + " er st�rre enn spiller.");

				// if (zombieList[i].getX() < GameMain.WIDTH &&
				// zombieList[i].getX() > 0 && zombieList[i].getY() <
				// GameMain.HEIGHT && zombieList[i].getY() > 0) {
				if (zombieList[i].getX() < ply.getX())
					zombieList[i].move(zombieList[i].getVelocity(), 0);
				if (zombieList[i].getX() > ply.getX())
					zombieList[i].move(-zombieList[i].getVelocity(), 0);
				if (zombieList[i].getY() > ply.getY())
					zombieList[i].move(0, -zombieList[i].getVelocity());
				if (zombieList[i].getY() < ply.getY())
					zombieList[i].move(0, zombieList[i].getVelocity());
				// } else {

				// System.out.println("Nope");
				// zombieList[i].setMoveZombie(false);
				// }
			}
		}
	}

	/*
	 * public void movePlayer(Player ply) {
	 * 
	 * if (Main.opp) ply.move(0, -ply.getVelocity()); if (Main.ned) ply.move(0, ply.getVelocity()); if (Main.venstre) ply.move(-ply.getVelocity(), 0); if (Main.h�yre) ply.move(ply.getVelocity(), 0);
	 * 
	 * int vel = ply.getVelocity();
	 * 
	 * if (GameMain.opp && GameMain.venstre) {
	 * 
	 * if (ply.getVelocity() >= 3) ply.setVelocity(ply.getVelocity() - 3); ply.move(-ply.getVelocity(), -ply.getVelocity()); ply.setVelocity(vel); } if (GameMain.opp && GameMain.h�yre) { if (ply.getVelocity() >= 3) ply.setVelocity(ply.getVelocity() - 3); ply.move(ply.getVelocity(), -ply.getVelocity()); ply.setVelocity(vel);
	 * 
	 * } if (GameMain.ned && GameMain.venstre) { if (ply.getVelocity() >= 3) ply.setVelocity(ply.getVelocity() - 3); ply.move(-ply.getVelocity(), ply.getVelocity()); ply.setVelocity(vel);
	 * 
	 * } if (GameMain.ned && GameMain.h�yre) { if (ply.getVelocity() >= 3) ply.setVelocity(ply.getVelocity() - 3); ply.move(ply.getVelocity(), ply.getVelocity()); ply.setVelocity(vel);
	 * 
	 * }
	 * 
	 * }
	 */

	public int returnDistance(Player ply, Zombie zomb) {

		double x = Math.sqrt((ply.getX() - zomb.getX()) * (ply.getX() - zomb.getX()));
		double y = Math.sqrt((ply.getY() - zomb.getY()) * (ply.getY() - zomb.getY()));

		double matte = x + y;
		return (int) matte;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (Main.state.toString().equalsIgnoreCase(State.PLAYING.toString())) {
			moveEnemy(Main.zombieList, Main.ply);
			checkColissionEnemy(Main.zombieList);
			checkCollision(Main.zombieList, Main.ply);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Min jobb her er n� ferdig!");
	}
}
