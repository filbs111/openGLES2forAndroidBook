package com.example.superuser.firstopenglproject;

import static android.opengl.GLES20.*;
import static android.opengl.GLUtils.*;
import static android.opengl.Matrix.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.opengl.GLSurfaceView.Renderer;

/**
 * Created by SuperUser on 14/09/2017.
 */

public class FirstOpenGLProjectRenderer implements Renderer {
    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config){
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height){
        //Set the OpenGL viewoprt to fill the entire surface
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 glUnused){
        //clear the rendering surface
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
