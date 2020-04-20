package house_lab;

import java.awt.Container;
import javax.media.j3d.*; // for transform
import javax.vecmath.Color3f;
import java.awt.Color;
import com.sun.j3d.utils.geometry.*;
import javax.vecmath.*; // for Vector3f
import com.sun.j3d.utils.image.TextureLoader;

public class House {
    public static Box getBody(float height, float width, float length) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Box(width, length, height, primflags, getBodyAppearence());
    }

    public static Box getWindow(float height, float width, float length) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Box(width, length, height, primflags, getWindowAppearence());
    }

    public static Cone getHouseRoof(float height, float radius){
        TransformGroup tg = new TransformGroup();
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Cone(radius, height, primflags, getRoofAppearence());
    }

    public static Box getWindowLine(float height, float width, float length) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Box(width, length, height, primflags, getBodyAppearence());
    }

    public static Box getDoor(float height, float width, float length) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Box(width, length, height, primflags, getDoorAppearence());
    }

    private static Appearance getBodyAppearence() {
        // завантажуємо текстуру
        TextureLoader loader = new TextureLoader("resources\\brick2.jpg", "LUMINANCE", new
                Container());
        Texture texture = loader.getTexture();
        // задаємо властивості границі текстури
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));

        // встановлюємо атрибути текстури
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.REPLACE); //  REPLACE, BLEND або DECAL замість MODULATE

        Appearance ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);
        return ap;
    }

    private static Appearance getWindowAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(100,38, 38));
        Color3f diffuse = new Color3f(new Color(255,38, 38));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    private static Appearance getDoorAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(10,28, 58));
        Color3f diffuse = new Color3f(new Color(25,28, 58));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    private static Appearance getRoofAppearence() {
        // завантажуємо текстуру
        TextureLoader loader = new TextureLoader("resources\\brick.jpeg", "LUMINANCE", new
                Container());

        Texture texture = loader.getTexture();
        // задаємо властивості границі текстури
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));

        // встановлюємо атрибути текстури
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.BLEND);

        Appearance ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);
        return ap;
    }
}