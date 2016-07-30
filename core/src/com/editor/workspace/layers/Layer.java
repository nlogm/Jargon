package com.editor.workspace.layers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.editor.lights.Light;
import com.editor.managers.LightManager;
import com.editor.managers.WorldManager;
import com.editor.workspace.EditorConstants;


/**
 * File: Layer.Java
 * Purpose: - A colleciton of data that is can be edited within Canvas.java
 *          - Layer.Node is a class that allows for the LayerTree in LayerPane.java gain nodes
 *          - TODO: MUST ADD BUTTON FUCNTIONALITY
 * Created by douglas on 7/26/16.
 * @author Douglas Rudolph
 */
public class Layer
{
    /**
     * Object that adds Nodes the LayerTree in LayerPane.java
     */
    public static class Node extends Tree.Node
    {
        /**
         * Layer.Node Constructor
         * Purpose: is called when a layer is added in LayerPane
         * @param name ; Name of the layer
         */
        public Node(String name)
        {
            super(new Label(name, EditorConstants.uiskin));
            //scene 2d skin

            //lbls
            Label imageLbl = new Label("Image", EditorConstants.uiskin);
            Label lightLbl = new Label("Light", EditorConstants.uiskin);
            Label bodyLbl = new Label("Body", EditorConstants.uiskin);

            //Initalze btns with text
            TextButton addImageBtn = new TextButton("+", EditorConstants.uiskin);
            TextButton addLightBtn = new TextButton("+", EditorConstants.uiskin);
            TextButton addBodyBtn = new TextButton("+", EditorConstants.uiskin);
            TextButton removeImageBtn = new TextButton("-", EditorConstants.uiskin);
            TextButton removeLightBtn = new TextButton("-", EditorConstants.uiskin);
            TextButton removeBodyBtn = new TextButton("-", EditorConstants.uiskin);
            /**
             * Adding event listeners for each button within every new layer
             */

            //
            addImageBtn.addListener(new ClickListener(){

                @Override
                public void clicked(InputEvent e, float x, float y)
                {

                }

            });

            addLightBtn.addListener(new ClickListener(){

                @Override
                public void clicked(InputEvent e, float x, float y)
                {

                }

            });

            addBodyBtn.addListener(new ClickListener(){

                @Override
                public void clicked(InputEvent e, float x, float y)
                {

                }

            });

            removeImageBtn.addListener(new ClickListener(){

                @Override
                public void clicked(InputEvent e, float x, float y)
                {

                }

            });

            removeLightBtn.addListener(new ClickListener(){

                @Override
                public void clicked(InputEvent e, float x, float y)
                {

                }

            });

            removeBodyBtn.addListener(new ClickListener(){

                @Override
                public void clicked(InputEvent e, float x, float y)
                {

                }

            });

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
            this.add(imageNode);
            this.add(lightNode);
            this.add(bodyNode);
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
        //remove image here
        imgCount--;
    }

    public void removeBody()
    {
        //remove body here
        bodyCount--;
    }

    public void removeLight()
    {
        //remove light here
        lightCount--;
    }

    public void setName(String newName)
    {
        name = newName;
    }


}
