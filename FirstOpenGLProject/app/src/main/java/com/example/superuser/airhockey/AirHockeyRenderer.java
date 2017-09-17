package com.example.superuser.airhockey;

import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import com.example.superuser.airhockey.objects.*;
import com.example.superuser.airhockey.programs.*;
import com.example.superuser.airhockey.util.MatrixHelper;
import com.example.superuser.airhockey.util.TextureHelper;


/**
 * Created by SuperUser on 14/09/2017.
 */

public class AirHockeyRenderer implements Renderer {
    private final Context context;
    private final float[] projectionMatrix = new float[16];
    private final float[] modelMatrix = new float[16];

    private Table table;
    private Mallet mallet;

    private TextureShaderProgram textureShaderProgram;
    private ColorShaderProgram colorShaderProgram;

    private int texture;

    public AirHockeyRenderer(Context context){
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config){
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        table = new Table();
        mallet = new Mallet();

        textureShaderProgram = new TextureShaderProgram(context);
        colorShaderProgram = new ColorShaderProgram(context);

        texture = TextureHelper.loadTexture(context, R.drawable.air_hockey_surface);
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height){
        //Set the OpenGL viewport to fill the entire surface
        glViewport(0, 0, width, height);
        MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width / (float) height, 1f, 10f);
        setIdentityM(modelMatrix, 0);
        translateM(modelMatrix, 0, 0f, 0f, -3f);
        rotateM(modelMatrix, 0, -60f, 1f, 0f, 0f);
        final float[] temp = new float[16];
        multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
        System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);
    }

    @Override
    public void onDrawFrame(GL10 glUnused){
        glClear(GL_COLOR_BUFFER_BIT);           //clear the rendering surface

        //draw table
        textureShaderProgram.useProgram();
        textureShaderProgram.setUniforms(projectionMatrix, texture);
        table.bindData(textureShaderProgram);
        table.draw();

        //draw mallets
        colorShaderProgram.useProgram();
        colorShaderProgram.setUniforms(projectionMatrix);
        mallet.bindData(colorShaderProgram);
        mallet.draw();
    }
}
