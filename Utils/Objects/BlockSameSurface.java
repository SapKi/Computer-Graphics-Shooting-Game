// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package Objects;

import Behaviour.Visible;
import Drawing.TextureKind;
import Drawing.TextureManager;
import Environment.Color.ColorManager;
import Environment.CoordinateSystem.CoordinateSystem;
import com.jogamp.opengl.util.texture.Texture;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.*;

/**
 * A block which all of his surfaces are the same.
 */
public class BlockSameSurface extends Visible {

    private String textureFile;
    private Texture texture;
    private TextureKind kind;
    private Color color;

    private boolean init;
    private int item;

    /**
     * Constructor.
     * @param system the block's position
     * @param kind the texture kind
     * @param textureFile the blocks texture
     * @param width width (x)
     * @param height height (y)
     * @param length length (z)
     */
    public BlockSameSurface(CoordinateSystem system, TextureKind kind, String textureFile, float width, float height,
                            float length) {
        super(system);
        this.textureFile = textureFile;
        this.kind = kind;
        this.width = width;
        this.height = height;
        this.length = length;
        color = null;
        init = true;
    }

    /**
     * Constructor.
     * @param system the block's position
     * @param color the block's colors
     * @param width width (x)
     * @param height height (y)
     * @param length length (z)
     */
    public BlockSameSurface(CoordinateSystem system, Color color, float width, float height,
                            float length) {
        super(system);
        this.textureFile = "";
        this.kind = null;
        this.width = width;
        this.height = height;
        this.length = length;
        this.color = color;
        init = true;
    }

    @Override
    public void draw(GLU glu, GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        if(init) {
            gl.glPushMatrix();
            gl.glNewList(item, GL2.GL_COMPILE);
            gl.glTranslatef((float) system.getPositionX(), (float) system.getPositionY(), (float) system.getPositionZ());
            if (color == null) {
                gl.glEnable(gl.GL_TEXTURE_2D);
                texture(gl);
            } else {
                color(glu, glAutoDrawable);
            }
            gl.glTranslatef((float)-system.getPositionX(), (float)-system.getPositionY(), (float)-system.getPositionZ());
            gl.glEnd();
            gl.glEndList();
            gl.glPopMatrix();
            init = false;
        }
        gl.glPushMatrix();
        gl.glCallList(item);
        gl.glPopMatrix();
    }

    /**
     * Display the texture.
     * @param gl a GL object
     */
    private void texture(GL2 gl) {
        texture = TextureManager.getInstance().getTexture(kind, textureFile);

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

        if (texture != null) {
            texture.bind(gl);
        }

        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(width, height, length);
        gl.glTexCoord2f(1f, 0.0f);
        gl.glVertex3f(width, 0, length);
        gl.glTexCoord2f(1f, 1.0f);
        gl.glVertex3f(0, 0, length);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0, height, length);
        // Back Face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(0,0,0);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(width,0,0);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(width, height, 0);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0, height,0);
        // Top Face
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0,height,0);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0,height,length);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(width,height,length);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(width,height,0);
        // Bottom Face
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(0,0,0);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0,0,length);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(width,0,length);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(width,0,0);
        // Right face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(width,0,0);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(width, 0, length);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(width, height, length);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(width, height, 0);
        // Left Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0, height, 0);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(0, height, length);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(0, 0, length);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0, 0, 0);
        gl.glEnd();
    }

    /**
     * Display the color.
     *
     * @param glu a gl object
     * @param glAutoDrawable a gl object
     */
    private void color(GLU glu, GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        ColorManager.setColor(color.getRed(), color.getGreen(), color.getBlue());
        ColorManager.set(glu, glAutoDrawable);
//        ColorManager.set(glu, glAutoDrawable, new float[] {1, 0, 1});
//        ColorManager.set(glu, glAutoDrawable, new float[] {color.getRed(), color.getGreen(), color.getBlue()});
        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glVertex3f(width, height, length);
        gl.glVertex3f(width, 0, length);
        gl.glVertex3f(0, 0, length);
        gl.glVertex3f(0, height, length);
        // Back Face
        gl.glVertex3f(0,0,0);
        gl.glVertex3f(width,0,0);
        gl.glVertex3f(width, height, 0);
        gl.glVertex3f(0, height,0);
        // Top Face
        gl.glVertex3f(0,height,0);
        gl.glVertex3f(0,height,length);
        gl.glVertex3f(width,height,length);
        gl.glVertex3f(width,height,0);
        // Bottom Face
        gl.glVertex3f(0,0,0);
        gl.glVertex3f(0,0,length);
        gl.glVertex3f(width,0,length);
        gl.glVertex3f(width,0,0);
        // Right face
        gl.glVertex3f(width,0,0);
        gl.glVertex3f(width, 0, length);
        gl.glVertex3f(width, height, length);
        gl.glVertex3f(width, height, 0);
        // Left Face
        gl.glVertex3f(0, height, 0);
        gl.glVertex3f(0, height, length);
        gl.glVertex3f(0, 0, length);
        gl.glVertex3f(0, 0, 0);
        gl.glEnd();

        ColorManager.resetColor();
    }

    @Override
    public void timePassed(long millisecondsPassed) {

    }

    @Override
    public ObjectKind getKind() {
        return ObjectKind.box;
    }
}
