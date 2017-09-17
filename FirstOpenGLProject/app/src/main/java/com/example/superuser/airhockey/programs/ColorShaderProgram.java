package com.example.superuser.airhockey.programs;

import android.content.Context;

import com.example.superuser.airhockey.R;

import static android.opengl.GLES20.*;

/**
 * Created by SuperUser on 17/09/2017.
 */

public class ColorShaderProgram extends ShaderProgram{

    //uniform locations
    private final int uMatrixLocation;

    //attribute locations
    private final int aPositionLocation;
    private final int aColorLocation;

    public ColorShaderProgram(Context context){
        super(context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader);

        //Retrieve uniform locations for the shader program.
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);

        //Retrieve attribute locations for the shader program.
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aColorLocation = glGetAttribLocation(program, A_COLOR);
    }

    public void setUniforms(float[] matrix){
        //Pass the matrix into the shader program
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    }

    public int getPositionAttributeLocation(){
        return aPositionLocation;
    }
    public int getColorAttributeLocation(){
        return aColorLocation;
    }

}
