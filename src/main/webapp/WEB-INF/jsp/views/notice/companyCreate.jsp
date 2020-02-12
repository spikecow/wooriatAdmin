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
            <h1>회사소식 관리</h1>
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
                                            <th class="text-center">제목</th>
                                            <td>
                                                <input type="text" name="title" id="title" class="form-control" value="${data.title}"/>
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
                                            <th class="text-center">내용</th>
                                            <td>
                                                <textarea name="content" id="content" style="width:100%; height:280px; display:none;"></textarea>
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
                        <button name="btnCancel" type="button" class="btn btn-info btn-group-sm" data-id="${ data.seqNo }" >취소</button>
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

            $("#regDateInput").datepicker();

            $('#dateIcon1').on('click', function () {
                $("#regDateInput").datepicker().datepicker("show");
            });

            createSE2('${data.content}');

        });

        var type = '${type}';

        $('#form').on('submit', function () {

            if(!valCheck('#title', '제목을 입력해 주세요.')){
                return false;
            }

            if(!valCheck('#regDateInput', '등록일을 입력해 주세요.')){
                return false;
            }

            var reqData = new FormData(this);

            $.ajax({
                url : '/notice/insert',
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
                    location.href = "/notice/list?menuCd=${menuCd}";
                }else{
                    location.href = "/notice/updateForm/" + result.seqNo +"/${menuCd}";
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
            location.href = "/notice/list?menuCd=${menuCd}";
        });

        $("input[type=file]").on('change', function() {
            fileNameAtTarget(this);
        });

        var oEditors = [];

        var sLang = "ko_KR";	// 언어 (ko_KR/ en_US/ ja_JP/ zh_CN/ zh_TW), default = ko_KR
        var isSECreate = false;
        // 추가 글꼴 목록
        // var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
        var aAdditionalFontSet = [['Noto Sans KR', 'sans-serif']];

        function createSE2(data){

            nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "content",
                sSkinURI: "/se2/SmartEditor2Skin.html",
                htParams : {
                    SE_EditingAreaManager: {
                        sDefaultEditingMode : "WYSIWYG"	// 로딩 시 디폴트 편집 모드를 설정 Editor로 설정
                    },
                    bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
                    bUseVerticalResizer : false,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
                    bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
                    bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
                    aAdditionalFontList : [['Noto Sans KR', 'sans-serif']],		// 추가 글꼴 목록
                    fOnBeforeUnload : function(){
                        //alert("완료!");
                    },
                    I18N_LOCALE : sLang
                },
                fOnAppLoad : function(){
                    $("iframe").css("height", "350px");
                    isSECreate = true;

                    if(data != null && data != ''){
                        $('textarea#content').val(safeTagToHtmlTag(data));
                        oEditors.getById["popupText"].exec("LOAD_CONTENTS_FIELD");
                    }

                },
                fCreator: "createSEditor2"
            });
        };


    </script>

</body>

