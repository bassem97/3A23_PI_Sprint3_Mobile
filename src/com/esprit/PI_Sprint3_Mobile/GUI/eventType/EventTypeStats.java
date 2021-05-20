package com.esprit.PI_Sprint3_Mobile.GUI.eventType;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.esprit.PI_Sprint3_Mobile.entities.Event;
import com.esprit.PI_Sprint3_Mobile.entities.EventType;
import com.esprit.PI_Sprint3_Mobile.services.EventService;
import com.esprit.PI_Sprint3_Mobile.services.EventTypeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EventTypeStats {


    public EventTypeStats() {
        this.createPieChartForm().show();

    }



    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(50);
        renderer.setLegendTextSize(50);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }


    protected CategorySeries buildCategoryDataset(String title, HashMap<String, Integer> values) {
        CategorySeries series = new CategorySeries(title);

        values.forEach(series::add);
        return series;
    }

    public Form createPieChartForm() {
        // Generate the values

        ArrayList<EventType> eventTypes = EventTypeService.getInstance().findAll();
        ArrayList<Event> events = EventService.getInstance().findAll();
        System.out.println(events);
        HashMap<String, Integer> values = new HashMap<>();
        eventTypes.forEach(eventType -> values.put(eventType.getName(), (int) events.stream().filter(event -> event.getEventType().getId() == eventType.getId()).count()));
        System.out.println(values);

        // Set up the renderer
        int[] colors = new int[20];
        for (int i = 0; i < eventTypes.size(); i++) {
            Random r = new Random();
            int low = ColorUtil.GREEN;
            int high = ColorUtil.CYAN;
            int result = r.nextInt(high - low) + low;
            colors[i] = result;
        }
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(30);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        renderer.setLabelsColor(ColorUtil.BLACK);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Themes", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("Type Events Statistique", new BorderLayout());
        f.add(BorderLayout.CENTER, c);
        f.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new EventTypeListForm().show());
        return f;

    }
}
