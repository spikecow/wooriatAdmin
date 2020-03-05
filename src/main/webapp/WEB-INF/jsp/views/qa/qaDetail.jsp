<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<% pageContext.setAttribute("lineChar", "\n"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>고객문의</title>
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
                <h1>고객문의</h1>
            </section>
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-body table-responsive no-padding">

                            <table class="table table-bordered">
                            	<colgroup>
                            		<col width="20%">
                            		<col width="30%">
                                    <col width="20%">
                                    <col width="30%">
                            	</colgroup>

                                <tbody>
                                    <tr>
                                        <th class="text-center">상태</th>
                                        <td colspan="3">
                                            <c:if test="${data.answers.size() ne 0}">답변완료</c:if>
                                            <c:if test="${data.answers.size() eq 0}">문의중</c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">이름</th>
                                        <td>
                                           ${data.name}
                                        </td>
                                        <th class="text-center">등록일</th>
                                        <td>
                                            <fmt:parseDate value="${ data.cretDtm }" pattern="yyyy-MM-dd'T'HH:mm" var="cretDtm" type="both" />
                                            <fmt:formatDate pattern="yyyy.MM.dd" value="${ cretDtm }" />
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">이메일</th>
                                        <td>
                                            ${data.email}
                                        </td>
                                        <th class="text-center">전화번호</th>
                                        <td>
                                            ${data.tel}
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">제목</th>
                                        <td colspan="3">
                                            ${data.title}
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">내용</th>
                                        <td colspan="3">
                                            ${fn:replace(data.content, lineChar, "<br/>")}
                                        </td>
                                    </tr>

                            </table>
                        </div>
                    </div>

                </div>
            </div>

            <form id="form">
                <input type="hidden" name="qid" id="qid" value="${data.qid}"/>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">

                            <div class="box-body table-responsive no-padding">
                                <table class="table table-bordered">
                                    <colgroup>
                                        <col width="20%">
                                        <col width="30%">
                                        <col width="20%">
                                        <col width="30%">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th colspan="4"><b>※ 답변</b></th>
                                        </tr>
                                        <tr>
                                            <th class="text-center">담당자명</th>
                                            <td>${sessionVo.userNm}</td>
                                            <th class="text-center">담당자 팀명</th>
                                            <td>${sessionVo.deptNm}</td>
                                        </tr>
                                        <tr>
                                            <th class="text-center" style="vertical-align: middle">답변내용</th>
                                            <td colspan="3">
                                                <textarea name="content" id="content" class="form-control" row="10" style="resize: none; height: 150px"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" class="text-right">
                                                ※ 담당자명과 담당자 팀명은 고객님께 노출되지 않습니다.
                                                <button type="submit" name="btnInsert" class="btn btn-app btn-sm">답변 메일보내기</button>
                                            </td>

                                        </tr>
                                    </tbody>
                                </table>

                                <c:if test="${data.answers.size() ne 0}">
                                <table style="background-color: #f9f9f9;" border="0" class="table table-bordered">
                                    <colgroup>
                                        <col width="20%">
                                        <col width="30%">
                                        <col width="20%">
                                        <col width="30%">
                                    </colgroup>
                                    <tr>
                                        <th colspan="4"><b>※ 기존답변</b></th>
                                    </tr>
                                    <tr>
                                        <td colspan="4" style="padding: 1px;background-color: #bbbbbb"></td>
                                    </tr>
                                    <c:forEach items="${data.answers}" var="answersList">
                                    <tr>
                                        <td>${answersList.userInfo.userNm} (${answersList.userInfo.deptNm})</td>
                                        <td>
                                            <fmt:parseDate value="${ answersList.cretDtm }" pattern="yyyy-MM-dd'T'HH:mm" var="cretDtm" type="both" />
                                            <fmt:formatDate pattern="yyyy.MM.dd" value="${ cretDtm }" />
                                        </td>
                                        <td colspan="2" class="text-right">
                                            <button type="button" name="btnAnswerDelete" class="btn btn-default btn-sm" data-id="${ answersList.aid }">삭제</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            ${fn:replace(answersList.content, lineChar, "<br/>")}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" style="padding: 1px;background-color: #bbbbbb"></td>
                                    </tr>
                                    </c:forEach>
                                </table>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

            </form>

            <div class="row">
                <div class="col-xs-6 text-left">
                    <button name="btnDelete" type="button" class="btn btn-info btn-group-sm" data-id="${ data.qid }" >삭제</button>
                </div>
                <div class="col-xs-6 text-right">
                    <button name="btnList" type="button" class="btn btn-info btn-group-sm" >목록</button>
                </div>
            </div>

        </section>
        </div>
    <script src="/common/js/js/common.js" type="text/javascript"></script>
    <script type="text/javascript">

        $('button[name=btnDelete]').on('click', function () {

            if(!confirm("고객님께서 문의하신 정보까지 모두 삭제됩니다. 정말 삭제하시겠습니까?")){
                return false;
            }

            $.ajax({
                url : '/qa/delete/question/'+$(this).attr('data-id'),
                async: true,
                cache: false,
                contentType: 'application/json',
                type : 'DELETE',
                processData: false,
                contentType: false,
                success : function(data){
                }, error : function(error){

                }
            }).done(function (result) {

                if (result.status == 'fail') {
                    alert('삭제하지 못했습니다.[' + result.errorMsg + ']\n반복 시 관리자에게 문의 바랍니다.');
                    return false;
                }

                alert('삭제 되었습니다.');
                location.href = "/qa/list";
            }).fail(function(xhr, textStatus, errorThrown) {
                if(xhr.status =='403'){
                    alert("해당 기능에 대한 권한이 없습니다.");
                }
            });
        });

        $('button[name=btnAnswerDelete]').on('click', function () {

            if(!confirm("선택하신 답변이 삭제됩니다. 정말 삭제하시겠습니까?")){
                return false;
            }

            $.ajax({
                url : '/qa/delete/answer/'+$(this).attr('data-id'),
                async: true,
                cache: false,
                contentType: 'application/json',
                type : 'DELETE',
                processData: false,
                contentType: false,
                success : function(data){
                }, error : function(error){

                }
            }).done(function (result) {

                if (result.status == 'fail') {
                    alert('삭제하지 못했습니다.[' + result.errorMsg + ']\n반복 시 관리자에게 문의 바랍니다.');
                    return false;
                }

                alert('삭제 되었습니다.');
                location.reload();
            }).fail(function(xhr, textStatus, errorThrown) {
                if(xhr.status =='403'){
                    alert("해당 기능에 대한 권한이 없습니다.");
                }
            });
        });

        var flag = true;
        $('#form').on('submit', function () {

            if(!valCheck('#content', '답변내용을 입력해주세요')){
                return false;
            }

            if(flag){
                flag = false;
            }else{
                alert('답변 등록 및 메일전송 중입니다.');
                return false;
            }

            var reqData = new FormData(this);

            $.ajax({
                url : '/qa/insert',
                async: true,
                cache: false,
                contentType: 'application/json',
                type : 'POST',
                data: reqData,
                processData: false,
                contentType: false,
                success : function(data){
                }, error : function(error){

                }
            }).done(function (result) {
                flag = true;
                if (result.status == 'fail') {
                    alert('등록하지 못했습니다.[' + result.errorMsg + ']\n반복 시 관리자에게 문의 바랍니다.');
                    return false;
                }

                alert('등록 되었습니다.');
                location.href = "/qa/detail/"+result.qid;

            }).fail(function(xhr, textStatus, errorThrown) {
                flag = true;
                if(xhr.status =='403'){
                    alert("해당 기능에 대한 권한이 없습니다.");
                }
            });

            return false;
        });

        $('button[name=btnList]').on('click', function () {
            location.href = "/qa/list";
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

    </script>

</body>

