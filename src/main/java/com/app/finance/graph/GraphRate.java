package com.app.finance.graph;

import javax.swing.*;

import com.app.finance.entity.RateParam;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.Map;

public class GraphRate extends JFrame {

    public GraphRate(Map<String, Double> map, RateParam rateParam) {
        super("Graph");
        XYDataset dataset = createDataset(map, rateParam);
        JFreeChart free = ChartFactory
                .createScatterPlot("График измения курса за указанный период", "курс", "дата", dataset);
        XYPlot plot = (XYPlot) free.getPlot();
        plot.setBackgroundPaint(new Color(255, 228, 196));
        ChartPanel panel = new ChartPanel(free);
        setContentPane(panel);
    }

    private XYDataset createDataset(Map<String, Double> map, RateParam rateParam) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("формат отображения: значение 1 = "
                + rateParam.getStart() + ", значение " + map.size() + " = " + rateParam.getEnd());
        int i = 1;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            series.add(i, entry.getValue());
            i++;
        }
        dataset.addSeries(series);
        return dataset;
    }

    public void createGraph(Map<String, Double> map, RateParam rateParam){
        SwingUtilities.invokeLater(() -> {
            GraphRate graphRate = new GraphRate(map,rateParam);
            graphRate.setSize(800,400);
            graphRate.setLocationRelativeTo(null);
            graphRate.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            graphRate.setVisible(true);
        });
    }
}
