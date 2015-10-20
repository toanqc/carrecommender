$( document ).ready(function() {
    $(function () {
        var items = "";
        $.getJSON("makes", function (data) {
            items += "<option value=''>-- Select Make --</option>";
            $.each(data, function (index, item) {
                items += "<option value='" + item.id + "'>" + item.name + "</option>";
            });
            $("#makes").html(items);
        });
    });

    $('#makes').on('change', function () {
        var makeId = $( "#makes option:selected").text();
        var items = "";
        $.getJSON("models?make="+makeId, function (data) {
            items += "<option value=''>-- Select Model --</option>";
            $.each(data, function (index, item) {
                items += "<option value='" + item.id + "'>" + item.name + "</option>";
            });
            $("#models").html(items);
        });
    });
    
    $('#models').on('change', function () {
        var items = "";
        $.getJSON("years", function (data) {
            items += "<option value=''>-- Select Year --</option>";
            $.each(data, function (index, item) {
                items += "<option value='" + item.id + "'>" + item.name + "</option>";
            });
            $("#years").html(items);
        });
    });
    
    $('#search').on('click', function () {
        var makeName = $( "#makes option:selected").text();
        var modelName = $( "#models option:selected").text();
        var year = $( "#years option:selected").text();
        var items = "";
       	var count = 0;
        $.getJSON("searchCar?make="+makeName+"&model="+modelName+"&year="+year, function (data) {
            $.each(data, function (index, item) {
            	count++;
            	if (count % 2 != 0) {
            		items += "<div>";
            	}
            	items += "<div class='search-result'>";
            	items += "<div class='search-result-img";
            	if (count % 2 == 0) {
            		items += " margin-left";
            	}
            	items += "'><img src='" + item.photo + "'></div>";
            	items += "<div class='search-result-text'>";
            	items += "<div>" + item.make + " " + item.submodel + " " + item.year + "</div>";
                items += "<div class='word-wrap'>" + item.name + "</div>";
                items += "<div>Transmission: " + item.transmission + "</div>";
            	items += "<div>Doors: " + item.doors + "</div>";
            	items += "<div>MSRP: $" + item.msrp + "</div>";
            	items += "<div>Rating: " + item.rating + "</div>";
            	items += "<div>Cosumer Rating: " + item.consumerRating + "</div>";
            	items += "</div></div>";
            	if (count % 2 == 0) {
            		items += "</div><div class='clear-left'></div>";
            	}
            });

            $("#cars").html(items);
        });
    });
});