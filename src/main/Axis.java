
package main;

import java.awt.Color;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class Axis extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a time series chart
     * with multiple axes.
     *
     * @param title the frame title.
     */
    public Axis(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates the demo chart.
     *
     * @return The chart.
     */
    private static JFreeChart createChart() {

//------------------------------------------------------------------------------
        if (Ui.tpData.isSelected()) {
            //cria a temperatura----------------------------------------------
            XYDataset dataset1 = cDataset(
                    "temperatura");

            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                    "Temperatura / Pressão por data",
                    "Data",
                    "Temperatura",
                    dataset1,
                    true,
                    true,
                    false);

            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setDomainPannable(true);
            plot.setOrientation(PlotOrientation.VERTICAL);
            //----------------------------------------------------------------

            //cria a pressão--------------------------------------------------
            // DOMAIN AXIS 2
            NumberAxis xAxis2 = new NumberAxis("Pressão");
            xAxis2.setVisible(false);
           
            xAxis2.setAutoRangeIncludesZero(false);
            plot.setDomainAxis(1, xAxis2);

            // RANGE AXIS 2
            NumberAxis yAxis2 = new NumberAxis("Pressão");
            yAxis2.setAxisLinePaint(Color.blue);
            yAxis2.setLabelPaint(Color.blue);
            yAxis2.setTickLabelPaint(Color.blue);
            yAxis2.setTickMarkPaint(Color.blue);
            
            plot.setRangeAxis(1, yAxis2);
            plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
                
            XYDataset dataset2 = cDataset("pressão");
            plot.setDataset(1, dataset2);
            plot.mapDatasetToDomainAxis(1, 1);
            plot.mapDatasetToRangeAxis(1, 1);
            
            XYLineAndShapeRenderer r = new XYLineAndShapeRenderer(true, false);
            r.setSeriesPaint(1, Color.blue);
            plot.setRenderer(1, r);
            
            yAxis2.setLabelPaint(Color.blue);
            yAxis2.setTickLabelPaint(Color.blue);
            //---------------------------------------------------------------
            ChartUtilities.applyCurrentTheme(chart);
            return chart;
        } //--------------------------------------------------------------------
        else if (Ui.tData.isSelected()) {
            XYDataset dataset1 = cDataset("temperatura");

            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                    "Temperatura por Data",
                    "Data",
                    "Temperatura",
                    dataset1,
                    true,
                    true,
                    false);

            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setDomainPannable(true);
            plot.setOrientation(PlotOrientation.VERTICAL);
            ChartUtilities.applyCurrentTheme(chart);
            return chart;
        } else if (Ui.pData.isSelected()) {
            XYDataset dataset1 = cDataset("pressão");

            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                    "Pressão por data",
                    "Data",
                    "pressão",
                    dataset1,
                    true,
                    true,
                    false);

            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setDomainPannable(true);
            plot.setOrientation(PlotOrientation.VERTICAL);
            ChartUtilities.applyCurrentTheme(chart);
            return chart;
        }
        return null;
    }

    /**
     * Creates a sample dataset.
     *
     * @param name the dataset name.
     * @return The dataset.
     */
    private static XYDataset createDataset(String name, double base,
            RegularTimePeriod start, int count) {

        TimeSeries series = new TimeSeries(name);
        RegularTimePeriod period = start;
        double value = base;
        for (int i = 0; i < count; i++) {
            series.add(period, value);
            period = period.next();
            value = value * (1 + (Math.random() - 0.495) / 10.0);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        return dataset;

    }

    private static XYDataset cDataset(String name) {

        if (name.matches("pressão")) {

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries series1 = new TimeSeries("Pressão");
            for (int i = 0; i < Ui.p.getL().size(); i++) {
                series1.add(Ui.h.getMinutos().get(i), Double.parseDouble(Ui.p.getL().get(i).toString()));

            }

            dataset.addSeries(series1);
            return dataset;
        } else if (name.matches("temperatura")) {

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries series1 = new TimeSeries(name);
            for (int i = 0; i < Ui.t.getL().size(); i++) {
                series1.add(Ui.h.getMinutos().get(i), Double.parseDouble(Ui.t.getL().get(i).toString()));
            }

            dataset.addSeries(series1);
            return dataset;
        }
        return null;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
        ChartPanel c = new ChartPanel(chart);
        c.setMouseWheelEnabled(true);
        return c;
    }


}

