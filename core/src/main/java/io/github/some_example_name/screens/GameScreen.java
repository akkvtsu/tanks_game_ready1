package io.github.some_example_name.screens;





import static io.github.some_example_name.GameState.PAUSED;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import io.github.some_example_name.GameResources;
import io.github.some_example_name.GameSession;
import io.github.some_example_name.GameSettings;
import io.github.some_example_name.GameState;
import io.github.some_example_name.Main;
import io.github.some_example_name.Maze;
import io.github.some_example_name.components.ButtonView;
import io.github.some_example_name.components.ImageView;
import io.github.some_example_name.components.MovingBackgroundView;
import io.github.some_example_name.components.TextView;
import io.github.some_example_name.objects.BulletObject;
import io.github.some_example_name.objects.TankObject;
import io.github.some_example_name.objects.WallObject;

public class GameScreen extends ScreenAdapter {
    Main main;
    Maze maze;
    TankObject tankObject;
    WallObject wallObject;
    MovingBackgroundView backgroundView;
    ButtonView exitInMenu;
    public ButtonView arrowUp;
    public ButtonView arrowDown;
    public ButtonView arrowLeft;
    public ButtonView arrowRight;
    ButtonView pauseButton;
    ButtonView homeButton;
    ButtonView homeButton2;
    ButtonView continueButton;
    public ButtonView whitePoint;
    GameSession gameSession;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean moveUp = false;
    private boolean moveDown = false;


    ButtonView greyBackGround;
    TextView pauseTextView;
    TextView scoreTextView;
    Texture blockTexture;
    int blockWidth = 55;
    int blockHeight = 58;
    int x1 =250;
    public ArrayList<BulletObject> bulletArray;
    public ArrayList<WallObject> mazeWalls;
    //imageview
    ImageView fullBlackoutView;

    public GameScreen(Main main) {
        this.main = main;
        backgroundView = new MovingBackgroundView(GameResources.PICTURE_GAMESCREEN);


        fullBlackoutView = new ImageView(0,480,GameResources.BLACKOUT_TOP_IMG_PATH);
        gameSession = new GameSession();


        bulletArray = new ArrayList<>();
        mazeWalls = new ArrayList<>();
        tankObject = new TankObject(x1 + blockWidth,
            2 * blockHeight, GameSettings.TANK_WIDTH, GameSettings.TANK_HEIGHT, GameResources.TANK_IMG_PATH, this.main.world);
//        wallObject = new WallObject(GameSettings.SCREEN_WIDTH / 2, 300,GameSettings.WALL_SIZE,GameSettings.WALL_SIZE, GameResources.PICTURE_BLOCK, main.world);
        pauseButton = new ButtonView(600,650,50,50,GameResources.PAUSE_IMG_PATH);


        pauseTextView = new TextView(this.main.largeWhiteFont,530,600,"PAUSE");
        homeButton = new ButtonView(450,500,140,60, this.main.commonBlackFont, GameResources.UTTON_SHORT_BG_IMG_PATH,"Menu");
        homeButton2 = new ButtonView(400,500,150,60, this.main.commonBlackFont, GameResources.UTTON_SHORT_BG_IMG_PATH,"Menu");
        continueButton = new ButtonView(650,500,140,60, this.main.commonBlackFont,GameResources.UTTON_SHORT_BG_IMG_PATH,"Continue");
        scoreTextView = new TextView(this.main.commonWhiteFont, 50, 600, "");


        greyBackGround = new ButtonView(80,250,150,120, this.main.commonBlackFont,GameResources.GREY_BACKGROUNG,"");
        arrowUp = new ButtonView(130,320,50,50, this.main.commonBlackFont, GameResources.ARROWUP_IMG,"");
        arrowDown= new ButtonView(130,260,50,50, this.main.commonBlackFont, GameResources.ARROWDOWN_IMG,"");
        arrowLeft = new ButtonView(97,288,50,50, this.main.commonBlackFont, GameResources.ARROWLEFT_IMG,"");
        arrowRight = new ButtonView(165,288,50,50, this.main.commonBlackFont, GameResources.ARROWRIGHT_IMG,"");
        whitePoint = new ButtonView(-94,64,500,500, this.main.commonBlackFont, GameResources.POINT_IMG,"");

        exitInMenu = new ButtonView(0, 650, 300, 74, this.main.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "EXIT IN MENU");
        blockTexture = new Texture(GameResources.PICTURE_BLOCK);
        maze = new Maze();
        createMazeWalls();
    }



    @Override
    public void show()
    {
        restartGame();
    }


