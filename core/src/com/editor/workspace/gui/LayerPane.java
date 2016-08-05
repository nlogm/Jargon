package com.editor.workspace.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.editor.workspace.EditorConstants;
import com.editor.workspace.gui.layers.Layer;

/**
 * File LayerPane.java
 * Purpose: Used as a way to visually show the order and created Layers within the editor
 * Last Edited: 07/30/2016 - 5:50 AM
 * @author: Douglas Rudolph
 */
public class LayerPane
{
    private boolean moveable;

    private Array<Layer> layers;
    private Array<Texture> textures;
    private Array<Body> bodies;

    private Window layerWdo;
    private Tree layerTree;
    private TextButton addLayerBtn;
    private TextButton removeLayerBtn;

    public LayerPane(Stage stage)
    {
        layerTree = new Tree(EditorConstants.defaultSkin);
        layerTree.add(new Layer.Node("Layer One"));
        layerWdo = new Window("Layers",EditorConstants.defaultSkin);
        layerWdo.add(layerTree);
        layerWdo.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2);
        layerWdo.setPosition(Gdx.graphics.getWidth()*2/3,0);
        stage.addActor(layerWdo);

    }

    //public void addLayer(String key,Layer newLayer) {layers.add(key, newLayer);}

    //public void deleteLayer(String key) {layers.remove(key);}

    public void setMoveable(boolean newMoveable)
    {
        moveable = newMoveable;
    }

    public boolean getMoveable()
    {
        return moveable;
    }

}
