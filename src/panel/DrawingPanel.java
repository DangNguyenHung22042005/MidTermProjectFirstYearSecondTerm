package panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	private ArrayList<ArrayList<Point>> lines;

    public DrawingPanel() {
        lines = new ArrayList<>();
        MyMouseListener myMouseListener = new MyMouseListener();
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
    }

    private class MyMouseListener extends MouseAdapter implements MouseMotionListener {
        public void mousePressed(MouseEvent e) {
            ArrayList<Point> newLine = new ArrayList<>();
            newLine.add(e.getPoint());
            lines.add(newLine);
            repaint();
        }

        public void mouseDragged(MouseEvent e) {
            lines.get(lines.size() - 1).add(e.getPoint());
            repaint();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ArrayList<Point> line : lines) {
            for (int i = 0; i < line.size() - 1; i++) {
                Point startPoint = line.get(i);
                Point endPoint = line.get(i + 1);
                g.setColor(Color.BLACK);
                g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            }
        }
    }
    
    public void clearDrawing() {
        lines.clear();
        repaint();
    }
    
    public ArrayList<ArrayList<Point>> getLines() {
    	return this.lines;
    }

	public void setLines(ArrayList<ArrayList<Point>> receivedLines) {
		this.lines = receivedLines;
	}
	
}





