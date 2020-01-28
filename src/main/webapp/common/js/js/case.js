var prKRList;
var prENList;

var indKRList;
var indENList;

var svcKRList;
var svcENList;

$(document).ready(function(){

    /*검색*/
    $('form#reqForm button').on('click', function () {
        if ($(this).data('type') == 'excel') {
            $('form#reqForm').attr('action', document.location.pathname + '/download');
        }else{
            $('form#reqForm').attr('action', document.location.pathname);
        }

        if (prevSearchWord != $('input[name=searchWord]').val()) {
            $('#reqForm input[name=page]').val(0);
            $('#reqForm input[name=size]').val(10);
        }
        $('form#reqForm').submit();
    });

    /*
    * 구축사례 전체 선택
    * */
    $('input[name=selectAll]').on('click', function () {
        var checked = $(this).prop('checked');
        $('input[name=caseSelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });

    /*구축사례 등록*/
    $('#caseForm').on('submit', function (event) {
        event.preventDefault();

        $("#langCdKr, #langCdEn").attr("disabled", false);

        if ($('#caseNm').val().trim() == '') {
            alert('구축사례 타이틀을 입력해주세요.');
            $('#caseNm').focus();
            return false;
        }

        if ($('#strtDt').val().trim() == '') {
            alert('구축 시작일을 지정해주세요.');
            $('#strtDt').focus();
            return false;
        }

        if ($('#endDt').val().trim() == '') {
            alert('구축 종료일을 지정해주세요.');
            $('#endDt').focus();
            return false;
        }

        if ($('#custNm').val().trim() == '') {
            alert('고객사를 입력해주세요.');
            $('#custNm').focus();
            return false;
        }

        if ($('#imageUrlCustLogo').val().trim() == '') {
            alert('고객사 로고를 입력해주세요.');
            $('#imageUrlCustLogo').focus();
            return false;
        }

        //산업군 선택 여부 체크
        if(!$('input:radio[name=indSeqId]').is(':checked')){
            alert('산업군을 선택해주세요.');
            return false;
        }

        //구축사례 배경 이미지 체크
        if ($('#caseBgImgUrl').val().trim() == '') {
            alert('구축사례 배경 이미지를 업로드해주세요.');
            $('#caseBgImgUrl').focus();
            return false;
        }

        if ($('#summary').val().trim() == '') {
            alert('프로젝트 개요 및 요약를 입력해주세요.');
            $('#summary').focus();
            return false;
        }

        if ($('#imageUrlPcl').val().trim() == '') {
            alert('구축사례 이미지를 업로드해주세요.');
            $('#imageUrlPcl').focus();
            return false;
        }

        var reqData = new FormData(this);

        //보도자료 MultiSelect 부분 검증
        var selectedPr = document.getElementById("prListView_to");

        if(selectedPr.length > 3){
            alert('뉴스는 최대 3개까지 선택 가능 합니다.');
            return false;
        }

        var svcList = [];
        $('input:checkbox[name="seqId"]').each(function() {
            if(this.checked){
                svcList.push(this.value);;
            }
        });
        var prList = [];
        for(var i=0; i < selectedPr.length; i++ ){
            prList.push(selectedPr.options[i].value);
        }

        reqData.append("svcList",svcList);
        reqData.append("prList",prList);

        $.ajax({
            url : '/case/insert',
            async: true,
            cache: false,
            type : 'POST',
            data: reqData,
            enctype: 'multipart/form-data',
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

            $('#DetailModal button[data-dismiss=modal]').trigger('click');
            location.reload();

        }).fail(function(xhr, textStatus, errorThrown) {
            if(xhr.status =='403'){
                alert("해당 기능에 대한 권한이 없습니다.");
            }
        });
        return false;
    });

    $('button[name=setUseYn]').on('click', function () {

        var name=$(this).attr('data-name');
        if($(this).attr('data-value') == 'N'){
            if(!confirm(name+'을(를) 프론트에 미노출하시겠습니까?')){
                return;
            }
        }else{
            if(!confirm(name+'을(를) 프론트에 노출하시겠습니까?')){
                return;
            }
        }

        $.post('/case/updateUseYn', {
            useYn:$(this).attr('data-value')
            ,seqId:$(this).attr('data-id')
        }, function(data) {
            if (data.status == 'success') {
                //alert('선택된 서비스가 삭제되었습니다.');
            } else {
                alert('처리되지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            }
        }).done(function (result) {
            location.reload();
        }).fail(function(xhr, textStatus, errorThrown) {
            if(xhr.status =='403'){
                alert("해당 기능에 대한 권한이 없습니다.");
            }
        });

    });
});

$( function() {

    $.datepicker.setDefaults({
        dateFormat: 'yymmdd'
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
    });

    //시작일
    $( "#strtDt" ).datepicker({
        onClose: function(selectedDate){
            $('#endDt').datepicker("option", "minDate", selectedDate);
        }
    });

    //종료일
    $( "#endDt" ).datepicker({
        onClose: function(selectedDate){
            $('#strtDt').datepicker("option", "maxDate", selectedDate);
        }
    });


});

/*구축사례 상세보기*/
$('button[name=btnViewDetail]').on('click', function () {
    document.getElementById('caseForm').reset();

    $('#DetailModalLabel').html('구축사례 상세 : ' + $(this).attr('data-id'));

    $("#langCdKr, #langCdEn").attr("disabled", false);

    $.get('/case/detail', { caseSeqId: $(this).attr('data-id') }, function (result) {

        if (result.status == 'fail') {
            alert('구축사례 정보를 가져오지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            $('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
        }

        var caseInfo = result.data;

        $('input:radio[name=langCd]').filter('[value=' + caseInfo.langCd + ']').trigger('click');
        $('input:radio[name=newsYn]').filter('[value=' + caseInfo.newsYn + ']').trigger('click');

        $('#caseSeqId').val(caseInfo.caseSeqId);
        $('#caseNm').val(caseInfo.caseNm);
        $('#caseTitl').val(caseInfo.caseTitl);
        $('#strtDt').val(caseInfo.strtDt);
        $('#endDt').val(caseInfo.endDt);
        $('#custNm').val(caseInfo.custNm);
        $('#imageUrlCustLogo').val(caseInfo.custLogoImgUrl);

        /* 산업군 리스트 */
        $('#indListView').empty();
        var industryList = result.industryList;
        if(industryList != null){
            for(var count = 0; count < industryList.length; count++){
                var checkbox = '<input type="radio" name="indSeqId" value="'+industryList[count].seqId+'"/> ' + industryList[count].titleNm + '&nbsp;&nbsp;&nbsp;&nbsp;';
                $('#indListView').append(checkbox);
            }
        }
        $("input:radio[name='indSeqId']:radio[value='"+caseInfo.indSeqId+"']").prop('checked', true);

        /* 서비스 리스트 */
        $('#svcListView').empty();
        var serviceList = result.serviceList;
        if(serviceList != null){
            for(var count = 0; count < serviceList.length; count++){
                var checkbox = '<input type="checkbox" name="seqId" value="'+serviceList[count].seqId+'"/> ' + serviceList[count].titleNm + '&nbsp;&nbsp;&nbsp;&nbsp;';
                $('#svcListView').append(checkbox);
            }
        }

        var selectedService = result.selectedService;
        console.log("실행:: "+result.selectedService);
        if(selectedService != null){
            for(var count = 0; count < selectedService.length; count++){
                $('input:checkbox[name="seqId"]').each(function() {
                    if(this.value == selectedService[count].seqId){
                        console.log("실행:: "+this.value);
                        this.checked = true;
                        return;
                    }
                });
            }
        }

        $('#caseBgImgUrl').val(caseInfo.caseBgImgUrl);


        $('textarea#summary').val(caseInfo.summary);
        $('#imageUrlPcl').val(caseInfo.pcCaseImgUrl);
        $('#imageUrlMbll').val(caseInfo.mblCaseImgUrl);

        $('textarea#issueCont').val(safeTagToHtmlTag(caseInfo.issueCont));
        $('textarea#reqCont').val(safeTagToHtmlTag(caseInfo.reqCont));

        $('#imageUrlPcSoll').val(caseInfo.pcSolImgUrl);
        $('#imageUrlMblSoll').val(caseInfo.mblSolImgUrl);
        $('textarea#solCont').val(safeTagToHtmlTag(caseInfo.solCont));
        $('textarea#bfCont').val(safeTagToHtmlTag(caseInfo.bfCont));


        //뉴스 멀티셀렉트
        $('#prListView').empty();
        var prList = result.prList;
        if(prList != null){
            for(var count = 0; count < prList.length; count++){
                var option = "<option value='"+prList[count].prSeqId +"'>"+prList[count].prTitl+"</option>";
                $('#prListView').append(option);
            }
        }

        $('#prListView_to').empty();
        var selectedPr = result.selectedPr;
        if(selectedPr != null){
            for(var count = 0; count < selectedPr.length; count++){
                var option = "<option value='"+selectedPr[count].prSeqId +"'>"+selectedPr[count].prTitl+"</option>";
                $('#prListView_to').append(option);
            }
        }


        /* 영원사원 리스트 */
        $('#saleUserSeqId').empty();
        var saleUserList = result.saleUserList;
        if(saleUserList != null){
            var baseOpt = "<option value=''>영업담당자를 선택해주세요</option>";
            $('#saleUserSeqId').append(baseOpt);
            for(var count = 0; count < saleUserList.length; count++){
                var option = "<option value='"+saleUserList[count].saleUserSeqId +"'>"+saleUserList[count].krNm+"["+saleUserList[count].krDeptNm+"]/"+saleUserList[count].enNm+"["+saleUserList[count].krDeptNm+"]</option>";
                $('#saleUserSeqId').append(option);
            }

            $('#saleUserSeqId').val(caseInfo.saleUserSeqId).prop("selected", true);
        }

        $('#dnldDocNm1').val(caseInfo.dnldDocNm1);
        $('#docFileUrl1').val(caseInfo.dnldDocLnkUrl1);
        $('#dnldDocNm2').val(caseInfo.dnldDocNm2);
        $('#docFileUrl2').val(caseInfo.dnldDocLnkUrl2);

        $("#langCdKr, #langCdEn").attr("disabled", true);

    }, 'json').fail(function(xhr, textStatus, errorThrown) {
        if(xhr.status =='403'){
            alert("해당 기능에 대한 권한이 없습니다.");
        }
    });
});

/*이미지 업로드시 변경 이벤트*/
$("input[type=file]").on('change', function() {
    fileNameAtTarget(this);
});

/*
* 이미지 미리보기
* */
$("label[id^='btnImagePreview']").on("click", function() {
    return imagePreview(this);
});

/*
* 이미지 미리보기
* */
function imagePreview(target) {

    var $fileEl = $('input#' + $(target).data('id'));
    // var $mediaEl = $('#ImageUrl' + $(target).attr("data-index"));
    var $mediaEl = $("#"+$(target).data('preview'));

    if ($fileEl.val().length == 0 && $mediaEl.val().length == 0) {
        alert("파일을 선택해 주세요.");
        return false;
    }
    if ($fileEl.val().length > 0) {
        previewOnTargetImage(document.getElementById($(target).data('id')));
    } else if ($mediaEl.val().indexOf('http') == 0) {
        $('.preview-modal-lg .modal-body .preview').each(function () {
            if( $(this).prop('tagName')  == 'IMG'){
                $(this).attr("src", $mediaEl.val()).show();
            }else{
                $(this).attr('src', '').hide();
            }

        });
    }
}

/*
* 구축사례 등록
* */
$('#btnRegist').on('click', function () {
    $('#DetailModalLabel').html('구축사례 등록');
    $('#caseSeqId').val("");
    $('#svcListView_to').empty();
    $('#prListView_to').empty();


    document.getElementById('caseForm').reset();

    $.get('/case/baseInfoList', function (result) {

        $("#langCdKr, #langCdEn").attr("disabled", false);

        indKRList = result.industryKRList;
        indENList = result.industryENList;

        svcKRList = result.serviceKRList;
        svcENList = result.serviceENList;

        prKRList = result.prKRList;
        prENList = result.prENList;

        /* 산업군 리스트 */
        $('#indListView').empty();
        var industryList = getIndList($("input:radio[name=langCd]:checked").val());
        if(industryList != null){
            for(var count = 0; count < industryList.length; count++){
                var checkbox = '<input type="radio" name="indSeqId" value="'+industryList[count].seqId+'" /> ' + industryList[count].titleNm + '&nbsp;&nbsp;&nbsp;&nbsp;';
                $('#indListView').append(checkbox);
            }
        }

        /* 서비스 리스트 */
        $('#svcListView').empty();
        var serviceList = getSvcList($("input:radio[name=langCd]:checked").val());
        if(serviceList != null){
            for(var count = 0; count < serviceList.length; count++){
                var checkbox = '<input type="checkbox" name="seqId" value="'+serviceList[count].seqId+'"/> ' + serviceList[count].titleNm + '&nbsp;&nbsp;&nbsp;&nbsp;';
                $('#svcListView').append(checkbox);
            }
        }

        $('#prListView').empty();
        var prList = getPrList($("input:radio[name=langCd]:checked").val());
        if(prList != null){
            for(var count = 0; count < prList.length; count++){
                var option = "<option value='"+prList[count].prSeqId +"'>" + prList[count].prTitl+"</option>";
                $('#prListView').append(option);
            }
        }

        /* 영원사원 리스트 */
        $('#saleUserSeqId').empty();
        var saleUserList = result.saleUserList;
        if(saleUserList != null){
            var baseOpt = "<option value=''>영업담당자를 선택해주세요</option>";
            $('#saleUserSeqId').append(baseOpt);
            for(var count = 0; count < saleUserList.length; count++){
                var option = "<option value='"+saleUserList[count].saleUserSeqId +"'>"+saleUserList[count].krNm+"["+saleUserList[count].krDeptNm+"]/"+saleUserList[count].enNm+"["+saleUserList[count].krDeptNm+"]</option>";
                $('#saleUserSeqId').append(option);
            }
        }

    });

});


/*
* 구축사례 삭제
* */
$('#btnDelete').on('click', function () {
    if ($('input[name=caseSelected]:checked').length < 1) {
        alert('삭제할 구축사례 대상을 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=caseSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    if (confirm('선택된 구축사례를 삭제하시겠습니까?')) {
        $.post('/case/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 구축사례가 삭제되었습니다.');
            } else {
                alert('삭제하지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            }
        }).done(function (result) {
            location.reload();
        }).fail(function(xhr, textStatus, errorThrown) {
            if(xhr.status =='403'){
                alert("해당 기능에 대한 권한이 없습니다.");
            }
        });
    }

});

function getPrList(langCd) {
    eval("var prList = pr" + langCd + "List");
    return prList
}

function setPrList(langCd){

    $('#prListView').empty();
    var prList = getPrList(langCd);
    if(prList != null){
        for(var count = 0; count < prList.length; count++){
            var option = "<option value='"+prList[count].prSeqId +"'>" + prList[count].prTitl+"</option>";
            $('#prListView').append(option);
        }
    }
}

function getIndList(langCd) {
    eval("var industryList = ind" + langCd + "List");
    return industryList;
}

function setIndList(langCd){

    $('#indListView').empty();
    var industryList = getIndList(langCd);
    if(industryList != null){
        for(var count = 0; count < industryList.length; count++){
            var checkbox = '<input type="radio" name="indSeqId" value="'+industryList[count].seqId+'" /> ' + industryList[count].titleNm + '&nbsp;&nbsp;&nbsp;&nbsp;';
            $('#indListView').append(checkbox);
        }
    }
}

function getSvcList(langCd) {
    eval("var serviceList = svc" + langCd + "List");
    return serviceList;
}

function setSvcList(langCd){

    $('#svcListView').empty();
    var serviceList = getSvcList(langCd);
    if(serviceList != null){
        for(var count = 0; count < serviceList.length; count++){
            var checkbox = '<input type="checkbox" name="seqId" value="'+serviceList[count].seqId+'"/> ' + serviceList[count].titleNm + '&nbsp;&nbsp;&nbsp;&nbsp;';
            $('#svcListView').append(checkbox);
        }
    }
}