<!DOCTYPE html>
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

        <div class="container">
            <div class="container pt-1 border border-info rounded-sm mt-4">
                <h4 class="text-center bg-primary">Employee Personal Details</h4>
                <hr>
                <div class="jumbotron mt-4">
                    <div class="row">
                        <div class="col-sm-2 text-primary"><label for="fname">First Name</label></div>
                        <div class="col-sm-4"><input type="text" class="form-control singleData" id="fname" name="fname" placeholder="First Name" required></div>
                        <div class="col-sm-2 text-primary"><label for="lname">Last Name</label></div>
                        <div class="col-sm-4"><input type="text" class="form-control singleData" id="lname" name="lname" placeholder="Last Name" required></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-primary"><label for="dob">Date Of Birth</label></div>
                        <div class="col-sm-4"><input type="date" id="dob" name="dob" class="form-control singleData" required></div>
                        <div class="col-sm-2 text-primary"><label for="gender">Gender</label></div>
                        <div class="col-sm-4"><select class="browser-default custom-select singleData" id="gender">
                                <option selected >Select Gender</option>
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-primary"><label for="mno">Mobile Number</label></div>
                        <div class="col-sm-4"><input type="text" class="form-control singleData" id="mno" name="mno" required></div>
                        <div class="col-sm-2 text-primary"><label for="lname">E Mail</label></div>
                        <div class="col-sm-4"><input type="text" class="form-control singleData" id="email" name="email" required></div>
                    </div>
                    <div class="row" >
                        <div class="col-sm-5"></div>
                        <div class="col-sm-2 mt-4 text-center"><input type="submit" hidden class="btn btn-primary text-center" id="UpdateBtn" value="updateData" onclick="updateData(id);"></div>
                        <div class="col-sm-5" hidden><input type="text" class="form-control singleData" id="uniqueID" name="uniqueID" required></div> 
                    </div>
                </div>
                <div class="row mt-4 mb-4 ml-4">
                    <div class="col-sm-4"></div>
                    <div class="col-sm-2 text-center"><input type="submit" class="btn btn-primary text-center" value="Submit" id="SubmitBtn" onclick="insertData();"></div>
                    <div class="col-sm-2 text-center"><input type="submit" class="btn btn-primary text-center" id="showAll" value="Records" onclick="showAll();"></div>
                    <div class="col-sm-4"></div>
                </div>
                <div class="jumbotron" id="DataTable" hidden>
                    <h4 class="text-center bg-primary">Table Data</h4>
                    <div class="table-responsive">
                        <table class="table table-bordered" id="eduTable" width="50%">
                            <thead>
                                <tr class="text-center">
                                    <th hidden>Unique ID</th>
                                    <th>Sr No</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Date of Birth</th>
                                    <th>Gender</th>
                                    <th>Mobile No</th>
                                    <th>E Mail</th>
                                    <th>Edit</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody id="EduTBody"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script>

            function escapeHtml(unsafe) {
                var str = unsafe + "";
                return str.toString().replace(/&/g, "&amp;")
                        .replace(/</g, "&lt;")
                        .replace(/>/g, "&gt;")
                        .replace(/"/g, "&quot;")
                        .replace(/'/g, "&#039;");
            }
            function phone_validate(phno)
            {
                var regexPattern = new RegExp(/^[0-9-+]+$/);    // regular expression pattern
                return regexPattern.test(phno);
            }
            function insertData()
            {
                if ($.trim($("#fname").val()) === "" || $.trim($("#lname").val()) === "" || $.trim($("#dob").val()) === "" || $.trim($("#mno").val()) === "" || $.trim($("#email").val()) === "" || $.trim($("#gender").val()) === "")
                {
                    alert("Please Fill details");
                    return false;
                } else
                {
                    var re = /^\w+([-+.'][^\s]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                    if (phone_validate($("#mno").val()))
                    {
                        // hides error msg if validation is true
                    } else
                    {
                        alert("Enter Valid Mobile Number");
                        $('#mno').focus();
                        event.preventDefault();
                        return false;
                    }
                    if (!(re.test($("#email").val())))
                    {
                        alert(" Please Fill Valid Email");
                        $('#email').focus();
                        return false;

                    } else
                    {
                        $.ajax({
                            type: "POST",
                            url: "Controller",
                            data:
                                    {
                                        callflag: "insert",
                                        fname: $("#fname").val(),
                                        lname: $("#lname").val(),
                                        dob: $("#dob").val(),
                                        mno: $("#mno").val(),
                                        email: $("#email").val(),
                                        gender: $("#gender").val()
                                    },
                            success: function (response) {
                                alert(response);

                                $("#fname").val("");
                                $("#lname").val("");
                                $("#gender").val("");
                                $("#mno").val("");
                                $("#dob").val("");
                                $("#email").val("");
                                showAll();
                            }
                        });
                    }
                }
            }


            function showAll() {
                $("#EduTBody").html("");
                $("#showAll").hide();
                debugger;
                $.ajax({
                    type: 'POST',
                    url: "Controller",
                    data: {
                        callflag: "showAll"
                    },
                    success: function (response) {
                        $("#DataTable").removeAttr("hidden");
                        debugger;
                        console.log(response);
                        var data = JSON.parse(response);
                        for (var i = 0; i < data.length; i++) {
                            debugger;
                            var id = escapeHtml(data[i].id);
                            var html = "<tr  id='" + id + "'>"
                                    + "<td hidden id='uniqueID" + id + "'>" + data[i].id + "</td>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td id='fname" + id + "'>" + escapeHtml(data[i].firstname) + "</td>"
                                    + "<td id='lname" + id + "'>" + escapeHtml(data[i].lasttname) + "</td>"
                                    + "<td id='dob" + id + "'>" + escapeHtml(data[i].dob) + "</td>"
                                    + "<td id='gender" + id + "'>" + escapeHtml(data[i].gender) + "</td>"
                                    + "<td id='mno" + id + "'>" + escapeHtml(data[i].mno) + "</td>"
                                    + "<td id='email" + id + "'>" + escapeHtml(data[i].email) + "</td>"
                                    + "<td><input type='button' class='btn btn-primary text-center' value='Edit'  id='EditId_" + data[i].id + "' onclick='editData(id);'></td>"
                                    + "<td><input type='button' class='btn btn-primary text-center' value='Remove' id='delID_" + data[i].id + "' onclick='RemoveData(id);'></td>"
                                    + "</tr>";
                            //'delID_'editID_
                            $("#EduTBody").append(html);
                            $("#showAll").hide();
                        }
                    }
                });
            }
            function editData(id) {
                debugger;
                alert(id);
                var id1 = $("#" + id).closest("tr").attr("id");
                //alert(idl);
                $("#uniqueID").val($("#uniqueID" + id1).text());
                $("#fname").val($("#fname" + id1).text());
                $("#lname").val($("#lname" + id1).text());
                $("#dob").val($("#dob" + id1).text());
                $("#gender").val($("#gender" + id1).text());
                $("#mno").val($("#mno" + id1).text());
                $("#email").val($("#email" + id1).text());
                $("#UpdateBtn").removeAttr("hidden");
                $("#UpdateBtn").attr("UniqueID", id1);
                $("#UpdateBtn").show();
                $("#SubmitBtn").hide();


            }
            function updateData(id) {
                // alert("Update Method");
                if ($.trim($("#fname").val()) === "" || $.trim($("#lname").val()) === "" || $.trim($("#dob").val()) === "" || $.trim($("#mno").val()) === "" || $.trim($("#email").val()) === "" || $.trim($("#gender").val()) === "")
                {
                    alert("Please Fill details");
                    return false;
                } else {
                    $.ajax(
                            {
                                type: 'POST',
                                url: "Controller",
                                data: {
                                    callflag: "update",
                                    fname: $("#fname").val(),
                                    lname: $("#lname").val(),
                                    dob: $("#dob").val(),
                                    mno: $("#mno").val(),
                                    email: $("#email").val(),
                                    gender: $("#gender").val(),
                                    uniqueID: $("#uniqueID").val()
                                },
                                success: function (response) {
                                    alert(response);
                                    $("#fname").val("");
                                    $("#lname").val("");
                                    $("#gender").val("");
                                    $("#mno").val("");
                                    $("#dob").val("");
                                    $("#email").val("");
                                    showAll();
                                    $("#UpdateBtn").hide();
                                    $("#SubmitBtn").show();

                                }
                            });
                    $("#SubmitBtn , #showAll").show();
                }
            }   
            function RemoveData(id) {
                debugger;
                console.log(id);
                //alert(id);
                var id1 = $("#" + id).closest("tr").attr("id");
                // alert(id1);
                $.ajax({
                    type: 'POST',
                    url: "Controller",
                    data: {
                        callflag: "delete",
                        recordid: id1
                    },
                    success: function (response) {
                        debugger;
                        alert(response);
                        $("#" + id).closest("tr").remove();
                    }
                });
            }
        </script>
    </body>
</html>