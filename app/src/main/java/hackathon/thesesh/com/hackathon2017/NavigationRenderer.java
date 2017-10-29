package hackathon.thesesh.com.hackathon2017;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by Jack on 29/10/2017.
 */

public class NavigationRenderer implements GLSurfaceView.Renderer, View.OnTouchListener {
    private static final String TAG = "NavigationRenderer";
    Context context;   // Application's context
    private Cube quad;

    // Lighting (NEW)
    boolean lightingEnabled = false;   // Is lighting on? (NEW)
    private float[] lightAmbient = {0.5f, 0.5f, 0.5f, 1.0f};
    private float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
    private float[] lightPosition = {0.0f, 0.0f, 2.0f, 1.0f};
    private int offset = 0;

    // Constructor with global application context
    public NavigationRenderer(Context context) {
        this.context = context;
        quad = new Cube();
    }

    // Call back when the surface is first created or re-created
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // Set color's clear-value to black
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
        gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance

        // Setup Texture, each time the surface is created
        gl.glEnable(GL10.GL_TEXTURE_2D);  // Enable texture

    }

    // Call back after onSurfaceCreated() or whenever the window's size changes
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;   // To prevent divide by zero
        float aspect = (float)width / height;

        // Set the viewport (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrix
        // Use perspective projection
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset

        // You OpenGL|ES display re-sizing code here
        // ......
    }

    // Call back to draw the current frame.
    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear color and depth buffers using clear-value set earlier
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);


        // Clear color and depth buffers using clear-values set earlier
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();                 // Reset model-view matrix ( NEW )

        // Translate right, relative to the previous translation ( NEW )
  //      gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);
        quad.draw(gl);
        // You OpenGL|ES rendering code here
        // ......
        gl.glPushMatrix();
        gl.glTranslatex(offset / 20, 0, 0);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glPopMatrix();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.e(TAG, "onDrag: " + "" + motionEvent.getX());
        offset = (int) motionEvent.getX();
        view.performClick();
        return false;
    }


    public class Cube {
        private FloatBuffer vertexBuffer;  // Buffer for vertex-array
        private int numFaces = 6;

        private float[][] colors = {  // Colors of the 6 faces
                {1.0f, 1.0f, 1.0f, 1.0f},  // 0. orange
                {0.8f, 0.8f, 0.8f, 1.0f},  // 1. violet
                {0.8f, 0.8f, 0.8f, 1.0f},  // 1. violet
                {0.8f, 0.8f, 0.8f, 1.0f},  // 1. violet
                {0.8f, 0.8f, 0.8f, 1.0f},  // 1. violet
                {0.8f, 0.8f, 0.8f, 1.0f},  // 1. violet
        };

        private float[] vertices = {  // Vertices of the 6 faces
                // FRONT
                -1.0f, -1.0f,  1.0f,  // 0. left-bottom-front
                1.0f, -1.0f,  1.0f,  // 1. right-bottom-front
                -1.0f,  1.0f,  1.0f,  // 2. left-top-front
                1.0f,  1.0f,  1.0f,  // 3. right-top-front
                // BACK
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f,  1.0f, -1.0f,  // 7. right-top-back
                -1.0f,  1.0f, -1.0f,  // 5. left-top-back
                // LEFT
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                -1.0f, -1.0f,  1.0f,  // 0. left-bottom-front
                -1.0f,  1.0f, -1.0f,  // 5. left-top-back
                -1.0f,  1.0f,  1.0f,  // 2. left-top-front
                // RIGHT
                1.0f, -1.0f,  1.0f,  // 1. right-bottom-front
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                1.0f,  1.0f,  1.0f,  // 3. right-top-front
                1.0f,  1.0f, -1.0f,  // 7. right-top-back
                // TOP
                -1.0f,  1.0f,  1.0f,  // 2. left-top-front
                1.0f,  1.0f,  1.0f,  // 3. right-top-front
                -1.0f,  1.0f, -1.0f,  // 5. left-top-back
                1.0f,  1.0f, -1.0f,  // 7. right-top-back
                // BOTTOM
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f,  1.0f,  // 0. left-bottom-front
                1.0f, -1.0f,  1.0f   // 1. right-bottom-front
        };

        // Constructor - Set up the buffers
        public Cube() {
            // Setup vertex-array buffer. Vertices in float. An float has 4 bytes
            ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
            vbb.order(ByteOrder.nativeOrder()); // Use native byte order
            vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
            vertexBuffer.put(vertices);         // Copy data into buffer
            vertexBuffer.position(0);           // Rewind
        }

        // Draw the shape
        public void draw(GL10 gl) {
            gl.glFrontFace(GL10.GL_CCW);    // Front face in counter-clockwise orientation
            gl.glEnable(GL10.GL_CULL_FACE); // Enable cull face
            gl.glCullFace(GL10.GL_BACK);    // Cull the back face (don't display)

            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

            // Render all the faces
            for (int face = 0; face < numFaces; face++) {
                // Set the color for each of the faces
                gl.glColor4f(colors[face][0], colors[face][1], colors[face][2], colors[face][3]);
                // Draw the primitive from the vertex-array directly
                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, face*4, 4);
            }
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisable(GL10.GL_CULL_FACE);
        }

    }

}
