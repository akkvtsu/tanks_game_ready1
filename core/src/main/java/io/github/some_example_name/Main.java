package io.github.some_example_name;

import static io.github.some_example_name.GameSettings.POSITION_ITERATIONS;
import static io.github.some_example_name.GameSettings.STEP_TIME;
import static io.github.some_example_name.GameSettings.VELOCITY_ITERATIONS;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.managers.AudioManager;
import io.github.some_example_name.screens.GameScreen;
import io.github.some_example_name.screens.ScreenMenu;
import io.github.some_example_name.screens.SettingsScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    //fonts
    public BitmapFont largeWhiteFont;
    public BitmapFont commonWhiteFont;
    public BitmapFont commonBlackFont;

    //
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    private Texture image;
    //classes
    public ScreenMenu screenMenu;
     public SettingsScreen settingsScreen;
     public GameScreen gameScreen;
    public World world;
    float accumulator;
    public AudioManager audioManager;


    @Override
    public void create() {
        audioManager = new AudioManager();
        Box2D.init();
        world = new World(new Vector2(0,-10),true);
        world.step(STEP_TIME,VELOCITY_ITERATIONS,POSITION_ITERATIONS);
        largeWhiteFont = FontBuilder.generate(48, Color.WHITE, GameResources.FONT_PATH);
        commonWhiteFont = FontBuilder.generate(24, Color.WHITE, GameResources.FONT_PATH);
        commonBlackFont = FontBuilder.generate(24, Color.BLACK, GameResources.FONT_PATH);


        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,GameSettings.SCREEN_WIDTH,GameSettings.SCREEN_HEIGHT);
        screenMenu = new ScreenMenu(this);
        settingsScreen = new SettingsScreen(this);
        gameScreen = new GameScreen(this);
        setScreen(screenMenu);


    }
    public void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += delta;
        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME,VELOCITY_ITERATIONS,POSITION_ITERATIONS);
        }
    }



    @Override
    public void dispose() {
        batch.dispose();

    }
}
