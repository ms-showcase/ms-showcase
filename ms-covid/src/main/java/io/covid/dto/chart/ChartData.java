package io.covid.dto.chart;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartData {
    private String name;
    @Builder.Default
    private List<Integer> data = new ArrayList<>();
}
