package com.editor.workspace;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.editor.workspace.gui.Canvas;
import com.editor.workspace.gui.LayerPane;
import com.editor.workspace.gui.ToolBox;

/**
 * Created by douglas on 7/26/16.
 */
public class Workspace
{
    private Stage stage;

    private Canvas canvas;
    private LayerPane layerPane;
    private ToolBox toolBox;

    public Workspace()
    {
        stage = new Stage();
    }

}
