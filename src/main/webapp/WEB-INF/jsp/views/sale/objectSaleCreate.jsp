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
            <form id="form" enctype="multipart/form-data">
                <input type="hidden" name="saleId" id="saleId" value="${data.saleId}"/>
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
                                            <input type="text" name="bunName" id="bunName" class="form-control" value="${data.bunName}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">담당팀</th>
                                        <td colspan="3">
                                            <input type="text" name="team" id="team" class="form-control" value="${data.team}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">사업상태</th>
                                        <td colspan="3">
                                            <div class="radio">
                                                <label for="rg"> <input type="radio" id="rg" name="status" value="1" <c:if test="${data.status eq '1'.toString()}">checked</c:if>> 진행 </label>
                                                <label for="rs"> <input type="radio" id="rs" name="status" value="2" <c:if test="${data.status eq '2'.toString()}">checked</c:if>> 완료 </label>
                                                <label for="rw"> <input type="radio" id="rw" name="status" value="0" <c:if test="${data.status eq '0'.toString()}">checked</c:if>> 예정 </label>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">분양방식</th>
                                        <td colspan="3">
                                            <select class="form-control" name="bizType" id="bizType">
                                                <option value="1" <c:if test="${data.bizType eq '1'.toString()}">selected</c:if>>분양관리신탁</option>
                                                <option value="2" <c:if test="${data.bizType eq '2'.toString()}">selected</c:if>>관리형토지신탁</option>
                                                <option value="3" <c:if test="${data.bizType eq '3'.toString()}">selected</c:if>>일반분양</option>
                                                <option value="4" <c:if test="${data.bizType eq '4'.toString()}">selected</c:if>>선착순분양</option>
                                                <option value="5" <c:if test="${data.bizType eq '5'.toString()}">selected</c:if>>공개추첨</option>
                                                <option value="6" <c:if test="${data.bizType eq '6'.toString()}">selected</c:if>>공개추첨후수의분양</option>
                                                <option value="7" <c:if test="${data.bizType eq '7'.toString()}">selected</c:if>>대리사무/담보신탁</option>
                                                <option value="8" <c:if test="${data.bizType eq '8'.toString()}">selected</c:if>>관리신탁/자금관리</option>
                                                <option value="9" <c:if test="${data.bizType eq '9'.toString()}">selected</c:if>>차입형토지신탁</option>
                                                <option value="10" <c:if test="${data.bizType eq '10'.toString()}">selected</c:if>>기타</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">사업종류</th>
                                        <td colspan="3">
                                            <select class="form-control" name="bizCase" id="bizCase">
                                                <option value="A" <c:if test="${data.bizCase eq 'A'.toString()}">selected</c:if>>아파트</option>
                                                <option value="F" <c:if test="${data.bizCase eq 'F'.toString()}">selected</c:if>>아파트형공장</option>
                                                <option value="O" <c:if test="${data.bizCase eq 'O'.toString()}">selected</c:if>>오피스텔</option>
                                                <option value="P" <c:if test="${data.bizCase eq 'P'.toString()}">selected</c:if>>오피스텔/상가</option>
                                                <option value="M" <c:if test="${data.bizCase eq 'M'.toString()}">selected</c:if>>오피스/상가</option>
                                                <option value="D" <c:if test="${data.bizCase eq 'D'.toString()}">selected</c:if>>도시형생활주택</option>
                                                <option value="U" <c:if test="${data.bizCase eq 'U'.toString()}">selected</c:if>>도시형생활주택/오피스텔</option>
                                                <option value="J" <c:if test="${data.bizCase eq 'J'.toString()}">selected</c:if>>주상복합</option>
                                                <option value="G" <c:if test="${data.bizCase eq 'G'.toString()}">selected</c:if>>골프장</option>
                                                <option value="S" <c:if test="${data.bizCase eq 'S'.toString()}">selected</c:if>>상가</option>
                                                <option value="T" <c:if test="${data.bizCase eq 'T'.toString()}">selected</c:if>>타운하우스</option>
                                                <option value="R" <c:if test="${data.bizCase eq 'R'.toString()}">selected</c:if>>리조트</option>
                                                <option value="L" <c:if test="${data.bizCase eq 'L'.toString()}">selected</c:if>>토지</option>
                                                <option value="K" <c:if test="${data.bizCase eq 'K'.toString()}">selected</c:if>>지식산업센터</option>
                                                <option value="N" <c:if test="${data.bizCase eq 'N'.toString()}">selected</c:if>>아파트/오피스텔</option>
                                                <option value="Q" <c:if test="${data.bizCase eq 'Q'.toString()}">selected</c:if>>생활숙박시설</option>
                                                <option value="S">오피스텔/오피스</option>
                                                <option value="Z" <c:if test="${data.bizCase eq 'Z'.toString()}">selected</c:if>>기타</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">분양시기</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="bunDateInput" id="bunDateInput" class="form-control" value="${data.bunDateInput}"/>
                                                <span id="dateIcon1" class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </td>
                                        <th class="text-center">입주시기</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" id="ipDateInput" name="ipDateInput" class="form-control" value="${data.ipDateInput}"/>
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
                                                <input type="text" name="address" id="address" class="form-control col-xs-3" value="${data.address}"/>
                                                <span class="input-group-addon">기타 :</span>
                                                <input type="text" name="etcAddress" id="etcAddress" class="form-control col-xs-3" id="inputdefault" value="${data.etcAddress}"/>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">시공사</th>
                                        <td colspan="3">
                                            <input type="text" name="sgOffice" id="sgOffice" class="form-control" value="${data.sgOffice}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">위탁사</th>
                                        <td colspan="3">
                                            <input type="text" name="siOffice" id="siOffice" class="form-control" value="${data.siOffice}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">금융사</th>
                                        <td colspan="3">
                                            <input type="text" name="bankOffice" id="bankOffice" class="form-control" value="${data.bankOffice}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">건폐율</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="gunPer" id="gunPer" class="form-control col-xs-3" value="${data.gunPer}"/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                        <th class="text-center">용적률</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="yungPer" id="yungPer" class="form-control col-xs-3" value="${data.yungPer}"/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">건축면적</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="constArea" id="constArea" class="form-control col-xs-3" value="${data.constArea}"/>
                                                <span class="input-group-addon">m²</span>
                                            </div>
                                        </td>
                                        <th class="text-center">대지면적</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="landArea" id="landArea" class="form-control col-xs-3" value="${data.landArea}"/>
                                                <span class="input-group-addon">m²</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">연면적</th>
                                        <td colspan="3">
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="pyung" id="pyung" class="form-control col-xs-3" value="${data.pyung}"/>
                                                <span class="input-group-addon">m²</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">사업규모</th>
                                        <td colspan="3">
                                            <input type="text" name="scale" id="scale" class="form-control" value="${data.scale}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">총세대수</th>
                                        <td colspan="3">
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="totalSedae" id="totalSedae" class="form-control col-xs-3" value="${data.totalSedae}"/>
                                                <span class="input-group-addon">세대</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">분양세대수</th>
                                        <td colspan="3">
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="bunSedae" id="bunSedae" class="form-control col-xs-3" value="${data.bunSedae}"/>
                                                <span class="input-group-addon">세대</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">주차대수</th>
                                        <td colspan="3">
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="parkn" id="parkn" class="form-control col-xs-3" value="${data.parkn}"/>
                                                <span class="input-group-addon">대</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">난방방식</th>
                                        <td colspan="3">
                                            <select class="form-control" name="hotWay" id="hotWay">
                                                <option value="1" <c:if test="${data.hotWay eq '1'.toString()}">selected</c:if>>지역난방</option>
                                                <option value="2" <c:if test="${data.hotWay eq '2'.toString()}">selected</c:if>>개별난방</option>
                                                <option value="3" <c:if test="${data.hotWay eq '3'.toString()}">selected</c:if>>중앙난방</option>
                                                <option value="10" <c:if test="${data.hotWay eq '10'.toString()}">selected</c:if>>기타</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">구조</th>
                                        <td colspan="3">
                                            <input type="text" name="construction" id="construction" class="form-control" value="${data.construction}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">연락처</th>
                                        <td colspan="3">
                                            <input type="text" name="reqTel" id="reqTel" class="form-control" value="${data.reqTel}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">사업자홈페이지</th>
                                        <td colspan="3">
                                            <input type="text" name="homePage" id="homePage" class="form-control" value="${data.homePage}" placeholder="http:// 또는 https:// 를 포함하여 입력"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">시행사홈페이지</th>
                                        <td colspan="3">
                                            <input type="text" name="sigongHomePage" id="sigongHomePage" class="form-control" value="${data.sigongHomePage}" placeholder="http:// 또는 https:// 를 포함하여 입력"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">건설사홈페이지</th>
                                        <td colspan="3">
                                            <input type="text" name="constHomePage" id="constHomePage" class="form-control" value="${data.constHomePage}" placeholder="http:// 또는 https:// 를 포함하여 입력"/>
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
                                                <input type="text" name="progress6" id="progress6" class="form-control col-xs-3" value="${data.progress6}"/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                        <th class="text-center">가설공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="progress1" id="progress1" class="form-control col-xs-3" value="${data.progress1}"/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">건축공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="progress2" id="progress2" class="form-control col-xs-3" value="${data.progress2}"/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                        <th class="text-center">토목공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="progress3" id="progress3" class="form-control col-xs-3" value="${data.progress3}"/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">전기공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="progress4" id="progress4" class="form-control col-xs-3" value="${data.progress4}"/>
                                                <span class="input-group-addon">%</span>
                                            </div>
                                        </td>
                                        <th class="text-center">설비공사</th>
                                        <td>
                                            <div class="input-group col-xs-12" >
                                                <input type="text" name="progress5" id="progress5" class="form-control col-xs-3" value="${data.progress5}"/>
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
                                            <input type="hidden" name="imgTitle1" value="리스트용 이미지">
                                            <div style="width: 100%;">
                                            <input type="file" id="imgFile1" name="imgFile1" tabindex="-1" style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
                                            <div class="bootstrap-filestyle input-group file_style">
                                                <input type="text" id="nPhoto1" class="form-control input-sm" placeholder="Select your file" />
                                                <span class="group-span-filestyle input-group-btn" tabindex="0">
                                                    <label for="imgFile1" class="btn btn-info text-info btn-sm btn-file">
                                                        <span class="buttonText"> 찾아보기</span>
                                                    </label>
                                                 </span>
                                                <span class="input-group-addon">* 215 X 153 사이즈 업로드</span>
                                            </div>
                                            </div>
                                            <c:if test="${ not empty data.NPhoto1}">
                                                <input type="hidden" name="nPhoto1" value="${data.NPhoto1}"/>
                                                <div>${data.NPhoto1}</div>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="text-center">상세페이지 이미지</th>
                                        <td colspan="3">
                                            <input type="hidden" name="imgTitle2" value="조감도">
                                            <div style="width: 100%;">
                                                <input type="file" id="imgFile2" name="imgFile2" tabindex="-1" style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
                                                <div class="bootstrap-filestyle input-group file_style">
                                                    <input type="text" id="nPhoto2" class="form-control input-sm" placeholder="Select your file"/>
                                                    <span class="group-span-filestyle input-group-btn" tabindex="0">
                                                    <label for="imgFile2" class="btn btn-info text-info btn-sm btn-file">
                                                        <span class="buttonText"> 찾아보기</span>
                                                    </label>
                                                    </span>
                                                    <span class="input-group-addon">* 775 X 475 사이즈 업로드</span>
                                                </div>
                                            </div>
                                            <c:if test="${ not empty data.NPhoto2}">
                                                <input type="hidden" name="nPhoto2" value="${data.NPhoto2}"/>
                                                <div>${data.NPhoto2}</div>
                                            </c:if>
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
                                            <textarea name="memo" id="memo" class="form-control" row="10" style="resize: none; height: 150px">${data.memo}</textarea>
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
                    <button name="btnCancel" type="button" class="btn btn-info btn-group-sm" data-id="${ data.saleId }" >취소</button>
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
                dateFormat: 'yy-mm-dd'
                , monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'] //달력의 월 부분 텍스트
                , monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] //달력의 월 부분 Tooltip 텍스트
                , dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] //달력의 요일 부분 텍스트
            });

            $("#bunDateInput").datepicker();
            $("#ipDateInput").datepicker();

            $('#dateIcon1').on('click', function () {
                $("#bunDateInput").datepicker().datepicker("show");
            });

            $('#dateIcon2').on('click', function () {
                $("#ipDateInput").datepicker().datepicker("show");
            });
        });

        var type = '${type}';

        $('#form').on('submit', function () {

            if(!valCheck('#bunName', '사업명을 입력해주세요')){
                return false;
            }

            if(!numCheck('#gunPer', '숫자형식이 아닙니다.')){
                return false;
            }

            if(!numCheck('#yungPer', '숫자형식이 아닙니다.')){
                return false;
            }

            if(!numCheck('#constArea', '숫자형식이 아닙니다.')){
                return false;
            }

            if(!numCheck('#landArea', '숫자형식이 아닙니다.')){
                return false;
            }

            if(!numCheck('#pyung', '숫자형식이 아닙니다.')){
                return false;
            }

            if(!numCheck('#progress6', '숫자형식이 아닙니다.')){
                return false;
            }

            if(!numCheck('#progress1', '숫자형식이 아닙니다.')){
                return false;
            }
            if(!numCheck('#progress2', '숫자형식이 아닙니다.')){
                return false;
            }
            if(!numCheck('#progress3', '숫자형식이 아닙니다.')){
                return false;
            }
            if(!numCheck('#progress4', '숫자형식이 아닙니다.')){
                return false;
            }
            if(!numCheck('#progress5', '숫자형식이 아닙니다.')){
                return false;
            }

            var reqData = new FormData(this);

            $.ajax({
                url : '/SaleItem/insert',
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
                    location.href = "/SaleItem/list/";
                }else{
                    location.href = "/SaleItem/detail/"+result.saleId;
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

        /* Validation Check */
        function numCheck(id, msg){
            var regex = /^[0-9]+(\.[0-9]+)?$/g;
            if($(id).val() != ''){
                if( !regex.test($(id).val()) ){
                    alert(msg);
                    $(id).focus();
                    return false;
                }
            }
            return true;
        }

        $('button[name=btnCancel]').on('click', function () {
            //if(type == 'POST'){
                location.href = "/SaleItem/list/";
            /*}else{
                location.href = "/SaleItem/detail/"+$(this).attr('data-id');
            }*/
        });

        $("input[type=file]").on('change', function() {
            fileNameAtTarget(this);
        });


    </script>

</body>

