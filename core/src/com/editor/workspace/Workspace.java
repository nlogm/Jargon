package com.editor.workspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.editor.states.State;
import com.editor.workspace.gui.Canvas;
import com.editor.workspace.gui.LayerPane;
import com.editor.workspace.gui.ToolBox;

/**
 * Created by douglas on 7/26/16.
 */
public class Workspace extends State
{
    private Stage stage;

    private Canvas canvas;
    private LayerPane layerPane;
    private ToolBox toolBox;

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        //UI Objects
        layerPane = new LayerPane(stage);
        //canvas = new Canvas();
        //toolBox= new ToolBox();
    }

    @Override
    public void update(float delta) {
        stage.act();
    }

    @Override
    public void render(SpriteBatch batch) {
        stage.draw();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void hide() {

    }

}
