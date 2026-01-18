package io.github.some_example_name.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import io.github.some_example_name.GameSettings;


public class WallObject extends GameObject{

    public WallObject(int x, int y, int width, int height, String texturePath, World world){
        super(texturePath, x, y, width, height, GameSettings.WALL_BIT, world);



    }
    public void draw(SpriteBatch batch)
    {

        super.draw(batch);
    }
//    private Body createWall(float x, float y, World world){
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//        Body body = world.createBody(bodyDef);
//
//        body.setUserData("wall");
//
//        PolygonShape polygonShape = new PolygonShape();
//        polygonShape.setAsBox(GameSettings.WALL_SIZE /2,GameSettings.WALL_SIZE /2 );
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = polygonShape;
//        body.createFixture(fixtureDef);
//
//        polygonShape.dispose();
//
//
//        return body;
//    }
}
