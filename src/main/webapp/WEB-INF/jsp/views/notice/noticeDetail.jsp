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
    <title>우리자산신탁</title>
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
            <h1>경영현황</h1>
        </section>
        <!-- Main content -->
        <section class="content">
            <form id="form" enctype="multipart/form-data">
                <input type="hidden" name="menuCd" value="${menuCd}" />
                <input type="hidden" name="seqNo" value="${data.seqNo}" />
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-body table-responsive no-padding">

                                <table class="table table-bordered">
                                    <colgroup>
                                        <col width="15%">
                                        <col width="*">
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <th class="text-center">분류</th>
                                        <td>
                                           <c:if test="${data.typeCd eq '01'.toString()}">수시공시</c:if>
                                           <c:if test="${data.typeCd eq '02'.toString()}">영업보고</c:if>
                                           <c:if test="${data.typeCd eq '03'.toString()}">영업순자본비</c:if>
                                           <c:if test="${data.typeCd eq '04'.toString()}">감사보고서</c:if>
                                           <c:if test="${data.typeCd eq '05'.toString()}">경영공시</c:if>
                                           <c:if test="${data.typeCd eq '06'.toString()}">약관공시</c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">제목</th>
                                        <td>
                                           ${data.title}
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">등록일</th>
                                        <td>
                                            ${data.regDateInput}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="text-center">파일첨부</th>
                                        <td>
                                            <a href="http://images.wooriat.com/Government/${data.img}" target="_blank">${data.img}</a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6 text-left">
                        <button name="btnDelete" type="button" class="btn btn-info btn-group-sm" data-id="${ data.seqNo }" >삭제</button>
                    </div>
                    <div class="col-xs-6 text-right">
                        <button name="btnCreate" type="button" class="btn btn-info btn-group-sm" data-id="${ data.seqNo }" >수정</button>
                        <button name="btnList" type="button" class="btn btn-info btn-group-sm" >목록</button>
                    </div>
                </div>
            </form>

        </section>
    </div>
    <script src="/common/js/js/common.js" type="text/javascript"></script>
        <script type="text/javascript">

            $('button[name=btnDelete]').on('click', function () {
                if(!confirm("정말 삭제하시겠습니까?")){
                    return false;
                }

                $.ajax({
                    url : '/notice/delete/'+$(this).attr('data-id'),
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
                    location.href = "/notice/list?menuCd=${menuCd}";
                }).fail(function(xhr, textStatus, errorThrown) {
                    if(xhr.status =='403'){
                        alert("해당 기능에 대한 권한이 없습니다.");
                    }
                });
            });

            $('button[name=btnCreate]').on('click', function () {
                location.href = "/notice/updateForm/"+$(this).attr('data-id')+"/${menuCd}";
            });

            $('button[name=btnList]').on('click', function () {
                location.href = "/notice/list?menuCd=${menuCd}";
            });
        </script>

</body>