    @Override
    public void render(float delta) {
        handleInput();
        draw();
//        createMazeWalls();
        updateTankMovement();

        if(gameSession.state == GameState.PLAYING) {



            if (gameSession.getScore() > 200) {
                gameSession.endGame();

            }
            gameSession.updateScore();
            scoreTextView.setText("Score " + gameSession.getScore());
            main.stepWorld();
        }


    }
    private void draw() {
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        ScreenUtils.clear(Color.CLEAR);
        main.batch.begin();
        backgroundView.draw(main.batch);

        tankObject.draw(main.batch);
        for (WallObject walls : mazeWalls) walls.draw(main.batch);
//        for (BulletObject bullet : bulletArray) bullet.draw(main.batch);







        arrowUp.draw(main.batch);
        arrowDown.draw(main.batch);
        arrowLeft.draw(main.batch);
        arrowRight.draw(main.batch);
        whitePoint.draw(main.batch);
        pauseButton.draw(main.batch);

        scoreTextView.draw(main.batch);




        if ( gameSession.state == PAUSED) {
            fullBlackoutView.draw(main.batch);
            pauseTextView.draw(main.batch);
            continueButton.draw(main.batch);
            homeButton.draw(main.batch);
            scoreTextView.draw(main.batch);

        } else if(gameSession.state == GameState.ENDED){
            homeButton2.draw(main.batch);

        }
        main.batch.end();

    }


private void createMazeWalls() {
    int[][] mazeArray = maze.getMaze();

    for (int y = 0; y < maze.getHeight(); y++) {
        for (int x = 0; x < maze.getWidth(); x++) {
            if (mazeArray[y][x] == 1) {
                WallObject wall = new WallObject(
                    x * blockWidth + x1,
                    y * blockHeight,
                    blockWidth,
                    blockHeight,
                    GameResources.PICTURE_BLOCK,
                    main.world
                );
                mazeWalls.add(wall);




            }
        }


    }
}

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            main.touch = main.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (arrowLeft.isHit(main.touch.x, main.touch.y)) {
                moveLeft = true;
            }
            if (arrowRight.isHit(main.touch.x, main.touch.y)) {
                moveRight = true;
            }
            if (arrowUp.isHit(main.touch.x, main.touch.y)) {
                moveUp = true;
            }
            if (arrowDown.isHit(main.touch.x, main.touch.y)) {
                moveDown = true;
            }

            switch (gameSession.state){
                case PLAYING:
                    if (whitePoint.isHit(main.touch.x, main.touch.y)) {
                        if (tankObject.needToShoot()) {
                            BulletObject bullet = new BulletObject(
                                tankObject.getX(),
                                tankObject.getY() + tankObject.height / 2,
                                GameSettings.BULLET_WIDTH,
                                GameSettings.BULLET_HIGHT,
                                GameResources.BULLET_IMG_PATH,
                                main.world
                            );
                            bulletArray.add(bullet);
                            if (main.audioManager.isSoundOn) main.audioManager.shootSound.play();
                            tankObject.updateShotTime();
                        }
                    }

                    if (pauseButton.isHit(main.touch.x, main.touch.y)) {
                        gameSession.pauseGame();
                    }
                    break;
                case PAUSED:
                    if (continueButton.isHit(main.touch.x, main.touch.y)) {
                        gameSession.resumeGame();
                    }
                    if (homeButton.isHit(main.touch.x, main.touch.y)) {
                        main.setScreen(main.screenMenu);
                    }
                    break;
                case ENDED:
                    if (homeButton2.isHit(main.touch.x, main.touch.y)) {
                        main.setScreen(main.screenMenu);
                    }
                    break;
            }
        }
        if (!Gdx.input.isTouched()) {
            moveLeft = false;
            moveRight = false;
            moveUp = false;
            moveDown = false;
        }

            }
    private void updateTankMovement() {
        if (tankObject != null && gameSession.state == GameState.PLAYING) {
            float speed = 1f; // скорость движения
//            float targetAngle = 0;


            if (moveLeft) {
                tankObject.setX(tankObject.getX() - (int)speed);
                tankObject.rotate(90);

            }
            if (moveRight) {
                tankObject.setX(tankObject.getX() + (int)speed);
                tankObject.rotate(-90);
            }
            if (moveUp) {
                tankObject.setY(tankObject.getY() + (int)speed);
                tankObject.rotate(0);

            }
            if (moveDown) {
                tankObject.setY(tankObject.getY() - (int)speed);
                tankObject.rotate(180);
            }

        }
    }
    private void restartGame() {
        if (tankObject != null) {
            main.world.destroyBody(tankObject.body);
            gameSession.startGame();
        }
    }
    @Override
    public void dispose() {
        blockTexture.dispose();
        backgroundView.dispose();
        pauseButton.dispose();


        tankObject.dispose();
        arrowRight.dispose();
        arrowLeft.dispose();
        arrowDown.dispose();
        arrowUp.dispose();
        whitePoint.dispose();
        exitInMenu.dispose();

    }
}
