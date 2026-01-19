package io.github.some_example_name.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;

import io.github.some_example_name.GameResources;
import io.github.some_example_name.GameSettings;
import io.github.some_example_name.Main;
import io.github.some_example_name.components.ButtonView;
import io.github.some_example_name.screens.GameScreen;

public class TankObject extends GameObject {
    long lastShotTime;
    Main main;
    GameScreen gameScreen;


    public TankObject(int x, int y, int width, int height, String texturePath, World world) {
        super(texturePath, x, y, width, height, GameSettings.TANK_BIT, world);


    }

    public void moveleft(Vector3 vector3) {
         int leftmoveX = (int) (vector3.x - GameSettings.WALL_SIZE);

    }

    boolean moveLeft;
    boolean moveRight;
    boolean moveUp;
    boolean moveDown;

    public boolean needToShoot() {
        if (TimeUtils.millis() - lastShotTime >= GameSettings.SHOOTING_COOL_DOWN) {
            lastShotTime = TimeUtils.millis();

            return true;

        }
        return false;
    }
    public void updateShotTime() {
        lastShotTime = TimeUtils.millis();
    }

    @Override
    public void draw(SpriteBatch batch)
    {

        super.draw(batch);
    }

public void handleButtons() {
    Vector3 move = new Vector3();


    if (moveLeft) move.x -= 40;
    if (moveRight) move.x += 40;
    if (moveUp) move.y += 40;
    if (moveDown)  move.y -= 40;
    if (Gdx.input.isTouched()) {


        moveUp = gameScreen.arrowUp.isHit(main.touch.x, main.touch.y);
        moveDown = gameScreen.arrowDown.isHit(main.touch.x, main.touch.y);
        moveLeft = gameScreen.arrowLeft.isHit(main.touch.x, main.touch.y);
        moveRight = gameScreen.arrowRight.isHit(main.touch.x, main.touch.y);
    } else {
        moveUp = moveDown = moveLeft = moveRight = false;
    }
}

}

