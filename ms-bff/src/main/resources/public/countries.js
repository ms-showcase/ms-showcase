$(document).ready(function () {
    let dropdown = $('#covid-countries');

    dropdown.empty();

    dropdown.prop('selectedIndex', 0);

    const url = '/data/isocodes';
    const urlPopulation = '/population/';

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
            $('#population').text(data.population);
        });
    });

});
