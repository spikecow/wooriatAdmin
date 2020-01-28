$(document).ready(function(){

    /*검색*/
    $('form#reqForm button').on('click', function () {
        $('form#reqForm').attr('action', document.location.pathname);
        if (prevSearchWord != $('input[name=searchWord]').val()) {
            $('#reqForm input[name=page]').val(0);
            $('#reqForm input[name=size]').val(10);
        }
        $('form#reqForm').submit();
    });

    $("input:radio[name=solutionCd]").on('click',function() {


        if($("input:radio[name=solutionCd]:checked").val() == 'E'){
            $('#solutionCdDesc').css('display','');
            $('#solutionCdLink').css('display','none');
        }else{
            $('#solutionCdDesc').css('display','none');
            $('#solutionCdLink').css('display','');
        }

    });

    /*
    * 파트너 전체 선택
    * */
    $('input[name=selectAll]').click(function(){
        var checked = $(this).prop('checked');
        $('input[name=distSelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });

    /*파트너 등록*/
    $('#distForm').on('submit', function (event) {
        event.preventDefault();
        $('iframe[src="/se2/SmartEditor2Skin.html"]').contents().find('iframe[src="smart_editor2_inputarea.html"]').contents().find('.se2_inputarea *').css('font-family','');

        oEditors.getById["solutionDesc"].exec("UPDATE_CONTENTS_FIELD",[]);

        if($('#imageUrlPcUpsidel').val().trim() == ''){
            alert('상단 배경 이미지영역(PC) 파일을 첨부해주세요.');
            $('imageUrlPcUpsidel').focus();
            return false;
        }

        if($('#imageUrlMblUpsidel').val().trim() == ''){
            alert('상단 배경 이미지영역(Mobile) 파일을 첨부해주세요.');
            $('imageUrlMblUpsidel').focus();
            return false;
        }

        if($("#menuCd").val() == "P" && !valCheck('#partnerNm', '파트너 명을 입력해주세요.')){
        	return false;
        }

        if($("#menuCd").val() == "P" && $('#partnerImgLogoUrl').val().trim() == ''){
            alert('파트너 로고 이미지 파일을 첨부해주세요..');
            $('partnerImgLogoUrl').focus();
            return false;
        }

        if($("#menuCd").val() == "S" && !valCheck('#solutionNm', '솔루션 명을 입력해주세요.')){
            return false;
        }
       
        var reqData = new FormData(this);
        // reqData.append("distTabList",$("div[name=svcDtlSvcAddList]"));


        // 영업담당자 선택 여부 체크
        if($("#menuCd").val() == "S" && $('#saleUserSeqId > option:selected').val() == 0){
        	alert('영업담당자를 선택해 주세요.');
        	return false;
        }
        
        $.ajax({
            url : '/dist/insert',
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

        $.post('/dist/updateUseYn', {
            useYn:$(this).attr('data-value')
            ,distSeqId:$(this).attr('data-id')
        }, function(data) {
            if (data.status == 'success') {
                //alert('선택된 가 삭제되었습니다.');
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

/* Validation Check */
function valCheck(id, msg){
	if ($(id).val().trim() == '') {
        alert(msg);
        $(id).focus();
        return false;
    }
	return true;
}

/*파트너 상세보기*/
$('button[name=btnViewDetail]').on('click', function () {
    document.getElementById('distForm').reset();

    $('#DetailModalLabel').html('상세 : ' + $(this).attr('data-id'));

    $.get('/dist/detail', { distSeqId: $(this).attr('data-id') }, function (result) {

        if (result.status == 'fail') {
            alert('상세 정보를 가져오지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            $('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
        }

        var distInfo = result.data;

        $('iframe').remove();

        if(($("#menuCd").val() == "P")){
        	/*
            $("#saleUserSeqId").closest("tr").hide();
            $("#saleUserSeqIdPop").closest("tr").hide();
            $("#brochureLnkFile").closest("tr").hide();
            $("#dtlLnk").closest("tr").hide();
            */
        }
        else {
            $("#partnerNm").closest("tr").hide();
            $("#partnerNmPop").closest("tr").hide();
            $("#partnerImgLogoFile").closest("tr").hide();
            $("#partnerImgLogoFilePop").closest("tr").hide();
        }

        createSE2("detail", distInfo);
        $('#distSeqId').val(distInfo.distSeqId);
        $('#partnerNm').val(safeTagToHtmlTag(distInfo.partnerNm));
        $('#solutionNm').val(safeTagToHtmlTag(distInfo.solutionNm));
        $('#dtlLnk').val(safeTagToHtmlTag(distInfo.dtlLnk));

        $('#imageUrlPcUpsidel').val(distInfo.pcUpsideImgUrl);
        $('#imageUrlMblUpsidel').val(distInfo.mblUpsideImgUrl);

        $('#partnerImgLogoUrl').val(distInfo.partnerImgLogoUrl);
        $('#brochureLnkUrl').val(distInfo.brochureLnkUrl);

        $('#solutionLnk').val(distInfo.solutionLnk);

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

            $('#saleUserSeqId').val(distInfo.saleUserSeqId).prop("selected", true);
        }

        $('input:radio[name=langCd]').filter('[value=' + distInfo.langCd + ']').trigger('click');


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
* 파트너 등록
* */
$('#btnRegist').on('click', function () {
    if(($("#menuCd").val() == "P")){
        $('#DetailModalLabel').html('파트너 등록');
        /*
        $("#saleUserSeqId").closest("tr").hide();
        $("#saleUserSeqIdPop").closest("tr").hide();
        $("#brochureLnkFile").closest("tr").hide();
        $("#dtlLnk").closest("tr").hide();
        */
    }
    else {
        $('#DetailModalLabel').html('솔루션 등록');
        $("#partnerNm").closest("tr").hide();
        $("#partnerNmPop").closest("tr").hide();
        $("#partnerImgLogoFile").closest("tr").hide();
        $("#partnerImgLogoFilePop").closest("tr").hide();
    }

    $('#distSeqId').val("");

    document.getElementById('distForm').reset();
    $('iframe').remove();
    createSE2("regist", null);

    $.get('/dist/baseInfoList', function (result) {

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
    $('input:radio[name=langCd]').filter('[value=KR]').trigger('click');

});


/*
* 파트너 삭제
* */
$('#btnDelete').on('click', function () {
    if ($('input[name=distSelected]:checked').length < 1) {
        alert('삭제할 대상을 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=distSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    if (confirm('삭제하시겠습니까?')) {
        $.post('/dist/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('삭제되었습니다.');
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


