package com.editor.workspace.layers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.utils.Array;
import com.editor.lights.Light;
import com.editor.managers.LightManager;
import com.editor.managers.WorldManager;

/**
 * File: Layer.Java
 * Purpose: - A colleciton of data that is can be edited within Canvas.java
 *          - Layer.Node is a class that allows for the LayerTree in LayerPane.java gain nodes
 *
 * Created by douglas on 7/26/16.
 * @author Douglas Rudolph
 */
public class Layer
{
    /**
     * Object that adds Nodes the LayerTree in LayerPane.java
     */
    static class Node
    {
        /**
         * Layer.Node Constructor
         * Purpose: is called when a layer is added in LayerPane
         * @param rootNode : name of the Node that represents the layer within the LayerList
         * @param name ; Name of the layer
         */
        public Node(Tree.Node rootNode, String name)
        {
            //scene 2d skin
            Skin uiskin = new Skin(Gdx.files.internal("uiskin.json"));

            //lbls
            Label imageLbl = new Label("Image", uiskin);
            Label lightLbl = new Label("Light", uiskin);
            Label bodyLbl = new Label("Body", uiskin);

            //btns
            TextButton addImageBtn = new TextButton("+", uiskin);
            TextButton addLightBtn = new TextButton("+", uiskin);
            TextButton addBodyBtn = new TextButton("+", uiskin);
            TextButton removeImageBtn = new TextButton("-", uiskin);
            TextButton removeLightBtn = new TextButton("-", uiskin);
            TextButton removeBodyBtn = new TextButton("-", uiskin);

            //Collection of tables that have the information that is stored within each child node
            //(Children Nodes: Image Node, Light Node, Body Node)
            Table imageTable = new Table();
            imageTable.add(imageLbl);
            imageTable.row();
            imageTable.add(addImageBtn);
            imageTable.add(removeImageBtn);

            Table lightTable = new Table();
            lightTable.add(lightLbl);
            lightTable.row();
            lightTable.add(addLightBtn);
            lightTable.add(removeLightBtn);

            Table bodyTable = new Table();
            bodyTable.add(bodyLbl);
            bodyTable.row();
            bodyTable.add(addBodyBtn);
            bodyTable.add(removeBodyBtn);

            //Tree.Node's that store the table collections
            Tree.Node imageNode = new Tree.Node(imageTable);
            Tree.Node lightNode = new Tree.Node(lightTable);
            Tree.Node bodyNode = new Tree.Node(bodyTable);

            //Store each child node with in the root node
            rootNode = new Tree.Node(new Label(name, uiskin));
            rootNode.add(imageNode);
            rootNode.add(lightNode);
            rootNode.add(bodyNode);
        }

    }


    private String name;
    private boolean renderFlag;
    private Array<Texture> texList;
    private Array<Light> lightList;
    //private Array<Body> bodyList;

    private static WorldManager worldManager;
    private static LightManager lightManager;

    //I cant believe this is actually a line of code
    private int bodyCount;
    private int lightCount;
    private int imgCount;

    public Layer(String name, Stage stage)
    {
        this.name = name;
        renderFlag = false;

        texList = new Array<Texture>();
        lightList = new Array<Light>();
        //bodyList = new Array<Body>();

        bodyCount = lightCount= imgCount = 0;

    }

    public void render()
    {

    }

    public void update()
    {

    }

    public boolean toggleRender()
    {
        renderFlag = !renderFlag;

        return renderFlag;

    }

    public void addImage(Texture texture)
    {
        //import image
        //create image here
        texList.add(texture);
        imgCount++;
    }

    /**
     * Still trying to figure out how Im going to store Bodies
     */
    public void addBody()
    {
        //create body here
        bodyCount++;
    }

    public void addLight(Light light)
    {
        //create light source here
        lightCount++;
    }

    public void removeImage()
    {

        imgCount--;
    }

    public void removeBody()
    {
        bodyCount--;
    }

    public void removeLight()
    {
        lightCount--;
    }

    public void setName(String newName)
    {
        name = newName;
    }


}
