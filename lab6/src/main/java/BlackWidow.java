import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.imageio.ImageIO;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class BlackWidow extends JFrame{

    private Canvas3D canvas;
    private Hashtable roachNamedObjects;

    private BlackWidow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        SimpleUniverse simpUniv = new SimpleUniverse(canvas);

        simpUniv.getViewingPlatform().setNominalViewingTransform();

        createSceneGraph(simpUniv);

        addLight(simpUniv);

        OrbitBehavior ob = new OrbitBehavior(canvas);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);

        setTitle("BlackWidow");
        setSize(1080,800);
        getContentPane().add("Center", canvas);
        setVisible(true);
    }

    private void createSceneGraph(SimpleUniverse su){
        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene bugScene = null;
        try {
            bugScene = f.load("D:\\Comp_Graphics\\lab6\\src\\main\\resources\\black_widow.obj");
        }
        catch (Exception e){
            System.out.println("File loading failed:" + e);
        }

        Transform3D scaling = new Transform3D();
        scaling.setScale(0.05);

        Transform3D tfBug = new Transform3D();
        tfBug.rotX(-3 * Math.PI / 2);
        tfBug.mul(scaling);
        tfBug.setTranslation(new Vector3d(-0.4f,0.1f,0.0f));

        TransformGroup tgBug = new TransformGroup(tfBug);

        TransformGroup sceneGroup = new TransformGroup();

        roachNamedObjects = bugScene.getNamedObjects();

        BranchGroup scene = new BranchGroup();

        TransformGroup tgBody = new TransformGroup();
        Shape3D blkwBody = (Shape3D) roachNamedObjects.get("blkw_body");
        Appearance app = new Appearance();
        Color3f black = new Color3f(Color.black);

        app.setMaterial(new Material(black, black, black, black, 70f));
        blkwBody.setAppearance(app);
        tgBody.addChild(blkwBody.cloneTree());


        int noRotHour = 1000;
        int timeRotationHour = 300;

        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);

        Transform3D legRotAxis = new Transform3D();
        legRotAxis.rotZ(Math.PI/2);
        Transform3D leg2RotAxis = new Transform3D();

        sceneGroup.addChild(getAnimatedLeg("leg1", noRotHour, timeRotationHour, legRotAxis, bs, (float) Math.PI/8, 100));
        sceneGroup.addChild(getAnimatedLeg("leg2", noRotHour, timeRotationHour, legRotAxis, bs, (float) Math.PI/8, 200));
        sceneGroup.addChild(getAnimatedLeg("leg3", noRotHour, timeRotationHour, legRotAxis, bs, (float) Math.PI/8, 300));
        sceneGroup.addChild(getAnimatedLeg("leg4", noRotHour, timeRotationHour, legRotAxis, bs, (float) Math.PI/8, 300));
        ;
        sceneGroup.addChild(getAnimatedLeg("leg5", noRotHour, timeRotationHour, leg2RotAxis, bs, -(float) Math.PI/8, 100));
        sceneGroup.addChild(getAnimatedLeg("leg6", noRotHour, timeRotationHour, leg2RotAxis, bs, -(float) Math.PI/8, 200));
        sceneGroup.addChild(getAnimatedLeg("leg7", noRotHour, timeRotationHour, leg2RotAxis, bs, -(float) Math.PI/8, 300));
        sceneGroup.addChild(getAnimatedLeg("leg8", noRotHour, timeRotationHour, leg2RotAxis, bs, -(float) Math.PI/8, 200));

        sceneGroup.addChild(tgBody.cloneTree());

        Transform3D tCrawl = new Transform3D();
        tCrawl.rotY(-Math.PI/2);

        long crawlTime = 20000;
        Alpha crawlAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, crawlTime,0,0,0,0,0);

        float crawlDistance = 20.0f;
        PositionInterpolator posICrawl = new PositionInterpolator(crawlAlpha, sceneGroup,tCrawl, -3.0f, crawlDistance);

        posICrawl.setSchedulingBounds(bs);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneGroup.addChild(posICrawl);

        tgBug.addChild(sceneGroup);
        scene.addChild(tgBug);

        addImageBackground("D:\\Comp_Graphics\\lab6\\src\\main\\resources\\web.jpg", scene);
        scene.compile();

        su.addBranchGraph(scene);
    }

    private void addImageBackground(String imagePath, BranchGroup root) {
        TextureLoader t = new TextureLoader(imagePath, canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }

    private TransformGroup getAnimatedLeg(String elementName, int noRotHour, long timeRotationHour, Transform3D legRotAxis, Bounds bs, float v, int l){
        Alpha legRotAlpha = new Alpha(noRotHour, Alpha.INCREASING_ENABLE, l,0, timeRotationHour,
                0,0,0,0,0);

        Shape3D leg = (Shape3D) roachNamedObjects.get(elementName);
        TransformGroup tgLeg = new TransformGroup();
        tgLeg.addChild(leg.cloneTree());

        RotationInterpolator legRotation = new RotationInterpolator(legRotAlpha , tgLeg, legRotAxis, v,0.0f);
        legRotation.setSchedulingBounds(bs);
        tgLeg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg.addChild(legRotation);
        return tgLeg;
    }

    private void addLight(SimpleUniverse su){
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour = new Color3f(1.0f,1.0f,1.0f);
        Vector3f lightDir = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light = new DirectionalLight(lightColour, lightDir);
        light.setInfluencingBounds(bounds);
        bgLight.addChild(light);
        su.addBranchGraph(bgLight);
    }

    private Image loadImage(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return img;
    }

    private Appearance loadTexture(String fileName, boolean emit) {
        Appearance app = new Appearance();

        TextureLoader loader = new TextureLoader(loadImage(fileName), null);
        Texture2D texture = (Texture2D)loader.getTexture();
        texture.setMinFilter(texture.BASE_LEVEL_LINEAR);
        texture.setMagFilter(texture.BASE_LEVEL_LINEAR);

        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        app.setTextureAttributes(texAttr);
        app.setTexture(texture);
        return app;
    }

    public static void main(String[] args){
        new BlackWidow();
    }
}