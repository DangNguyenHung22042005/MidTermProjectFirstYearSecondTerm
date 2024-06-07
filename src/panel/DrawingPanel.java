package panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	private ArrayList<ArrayList<Point>> lines;
    private Color currentColor = Color.BLACK; // Màu sắc hiện tại của đường vẽ
    private float currentStrokeSize = 1.0f;   // Kích cỡ hiện tại của đường vẽ

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
        Graphics2D g2d = (Graphics2D) g; // Ép kiểu Graphics thành Graphics2D để sử dụng các tính năng nâng cao
        g2d.setColor(currentColor);      // Đặt màu hiện tại
        g2d.setStroke(new BasicStroke(currentStrokeSize)); // Đặt kích cỡ đường vẽ hiện tại
        
        for (ArrayList<Point> line : lines) {
            for (int i = 0; i < line.size() - 1; i++) {
                Point startPoint = line.get(i);
                Point endPoint = line.get(i + 1);
                g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            }
        }
    }

    // Phương thức để xóa bản vẽ
    public void clearDrawing() {
        lines.clear();
        repaint();
    }

    // Getter và setter cho lines
    public ArrayList<ArrayList<Point>> getLines() {
        return this.lines;
    }

    public void setLines(ArrayList<ArrayList<Point>> receivedLines) {
        this.lines = receivedLines;
    }

    // Phương thức để thay đổi màu sắc của đường vẽ
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    // Phương thức để thay đổi kích cỡ của đường vẽ
    public void setCurrentStrokeSize(float size) {
        this.currentStrokeSize = size;
    }
    
}





