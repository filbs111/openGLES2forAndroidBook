package com.example.superuser.airhockey;

import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import com.example.superuser.airhockey.util.LoggerConfig;
import com.example.superuser.airhockey.util.ShaderHelper;
import com.example.superuser.airhockey.util.TextResourceReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by SuperUser on 14/09/2017.
 */

public class AirHockeyRenderer implements Renderer {
    private final Context context;
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer vertexData;
    private int program;
    private static final String A_COLOR = "a_Color";
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT ) * BYTES_PER_FLOAT;
    private int aColorLocation;
    private static final String A_POSITION = "a_Position";
    private int aPositionLocation;
    private static final String U_MATRIX = "u_Matrix";
    private final float[] projectionMatrix = new float[16];
    private int uMatrixLocation;

    public AirHockeyRenderer(Context context){
        this.context = context;
        float[] tableVertices = {
                // Triangle fan
                0f, 0f,         1f,1f,1f,
                -0.5f, -0.8f,   0.7f,0.7f,0.7f,
                0.5f, -0.8f,    0.7f,0.7f,0.7f,
                0.5f, 0.8f,     0.7f,0.7f,0.7f,
                -0.5f, 0.8f,    0.7f,0.7f,0.7f,
                -0.5f, -0.8f,   0.7f,0.7f,0.7f,

                // Line 1
                -0.5f, 0f,      1f,0f,0f,
                0.5f, 0f,       1f,0f,0f,

                // Mallets
                0f, -0.4f,     0f,0f,1f,
                0f, 0.4f,      1f,0f,0f,

                // puck
                0f, 0f,         1f,1f,0f
        };

        vertexData = ByteBuffer
                .allocateDirect(tableVertices.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(tableVertices);
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config){
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader);
        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);
        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
        if (LoggerConfig.ON){
            ShaderHelper.validateProgram(program);
        }
        glUseProgram(program);
        aColorLocation = glGetAttribLocation(program, A_COLOR);
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT, false, STRIDE, vertexData);
        glEnableVertexAttribArray(aPositionLocation);
        vertexData.position(POSITION_COMPONENT_COUNT);
        glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GL_FLOAT, false, STRIDE, vertexData);
        glEnableVertexAttribArray(aColorLocation);
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height){
        //Set the OpenGL viewport to fill the entire surface
        glViewport(0, 0, width, height);

        final float aspectRatio = width > height ?
                (float) width / (float) height :
                (float) height / (float) width;

        if (width > height){
            //landscape
            orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
        }else{
            //portrait or square
            orthoM(projectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        }
    }

    @Override
    public void onDrawFrame(GL10 glUnused){
        glClear(GL_COLOR_BUFFER_BIT);           //clear the rendering surface
        glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);

        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);    //table

        glDrawArrays(GL_LINES, 6, 2);           //dividing line

        glDrawArrays(GL_POINTS, 8, 1);          //mallets
        glDrawArrays(GL_POINTS, 9, 1);

        glDrawArrays(GL_POINTS, 10, 1);         //puck
    }
}
