package main;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Generate {

    public static XYDataset createDataset() {
        if (Ui.radioButton02.isSelected()) {

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries series1 = new TimeSeries("Temperatura");
            for (int i = 0; i < Ui.t.getL().size(); i++) {
                series1.add(Ui.h.getMinutos().get(i), Double.parseDouble(Ui.t.getL().get(i).toString()));
            }

            dataset.addSeries(series1);
            return dataset;

        } else if (Ui.radioButton01.isSelected()) {
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries series1 = new TimeSeries("Temperatura");
            for (int i = 0; i < Ui.t.getL().size(); i++) {
                series1.add(Ui.h.getMinutos().get(i), Double.parseDouble(Ui.t.getL().get(i).toString()));
            }
            dataset.addSeries(series1);

            TimeSeries series2 = new TimeSeries("Pressão");

            for (int i = 0; i < Ui.p.getL().size(); i++) {
                series2.add(Ui.h.getMinutos().get(i), Double.parseDouble(Ui.p.getL().get(i).toString()));
            }

            dataset.addSeries(series2);
            return dataset;
        } else if (Ui.radioButton03.isSelected()) {
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries series1 = new TimeSeries("Pressão");
            for (int i = 0; i < Ui.p.getL().size(); i++) {
                series1.add(Ui.h.getMinutos().get(i), Double.parseDouble(Ui.p.getL().get(i).toString()));
            }

            dataset.addSeries(series1);
            return dataset;
        }

        return null;

    }

    public static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Pressão / Temperatura / Data ", // Chart
                "Data", // X-Axis Label
                "Valor", // Y-Axis Label
                dataset);

        NumberAxis axis2 = new NumberAxis("Pressão");
        axis2.setLabelPaint(Color.blue);
        axis2.setTickLabelPaint(Color.blue);

        XYPlot plot = (XYPlot) chart.getPlot();

        //XYDataset dataset3 = createDataset(); //modificar
        TimeSeriesCollection dataset3 = new TimeSeriesCollection();
        plot.setDataset(2, dataset3);
        plot.setRangeAxis(2, axis2);
        plot.mapDatasetToRangeAxis(1, 1);

        TimeSeries series1 = new TimeSeries("Pressão");
        for (int i = 0; i < Ui.p.getL().size(); i++) {
            series1.add(Ui.h.getMinutos().get(i), Double.parseDouble(Ui.p.getL().get(i).toString()));
        }
        dataset3.addSeries(series1);

        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(false);
        renderer.setBaseShapesFilled(true);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }

}
