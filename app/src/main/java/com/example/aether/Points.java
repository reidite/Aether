package com.example.aether;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.microedition.khronos.opengles.GL10;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class Points {
    private double[] vertices;
    private float[] fvertices;
    float[] colorArray;
    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;
    public native double[] getPointsArray();
    public native float[] getColorsArray();
    public Points()
    {
        String quebraLinha = System.getProperty("line.separator");
        this.vertices = getPointsArray();
        fvertices = new float[vertices.length];
        colorArray = getColorsArray();
        Log.e("Size", ""+colorArray.length);
        String show = "",color="";
        for (int i = 0, j = 0; i <  vertices.length; i+=3, j+=4)
        {
            fvertices[i] = (float)vertices[i];
            fvertices[i+1] = (float)vertices[i+1];
            fvertices[i+2] = (float)vertices[i+2];
            show += fvertices[i]+" "+fvertices[i+1]+" "+fvertices[i+2]+" "+(int)Math.round(colorArray[j]*255)+" "+(int)Math.round(colorArray[j+1]*255)+" "+(int)Math.round(colorArray[j+2]*255) + quebraLinha;
        }
        writeToFile(show);

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length*4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(fvertices);
        vertexBuffer.position(0);
        ByteBuffer cbb = ByteBuffer.allocateDirect(colorArray.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colorArray);
        colorBuffer.position(0);
    }

    public void draw(final GL10 gl)
    {
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer); // NEW LINE ADDED.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY); // NEW LINE ADDED.

        gl.glPointSize(4);
        gl.glDrawArrays(GL10.GL_POINTS, 0, vertices.length/3);
        gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }

    public void writeToFile(String text)
    {
        String path = "/storage/emulated/0/dcim/sfm";

        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
    }
}


