package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.main.classes.EntityA;
import game.main.classes.EntityB;
import game.main.libs.Animation;

public class Player extends GameObject implements EntityA {
	
	private double velX = 0;
	private double velY = 0;
	private Textures tex;
	
	Game game;
	Controller controller;
	Animation animation;
	
	public Player(double x, double y, Textures tex, Game game, Controller controller) {
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
		
		animation = new Animation(3, tex.player[0], tex.player[1], tex.player[2]);
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		
		if(x <= 0)
			x =0;
		if(x >= 640 - 32)
			x = 640 - 32;
		if(y <= 0)
			y = 0;
		if(y >= 480 - 42)
			y = 480 - 42;
		
		for(int i = 0; i < game.eb.size(); i++) {
			EntityB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt))
			{
				controller.removeEntity(tempEnt);
				Game.HEALTH -= 10;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
		}
		
		animation.runAnimation();
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void render(Graphics g) {
		animation.drawAnimation(g, x, y, 0);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	//Setters and Getters
	
}
