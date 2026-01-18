package io.github.some_example_name.components;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.GameSettings;
import io.github.some_example_name.Main;

public class MovingBackgroundView  {
    Texture texture;
    int texture1X, texture2X;
    int speed = 2;
    public MovingBackgroundView(String pathToTexture){

        texture1X = 0;
        texture2X = GameSettings.SCREEN_WIDTH;
        texture = new Texture(pathToTexture);
    }
    public void move() {
        texture1X -= speed;
        texture2X -= speed;

        if (texture1X <= -GameSettings.SCREEN_WIDTH) {
            texture1X = GameSettings.SCREEN_WIDTH;
        }
        if (texture2X <= -GameSettings.SCREEN_WIDTH) {
            texture2X = GameSettings.SCREEN_WIDTH;
        }
    }
    public void draw(Batch batch) {
        batch.draw(texture, texture1X, 0, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
        batch.draw(texture, texture2X, 0, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
    }

    public void dispose() {
        texture.dispose();
    }

}
