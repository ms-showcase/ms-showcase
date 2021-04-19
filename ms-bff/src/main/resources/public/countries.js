$(document).ready(function () {
    let dropdown = $('#covid-countries');

    dropdown.empty();

    dropdown.prop('selectedIndex', 0);

    const url = 'http://127.0.0.1:8998/data/isocodes';

    // Populate dropdown with list of provinces
    $.getJSON(url, function (data) {
        $.each(data, function (key, entry) {
            // var listItem = document.createElement('li');
            // var dropdownitem = listItem.append($('<a class="dropdown-item" href="#"></a>').text(entry));
            // dropdown.append(dropdownitem);
            dropdown.append(
                $('<li/>').append(
                    $('<a/>', {'class': 'dropdown-item'}).text(entry)
                )
            );
        })
    });
    $(dropdown).find('temp').remove();
});