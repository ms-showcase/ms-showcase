$(document).ready(function () {
    let dropdown = $('#covid-countries');

    dropdown.empty();

    dropdown.prop('selectedIndex', 0);

    const url = 'http://159.122.175.61:31234/data/isocodes';

    $.getJSON(url, function (data) {
        $.each(data, function (key, entry) {

            dropdown.append(
                $('<li/>').append(
                    $('<a/>', {'class': 'dropdown-item'}).text(entry)
                )
            );
        })
    });
    $(dropdown).find('temp').remove();
    $('#covid-countries').click(function () {
        loadStatistics();
    });

});
