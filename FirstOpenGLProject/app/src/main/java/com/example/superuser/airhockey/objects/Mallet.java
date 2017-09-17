package com.example.superuser.airhockey.objects;

import com.example.superuser.airhockey.data.VertexArray;
import com.example.superuser.airhockey.programs.ColorShaderProgram;

import static android.opengl.GLES20.*;
import static com.example.superuser.airhockey.Constants.*;

/**
 * Created by SuperUser on 17/09/2017.
 */

public class Mallet {

    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT ) * BYTES_PER_FLOAT;

    private static final float[] VERTEX_DATA = {
            //order of co-ordinates: X,Y, R,G,B

            // Mallets
            0f, -0.4f,     0f,0f,1f,
            0f, 0.4f,      1f,0f,0f,
    };

    private final VertexArray vertexArray;

    public Mallet(){
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(ColorShaderProgram colorProgram){
        vertexArray.setVertexAttribPointer(0,
                colorProgram.getPositionAttributeLocation() , POSITION_COMPONENT_COUNT, STRIDE);
        vertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT,
                colorProgram.getColorAttributeLocation() , COLOR_COMPONENT_COUNT, STRIDE);
    }

    public void draw(){
        glDrawArrays(GL_POINTS, 0, 2);
    }




}
