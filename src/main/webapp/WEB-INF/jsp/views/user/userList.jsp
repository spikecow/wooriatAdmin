<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>회원 관리 (목록)</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">--%>

<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap custom css -->
<link rel="stylesheet" href="/bootstrap/css/custom.css">
<link rel="stylesheet" href="/dist/css/custom.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="/dist/css/skins/_all-skins.min.css">

<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

<!-- jQuery 2.1.4 -->
<script src="/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment-with-locales.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="/bootstrap/js/bootstrap.min.js"></script>
<!-- Slimscroll -->
<script src="/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/dist/js/demo.js"></script>
<!-- Page -->
<script src="/customJS/jquery.twbsPagination.min.js"></script>

<script type="text/javascript" src="/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
        <%@ include file="../layout/header.jsp"%>
        <%@ include file="../layout/leftMenu.jsp"%>
        <div class="content-wrapper">
            <section class="content-header">
                <h1>회원 관리 (목록)</h1>
            </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-danger">
                        <div class="box-body text-right">
                            <form id="reqForm" class="form-inline col-md">
                                <input style="width: 400px;" type="text" id="searchWord" name="searchWord" class="form-control" placeholder="검색어를 입력해 주세요." />
                                    <button type="button" id="reqFormSubmit" class="btn btn-warning btn-group-sm" data-type="web">검색</button>
                                <input type="hidden" name="page" value="0" />
                                <input type="hidden" name="size" value="10"/>
                            </form>
                        </div>
                    </div>

                    <div class="box">
                        <div class="box-body table-responsive no-padding">

                            <table class="table table-striped" id="regionTable">
                            	<colgroup>
                            		<col width="5%">
                            		<col width="45%">
                            		<col width="13%">
                            		<col width="17%">
                            		<col width="10%">
                            		<col width="10%">
                            	</colgroup>
                            	
                                <thead class="thead-light">
                                    <tr>
                                        <th scope="col" class="text-center">No.</th>
                                        <th scope="col" class="text-center">메뉴관리</th>
                                        <th scope="col" class="text-center">이름</th>
                                        <th scope="col" class="text-center">이메일</th>
                                        <th scope="col" class="text-center">회원등록일</th>
                                        <th scope="col" class="text-center">최종접속일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list.content}" var="list" varStatus="status">
                                    <tr class="text-center">
                                        <td>${(totalCount - status.index) - ( (page - 1)  *  10 ) }</td>
                                        <td style = "cursor:pointer;" onClick = "location.href='/user/detail/${ list.uid }' ">
                                            <%--<button name="btnViewDetail" style="border: 0px; background-color: rgba(255,255,255,0.5); color: black;" class="btn btn-danger btn-sm" data-id="${ list.id }">메뉴관리</button>--%>
                                            <c:forEach items="${list.userMenus}" var="menu" varStatus="menu_status">
                                                ${menu.menuNm}<c:if test="${!menu_status.last}">, </c:if>
                                            </c:forEach>
                                        </td>
                                        <td style = "cursor:pointer;" onClick = "location.href='/user/detail/${ list.uid }' ">
                                                ${list.userNm}
                                        </td>
                                        <td style = "cursor:pointer;" onClick = "location.href='/user/detail/${ list.uid }' ">
                                                ${list.email}
                                        </td>
                                        <td>
                                            <fmt:parseDate value="${ list.cretDtm }" pattern="yyyy-MM-dd'T'HH:mm" var="cretDtm" type="both" />
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${ cretDtm }" />
                                        </td>
                                        <td>
                                            <fmt:parseDate value="${ list.lastLoginDtm }" pattern="yyyy-MM-dd'T'HH:mm" var="lastLoginDtm" type="both" />
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${ lastLoginDtm }" />
                                        </td>
                                    </tr>

                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 text-right">
                    <button name="btnRegist" type="button" class="btn btn-info btn-group-sm" >등록</button>
                </div>
            </div>
            <div id="paging" class="row">
                <div class="col-sm-10">
                    <ul id="pagination" class="pagination-sm"></ul>
                </div>
            </div>

            <!-- Preview Modal -->
        </section>
    </div>

	<script src="/common/js/js/common.js" type="text/javascript"></script>
    <script type="text/javascript">

        var totalPage = Number('${totalPage}');

        var currentPage = Number('${page}');
        var prevSearchWord = '${searchWord}';
        if(totalPage == 0){
            totalPage = currentPage;
        }

        $(function () {
            $('#pagination').twbsPagination({
                totalPages: totalPage,
                visiblePages: 10,
                startPage: currentPage,
                onPageClick: function (event, page) {

                    if (currentPage != page) {
                        $('#reqForm input[name=page]').val(page-1);
                        $('#reqFormSubmit').trigger('click');
                    }
                }
            });

            $('input[name=searchWord]').val(prevSearchWord);
        });

        $('button[name=btnRegist]').on('click', function () {
            location.href = "/user/createForm/"
        });

        $('button[name=btnViewDetail]').on('click', function () {
            location.href = "/user/detail/"+$(this).attr('data-id');
        });
    </script>
    
    
</div>
</body>
