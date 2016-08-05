package com.editor.workspace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.editor.states.State;
import com.editor.workspace.EditorConstants;

/**
 * Created by douglas on 8/5/16.
 */
public class LevelSelector extends State
{
    private Stage stage;

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Window win = new Window("Files", EditorConstants.defaultSkin);

        stage.addActor(win);

    }

    @Override
    public void update(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(SpriteBatch batch) {
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void hide() {

    }
}
