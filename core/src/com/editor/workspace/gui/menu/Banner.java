package com.editor.workspace.gui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.editor.workspace.EditorConstants;

/**
 * Created by douglas on 8/5/16.
 */
public class Banner
{
    public Banner(Stage stage)
    {
        Window bannerWdo = new Window("Banner", EditorConstants.defaultSkin);
        bannerWdo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/5);
        bannerWdo.setPosition(0,Gdx.graphics.getHeight());

        stage.addActor(bannerWdo);

    }

}
