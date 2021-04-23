$(document).ready(function () {
    let dropdown = $('#covid-countries');

    dropdown.empty();

    dropdown.prop('selectedIndex', 0);

    const url = 'http://159.122.175.61:31234/data/isocodes';
    const urlPopulation = 'http://159.122.175.61:31234/population/';



    $.getJSON(url, function (data) {
        $.each(data, function (key, entry) {

            dropdown.append(
                    $('<a/>', {'class': 'dropdown-item'}).text(entry)
            );
        })
    });

    $('#covid-countries').click(function (e) {
        $('.country-data').removeClass('invisible');
        $('.country-data').addClass('visible');
        let iso = e.target.innerHTML;
        loadStatistics(iso);
        $.getJSON(urlPopulation + iso, function (data) {
            $('#country').text(data.country);

            var population = data.population;
            if (Number.isInteger(population)) {
                population = Number(population).toFixed(2) + " mil";
            }
            $('#population').text(population);
        });
    });

});
