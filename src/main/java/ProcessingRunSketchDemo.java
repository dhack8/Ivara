import processing.core.PApplet;

/**
 * Created by Callum Li on 9/16/17.
 */
public class ProcessingRunSketchDemo extends PApplet {

    @Override
    public void settings(){
        size(500, 500);
    }

    @Override
    public void draw(){
        pushMatrix();
        translate(50, 50);
        scale(5);
        ellipse(mouseX, mouseY, 50, 50);
        popMatrix();
    }

    @Override
    public void mousePressed(){
        background(64);
    }

    public static void main(String[] args) {
        ProcessingRunSketchDemo p = new ProcessingRunSketchDemo();
        PApplet.runSketch(new String[]{"Sketch Demo"}, p);
    }
}
