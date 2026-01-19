package io.github.some_example_name.objects;

import static io.github.some_example_name.GameSettings.SCALE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import io.github.some_example_name.GameSettings;
import io.github.some_example_name.Main;
import io.github.some_example_name.screens.GameScreen;


public class GameObject {
    protected static final int DEFAULT_PADDING_HORIZONTAL = 50;
    public Body body;
    public int width;
    Main main;
    public int height;
    public short cBits;
    Texture texture;
    GameObject(String texturePath, int x, int y, int width, int height,short cBits, World world) {
        this.main =main;
        this.width = width;
        this.height = height;
        this.cBits = cBits;
        texture = new Texture(texturePath);
        body = createBody(x, y, world);

    }
    public void hit() {

    }
    String userData = "game_object";
    private Body createBody(float x, float y, World world){

        BodyDef def = new BodyDef();

        Body body = world.createBody(def);
        float Width = width * SCALE;
        float Height = height * SCALE;

        if (cBits == GameSettings.TANK_BIT) {
            userData = "tank";
            def.type = BodyDef.BodyType.DynamicBody;

            PolygonShape shape = new PolygonShape();

            def.fixedRotation = false;
            shape.setAsBox(Width / 2f, Height / 2f);
            FixtureDef fixtDef = new FixtureDef();
            fixtDef.shape = shape;

            fixtDef.density = 2.0f;
            fixtDef.friction = 0.9f;
            fixtDef.restitution = 1f;
            shape.dispose();



        } else if (cBits == GameSettings.BULLET_BIT) {
            userData = "bullet";
            def.type = BodyDef.BodyType.DynamicBody;
            CircleShape circleShape = new CircleShape();
            def.fixedRotation = true;

            circleShape.setRadius(Math.max(width, height) * SCALE / 2f);

            FixtureDef fixDef = new FixtureDef();
            fixDef.shape = circleShape;
            fixDef.density = 0.1f;
            fixDef.friction = 1f;
            fixDef.filter.categoryBits = cBits;
            circleShape.dispose();



        } else if (cBits == GameSettings.WALL_BIT) {
            userData = "wall";
            def.type = BodyDef.BodyType.StaticBody;
            PolygonShape polygonShape = new PolygonShape();
            polygonShape.setAsBox(Width / 2f, Height / 2f);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = polygonShape;
            polygonShape.dispose();

        }
        body.setTransform(x * SCALE, y * SCALE, 0);

        return body;


    }
    public void draw(SpriteBatch batch) {
        batch.draw(
            texture,
            getX() - (width / 2f),
            getY() - (height / 2f),
            width / 2f,
            height / 2f,
            width,
            height,
            1f,
            1f,
            body.getAngle() * 57.2958f,
            0,
            0,
            texture.getWidth(),
            texture.getHeight(),
            false,
            false
        );


    }
    public void rotate(float degrees) {
        if (body != null) {
            float radians = degrees * 0.0174533f; // примерно PI/180
            body.setTransform(body.getPosition(), radians);
        }
    }
    public int getX() {
        return (int) (body.getPosition().x / SCALE);
    }

    public int getY() {
        return (int) (body.getPosition().y / SCALE);
    }

    public void setX(int x) {
        body.setTransform(x * SCALE, body.getPosition().y, 0);
    }

    public void setY(int y) {
        body.setTransform(body.getPosition().x, y * SCALE, 0);
    }
    public void dispose()
    {
        texture.dispose();

    }
}
