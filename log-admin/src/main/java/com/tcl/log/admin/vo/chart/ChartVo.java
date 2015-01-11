package com.tcl.log.admin.vo.chart;

import java.util.List;

/**
 * Created by _think on 2015/1/10.
 */
public class ChartVo {
    private List categories;
    private List<ChartSeries> chartSeriesList;

    public ChartVo(List categories, List<ChartSeries> chartSeriesList) {
        this.categories = categories;
        this.chartSeriesList = chartSeriesList;
    }

    public List<ChartSeries> getChartSeriesList() {
        return chartSeriesList;
    }

    public List getCategories() {
        return categories;
    }
}
