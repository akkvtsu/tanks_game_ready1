package io.github.some_example_name;

public class GameSettings {
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    // for world
    public static final float STEP_TIME = 1f /60;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 6;
    //body
    public static final float SCALE = 0.05f; //1 пиксель — 0.05 метров или по-другому, 1 метр — 20 пикселей (1/0.05=20).
    //tank
    public static final int TANK_WIDTH = 40;
    public static final int TANK_HEIGHT = 40;
    public static final short TANK_BIT = 2;
    //bullet
    public static final short BULLET_BIT = 4;
    public static final int BULLET_VELOCITY = 200;
    public static final int SHOOTING_COOL_DOWN= 1000;
    public static final int BULLET_WIDTH = 15;
    public static final int BULLET_HIGHT = 45;
    //wall
    public static final short WALL_BIT = 1;
    public static final int WALL_SIZE = 40;


}
