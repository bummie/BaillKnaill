package net.bevster.baillknaill;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

import net.bevster.baillknaill.input.InputHandler;
import net.bevster.baillknaill.physics.Physics;
import net.bevster.baillknaill.entities.*;

/**
 * Main class for the game
 */
public class Main extends JFrame {

	final private static String TITTEL = "Feeeeeeeest";

	final static int fps = 60;
	final static int windowWidth = 720;
	final static int windowHeight = 600;

	static final int ENEMIES_AMOUNT = 5;
	public static final boolean CHECK_COLLISION = true;
	public static final int MOVEMENT_TYPE = 0; // 0 = WASD, 1 = Arrows

	static boolean isRunning = true;

	public BufferedImage backBuffer;

	Insets insets;
	InputHandler input;

	Thread physThread;
	Physics Phys;

	Random ran;

	public static Zombie[] zombieList;
	public static Player ply;

	public static enum State {
		INITIALIZED, PLAYING, PAUSED, GAMEOVER, DESTROYED
	}

	public static State state;

	public static void main(String[] args) {
		Main game = new Main();
		game.run();
		System.exit(0);
	}

	/**
	 * This method starts the game and runs it in a loop
	 */
	public void run() {
		initialize();

		while (isRunning) {

			long time = System.currentTimeMillis();

			update();
			draw();

			// delay for each frame - time it took for one frame
			time = (1000 / fps) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e) {
				}
			}
		}

		setVisible(false);

	}

	/**
	 * This method will set up everything need for the game to run
	 */
	void initialize() {
		setTitle(TITTEL);
		setSize(windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		state = State.INITIALIZED;

		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right, insets.top + windowHeight + insets.bottom);

		backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);

		ran = new Random();
		Phys = new Physics();

		physThread = new Thread(Phys, "Fysikktrad");

		zombieList = new Zombie[ENEMIES_AMOUNT];

		ply = new Player("Seb", 50, 50, 30, 30);
		ply.setVelocity(5);

		for (int i = 0; i < zombieList.length; i++) {

			zombieList[i] = new Zombie("Zombie " + i, ran.nextInt(windowWidth), ran.nextInt(windowHeight), 30, 30);
			zombieList[i].setVelocity(1);

		}

		state = State.PLAYING;

		physThread.start();

	}
	/**
	 * This method will check for input, move things around and check for win conditions, etc
	 */

	int x = 1, y = 1;

	void update() {

		ply.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));

		if (state.toString().equalsIgnoreCase(State.GAMEOVER.toString())) {

			for (int i = 0; i < zombieList.length; i++) {
				zombieList[i] = null;
			}
		}

		// Movement
		if (MOVEMENT_TYPE == 1) {
			if (input.isKeyDown(KeyEvent.VK_LEFT)) {
				ply.move(-ply.getVelocity(), 0);
			}
			if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
				ply.move(ply.getVelocity(), 0);
			}
			if (input.isKeyDown(KeyEvent.VK_UP)) {
				ply.move(0, -ply.getVelocity());
			}
			if (input.isKeyDown(KeyEvent.VK_DOWN)) {
				ply.move(0, ply.getVelocity());
			}
		}

		if (MOVEMENT_TYPE == 0) {
			if (input.isKeyDown(KeyEvent.VK_A)) {
				ply.move(-ply.getVelocity(), 0);
			}
			if (input.isKeyDown(KeyEvent.VK_D)) {
				ply.move(ply.getVelocity(), 0);
			}
			if (input.isKeyDown(KeyEvent.VK_W)) {
				ply.move(0, -ply.getVelocity());
			}
			if (input.isKeyDown(KeyEvent.VK_S)) {
				ply.move(0, ply.getVelocity());
			}
		}
		// Movement

	}

	/**
	 * This method will draw everything
	 */
	void draw() {
		Graphics2D g = (Graphics2D) getGraphics();
		Graphics2D bbg = (Graphics2D) backBuffer.getGraphics();

		bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		bbg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		switch (state) {

			case PLAYING :
				bbg.setColor(Color.BLACK);
				bbg.fillRect(0, 0, windowWidth, windowHeight);

				// Tegn objekter under denne linjen

				ply.Paint(bbg);

				for (int i = 0; i < zombieList.length; i++) {

					if (zombieList[i].getDrawZombie())
						zombieList[i].Paint(bbg);
				}

				// Tegn objekter over denne linjen

				break;
			case GAMEOVER :
				bbg.setColor(Color.GREEN);
				bbg.fillRect(0, 0, windowWidth, windowHeight);

				break;
			case INITIALIZED :

				break;
		}

		g.drawImage(backBuffer, insets.left, insets.top, this);
	}
}