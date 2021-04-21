var options = {
    chart: {
        id: 'covid-chart',
        height: 350,
        type: 'bar',
    },
    dataLabels: {
        enabled: false
    },
    series: [],
    title: {
        text: 'Covid statistics',
    },
    noData: {
      text: 'Loading...'
    }
  }
  
  var chart = new ApexCharts(
    document.querySelector("#chart"),
    options
  );
  
  chart.render();
