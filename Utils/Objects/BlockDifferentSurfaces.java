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
import java.awt.Color;

/**
 * A block which can have different surfaces.
 */
public class BlockDifferentSurfaces extends Visible {

    private String[] textureFiles;
    private Texture texture;
    private TextureKind[] kinds;
    private Color[] colors;

    /**
     * Constructor.
     *
     * @param system       the block's position
     * @param kinds        the texture kinds's
     * @param textureFiles the textures for the block
     * @param width        width (x)
     * @param height       height (y)
     * @param length       length (z)
     */
    public BlockDifferentSurfaces(CoordinateSystem system, TextureKind[] kinds, String[] textureFiles,
                                  float width, float height, float length) {
        super(system);
        this.textureFiles = textureFiles;
        this.kinds = kinds;
        this.width = width;
        this.height = height;
        this.length = length;
        colors = null;
    }

    /**
     * Constructor.
     *
     * @param system the block's position
     * @param colors the block's colors
     * @param width  width (x)
     * @param height height (y)
     * @param length length (z)
     */
    public BlockDifferentSurfaces(CoordinateSystem system, Color[] colors, float width, float height,
                                  float length) {
        super(system);
        this.textureFiles = null;
        this.kinds = null;
        this.width = width;
        this.height = height;
        this.length = length;
        this.colors = colors;
    }

    @Override
    public void draw(GLU glu, GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glPushMatrix();

        gl.glTranslatef((float) system.getPositionX(), (float) system.getPositionY(), (float) system.getPositionZ());

        if (colors == null) {
            gl.glEnable(gl.GL_TEXTURE_2D);
            texture(gl);
        } else {
            color(glu, glAutoDrawable);
        }

        gl.glTranslatef((float) -system.getPositionX(), (float) -system.getPositionY(), (float) -system.getPositionZ());

        gl.glPopMatrix();
    }

    /**
     * Display the textures.
     *
     * @param gl a GL object
     */
    private void texture(GL2 gl) {
        texture = TextureManager.getInstance().getTexture(kinds[0], textureFiles[0]);
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
        gl.glEnd();

        texture = TextureManager.getInstance().getTexture(kinds[1], textureFiles[1]);
        if (texture != null) {
            texture.bind(gl);
        }
        gl.glBegin(GL2.GL_QUADS);
        // Back Face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(0, 0, 0);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(width, 0, 0);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(width, height, 0);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0, height, 0);
        gl.glEnd();

        texture = TextureManager.getInstance().getTexture(kinds[2], textureFiles[2]);
        if (texture != null) {
            texture.bind(gl);
        }
        gl.glBegin(GL2.GL_QUADS);
        // Top Face
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0, height, 0);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0, height, length);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(width, height, length);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(width, height, 0);
        gl.glEnd();

        texture = TextureManager.getInstance().getTexture(kinds[3], textureFiles[3]);
        if (texture != null) {
            texture.bind(gl);
        }
        gl.glBegin(GL2.GL_QUADS);
        // Bottom Face
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(0, 0, 0);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0, 0, length);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(width, 0, length);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(width, 0, 0);
        gl.glEnd();

        texture = TextureManager.getInstance().getTexture(kinds[4], textureFiles[4]);
        if (texture != null) {
            texture.bind(gl);
        }
        gl.glBegin(GL2.GL_QUADS);
        // Right face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(width, 0, 0);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(width, 0, length);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(width, height, length);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(width, height, 0);
        gl.glEnd();

        texture = TextureManager.getInstance().getTexture(kinds[5], textureFiles[5]);
        if (texture != null) {
            texture.bind(gl);
        }
        gl.glBegin(GL2.GL_QUADS);
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

    private void color(GLU glu, GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
//        gl.glColor3f(colors[0].getRed(), colors[0].getGreen(), colors[0].getBlue());
        ColorManager.setColor(colors[0].getRed(), colors[0].getGreen(), colors[0].getBlue());
        ColorManager.set(glu, glAutoDrawable);
        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glVertex3f(width, height, length);
        gl.glVertex3f(width, 0, length);
        gl.glVertex3f(0, 0, length);
        gl.glVertex3f(0, height, length);
        gl.glEnd();

//        gl.glColor3f(colors[1].getRed(), colors[1].getGreen(), colors[1].getBlue());
        ColorManager.setColor(colors[1].getRed(), colors[1].getGreen(), colors[1].getBlue());
        ColorManager.set(glu, glAutoDrawable);
        gl.glBegin(GL2.GL_QUADS);
        // Back Face
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(width, 0, 0);
        gl.glVertex3f(width, height, 0);
        gl.glVertex3f(0, height, 0);
        gl.glEnd();

//        gl.glColor3f(colors[2].getRed(), colors[2].getGreen(), colors[2].getBlue());
        ColorManager.setColor(colors[2].getRed(), colors[2].getGreen(), colors[2].getBlue());
        ColorManager.set(glu, glAutoDrawable);
        gl.glBegin(GL2.GL_QUADS);
        // Top Face
        gl.glVertex3f(0, height, 0);
        gl.glVertex3f(0, height, length);
        gl.glVertex3f(width, height, length);
        gl.glVertex3f(width, height, 0);
        gl.glEnd();

//        gl.glColor3f(colors[3].getRed(), colors[3].getGreen(), colors[3].getBlue());
        ColorManager.setColor(colors[3].getRed(), colors[3].getGreen(), colors[3].getBlue());
        ColorManager.set(glu, glAutoDrawable);
        gl.glBegin(GL2.GL_QUADS);
        // Bottom Face
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, 0, length);
        gl.glVertex3f(width, 0, length);
        gl.glVertex3f(width, 0, 0);
        gl.glEnd();

//        gl.glColor3f(colors[4].getRed(), colors[4].getGreen(), colors[4].getBlue());
        ColorManager.setColor(colors[4].getRed(), colors[4].getGreen(), colors[4].getBlue());
        ColorManager.set(glu, glAutoDrawable);
        gl.glBegin(GL2.GL_QUADS);
        // Right face
        gl.glVertex3f(width, 0, 0);
        gl.glVertex3f(width, 0, length);
        gl.glVertex3f(width, height, length);
        gl.glVertex3f(width, height, 0);
        gl.glEnd();

//        gl.glColor3f(colors[5].getRed(), colors[5].getGreen(), colors[5].getBlue());
        ColorManager.setColor(colors[5].getRed(), colors[5].getGreen(), colors[5].getBlue());
        ColorManager.set(glu, glAutoDrawable);
        gl.glBegin(GL2.GL_QUADS);
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
