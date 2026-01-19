package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.GameResources;
import io.github.some_example_name.Main;
import io.github.some_example_name.components.ButtonView;
import io.github.some_example_name.components.MovingBackgroundView;
import io.github.some_example_name.components.TextView;
import io.github.some_example_name.managers.MemoryManager;

public class SettingsScreen extends ScreenAdapter {
    Main main;
    MovingBackgroundView backgroundView;
    //buttons
    ButtonView returnButton;
    ButtonView musicSettingView1;
    ButtonView soundSettingView1;
    TextView musicSettingView;
    TextView soundSettingView;
    TextView titleTextView;
    public SettingsScreen(Main main) {
        this.main = main;
        backgroundView = new MovingBackgroundView(GameResources.BACKGROUND_IMG_PATH);
        returnButton = new ButtonView(420,156,440,70,main.commonBlackFont,GameResources.BUTTON_LONG_BG_IMG_PATH,"Return");
        titleTextView = new TextView(main.largeWhiteFont,550,500,"Settings");
        musicSettingView = new TextView(main.commonBlackFont, 570, 375, "Music: " + "ON");
        soundSettingView = new TextView(main.commonBlackFont, 570, 275, "Sound: " + "ON");

        musicSettingView1 = new ButtonView(420,350,440,70,main.commonBlackFont,GameResources.BUTTON_LONG_BG_IMG_PATH,"");
        soundSettingView1 = new ButtonView(420,250,440,70,main.commonBlackFont,GameResources.BUTTON_LONG_BG_IMG_PATH,"");

    }
    @Override
    public void render(float delta){
        handleInput();
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        main.batch.begin();
        backgroundView.draw(main.batch);
        titleTextView.draw(main.batch);
        musicSettingView1.draw(main.batch);
        soundSettingView1.draw(main.batch);
        musicSettingView.draw(main.batch);
        soundSettingView.draw(main.batch);
        returnButton.draw(main.batch);
        main.batch.end();
    }
    private String translateStateToText(boolean state) {
        return state ? "ON" : "OFF";
    }
    void handleInput() {
        if (Gdx.input.justTouched()) {
            main.touch = main.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (returnButton.isHit(main.touch.x, main.touch.y)) {
                main.setScreen(main.screenMenu);
            }
            if (musicSettingView.isHit(main.touch.x, main.touch.y)){
                MemoryManager.saveMusicSettings(!MemoryManager.loadIsMusicOn());
                musicSettingView.setText("Music: " + translateStateToText(MemoryManager.loadIsMusicOn()));
                main.audioManager.updateMusicFlag();

            }
            if (soundSettingView.isHit(main.touch.x, main.touch.y)) {
                MemoryManager.saveSoundSettings(!MemoryManager.loadIsSoundOn());
                soundSettingView.setText("Sound: " + translateStateToText(MemoryManager.loadIsSoundOn()));
                main.audioManager.updateSoundFlag();

            }

        }
    }
}
