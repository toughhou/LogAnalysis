<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%
        String contextPath = request.getContextPath();
    %>
    <link href="<%=contextPath%>/resource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath%>/resource/bootstrap/css/bootstrap-responsive.min.css"
          rel="stylesheet">
    <link href="<%=contextPath%>/resource/bootstrap/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet">
    <script src="<%=contextPath%>/resource/scripts/jquery-1.10.1.min.js"></script>
    <script src="<%=contextPath%>/resource/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/resource/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker_hour').datetimepicker({
                language: 'en',
                pickTime: false
            });
        });

        function searchPv() {
            var appName = $("#appName").val();
            var timeKey = $("#timeKey").val();
            var type=$("#statType").val();
            $.ajax({
                url: "/log/stat",
                type: "POST",
                data: {appName: appName, timeKey: timeKey,type:type},
                dataType: 'json',
                success: function (data) {
                    buildTable(data);
                },
                error: function (error) {
                }
            });
        }

        function buildTable(pvData) {
            var tbody = $('<tbody></tbody>');
            $.each(pvData, function (i, item) {
                var tr = $('<tr></tr>');
                tr.append("<td>" + item["pvKey"] + "</td>");
                tr.append("<td>" + item["requestUrl"] + "</td>");
                tr.append("<td>" + item["totalNum"] + "</td>");
                tr.append("<td>" + item["successNum"] + "</td>");
                tr.append("<td>" + item["ipNum"] + "</td>");
                tr.append("<td>" + item["maxRequestTime"] + "</td>");
                tr.append("<td>" + item["avgRequestTime"] + "</td>");
                tbody.append(tr);
            })
            $('#pvTable tbody').replaceWith(tbody);
        }
    </script>
</head>
<html>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse"
                    data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">LogAnalyzer</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li class="nav-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="span12">
            <h2>Heading</h2>
            <div class="well">
                    <form class="form-inline">
                        <label>项目:</label>
                        <select id="appName">
                            <option value="findmeback">findmeback</option>
                        </select>
                        <%--<div id="datetimepicker_hour" class="input-append">--%>
                        <%--<input data-format="yyyy-MM-dd HH:mm:ss" type="text" id="hour"/>--%>
                        <%--<span class="add-on">--%>
                        <%--<i data-time-icon="icon-time" data-date-icon="icon-calendar">--%>
                        <%--</i>--%>
                        <%--</span>--%>
                        <%--</div>--%>
                        <lable>维度</lable>
                        <select id="statType">
                            <option value="1">小时</option>
                            <option value="2">天</option>
                        </select>
                        <label>时间段:</label>
                        <input id="timeKey" type="text" placeholder="yyyyMMddHH">
                        <a onclick="searchPv()" class="btn btn-primary">查询&raquo;</a>
                    </form>
            </div>
            <table class="table table-hover" id="pvTable">
                <thead>
                <tr>
                    <th>
                        rowKey
                    </th>
                    <th>
                        请求
                    </th>
                    <th>
                        PV
                    </th>
                    <th>
                        成功
                    </th>
                    <th>
                        IP
                    </th>
                    <th>
                        MAX(Request_Time)
                    </th>
                    <th>
                        AVG(Request_Time)
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>

    <hr>

    <footer>
        <p>&copy; Company 2015</p>
    </footer>

</div>
</body>
</html>
