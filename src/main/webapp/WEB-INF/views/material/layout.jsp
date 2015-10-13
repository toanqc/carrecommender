<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=no">
        <title>Material CSS Demo</title>
        <link rel="stylesheet" href="http://localhost:8080/iSelect-Web/resources/material/css/md-css.min.css">
        <link rel="stylesheet" href="http://localhost:8080/iSelect-Web/resources/material/css/md-icons.min.css">
    </head>
    <body material fluid style="overflow: auto;">
        <div panel nodim>
            <!-- side bar -->
            <tiles:insertAttribute name="sidebar" />
            <div main>
                <tiles:insertAttribute name="menubar" />
                <tiles:insertAttribute name="body" />
            </div><!-- end of main -->
        </div><!-- end of panel -->
        <!-- load scripts at the end -->
        <script src="http://localhost:8080/iSelect-Web/resources/material/js/zepto.min.js"></script>
        <script src="http://localhost:8080/iSelect-Web/resources/material/js/velocity.min.js"></script>
        <script src="http://localhost:8080/iSelect-Web/resources/material/js/md-js.min.js"></script>
    </body>
</html>