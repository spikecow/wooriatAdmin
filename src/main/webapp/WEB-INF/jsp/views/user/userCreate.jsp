<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>회원 관리 (등록)</title>
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
<script src="/se2/js/service/HuskyEZCreator.js"></script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
        <%@ include file="../layout/header.jsp"%>
        <%@ include file="../layout/leftMenu.jsp"%>
        <div class="content-wrapper">
            <section class="content-header">
                <h1>회원 관리 (등록)</h1>
            </section>
        <!-- Main content -->
        <section class="content">
            <form id="form">
                <input type="hidden" name="uid" id="id" value="${data.uid}"/>
                <input type="hidden" name="authCd" id="authCd" value="${data.authCd.value}"/>
            <div class="row">
                <div class="col-xs-12">

                    <div class="box">
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-striped" id="regionTable">
                            	<colgroup>
                            		<col width="15%">
                            		<col width="35%">
                                    <col width="15%">
                                    <col width="35%">
                            	</colgroup>

                                <tbody>
                                    <tr class="text-center">
                                        <th class="text-center">당담자 팀명</th>
                                        <td><input type="text" name="deptNm" class="form-control" id="deptNm" value="${data.deptNm}"/></td>
                                        <th class="text-center">당담자 이름</th>
                                        <td><input type="text" name="userNm" class="form-control" id="userNm" value="${data.userNm}"/></td>
                                    </tr>

                                    <tr class="text-center">
                                        <th class="text-center">담당자 아이디(ID)</th>
                                        <td><input type="text" name="userId" class="form-control" id="userId" value="${data.userId}"/></td>
                                        <th class="text-center">담당자 이메일</th>
                                        <td><input type="text" name="email" class="form-control" id="email" value="${data.email}"/></td>
                                    </tr>

                                    <tr class="text-center">
                                        <th class="text-center">비밀번호</th>
                                        <td colspan="3"><input type="password" name="userPwd" class="form-control" id="userPwd"/></td>
                                    </tr>

                                    <tr>
                                        <th class="text-center" rowspan="4">메뉴관리</th>
                                        <td class="text-bold">메인 팝업 관리</td>
                                        <td colspan="2">|
                                            <c:forEach items="${menu.menuList1}" var="list">
                                                <input type="checkbox" name="menuId" value="${list.menuId}"
                                                    <c:forEach items="${data.userMenus}" var="menu" varStatus="menu_status">
                                                         <c:if test="${menu.menuId eq list.menuId}">checked</c:if>
                                                    </c:forEach>
                                                >
                                                ${list.menuNm}
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="text-bold">회사소개</td>
                                        <td colspan="2">|
                                            <c:forEach items="${menu.menuList2}" var="list">
                                                <input type="checkbox" name="menuId" value="${list.menuId}"
                                                <c:forEach items="${data.userMenus}" var="menu" varStatus="menu_status">
                                                       <c:if test="${menu.menuId eq list.menuId}">checked</c:if>
                                                </c:forEach>
                                                >
                                                ${list.menuNm}
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="text-bold">물건정보</td>
                                        <td colspan="2">|
                                            <c:forEach items="${menu.menuList3}" var="list">
                                                <input type="checkbox" name="menuId" value="${list.menuId}"
                                                <c:forEach items="${data.userMenus}" var="menu" varStatus="menu_status">
                                                       <c:if test="${menu.menuId eq list.menuId}">checked</c:if>
                                                </c:forEach>
                                                >
                                                ${list.menuNm}
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="text-bold">고객센터</td>
                                        <td colspan="2">|
                                            <c:forEach items="${menu.menuList4}" var="list">
                                                <input type="checkbox" name="menuId" value="${list.menuId}"
                                                <c:forEach items="${data.userMenus}" var="menu" varStatus="menu_status">
                                                       <c:if test="${menu.menuId eq list.menuId}">checked</c:if>
                                                </c:forEach>
                                                >
                                                ${list.menuNm}
                                            </c:forEach>
                                        </td>
                                    </tr>

                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 text-right">
                    <button name="btnCancel" type="button" class="btn btn-info btn-group-sm" data-id="${ data.uid }" >취소</button>
                    <button name="btnRegist" type="submit" class="btn btn-info btn-group-sm" >등록</button>
                </div>
            </div>
            </form>

        </section>
        </div>
    <script src="/common/js/js/common.js" type="text/javascript"></script>
    <script type="text/javascript">

        var type = '${type}';

        $('#form').on('submit', function () {
/*

            if(!valCheck('#indDesc', 'Industries 소개를 입력해주세요')){
                return false;
            }

            if(!valCheck('#partnerImgUrl1', '파트너 로고 파일을 첨부해주세요.')){
                return false;
            }
*/
            var reqData = new FormData(this);

            $.ajax({
                url : '/user/insert',
                async: true,
                cache: false,
                contentType: 'application/json',
                type : type,
                data: reqData,
                processData: false,
                contentType: false,
                success : function(data){
                }, error : function(error){

                }
            }).done(function (result) {

                if (result.status == 'fail') {
                    alert('등록하지 못했습니다.[' + result.errorMsg + ']\n반복 시 관리자에게 문의 바랍니다.');
                    return false;
                }

                alert('등록 되었습니다.');
                if(type == 'POST'){
                    location.href = "/user/list/";
                }else{
                    location.href = "/user/detail/"+result.uid;
                }

            }).fail(function(xhr, textStatus, errorThrown) {
                if(xhr.status =='403'){
                    alert("해당 기능에 대한 권한이 없습니다.");
                }
            });

            return false;
        });

        /* Validation Check */
        function valCheck(id, msg){
            if ($(id).val().trim() == '') {
                alert(msg);
                $(id).focus();
                return false;
            }
            return true;
        }

        $('button[name=btnCancel]').on('click', function () {
            if(type == 'POST'){
                location.href = "/user/list/";
            }else{
                location.href = "/user/detail/"+$(this).attr('data-id');
            }
        });

    </script>

</body>

