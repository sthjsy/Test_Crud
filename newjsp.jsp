<!DOCTYPE html>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="bootstrap.min.css">
        <script src="jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> 

    </head>
    <body>       
        <div class="container form-control">
            <div class="container pt-1 border border-info rounded-sm mt-4">
                <h3 class="text-center bg-danger"  style="color:white">ATM MASTER DATA</h3>
                <button type="button" class="btn btn-danger" onclick="addATMData();">ADD NEW ATM DATA</button>  
                <!--<button type="button" class="btn btn-success" onclick="getATMData();">GET DATA</button>-->    

                <div class="container " id="DataTable" >
                    <div class="table-responsive ">
                        <table class="table table-bordered  " id="eduTable" width="50%">
                            <thead>
                                <tr class="text-center">                                   
                                    <th>ATM ID</th>
                                    <th>BRANCH NAME</th>
                                    <th>MACHINE MAKE</th>
                                    <th>ATM ADDRESS</th>
                                    <th>ATM LOCATION</th>
                                    <th>EMAIL</th>
                                    <th>CUSTODIAN</th>
                                    <th>CUSTODIAN NAME</th>
                                    <th>CUSTODIAN MOBILE NUMBER</th>
                                </tr>
                            </thead>
                            <tbody id="EduTBody"></tbody>
                        </table>
                    </div>
                </div>
            </div> 
        </div>   
        <script>
            function addATMData() {
                alert("add row");
                var html = "<tr id='abc'>" +
                        "<td><input type='text' id='atm_id'></td>" +
                        "<td><input type='text' id='branch_name'></td>" +
                        "<td><input type='text' id='machine_make'></td>" +
                        "<td><input type='text' id='atm_address'></td>" +
                        "<td><input type='text' id='atm_location'></td>" +
                        "<td><input type='text' id='email'></td>" +
                        "<td><input type='text' id='custodian'></td>" +
                        "<td><input type='text' id='custodian_name'></td>" +
                        "<td><input type='text' id='custodian_mobile_number'></td>" +
                        "<td><input class='form-group form-control btn-danger' type='button' id='removeRow' value='Remove' onclick='removeRow(id);'></td>" +
                        "<td><input class='form-group form-control btn-primary' type='button' id='editRow' value='edit' onclick='editRow(id);'></td>" +
                        "</tr>";
                $("#EduTBody").append(html);
            }
            function removeRow(id) {
                alert("inside remove2");
                var count = $("#" + id).closest("tr").attr("id");
                $("#" + id).closest("tr").remove();
                deleteATM_Data(count);
                alert("inside remove`13234");
            }
            function deleteATM_Data(count) {
                alert(count);
                $.registerDeleteComplex("DELETE_CM_EDU_DATA").setData(function ()
                {
                    var complexData = new Array();
                    var row = new Array();
                    row.push(count);
                    complexData.push(row);
                    return complexData;
                });
            }
            function editRow(id) {
                alert("hii");
                alert("ID...." + id);
                $("#" + id).closest("tr").find("td").each(function () {
                    $(this).find("input").removeAttr("disabled");
                    alert("hii12312");
                });
                $.registerUpdateComplex("UPDATE_CM_EDU_DATA").setData(function () {
                    var complexData = new Array();
                    $("#" + id).closest("tr").each(function () {
                        var row = new Array();
                        alert("inside");
                        if ($(this).attr("queryType") === "U") {
                            row.push($(this).find("td:eq(0)").find("input").val());
                            row.push($(this).find("td:eq(1)").find("input").val());
                            row.push($(this).find("td:eq(2)").find("input").val());
                            row.push($(this).find("td:eq(3)").find("input").val());
                            row.push($(this).find("td:eq(4)").find("input").val());
                            row.push($(this).find("td:eq(5)").find("input").val());
                            row.push($(this).find("td:eq(6)").find("input").val());
                            row.push($(this).find("td:eq(7)").find("input").val());
                            row.push($(this).find("td:eq(8)").find("input").val());
                            row.push($("#" + id).closest("tr").attr("id"));
                            complexData.push(row);
                        }
                    });
                    if (complexData.length > 0) {
                        return complexData;
                    } else {
                        return null;
                    }
                });
            }
        </script>
    </body>
</html>