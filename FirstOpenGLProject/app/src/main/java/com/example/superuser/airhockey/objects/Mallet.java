package com.example.superuser.airhockey.objects;

import com.example.superuser.airhockey.data.VertexArray;
import com.example.superuser.airhockey.objects.ObjectBuilder.*;

import com.example.superuser.airhockey.programs.ColorShaderProgram;
import com.example.superuser.airhockey.util.Geometry.*;

import java.util.List;

import static android.opengl.GLES20.*;
import static com.example.superuser.airhockey.Constants.*;

/**
 * Created by SuperUser on 17/09/2017.
 */

public class Mallet {
    private static final int POSITION_COMPONENT_COUNT = 3;
    public final float radius, height;
    private final VertexArray vertexArray;
    private final List<DrawCommand> drawList;

    public Mallet(float radius, float height, int numPointsAroundMallet){
        GeneratedData generatedData = ObjectBuilder.createMallet(new Point(0f, 0f, 0f),
                radius, height, numPointsAroundMallet);

        this.radius = radius;
        this.height = height;

        vertexArray = new VertexArray(generatedData.vertexData);
        drawList = generatedData.drawList;
    }

    public void bindData(ColorShaderProgram colorProgram){
        vertexArray.setVertexAttribPointer(0,
                colorProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT, 0);
    }

    public void draw(){
        for (ObjectBuilder.DrawCommand drawCommand : drawList){
            drawCommand.draw();
        }
    }



}
