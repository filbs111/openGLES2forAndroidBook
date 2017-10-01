package com.example.superuser.airhockey.programs;

import android.content.Context;

import com.example.superuser.airhockey.util.ShaderHelper;
import com.example.superuser.airhockey.util.TextResourceReader;

import static android.opengl.GLES20.*;

/**
 * Created by SuperUser on 17/09/2017.
 */

public class ShaderProgram {
    //uniform constants
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
    protected static final String U_COLOR = "u_Color";

    //attribute constants
    protected static final String A_POSITION = "a_Position";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

    //Shader program
    protected final int program;
    protected ShaderProgram(Context context, int vertexShaderResourceId, int fragmentShaderResourceId){
        //compile the shaders and link the program
        program = ShaderHelper.buildProgram(TextResourceReader.readTextFileFromResource(context, vertexShaderResourceId),
                TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceId) );
    }

    public void useProgram(){
        //set the current OpenGL shader program to use this program.
        glUseProgram(program);
    }

}
