package io.github.some_example_name;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSession {
    long sessionStartTime;
    long pauseStartTime;
    public GameState state;
    int destructedNumber;
    private int score;
    public GameSession()
    {
        this.state = GameState.PLAYING;
    }


    public void startGame(){
        state = GameState.PLAYING;
        sessionStartTime = TimeUtils.millis();

    }
    public void pauseGame() {
        state = GameState.PAUSED;
        pauseStartTime = TimeUtils.millis();
    }
    public void resumeGame() {
        state = GameState.PLAYING;
        sessionStartTime += TimeUtils.millis() - pauseStartTime;
    }
    public void endGame(){
        updateScore();
        state = GameState.ENDED;
    }
    public void destructionRegistration() {
        destructedNumber += 1;
    }
    public void updateScore() {
        score =  destructedNumber * 100;
    }
    public int getScore() {
        return score;
    }
}
