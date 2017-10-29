package hackathon.thesesh.com.hackathon2017;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Jack on 29/10/2017.
 */

public class NavigationRenderer implements GLSurfaceView.Renderer {

    Context context;   // Application's context
    private Triangle quad;

    // Constructor with global application context
    public NavigationRenderer(Context context) {
        this.context = context;
        quad = new Triangle();
    }

    // Call back when the surface is first created or re-created
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);  // Set color's clear-value to black
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
        gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance

        // You OpenGL|ES initialization code here
        // ......
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
    }

    public class Triangle {
        private FloatBuffer vertexBuffer;  // Buffer for vertex-array
        private ByteBuffer indexBuffer;    // Buffer for index-array

        private float[] vertices = {  // Vertices of the triangle
                0.0f,  1.0f, 0.0f, // 0. top
                -1.0f, -1.0f, 0.0f, // 1. left-bottom
                1.0f, -1.0f, 0.0f  // 2. right-bottom
        };
        private byte[] indices = { 0, 1, 2 }; // Indices to above vertices (in CCW)

        // Constructor - Setup the data-array buffers
        public Triangle() {
            // Setup vertex-array buffer. Vertices in float. A float has 4 bytes.
            ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
            vbb.order(ByteOrder.nativeOrder()); // Use native byte order
            vertexBuffer = vbb.asFloatBuffer(); // Convert byte buffer to float
            vertexBuffer.put(vertices);         // Copy data into buffer
            vertexBuffer.position(0);           // Rewind

            // Setup index-array buffer. Indices in byte.
            indexBuffer = ByteBuffer.allocateDirect(indices.length);
            indexBuffer.put(indices);
            indexBuffer.position(0);
        }

        // Render this shape
        public void draw(GL10 gl) {
            // Enable vertex-array and define the buffers
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

            // Draw the primitives via index-array
            gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        }
    }

}
