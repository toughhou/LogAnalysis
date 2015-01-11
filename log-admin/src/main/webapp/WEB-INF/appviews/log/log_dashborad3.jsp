<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
	<head>
        <%
            String contextPath = request.getContextPath();
        %>
		<meta charset="utf-8" />
		<title>控制台 - Bootstrap后台管理系统模版Ace下载</title>
		<!-- basic styles -->
		<link href="<%=contextPath%>/resource/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=contextPath%>/resource/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=contextPath%>/resource/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- ace styles -->

		<link rel="stylesheet" href="<%=contextPath%>/resource/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=contextPath%>/resource/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=contextPath%>/resource/assets/css/ace-skins.min.css" />
        <link href="<%=contextPath%>/resource/bootstrap/css/bootstrap-datetimepicker.min.css"
              rel="stylesheet">

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=contextPath%>/resource/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="<%=contextPath%>/resource/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="<%=contextPath%>/resource/assets/js/html5shiv.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							ACE后台管理系统
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="<%=contextPath%>/resource/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									Jason
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="icon-cog"></i>
										设置
									</a>
								</li>

								<li>
									<a href="#">
										<i class="icon-user"></i>
										个人资料
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="#">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="icon-signal"></i>
							</button>

							<button class="btn btn-info">
								<i class="icon-pencil"></i>
							</button>

							<button class="btn btn-warning">
								<i class="icon-group"></i>
							</button>

							<button class="btn btn-danger">
								<i class="icon-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->

					<ul class="nav nav-list">
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text"> UI 组件 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="elements.html">
										<i class="icon-double-angle-right"></i>
										组件
									</a>
								</li>

								<li>
									<a href="buttons.html">
										<i class="icon-double-angle-right"></i>
										按钮 &amp; 图表
									</a>
								</li>

								<li>
									<a href="treeview.html">
										<i class="icon-double-angle-right"></i>
										树菜单
									</a>
								</li>

								<li>
									<a href="jquery-ui.html">
										<i class="icon-double-angle-right"></i>
										jQuery UI
									</a>
								</li>

								<li>
									<a href="nestable-list.html">
										<i class="icon-double-angle-right"></i>
										可拖拽列表
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>
							<li class="active">控制台</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="hr hr32 hr-dotted"></div>

								<div class="row">
									<div class="col-sm-12">
										<div class="widget-box transparent" id="recent-box">
											<div class="widget-header">
												<h4 class="lighter smaller">
													<i class="icon-rss orange"></i>
													24小时统计
                                                        <select id="appName">
                                                            <option>AOTA</option>
                                                            <option>FSTORE</option>
                                                        </select>
                                                        <div id="datetimepicker1" class="input-append date">
                                                            <input data-format="yyyy-MM-dd" type="text"
                                                                   class="col-xs-10 col-sm-5 input-small" id="timeKey"/>
                                                         <span class="add-on">
                                                          <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                                                          </i>
                                                       </span>
                                                        </div>
                                                        <button class="btn btn-sm btn-yellow" onclick="searchAppPv()">查询
                                                        </button>
												</h4>

												<div class="widget-toolbar no-border">
													<ul class="nav nav-tabs" id="recent-tab">
														<li class="active">
															<a data-toggle="tab" href="#task-tab">PV</a>
														</li>

														<li>
															<a data-toggle="tab" href="#member-tab">用户数</a>
														</li>

														<li>
															<a data-toggle="tab" href="#comment-tab">流量</a>
														</li>
													</ul>
												</div>
											</div>

											<div class="widget-body">
												<div class="widget-main padding-4">
													<div class="tab-content padding-8 overflow-visible">
														<div id="task-tab" class="tab-pane active">
                                                            <div id="container" style="min-width: 310px; height: 270px; margin: 0 auto"></div>
														</div>

														<div id="member-tab" class="tab-pane">
                                                            <div id="container2" style="min-width: 310px; height: 270px; margin: 0 auto"></div>
														</div><!-- member-tab -->

														<div id="comment-tab" class="tab-pane">
                                                            <div id="container3" style="min-width: 310px; height: 270px; margin: 0 auto"></div>
														</div>
													</div>
												</div><!-- /widget-main -->
											</div><!-- /widget-body -->
										</div><!-- /widget-box -->
									</div><!-- /span -->
								</div><!-- /row -->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="widget-box transparent">
                                            <div class="widget-header widget-header-flat">
                                                <h4 class="lighter">
                                                    <i class="icon-star orange"></i>
                                                    热门域名
                                                </h4>

                                                <div class="widget-toolbar">
                                                    <a href="#" data-action="collapse">
                                                        <i class="icon-chevron-up"></i>
                                                    </a>
                                                </div>
                                            </div>

                                            <div class="widget-body">
                                                <div class="widget-main no-padding">
                                                    <table class="table table-bordered table-striped" id="pvTable">
                                                        <thead class="thin-border-bottom">
                                                        <tr>
                                                            <th>
                                                                <i class="icon-caret-right blue"></i>
                                                                时间
                                                            </th>
                                                            <th>
                                                                <i class="icon-caret-right blue"></i>
                                                                请求
                                                            </th>
                                                            <th class="hidden-480">
                                                                <i class="icon-caret-right blue"></i>
                                                                PV
                                                            </th>
                                                            <th class="hidden-480">
                                                                <i class="icon-caret-right blue"></i>
                                                                用户数
                                                            </th>
                                                            <th class="hidden-480">
                                                                <i class="icon-caret-right blue"></i>
                                                                成功PV
                                                            </th>
                                                            <th class="hidden-480">
                                                                <i class="icon-caret-right blue"></i>
                                                                最大耗时
                                                            </th>
                                                            <th class="hidden-480">
                                                                <i class="icon-caret-right blue"></i>
                                                                平均耗时
                                                            </th>
                                                            <th class="hidden-480">
                                                                <i class="icon-caret-right blue"></i>
                                                                总流量
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        </tbody>


                                                    </table>
                                                </div><!-- /widget-main -->
                                            </div><!-- /widget-body -->
                                        </div><!-- /widget-box -->
                                    </div>
                                </div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->


			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->



		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=contextPath%>/resource/assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<%=contextPath%>/resource/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='<%=contextPath%>/resource/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
		<script src="<%=contextPath%>/resource/assets/js/bootstrap.min.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="<%=contextPath%>/resource/assets/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="<%=contextPath%>/resource/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/jquery.slimscroll.min.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/jquery.sparkline.min.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/flot/jquery.flot.min.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/flot/jquery.flot.pie.min.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/flot/jquery.flot.resize.min.js"></script>

		<!-- ace scripts -->

		<script src="<%=contextPath%>/resource/assets/js/ace-elements.min.js"></script>
		<script src="<%=contextPath%>/resource/assets/js/ace.min.js"></script>
        <script src="<%=contextPath%>/resource/hcharts/highcharts.js"></script>
        <script src="<%=contextPath%>/resource/bootstrap/js/bootstrap-datetimepicker.min.js"></script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			jQuery(function($) {
			alert('111');
                var options={
                    chart: {
                        renderTo: 'container'
                    },
                    title: {
                        text: 'PV/DAY'
                    },
                    xAxis: {
                        categories: []
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'PV'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        valueSuffix: ''
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    },
                    series: []
                }
                $.ajax({
                    url: "/log/chart",
                    type: "POST",
                    data: {rowName: "aota_20150110", kpiName: ""},
                    dataType: 'json',
                    success: function (data) {
                        options.categories=JSON.stringify(data["categories"]);
                        options.series=data["chartSeriesList"];
                        var chart = new Highcharts.Chart(options);
                    },
                    error: function (error) {
                    }
                });

                $('#datetimepicker1').datetimepicker({
                    language: 'pt-BR'
                });
            })


            function searchAppPv() {
                var appName = $("#appName").val();
                var timeKey = $("#timeKey").val();
                $.ajax({
                    url: "/log/stat",
                    type: "POST",
                    data: {appName: appName, timeKey: timeKey},
                    dataType: 'json',
                    success: function (data) {
                        buildTable(data);
                    },
                    error: function (error) {
                    }
                });
            }

            function buildTable(data) {
                var tbody = $('<tbody></tbody>');
                var pvData=data["data"];
                $.each(pvData, function (i, item) {
                    var tr = $('<tr></tr>');
                    tr.append("<td class='hidden-320'> <s class='red'>" + item["pvKey"] + "</s></td>");
                    tr.append("<td class='hidden-320><s class='red'>" + item["requestUrl"] + "</s></td>");
                    tr.append("<td class='hidden-320>" + item["pv"] + "</td>");
                    tr.append("<td class='hidden-320>" + item["spv"] + "</td>");
                    tr.append("<td class='hidden-320>" + item["uv"] + "</td>");
                    tr.append("<td class='hidden-320>" + item["maxRequestTime"] + "</td>");
                    tr.append("<td class='hidden-320>" + item["avgRequestTime"] + "</td>");
                    tr.append("<td class='hidden-320>" + item["uf"] + "</td>");
                    tbody.append(tr);
                })
                $('#pvTable tbody').replaceWith(tbody);
            }
		</script>
</body>
</html>

