package com.bootup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.editor.states.State;
import com.editor.workspace.EditorConstants;
import com.editor.workspace.screens.Workspace;

/**
 * Created by douglas on 8/5/16.
 */
public class StartUp extends State
{
    private Stage stage;

    @Override
    public void create()
    {
        TextButton loadEditorBtn = new TextButton("Edtior" ,EditorConstants.defaultSkin);
        loadEditorBtn.setPosition(Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/2);
        loadEditorBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                State.stateManager.setState(new Workspace());
            }
        });

        TextButton LoadLevelSelectionBtn = new TextButton("Level Selection", EditorConstants.defaultSkin);
        LoadLevelSelectionBtn.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getWidth()/2);
        LoadLevelSelectionBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
               // State.stateManager.setState(new Workspace());
            }
        });

        stage = new Stage();

        stage.addActor(loadEditorBtn);
        stage.addActor(LoadLevelSelectionBtn);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta)
    {
        stage.act(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(SpriteBatch batch)
    {
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
