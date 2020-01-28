var prKRList;
var prENList;

var indKRList;
var indENList;

$(document).ready(function(){

    $('#tabLanght').on('change', function () {

        $('.group_tab1').css('display','none');
        $('.group_tab2').css('display','none');
        $('.group_tab3').css('display','none');
        $('.group_tab4').css('display','none');

        if(this.value >= 1){
            $('.group_tab1').css('display','');
            if(this.value >= 2){
                $('.group_tab2').css('display','');
                if(this.value >= 3){
                    $('.group_tab3').css('display','');
                    if(this.value >= 4){
                        $('.group_tab4').css('display','');
                    }
                }
            }
        }
    });

    $('.btn-link').on('click', function (event) {

        var serviceUrl = "https://" + $("#serviceUrl").val() + "/" + $(this).attr('data-id') + "/" + $(this).attr('data-name') + "?" + $(this).attr('data-value') + "=";
        event.preventDefault();

        oEditors.getById["tab1PcDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab2PcDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab3PcDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab4PcDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab1MblDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab2MblDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab3MblDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab4MblDesc"].exec("UPDATE_CONTENTS_FIELD",[]);

        if(!valCheck('#pcUpsideImgUrl', '상단 배경 이미지(PC) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#mblUpsideImgUrl', '상단 배경 이미지(Mobile) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#titleNm', '서비스 명을 입력해주세요.')){
            return false;
        }

        if(!valCheck('#iconImgUrl1', '서비스 아이콘을 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#dtlImgUrl', '서비스 설명 이미지를 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#introDesc', '서비스 개요(PC용)을 입력해주세요.')){
            return false;
        }

        if(!valCheck('#mblIntroDesc', '서비스 개요(Mobile용)을 입력해주세요.')){
            return false;
        }

        if(!valCheck('#dtlDesc', '서비스 설명(PC용)을 입력해주세요.')){
            return false;
        }

        if(!valCheck('#mblDtlDesc', '서비스 설명(Mobile용)을 입력해주세요.')){
            return false;
        }

        if(!valCheck('#benefit1IconUrl', 'Benefit 01 이미지를 첨부해 주세요.') || !valCheck('#benefit1PcDesc', 'Benefit 01 설명(PC) 첨부해 주세요.')
            || !valCheck('#benefit1MblDesc', 'Benefit 01 설명(Mobile)을 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#benefit2IconUrl', 'Benefit 02 이미지를 첨부해 주세요.') || !valCheck('#benefit2PcDesc', 'Benefit 02 설명(PC) 첨부해 주세요.')
            || !valCheck('#benefit2MblDesc', 'Benefit 02 설명(Mobile)을 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#benefit3IconUrl', 'Benefit 03 이미지를 첨부해 주세요.') || !valCheck('#benefit3PcDesc', 'Benefit 03 설명(PC) 첨부해 주세요.')
            || !valCheck('#benefit3MblDesc', 'Benefit 03 설명(Mobile)을 첨부해 주세요.')){
            return false;
        }

        var reqData = new FormData(document.getElementById('serviceForm'));

        //보도자료 MultiSelect 부분 검증
        var selectedPr = document.getElementById("prListView_to");

        if(selectedPr.length > 3){
            alert('뉴스는 최대 3개까지 선택 가능 합니다.');
            return false;
        }

        var prList = [];

        for(var i=0; i < selectedPr.length; i++ ){
            prList.push(selectedPr.options[i].value);
        }

        reqData.append("prList",prList);

        if($("#seqId").val() == "") {
            reqData.set("ogSeqId", 0);
            reqData.set("useYn", "T");
        }
        else{
            // 기존에 있던 글의 수정시 임시글에 값들에 대해서 세팅을 한다.
            if($("#useYn").val() != "T") {
                reqData.set("useYn", "T");
                reqData.set("ogSeqId", $("#seqId").val());
                reqData.set("seqId", "");
            }
        }

        $.ajax({
            url : '/service/insert',
            async: true,
            cache: false,
            type : 'POST',
            data: reqData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success : function(data){
            }, error : function(error){
                console.log(error)
            }
        }).done(function (result) {
            if (result.status == 'fail') {
                alert('등록하지 못했습니다.[' + result.errorMsg + ']\n반복 시 관리자에게 문의 바랍니다.');
                return false;
            }

            //$('#DetailModal button[data-dismiss=modal]').trigger('click');
            window.open(serviceUrl + result.seqId,'_blank','');

            $('#DetailModal button[data-dismiss=modal]').trigger('click');
            location.reload();

        }).fail(function(xhr, textStatus, errorThrown) {
            if(xhr.status =='403'){
                alert("해당 기능에 대한 권한이 없습니다.");
            }
        });

    });

    $('button[name=setUseYn]').on('click', function () {

        var name=$(this).attr('data-name');

        if($(this).attr('data-value') == 'T'){
            if(!confirm("임시저장 상태인 " + name + '을(를) 프론트에 노출하시겠습니까?')){
                return;
            }
        }else if($(this).attr('data-value') == 'N'){
            if(!confirm(name+'을(를) 프론트에 미노출하시겠습니까?')){
                return;
            }
        }else{
            if(!confirm(name+'을(를) 프론트에 노출하시겠습니까?')){
                return;
            }
        }

        $.post('/service/updateUseYn', {
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


    /*
    * 서비스 탭 영역 리스트 추가
    * */
    $("#serviceAdd").click(function() {

        var dtlsvcTag = "<li>\n" +
            "<p class='txt blue'></p>\n" +
            "<p class='txt'></p>\n" +
            "</li>";
        serviceTabV2('',dtlsvcTag,dtlsvcTag);
    });

    /*
    * 동적탭 삭제부분
    * */
    $(document).on("click","#tabDelete",function(event) {


        $('#field'+$(this).attr('data-id')).parent().remove();


        return false;
    });


    /*검색*/
    $('form#reqForm button').on('click', function () {
        $('form#reqForm').attr('action', document.location.pathname);
        if (prevSearchWord != $('input[name=searchWord]').val()) {
            $('#reqForm input[name=page]').val(0);
            $('#reqForm input[name=size]').val(10);
        }
        $('form#reqForm').submit();
    });

    /*
    * 서비스 전체 선택
    * */
    $('input[name=selectAll]').on('click', function () {
        var checked = $(this).prop('checked');
        $('input[name=svcSelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });

    /*서비스 등록*/
    $('#serviceForm').on('submit', function (event) {
        event.preventDefault();

        $("#langCdKr, #langCdEn").attr("disabled", false);

        oEditors.getById["tab1PcDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab2PcDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab3PcDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab4PcDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab1MblDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab2MblDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab3MblDesc"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["tab4MblDesc"].exec("UPDATE_CONTENTS_FIELD",[]);

        if(!valCheck('#pcUpsideImgUrl', '상단 배경 이미지(PC) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#mblUpsideImgUrl', '상단 배경 이미지(Mobile) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#titleNm', '서비스 명을 입력해주세요.')){
        	return false;
        }

        if(!valCheck('#iconImgUrl1', '서비스 아이콘을 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#dtlImgUrl', '서비스 설명 이미지를 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#introDesc', '서비스 개요(PC용)을 입력해주세요.')){
            return false;
        }

        if(!valCheck('#mblIntroDesc', '서비스 개요(Mobile용)을 입력해주세요.')){
            return false;
        }
        
        if(!valCheck('#dtlDesc', '서비스 설명(PC용)을 입력해주세요.')){
        	return false;
        }

        if(!valCheck('#mblDtlDesc', '서비스 설명(Mobile용)을 입력해주세요.')){
            return false;
        }

        if(!valCheck('#benefit1IconUrl', 'Benefit 01 이미지를 첨부해 주세요.') || !valCheck('#benefit1PcDesc', 'Benefit 01 설명(PC) 첨부해 주세요.')
            || !valCheck('#benefit1MblDesc', 'Benefit 01 설명(Mobile)을 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#benefit2IconUrl', 'Benefit 02 이미지를 첨부해 주세요.') || !valCheck('#benefit2PcDesc', 'Benefit 02 설명(PC) 첨부해 주세요.')
            || !valCheck('#benefit2MblDesc', 'Benefit 02 설명(Mobile)을 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#benefit3IconUrl', 'Benefit 03 이미지를 첨부해 주세요.') || !valCheck('#benefit3PcDesc', 'Benefit 03 설명(PC) 첨부해 주세요.')
            || !valCheck('#benefit3MblDesc', 'Benefit 03 설명(Mobile)을 첨부해 주세요.')){
            return false;
        }


        var reqData = new FormData(this);

        //보도자료 MultiSelect 부분 검증
        var selectedPr = document.getElementById("prListView_to");

        if(selectedPr.length > 3){
            alert('뉴스는 최대 3개까지 선택 가능 합니다.');
            return false;
        }

        var prList = [];
        for(var i=0; i < selectedPr.length; i++ ){
            prList.push(selectedPr.options[i].value);
        }

        reqData.append("prList",prList);


        if($("#seqId").val() == "") {
            reqData.set("ogSeqId", 0);
            reqData.set("useYn", "T");
        }
        else{
            // 기존에 있던 글의 수정시 임시글에 값들에 대해서 세팅을 한다.
            if($("#useYn").val() != "T") {
                reqData.set("useYn", "T");
                reqData.set("ogSeqId", $("#seqId").val());
                reqData.set("seqId", "");
            }
        }

        // reqData.append("serviceTabList",$("div[name=svcDtlSvcAddList]"));

/*
        //산업군 MultiSelect 부분 검증
        var selectedInd = document.getElementById("indListView_to");

        if(selectedInd.length <= 0){
        	alert('산업군 영역은 1개 이상 반드시 선택해야 합니다. (최대 3개까지 가능)');
        	return false;
        }
        else if(selectedInd.length > 3){
        	alert('산업군 영역은 3개 이하만 선택 가능 합니다.');
        	return false;
        }


        var indList = [];
        for(var i=0; i < selectedInd.length; i++ ){
            indList.push(selectedInd.options[i].value);
        }

        reqData.append("indList",indList);



        // 영업담당자 선택 여부 체크
        if($('#saleUserSeqId > option:selected').val() == 0){
        	alert('영업담당자를 선택해 주세요.');
        	return false;
        }
        */

        $.ajax({
            url : '/service/insert',
            async: true,
            cache: false,
            type : 'POST',
            data: reqData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success : function(data){
            }, error : function(error){
                console.log(error)
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

/*서비스 상세보기*/
$('button[name=btnViewDetail]').on('click', function () {
    document.getElementById('serviceForm').reset();
    $('#btn_sunmit').css("display", "");
    $('#DetailModalLabel').html('서비스 상세 : ' + $(this).attr('data-id'));

    $("#langCdKr, #langCdEn").attr("disabled", false);

    $.get('/service/detail', { seqId: $(this).attr('data-id') }, function (result) {

        if (result.status == 'fail') {
            alert('서비스 정보를 가져오지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            $('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
        }

        var serviceInfo = result.data;

        $('input:radio[name=langCd]').filter('[value=' + serviceInfo.langCd + ']').trigger('click');

        if(serviceInfo.useYn == "T"){
            $('#btn_sunmit').css("display", "none");
        }
        if(result.ogMgt != null && result.ogMgt.seqId != null){
            $('#btn_sunmit').css("display", "none");
        }

        $('#seqId').val(serviceInfo.seqId);
        $('#ogSeqId').val(serviceInfo.ogSeqId);

        $('#pcUpsideImgUrl').val(serviceInfo.pcUpsideImgUrl);
        $('#mblUpsideImgUrl').val(serviceInfo.mblUpsideImgUrl);

        $('#titleNm').val(serviceInfo.titleNm);

        $('#iconImgUrl1').val(serviceInfo.iconImgUrl1);
        $('#dtlImgUrl').val(serviceInfo.dtlImgUrl);

        $('textarea#introDesc').val(serviceInfo.introDesc);
        $('textarea#mblIntroDesc').val(serviceInfo.mblIntroDesc);

        $('textarea#dtlDesc').val(serviceInfo.dtlDesc);
        $('textarea#mblDtlDesc').val(serviceInfo.mblDtlDesc);

        $('#benefit1IconUrl').val(serviceInfo.benefit1IconUrl);
        $('#benefit1PcDesc').val(serviceInfo.benefit1PcDesc);
        $('#benefit1MblDesc').val(serviceInfo.benefit1MblDesc);
        $('#benefit2IconUrl').val(serviceInfo.benefit2IconUrl);
        $('#benefit2PcDesc').val(serviceInfo.benefit2PcDesc);
        $('#benefit2MblDesc').val(serviceInfo.benefit2MblDesc);
        $('#benefit3IconUrl').val(serviceInfo.benefit3IconUrl);
        $('#benefit3PcDesc').val(serviceInfo.benefit3PcDesc);
        $('#benefit3MblDesc').val(serviceInfo.benefit3MblDesc);

        $('#processPcImgUrl').val(serviceInfo.processPcImgUrl);
        $('textarea#processPcDesc').val(serviceInfo.processPcDesc);
        $('#processMblImgUrl').val(serviceInfo.processMblImgUrl);
        $('textarea#processMblDesc').val(serviceInfo.processMblDesc);

        $('#tab1Nm').val(serviceInfo.tab1Nm);
        $('#tab1Title').val(serviceInfo.tab1Title);
        $('#tab2Nm').val(serviceInfo.tab2Nm);
        $('#tab2Title').val(serviceInfo.tab2Title);
        $('#tab3Nm').val(serviceInfo.tab3Nm);
        $('#tab3Title').val(serviceInfo.tab3Title);
        $('#tab4Nm').val(serviceInfo.tab4Nm);
        $('#tab4Title').val(serviceInfo.tab4Title);

        $('#useYn').val(serviceInfo.useYn);

        $('#tabLanght option[value='+serviceInfo.tabLanght+']').attr('selected','selected');

        $('input:radio[name="tabYn"]:input[value="'+serviceInfo.tabYn+'"]').prop("checked", true);
        $('input:radio[name="itoYn"]:input[value="'+serviceInfo.itoYn+'"]').prop("checked", true);
        $('input:radio[name="newsYn"]:input[value="'+serviceInfo.newsYn+'"]').prop("checked", true);

        $('#newsTitlImgUrl').val(serviceInfo.newsTitlImgUrl);
        $('#refTitlImgUrl').val(serviceInfo.refTitlImgUrl);

        $('iframe').remove();
        createSE2("detail", serviceInfo.tab1PcDesc, "tab1PcDesc");
        createSE2("detail", serviceInfo.tab2PcDesc, "tab2PcDesc");
        createSE2("detail", serviceInfo.tab3PcDesc, "tab3PcDesc");
        createSE2("detail", serviceInfo.tab4PcDesc, "tab4PcDesc");
        createSE2("detail", serviceInfo.tab1MblDesc, "tab1MblDesc");
        createSE2("detail", serviceInfo.tab2MblDesc, "tab2MblDesc");
        createSE2("detail", serviceInfo.tab3MblDesc, "tab3MblDesc");
        createSE2("detail", serviceInfo.tab4MblDesc, "tab4MblDesc");
/*
        $('.group_tab1').css('display','none');
        $('.group_tab2').css('display','none');
        $('.group_tab3').css('display','none');
        $('.group_tab4').css('display','none');

        if(serviceInfo.tabLanght ==1){
            $('.group_tab1').css('display','');
        }else if(serviceInfo.tabLanght ==2){
            $('.group_tab1').css('display','');
            $('.group_tab2').css('display','');
        }else if(serviceInfo.tabLanght ==3){
            $('.group_tab1').css('display','');
            $('.group_tab2').css('display','');
            $('.group_tab3').css('display','');
        }else if(serviceInfo.tabLanght ==4){
            $('.group_tab1').css('display','');
            $('.group_tab2').css('display','');
            $('.group_tab3').css('display','');
            $('.group_tab4').css('display','');
        }*/

        //$('textarea#dtlDesc').val(safeTagToHtmlTag(serviceInfo.dtlDesc));

        // 서비스탭
        $('#svcDtlsvcList').empty();
        for(var i in serviceInfo.svcDtlsvcList){
            serviceTabV2(serviceInfo.svcDtlsvcList[i].tbSvcDtlsvcKey.dtlsvcNm,serviceInfo.svcDtlsvcList[i].dtlsvcDesc,serviceInfo.svcDtlsvcList[i].mbldtlsvcDesc);
        }
        //산업군 멀티셀렉트
        $('#indListView').empty();
        var industryList = result.industryList;
        if(industryList != null){
            for(var count = 0; count < industryList.length; count++){
                var checkbox = '<input type="checkbox" name="indSeqId" value="'+industryList[count].seqId+'" /> ' + industryList[count].titleNm + '&nbsp;&nbsp;';
                $('#indListView').append(checkbox);
            }
        }

        var industryCheckList = serviceInfo.indMapping;
        if(industryCheckList != null){
            for(var count = 0; count < industryCheckList.length; count++){
                $('input:checkbox[name="indSeqId"]').each(function() {

                    if(this.value == industryCheckList[count].indSeqId){
                        this.checked = true;
                        return;
                    }
                });

            }
        }
        /*if(industryList != null){
            for(var count = 0; count < industryList.length; count++){
                var option = "<option value='"+industryList[count].indSeqId+"'>"+industryList[count].indNm+"</option>";
                $('#indListView').append(option);
            }
        }*/

        $('#indListView_to').empty();
        var selectedIndustry = result.selectedIndustry;
        if(selectedIndustry != null){
            for(var count = 0; count < selectedIndustry.length; count++){
                var option = "<option value='"+selectedIndustry[count].indSeqId +"'>"+selectedIndustry[count].indNm+"</option>";
                $('#indListView_to').append(option);
            }
        }


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

            $('#saleUserSeqId').val(serviceInfo.saleUserSeqId).prop("selected", true);
        }

        $('#dnldDocNm1').val(serviceInfo.dnldDocNm1);
        $('#docFileUrl1').val(serviceInfo.dnldDocLnkUrl1);
        $('#dnldDocNm2').val(serviceInfo.dnldDocNm2);
        $('#docFileUrl2').val(serviceInfo.dnldDocLnkUrl2);

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
    var $mediaEl = $("#"+$(target).data('preview'));
    // var $mediaEl = $('#ImageUrl' + $(target).attr("data-index"));

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
* 서비스 등록
* */
$('#btnRegist').on('click', function () {
    $('#DetailModalLabel').html('서비스 등록');
    $('#seqId').val("");
    $("#svcDtlsvcList").empty();
    $('#btn_sunmit').css("display", "");
    document.getElementById('serviceForm').reset();

    // 산업군/ 홍보자료 목록 초기화
    $('#indListView').empty();
    $('#indListView_to').empty();
    $('#prListView').empty();
    $('#prListView_to').empty();

    $('iframe').remove();
    createSE2("regist", null, tab1PcDesc);
    createSE2("regist", null, tab2PcDesc);
    createSE2("regist", null, tab3PcDesc);
    createSE2("regist", null, tab4PcDesc);
    createSE2("regist", null, tab1MblDesc);
    createSE2("regist", null, tab2MblDesc);
    createSE2("regist", null, tab3MblDesc);
    createSE2("regist", null, tab4MblDesc);

    $("#langCdKr, #langCdEn").attr("disabled", false);

    $.get('/service/baseInfoList', function (result) {
        //산업군 멀티셀렉트

        prKRList = result.prKRList;
        prENList = result.prENList;

        indKRList = result.industryKRList;
        indENList = result.industryENList;

        var industryList = getIndList($("input:radio[name=langCd]:checked").val());
        if(industryList != null){
            for(var count = 0; count < industryList.length; count++){
                var checkbox = '<input type="checkbox" name="indSeqId" value="'+industryList[count].seqId+'" />' + industryList[count].titleNm + '&nbsp;&nbsp;';
                $('#indListView').append(checkbox);
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
        
        // 영업사원 멀티셀릭트
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

    //$('input:radio[name=langCd]').filter('[value=KR]').trigger('click');

});


/*
* 서비스 삭제
* */
$('#btnDelete').on('click', function () {
    if ($('input[name=svcSelected]:checked').length < 1) {
        alert('삭제할 서비스 대상을 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=svcSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    if (confirm('선택된 서비스를 삭제하시겠습니까?')) {
        $.post('/service/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 서비스가 삭제되었습니다.');
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

function serviceTab(dtlSvcNm, dtlSvcDesc,mbldtlsvcDesc){
    //var intId = $("#svcDtlsvcList div").length + 1;
	//var intId = $("#svcDtlsvcList id").length + 1;
	var intId = $("#svcDtlsvcList input[name=detailNm]").length + 1;

    var fieldWrapper = $("<div class='col-sm-12' id='field" + intId + "' />");
    var fInputName =
        $("<div class='col-sm-10' name='svcDtlSvcAddList'>"+
            // "<input type='text' name='dtlSvcNm' class='col-sm-8 form-control' id='dtlSvcNm'/>"+
            //"<input type='text' name='detailNm' class='col-sm-8 form-control' id='dtlSvcNm' value='"+dtlSvcNm+"'/>"+
            //"");
            "<label class='col-sm-2'>탭  이름</label><div class='col-sm-10'>"+
        	"<input type='text' name='detailNm' class='col-sm-12 form-control' value='"+dtlSvcNm+"'/></div>"+
        	"");
    var removeButton =
        $("<div class='col-sm-2'><button type='button' class='btn btn-primary btn-sm'>삭제</button>"+
            "</div></div>");
    var fInputPcBody =
        $("<div class='col-sm-12'>"+
            // "<textarea  name='dtlSvcDesc' class='form-control' id='dtlSvcDesc' row='5'/>"+
            "<label class='col-sm-2 col-form-label'>내용<br>(PC용)</label><div class='col-sm-10'>"+
            "<textarea  name='detailDesc' class='form-control' id='dtlSvcDesc' row='5'>"+
            dtlSvcDesc+
            "</textarea>"+
            "</div></div>");
    var fInputMblBody =
        $("<div class='col-sm-12'>"+
            // "<textarea  name='dtlSvcDesc' class='form-control' id='dtlSvcDesc' row='5'/>"+
            "<label class='col-sm-2 col-form-label'>내용<br>(Mobile용)</label><div class='col-sm-10'>"+
            "<textarea  name='mblDetailDesc' class='form-control' id='mbldtlsvcDesc' row='5'>"+
            mbldtlsvcDesc+
            "</textarea>"+
            "</div></div></div><BR>");
    removeButton.click(function() {
        $(this).parent().remove();
    });
    fieldWrapper.append(fInputName);
    fieldWrapper.append(removeButton);
    // fieldWrapper.append(fInputTitle);
    fieldWrapper.append(fInputPcBody);
    fieldWrapper.append(fInputMblBody);
    $("#svcDtlsvcList").append(fieldWrapper);
}


function serviceTabV2(dtlSvcNm, dtlSvcDesc,mbldtlsvcDesc){
    //var intId = $("#svcDtlsvcList div").length + 1;
    //var intId = $("#svcDtlsvcList id").length + 1;
    var intId = $("#svcDtlsvcList input[name=detailNm]").length + 1;
    var intMaxId = intId;

    $("div[name=tabDetail]").each(function(i){

        var nowIdx = $(this).attr('data-id');
        if(intMaxId < nowIdx){

            intMaxId = Number(nowIdx)+1;

        }else if(intMaxId == nowIdx){
            intMaxId = Number(nowIdx)+1;
        }

    });

    var fieldGrpStartHtml = "<div class='form-group row' style='margin-bottom: 15px;'><div class='col-sm-12' name='tabDetail' data-id='" + intMaxId + "' id='field" + intMaxId + "'>";
    var fieldTabNmStartHtml    = "<div class='form-group row' style='margin-bottom: 5px;'><div class='col-sm-12' name='svcDtlSvcAddList' style='height: 30px;'>";
    var fieldTabNmBodyHeadHtml      = "<div class='form-group row' style='margin-bottom: 5px;'><label class='col-sm-2'>탭  이름</label>";
    var fieldTabNmBodyContHtml      = "<div class='col-sm-10' style='height: 30px;'>"+
                                        "<div class='form-group row' style='margin-bottom: 5px;'>" +
                                            "<div class='col-sm-10' style='height: 30px;'><input type='text' name='detailNm' class='form-control' value='"+dtlSvcNm+"'/></div>" +
                                            "<div class='col-sm-2' style='height: 30px;'><button type='button' class='btn btn-primary btn-sm' id='tabDelete' data-id='" + intMaxId + "'>삭제</button></div>" +
                                        "</div>"+
                                      "</div></div>";
    var fieldTabNmEndHtml      = "</div></div>";

    var fieldInputPCStartHtml  = "<div class='form-group row' style='margin-bottom: 5px;'><div class='col-sm-12'>";
    var fieldInputPCBodyHeadHtml  = "<div class='form-group row' style='margin-bottom: 5px;'><label class='col-sm-2 col-form-label'>내용<br>(PC용)</label>";
    var fieldInputPCBodyContHtml  = "<div class='col-sm-10' >" +
                                        "<textarea  name='detailDesc' class='form-control' id='dtlSvcDesc' row='20' >"+
                                            dtlSvcDesc+
                                        "</textarea>"+
                                    "</div></div>";
    var fieldInputPCEndHtml    = "</div></div>";

    var fieldInputMblStartHtml  = "<div class='form-group row' style='margin-bottom: 5px;'><div class='col-sm-12'>";
    var fieldInputMblBodyHeadHtml  = "<div class='form-group row' style='margin-bottom: 5px;'><label class='col-sm-2 col-form-label'>내용<br>(Mobile용)</label>";
    var fieldInputMblBodyContHtml  = "<div class='col-sm-10'>" +
                                        "<textarea  name='mblDetailDesc' class='form-control' id='mbldtlsvcDesc' row='20' >"+
                                            mbldtlsvcDesc+
                                        "</textarea>"+
                                     "</div></div>";
    var fieldInputMblEndHtml    = "</div></div>";

    var fieldGrpEndHtml   = "</div></div>";

    var tabHtml = fieldGrpStartHtml +
        fieldTabNmStartHtml + fieldTabNmBodyHeadHtml + fieldTabNmBodyContHtml + fieldTabNmEndHtml +
        fieldInputPCStartHtml + fieldInputPCBodyHeadHtml + fieldInputPCBodyContHtml + fieldInputPCEndHtml +
        fieldInputMblStartHtml + fieldInputMblBodyHeadHtml + fieldInputMblBodyContHtml + fieldInputMblEndHtml +
        fieldGrpEndHtml;

    $("#svcDtlsvcList").append(tabHtml);
}

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