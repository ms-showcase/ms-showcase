var style = new ol.style.Style({
    fill: new ol.style.Fill({
      color: 'rgba(255, 255, 255, 0.6)'
    }),
    stroke: new ol.style.Stroke({
      color: '#319FD3',
      width: 1
    }),
    text: new ol.style.Text({
      font: '12px Calibri,sans-serif',
      fill: new ol.style.Fill({
        color: '#000'
      }),
      stroke: new ol.style.Stroke({
        color: '#fff',
        width: 3
      })
    })
  });

  var vectorLayer = new ol.layer.Vector({
    source: new ol.source.Vector({
      url: 'https://openlayers.org/en/latest/examples/data/geojson/countries.geojson',
      format: new ol.format.GeoJSON()
    }),
    style: function(feature) {
      style.getText().setText(feature.get('name'));
      return style;
    }
  });

var view = new ol.View({
    center: [2195115.7737817545, 6219583.835109638],
    zoom: 4
});

  var map = new ol.Map({
    layers: [vectorLayer],
    target: 'map',
    view: view
  });


  var highlightStyle = new ol.style.Style({
    stroke: new ol.style.Stroke({
      color: '#f00',
      width: 1
    }),
    fill: new ol.style.Fill({
      color: 'rgba(255,0,0,0.1)'
    }),
    text: new ol.style.Text({
      font: '12px Calibri,sans-serif',
      fill: new ol.style.Fill({
        color: '#000'
      }),
      stroke: new ol.style.Stroke({
        color: '#f00',
        width: 3
      })
    })
  });

  var featureOverlay = new ol.layer.Vector({
    source: new ol.source.Vector(),
    map: map,
    style: function(feature) {
      highlightStyle.getText().setText(feature.get('name'));
      return highlightStyle;
    }
  });
  var highlight;

$('#covid-countries').click(function (e) {
    var feature = vectorLayer.getSource().getFeatureById(e.target.innerHTML);
    if (highlight) {
        featureOverlay.getSource().removeFeature(highlight);
    }
    if (feature) {
        featureOverlay.getSource().addFeature(feature);
    }
    highlight = feature;
});