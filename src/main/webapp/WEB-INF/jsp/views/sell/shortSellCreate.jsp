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
<title>공매물건정보 (등록)</title>
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
<script src="/plugins/jQuery/jquery.form.min.js"></script>
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
                <h1>공매물건정보 (상세)</h1>
            </section>
        <!-- Main content -->
        <section class="content">
            <form id="form" enctype="multipart/form-data" action="/ShortSell/${type}" method="POST" accept-charset="UTF-8">
                <input type="hidden" name="sellId" id="sellId" value="${data.sellId}"/>
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-body table-responsive no-padding">

                            <table class="table table-bordered">
                            	<colgroup>
                            		<col width="35%">
                            		<col width="65%">
                            	</colgroup>

                                <tbody>
                                    <tr>
                                        <th class="text-center">제목</th>
                                        <td>
                                            <input type="text" name="newsTitle" id="newsTitle" class="form-control" value="${data.newsTitle}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">등록일</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="regDateInput" id="regDateInput" class="form-control" value="${data.regDateInput}"/>
                                                <span id="dateIcon1" class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">진행상황</th>
                                        <td>
                                            <select class="form-control" name="sortStatus">
                                                <option value="진행" <c:if test="${data.sortStatus eq '진행'}">selected</c:if> >진행</option>
                                                <option value="중지" <c:if test="${data.sortStatus eq '중지'}">selected</c:if>>중지</option>
                                                <option value="취소" <c:if test="${data.sortStatus eq '취소'}">selected</c:if>>취소</option>
                                                <option value="낙찰" <c:if test="${data.sortStatus eq '낙찰'}">selected</c:if>>낙찰</option>
                                                <option value="일부낙찰" <c:if test="${data.sortStatus eq '일부낙찰'}">selected</c:if>>일부낙찰</option>
                                                <option value="공지" <c:if test="${data.sortStatus eq '공지'}">selected</c:if>>공지</option>
                                                <option value="유찰" <c:if test="${data.sortStatus eq '유찰'}">selected</c:if>>유찰</option>
                                                <option value="종료" <c:if test="${data.sortStatus eq '종료'}">selected</c:if>>종료</option>
                                                <option value="수의계약진행" <c:if test="${data.sortStatus eq '수의계약진행'}">selected</c:if>>수의계약진행</option>
                                                <option value="수의계약완료" <c:if test="${data.sortStatus eq '수의계약완료'}">selected</c:if>>수의계약완료</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">PDF 파일첨부</th>
                                        <td>
                                            <div style="width: 100%;">
                                                <input type="file" id="imgFile1" name="imgFile1" tabindex="-1" style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
                                                <div class="bootstrap-filestyle input-group file_style">
                                                    <input type="text" class="form-control input-sm" placeholder="Select your file"/>
                                                    <span class="group-span-filestyle input-group-btn" tabindex="0">
                                                    <label for="imgFile1" class="btn btn-info text-info btn-sm btn-file">
                                                        <span class="buttonText"> 찾아보기</span>
                                                    </label>
                                                    </span>
                                                </div>
                                            </div>
                                            <c:if test="${ not empty data.insertFile1}">
                                                <input type="hidden" name="insertFile1" value="${data.insertFile1}"/>
                                                <div>${data.insertFile1}</div>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">첨부2</th>
                                        <td>
                                            <div style="width: 100%;">
                                                <input type="file" id="imgFile2" name="imgFile2" tabindex="-1" style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
                                                <div class="bootstrap-filestyle input-group file_style">
                                                    <input type="text" class="form-control input-sm" placeholder="Select your file"/>
                                                    <span class="group-span-filestyle input-group-btn" tabindex="0">
                                                    <label for="imgFile2" class="btn btn-info text-info btn-sm btn-file">
                                                        <span class="buttonText"> 찾아보기</span>
                                                    </label>
                                                    </span>
                                                </div>
                                            </div>
                                            <c:if test="${ not empty data.insertFile2}">
                                                <input type="hidden" name="insertFile2" value="${data.insertFile2}"/>
                                                <div>${data.insertFile2}</div>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">첨부3</th>
                                        <td>
                                            <div style="width: 100%;">
                                                <input type="file" id="imgFile3" name="imgFile3" tabindex="-1" style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
                                                <div class="bootstrap-filestyle input-group file_style">
                                                    <input type="text" class="form-control input-sm" placeholder="Select your file"/>
                                                    <span class="group-span-filestyle input-group-btn" tabindex="0">
                                                    <label for="imgFile3" class="btn btn-info text-info btn-sm btn-file">
                                                        <span class="buttonText"> 찾아보기</span>
                                                    </label>
                                                    </span>
                                                </div>
                                            </div>
                                            <c:if test="${ not empty data.insertFile3}">
                                                <input type="hidden" name="insertFile3" value="${data.insertFile3}"/>
                                                <div>${data.insertFile3}</div>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">첨부4</th>
                                        <td>
                                            <div style="width: 100%;">
                                                <input type="file" id="imgFile4" name="imgFile4" tabindex="-1" style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
                                                <div class="bootstrap-filestyle input-group file_style">
                                                    <input type="text" class="form-control input-sm" placeholder="Select your file"/>
                                                    <span class="group-span-filestyle input-group-btn" tabindex="0">
                                                    <label for="imgFile4" class="btn btn-info text-info btn-sm btn-file">
                                                        <span class="buttonText"> 찾아보기</span>
                                                    </label>
                                                    </span>
                                                </div>
                                            </div>
                                            <c:if test="${ not empty data.insertFile4}">
                                                <input type="hidden" name="insertFile4" value="${data.insertFile4}"/>
                                                <div>${data.insertFile4}</div>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">첨부5</th>
                                        <td>
                                            <div style="width: 100%;">
                                                <input type="file" id="imgFile5" name="imgFile5" tabindex="-1" style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
                                                <div class="bootstrap-filestyle input-group file_style">
                                                    <input type="text" class="form-control input-sm" placeholder="Select your file"/>
                                                    <span class="group-span-filestyle input-group-btn" tabindex="0">
                                                    <label for="imgFile5" class="btn btn-info text-info btn-sm btn-file">
                                                        <span class="buttonText"> 찾아보기</span>
                                                    </label>
                                                    </span>
                                                </div>
                                            </div>
                                            <c:if test="${ not empty data.insertFile5}">
                                                <input type="hidden" name="insertFile5" value="${data.insertFile5}"/>
                                                <div>${data.insertFile5}</div>
                                            </c:if>
                                        </td>
                                    </tr>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 text-right">
                    <button id="btnCancel" type="button" class="btn btn-info btn-group-sm" data-id="${ data.sellId }" >취소</button>
                    <button id="btnRegist" type="submit" class="btn btn-info btn-group-sm" >등록</button>
                </div>
            </div>

            </form>

        </section>
        </div>
    <script src="/common/js/js/common.js" type="text/javascript"></script>
    <script type="text/javascript">
        $( function() {
            $.datepicker.setDefaults({
                dateFormat: 'yy-mm-dd'
                , monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'] //달력의 월 부분 텍스트
                , monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] //달력의 월 부분 Tooltip 텍스트
                , dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] //달력의 요일 부분 텍스트
            });

            $("#regDateInput").datepicker();

            $('#dateIcon1').on('click', function () {
                $("#regDateInput").datepicker().datepicker("show");
            });

        });

        var type = '${type}';

        $("#form").ajaxForm({
            dataType:'json',
            cache: false,
            processData: false,
            contentType: false,
            success : function(result) {

                if (result.status == 'fail') {
                    alert('등록하지 못했습니다.[' + result.errorMsg + ']\n반복 시 관리자에게 문의 바랍니다.');
                    return false;
                }

                alert('등록 되었습니다.');
                if(type == 'update'){
                    location.href = "/ShortSell/detail/"+result.sellId;
                }else{
                    location.href = "/ShortSell/list/";
                }
            },
            error : function(jqXHR) {
                console.log(jqXHR);
                alert('error');
            }

        });
/*
        $('#form').on('submit', function () {

            if(!valCheck('#newsTitle', '제목을 입력해주세요')){
                return false;
            }

            if(!valCheck('#regDateInput', '등록일을 입력해주세요')){
                return false;
            }




            var reqData = new FormData(this);

            $.ajax({
                url : '/ShortSell/insert',
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
                    location.href = "/ShortSell/list/";
                }else{
                    location.href = "/ShortSell/detail/"+result.sellId;
                }

            }).fail(function(xhr, textStatus, errorThrown) {
                if(xhr.status =='403'){
                    alert("해당 기능에 대한 권한이 없습니다.");
                }
            });


            return false;
        });*/

        /* Validation Check */
        function valCheck(id, msg){
            if ($(id).val().trim() == '') {
                alert(msg);
                $(id).focus();
                return false;
            }
            return true;
        }

        $('button[id=btnCancel]').on('click', function () {
            //if(type == 'POST'){
                location.href = "/ShortSell/list/";
            /*}else{
                location.href = "/ShortSell/detail/"+$(this).attr('data-id');
            }*/
        });

        $("input[type=file]").on('change', function() {
            fileNameAtTarget(this);
        });

    </script>

</body>

