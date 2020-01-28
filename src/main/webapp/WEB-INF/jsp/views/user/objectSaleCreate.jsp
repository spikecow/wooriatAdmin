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
<title>분양물건정보 (등록)</title>
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
                <h1>분양물건정보 (등록)</h1>
            </section>
        <!-- Main content -->
        <section class="content">
            <form id="mainForm" enctype="multipart/form-data">
            <div class="row">
                <div class="col-xs-12">
                    <h4 class="modal-title" >※ 기본정보</h4>
                    <div class="box">
                        <div class="box-body table-responsive no-padding">

                            <table class="table table-bordered">
                            	<colgroup>
                            		<col width="15%">
                            		<col width="35%">
                                    <col width="15%">
                                    <col width="35%">
                            	</colgroup>

                                <tbody>
                                    <tr>
                                        <th class="text-center">사업명</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value=""/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">담당팀</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value=""/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">사업상태</th>
                                        <td colspan="3">
                                            <div class="radio">
                                                <label for="rg"> <input type="radio" id="rg" name="null" value="" > 진행 </label>
                                                <label for="rs"> <input type="radio" id="rs" name="null" value=""> 완료 </label>
                                                <label for="rw"> <input type="radio" id="rw" name="null" value=""> 예정 </label>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">분양방식</th>
                                        <td colspan="3">
                                            <select class="form-control" name="" >
                                                <option value="">분양관리신탁</option>
                                                <option value="">관리형토지신탁</option>
                                                <option value="">일반분양</option>
                                                <option value="">선착순분양</option>
                                                <option value="">공개추첨</option>
                                                <option value="">공개추점후수의분양</option>
                                                <option value="">대리사무/담보신탁</option>
                                                <option value="">관리신탁/자금관리</option>
                                                <option value="">차입형토지신탁</option>
                                                <option value="">분양관리신탁</option>
                                                <option value="">기타</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">사업종류</th>
                                        <td colspan="3">
                                            <select class="form-control"  name="">
                                                <option value="">아파트</option>
                                                <option value="">아파트형공장</option>
                                                <option value="">오피스텔</option>
                                                <option value="">오피스텔/상가</option>
                                                <option value="">오피스/상가</option>
                                                <option value="">도시형생활주택</option>
                                                <option value="">도시형생활주택/오피스텔</option>
                                                <option value="">주상복합</option>
                                                <option value="">골프장</option>
                                                <option value="">상가</option>
                                                <option value="">타운하우스</option>
                                                <option value="">리조트</option>
                                                <option value="">토지</option>
                                                <option value="">지식산업센터</option>
                                                <option value="">아파트/오피스텔</option>
                                                <option value="">생활숙박시설</option>
                                                <option value="">오피스텔/오피스</option>
                                                <option value="">기타</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">분양시기</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" id="date1" class="form-control" value=""/>
                                                <span id="dateIcon1" class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </td>
                                        <th class="text-center">입주시기</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" id="date2" name="" class="form-control" value=""/>
                                                <span id="dateIcon2" class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">위치</th>
                                        <td colspan="3">
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">기타 :</span>
                                                <input type="text" name="" class="form-control col-xs-3" id="inputdefault" value=""/>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">시공사</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value=""/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">위탁사</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value=""/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">금융사</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value=""/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">건폐율</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                        <th class="text-center">용적률</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">건축면적</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">m²</span>
                                            </div>
                                        </td>
                                        <th class="text-center">대지면적</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">m²</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">연면적</th>
                                        <td colspan="3">
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">m²</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">사업규모</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value=""/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">총세대수</th>
                                        <td colspan="3">
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">세대</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">분양세대수</th>
                                        <td colspan="3">
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">세대</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">주차대수</th>
                                        <td colspan="3">
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">대</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">난방방식</th>
                                        <td colspan="3">
                                            <select class="form-control" name="">
                                                <option value="">지역난방</option>
                                                <option value="">개별난방</option>
                                                <option value="">중앙난방</option>
                                                <option value="">기타</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">구조</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value=""/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">연락처</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value=""/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">사업자홈페이지</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value="" placeholder="http:// 또는 https:// 를 포함하여 입력"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">시행사홈페이지</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value="" placeholder="http:// 또는 https:// 를 포함하여 입력"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">건설사홈페이지</th>
                                        <td colspan="3">
                                            <input type="text" name="" class="form-control" value="" placeholder="http:// 또는 https:// 를 포함하여 입력"/>
                                        </td>
                                    </tr>

                                </tbody>
                            </table>

                        </div>
                    </div>

                    <h4 class="modal-title" >※ 공정률</h4>
                    <div class="box">
                        <div class="box-body table-responsive no-padding">

                            <table class="table table-bordered">
                                <colgroup>
                                    <col width="15%">
                                    <col width="35%">
                                    <col width="15%">
                                    <col width="35%">
                                </colgroup>

                                <tbody>
                                    <tr>
                                        <th class="text-center">전체</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="number" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                        <th class="text-center">가설공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="number" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">건축공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="number" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                        <th class="text-center">토목공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="number" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">전기공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="number" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                        <th class="text-center">설비공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="number" name="" class="form-control col-xs-3" value=""/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <h4 class="modal-title" >※ 사진첨부 (jpg 또는 gif 파일만 가능)</h4>
                    <div class="box">
                        <div class="box-body table-responsive no-padding">

                            <table class="table table-bordered">
                                <colgroup>
                                    <col width="15%">
                                    <col width="35%">
                                    <col width="15%">
                                    <col width="35%">
                                </colgroup>

                                <tbody>
                                    <tr>
                                        <th class="text-center">목록이미지</th>
                                        <td colspan="3">
                                            <div style="width: 100%;">
                                            <input type="file" id="newTitlImgFile1" name="newTitlImgFile1" tabindex="-1" style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
                                            <div class="bootstrap-filestyle input-group file_style">
                                                <input type="text" id="newsTitlImgUrl" name="newsTitlImgUrl" class="form-control input-sm" placeholder="Select your file" />
                                                <span class="group-span-filestyle input-group-btn" tabindex="0">
                                                    <label for="newTitlImgFile1" class="btn btn-info text-info btn-sm btn-file">
                                                        <span class="buttonText"> 찾아보기</span>
                                                    </label>
                                                 </span>
                                                <span class="input-group-addon">* 215 X 153 사이즈 업로드</span>
                                            </div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">상세페이지 이미지</th>
                                        <td colspan="3">
                                            <div style="width: 100%;">
                                                <input type="file" id="newTitlImgFile2" name="newTitlImgFile2" tabindex="-1" style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
                                                <div class="bootstrap-filestyle input-group file_style">
                                                    <input type="text" id="newsTitlImgUrl2" name="newsTitlImgUrl2" class="form-control input-sm" placeholder="Select your file" />
                                                    <span class="group-span-filestyle input-group-btn" tabindex="0">
                                                    <label for="newTitlImgFile2" class="btn btn-info text-info btn-sm btn-file">
                                                        <span class="buttonText"> 찾아보기</span>
                                                    </label>
                                                    </span>
                                                    <span class="input-group-addon">* 775 X 475 사이즈 업로드</span>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <h4 class="modal-title" >※ 특정 및 기타내용</h4>
                    <div class="box">
                        <div class="box-body table-responsive no-padding">

                            <table class="table table-bordered">
                                <colgroup>
                                    <col width="100%">
                                </colgroup>

                                <tbody>
                                    <tr>
                                        <td>
                                            <textarea name='' class='form-control' row='10' style="resize: none; height: 150px"></textarea>
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
                    <button name="btnCancel" type="button" class="btn btn-info btn-group-sm" data-id="${ data.id }" >취소</button>
                    <button name="btnRegist" type="submit" class="btn btn-info btn-group-sm" >등록</button>
                </div>
            </div>

            </form>

        </section>
        </div>
    <script src="/common/js/js/common.js" type="text/javascript"></script>
    <script type="text/javascript">

        $( function() {
            $.datepicker.setDefaults({
                dateFormat: 'yy.mm.dd'
                , monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'] //달력의 월 부분 텍스트
                , monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] //달력의 월 부분 Tooltip 텍스트
                , dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] //달력의 요일 부분 텍스트
            });

            $("#date1").datepicker();
            $("#date2").datepicker();

            $('#dateIcon1').on('click', function () {
                $("#date1").datepicker().datepicker("show");
            });

            $('#dateIcon2').on('click', function () {
                $("#date2").datepicker().datepicker("show");
            });
        });


    </script>

</body>

