package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.main.classes.EntityA;
import game.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB {

	private Textures tex;
	Random r = new Random();
	private Game game;
	private Controller controller;
	
	private int speed = r.nextInt(3) + 1;
	
	public Enemy(double x, double y, Textures tex, Controller controller, Game game) {
		super(x,y);
		this.tex = tex;
		this.controller = controller;
		this.game = game;
	}
	
	public void tick() {
		y += speed;
		
		if(y > (Game.HEIGHT * Game.SCALE)) {
			x = r.nextInt(640);
			y = -10;
		}
		
		for(int i =0; i < game.ea.size(); i++) {
			EntityA tempEnt = game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt)) {
				// Checks to see if there is collision between the bullet and meteor and if there is, the bullet and meteor are destroyed
				controller.removeEntity(tempEnt);
				controller.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				// Enemy killed counter is increased
			}
		}
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}
	
	// Setters and Getters
	
}
