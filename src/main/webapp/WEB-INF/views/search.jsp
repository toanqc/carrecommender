<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>HTM Cars</title>
    </head>
    <body>
        <h1>HTM Cars</h1>

        Find cars
        <form action="dosearch" method="get">
            Make
            <select name="make">
                <option></option>
            </select>
            Model
            <select name="model">
                <option></option>
            </select>

            Year
            <select name="year">
                <option></option>
            </select>

            <input type="submit" value="Submit" />
        </form>
    </body>
</html>