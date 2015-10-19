<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Car Recommender</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="resources/css/main.css" />" rel="stylesheet">
        <script type="text/javascript"
        src="<c:url value="resources/js/lib/jquery-1.11.3.min.js" />"></script>
        <script type="text/javascript"
        src="<c:url value="resources/js/facebook.js" />"></script>
    </head>
    <body>
        <div class="container">
            <div class="header-container">
                <div class="header-left">Logo!</div>
                <div class="header-right">
                    <fb:login-button scope="public_profile,email" autologoutlink="true"
                                     onlogin="checkLoginState();">
                    </fb:login-button>
                    <div id="status"></div>
                </div>
            </div>
            <div class="search-container">
                <div class="header-text">
                    Find your dream car <sub> &reg;</sub>
                </div>
                <div class="type-search-container">
                    <ul>
                        <li>Buy</li>
                        <li>Rent</li>
                    </ul>
                </div>
                <div class="search-box-container">
                    <div>
                        <select name="makes" id="makes">
                            <option value="" selected="selected" >&nbsp;--Select Make--&nbsp;</option>
                            <c:if test="${not empty makes}">
                                <c:forEach var="make" items="${makes}">
                                    <option value="${make.id}">${make.name}</option>
                                </c:forEach>
                            </c:if>
                        </select> <br> <span class="eg-make">e.g. Nissan</span>
                    </div>
                    <div id="txtHint" name="txtHint"></div>
                    <div class="model-box">
                        <select name="models" id="models">
                            <option value="">&nbsp;Select Model&nbsp;</option>
                        </select> <br> <span class="eg-model">e.g. Maxima</span>
                    </div>
                    <div>
                        <select name="years" id="years">
                            <option value="">&nbsp;Select Year&nbsp;</option>
                        </select> <br>&nbsp;
                    </div>
                </div>
                <div class="button-container">
                    <div class="go-button">
                        <button id="search">Go</button>
                    </div>
                </div>
            </div>
            <div name="cars"></div>
        </div>
        <script type="text/javascript">
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
                $.getJSON("searchCar?make="+makeName+"&model="+modelName+"&year="+year, function (data) {
                    $.each(data, function (index, item) {
                        items += "<option value='" + item.id + "'>" + item.name + "</option>";
                    });
                    $("#cars").html(items);
                });
            });

        </script>
    </body>
</html>
