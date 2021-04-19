package io.covid.dto.chart;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Series {
    private List<String> xaxis;
    private List<ChartData> series;
}
