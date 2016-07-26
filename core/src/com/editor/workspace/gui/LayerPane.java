package com.editor.workspace.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.editor.workspace.layers.Layer;

import java.util.HashMap;



/**
 * Created by douglas on 7/26/16.
 */
public class LayerPane
{
    private boolean moveable;
    private HashMap<String, Layer> layers;
    private Array<Texture> textures;
    private Array<Body> bodies;

    private Window layerWdp;
    private Tree layerTree;
    private TextButton addLayerBtn;
    private TextButton removeLayerBtn;

    public LayerPane(Stage stage)
    {

    }

    public void update()
    {

    }

    public void addLayer(String key,Layer newLayer)
    {
        layers.put(key, newLayer);
    }

    public void deleteLayer(String key)
    {
        layers.remove(key);
    }

    public void setMoveable(boolean newMoveable)
    {
        moveable = newMoveable;
    }

    public boolean getMoveable()
    {
        return moveable;
    }




}
