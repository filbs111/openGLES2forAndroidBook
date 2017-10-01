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
    private final int uColorLocation;

    //attribute locations
    private final int aPositionLocation;

    public ColorShaderProgram(Context context){
        super(context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader);

        //Retrieve uniform locations for the shader program.
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
        uColorLocation = glGetUniformLocation(program, U_COLOR);

        //Retrieve attribute locations for the shader program.
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
    }

    public void setUniforms(float[] matrix, float r, float g, float b){
        //Pass the matrix into the shader program
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        glUniform4f(uColorLocation, r, g, b, 1f);
    }

    public int getPositionAttributeLocation(){
        return aPositionLocation;
    }
}
