package Pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PieChartObserver extends JPanel implements Observer {

    public PieChartObserver(CourseData data) {
        data.attach(this);
        this.courseData = data.getUpdate();
        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.setBackground(Color.white);
    }

    public void paint(Graphics g) {
        super.paint(g);
        int radius = 100;
        double total = 0.0;
        for (int i = 0; i < courseData.size(); i++) {
            CourseRecord record = (CourseRecord) courseData.get(i);
            total += record.getNumOfStudents();
        }
        if (total != 0) {
            double startAngle = 0.0;
            for (int i = 0; i < courseData.size(); i++) {
                CourseRecord record = (CourseRecord) courseData.get(i);
                double ratio = (record.getNumOfStudents() / total) * 360.0;
                g.setColor(LayoutConstants.courseColours[i % LayoutConstants.courseColours.length]);
                g.fillArc(LayoutConstants.xOffset, LayoutConstants.yOffset, 2 * radius, 2 * radius, (int) startAngle, (int) ratio);
                startAngle += ratio;
            }
        }
    }

    public void update(Object o) {
        this.courseData = (ArrayList<CourseRecord>) o;

        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.revalidate();
        this.repaint();
    }

    private ArrayList<CourseRecord> courseData;
}
