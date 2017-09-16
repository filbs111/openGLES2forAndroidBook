uniform mat4 u_Matrix;

attribute vec4 a_Position;
attribute vec4 a_Color;

varying vec4 v_Color;

void main()
{
    v_Color = a_Color;
    //v_Color = vec4(1.0);
    gl_Position = u_Matrix * a_Position;
    gl_PointSize = 10.0;
}