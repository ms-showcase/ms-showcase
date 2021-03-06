function loadStatistics(iso) {
    var url = '/data/statistics/' + iso;

    $.getJSON(url, function (response) {
        ApexCharts.exec('covid-chart', 'updateOptions', {
            xaxis: {
                categories: response['xaxis']
            }
        }, false, true);
        chart.updateSeries([{
            name: 'New Cases',
            data: response["series"][0].data
        },
        {
            name: 'ICU Patients',
            data: response["series"][1].data
        },
        {
            name: 'New Deaths',
            data: response["series"][2].data
        }
        ])

    });
}



