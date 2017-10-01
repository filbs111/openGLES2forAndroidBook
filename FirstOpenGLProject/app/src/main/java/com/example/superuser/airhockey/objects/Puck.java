package com.example.superuser.airhockey.objects;

import com.example.superuser.airhockey.data.VertexArray;
import com.example.superuser.airhockey.objects.ObjectBuilder.*;
import com.example.superuser.airhockey.programs.ColorShaderProgram;
import com.example.superuser.airhockey.util.Geometry.*;

import java.util.List;

/**
 * Created by SuperUser on 29/09/2017.
 */

public class Puck {
    private static final int POSITION_COMPONENT_COUNT = 3;
    public final float radius, height;
    private final VertexArray vertexArray;
    private final List<DrawCommand> drawList;

    public Puck(float radius, float height, int numPointsAroundPuck){
        GeneratedData generatedData = ObjectBuilder.createPuck(
                new Cylinder(new Point(0f, 0f, 0f), radius, height),
                numPointsAroundPuck);
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
        for (DrawCommand drawCommand : drawList){
            drawCommand.draw();
        }
    }

}
