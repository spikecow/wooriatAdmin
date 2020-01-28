var g_badgeCount = 1;

$(document).ready(function(){

    /*검색*/
    $('form#reqForm button').on('click', function () {

        $('form#reqForm').attr('action', document.location.pathname);
        if (prevSearchWord != $('input[name=searchWord]').val()) {

            // size=10, sort="indSeqId",direction = Sort.Direction.DESC
            $('#reqForm input[name=page]').val(0);
            $('#reqForm input[name=size]').val(10);
        }
        // else{
        //     var page = $('#reqForm input[name=page]').val();
        //     var page = $('#reqForm input[name=page]').val(page - 1);
        // }

        $('form#reqForm').submit();
    });

    /*
    * 산업군 전체 선택
    * */
    $('input[name=selectAll]').on('click', function () {

        var checked = $(this).prop('checked');
        $('input[name=indSelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });

    $('.btn-link').on('click', function (event) {

        var serviceUrl = "https://" + $("#serviceUrl").val() + "/" + $(this).attr('data-id') + "/" + $(this).attr('data-name') + "?" + $(this).attr('data-value') + "=";
        event.preventDefault();

        if(!valCheck('#pcUpsideImgUrl', '상단 배경 이미지(PC) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#mblUpsideImgUrl', '상단 배경 이미지(Mobile) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#titleNm', '산업군 명을 입력해주세요.')){
            return false;
        }


        if(!valCheck('#iconImgUrl2', '산업군 아이콘(구축사례 썸네일)을 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#iconImgUrl1', '산업군 아이콘(구축사례 상세화면)을 첨부해 주세요.')){
            return false;
        }


        if(!valCheck('#dtlImgUrl', '산업군 설명 이미지를 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#introDesc', '산업군 개요를 입력해주세요.')){
            return false;
        }

        if(!valCheck('#dtlDesc', '산업군 설명을 입력해주세요.')){
            return false;
        }

        if($('#indSysTable tbody tr').length == 0){
            alert('System / Method 을 추가해주세요');
            $('#indSysTable').focus();
            return false;
        }
        var isExsit = false;

        if($('#indSysTable tbody tr').length > 0){
            $("#indSysTable input[name=sysNm]").each(function(i){

                if($(this).val().trim() == ''){
                    isExsit = true;
                }
            });
        }
        if(isExsit){
            alert('System / Method의 시스템명을 입력해야 합니다.');
            return false;
        }

        if(!valCheck('#mainIconImgUrl', '메입 산업군 아이콘 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#mainDesc', '메인 산업군 설명을 입력해주세요.')){
            return false;
        }

        if(!valCheck('#bannerPcImgUrl', '서비스 산업군 배너(PC) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#bannerMblImgUrl', '서비스 산업군 배너(MOBILE) 파일을 첨부해주세요.')){
            return false;
        }

        var result = $('#dtlDesc').val().replace(/(\n|\r\n)/g, '<br>');
        $('textarea#dtlDesc').val(safeTagToHtmlTag(result));

        var reqData = new FormData(document.getElementById('industryForm'));

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
            url : '/industry/insert',
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

        $.post('/industry/updateUseYn', {
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

    $('button[name=indSysAdd]').on('click', function () {
        var tr = $("#indSysTable tbody tr");
        tr = tr.length + 1;

        $('#indSysTable > tbody:last').append('<tr><td>'+tr+'</td>' +
            '<td><input type="text" name="sysNm" class="form-control" /></td>' +
            '<td><input type="text" name="sysDesc" class="form-control" /></td>' +
            '<td><button type="button" class="btn btn-primary" onclick="deleteLine(this);">삭제</button></td></tr>');
    });



});

function deleteLine(obj) {
    var tr = $(obj).parent().parent();

    tr.remove();
}

    /*산업군 등록*/
    $('#industryForm').on('submit', function () {

        if(!valCheck('#pcUpsideImgUrl', '상단 배경 이미지(PC) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#mblUpsideImgUrl', '상단 배경 이미지(Mobile) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#titleNm', '산업군 명을 입력해주세요.')){
            return false;
        }


        if(!valCheck('#iconImgUrl2', '산업군 아이콘(구축사례 썸네일)을 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#iconImgUrl1', '산업군 아이콘(구축사례 상세화면)을 첨부해 주세요.')){
            return false;
        }


        if(!valCheck('#dtlImgUrl', '산업군 설명 이미지를 첨부해 주세요.')){
            return false;
        }

        if(!valCheck('#introDesc', '산업군 개요를 입력해주세요.')){
            return false;
        }

        if(!valCheck('#dtlDesc', '산업군 설명을 입력해주세요.')){
            return false;
        }

        if($('#indSysTable tbody tr').length == 0){
            alert('System / Method 을 추가해주세요');
            $('#indSysTable').focus();
            return false;
        }
        var isExsit = false;

        if($('#indSysTable tbody tr').length > 0){
            $("#indSysTable input[name=sysNm]").each(function(i){

                if($(this).val().trim() == ''){
                    isExsit = true;
                }
            });
        }
        if(isExsit){
            alert('System / Method의 시스템명을 입력해야 합니다.');
            return false;
        }

        if(!valCheck('#mainIconImgUrl', '메입 산업군 아이콘 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#mainDesc', '메인 산업군 설명을 입력해주세요.')){
            return false;
        }

        if(!valCheck('#bannerPcImgUrl', '서비스 산업군 배너(PC) 파일을 첨부해주세요.')){
            return false;
        }

        if(!valCheck('#bannerMblImgUrl', '서비스 산업군 배너(MOBILE) 파일을 첨부해주세요.')){
            return false;
        }

        var result = $('#dtlDesc').val().replace(/(\n|\r\n)/g, '<br>');
        $('textarea#dtlDesc').val(safeTagToHtmlTag(result));

        var reqData = new FormData(this);

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
            url : '/industry/insert',
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

/*산업군 상세보기*/
$('button[name=btnViewDetail]').on('click', function () {
    document.getElementById('industryForm').reset();
    $('#indSysTable tbody').empty();
    $('#btn_sunmit').css("display", "");
    $('#DetailModalLabel').html('산업군 상세 : ' + $(this).attr('data-id'));

    $.get('/industry/detail', { seqId: $(this).attr('data-id') }, function (result) {

        if (result.status == 'fail') {
            alert('산업군 정보를 가져오지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            $('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
        }

        var industryInfo = result.data;

        if(industryInfo.useYn == "T"){
            $('#btn_sunmit').css("display", "none");
        }
        if(result.ogMgt != null && result.ogMgt.seqId != null){
            $('#btn_sunmit').css("display", "none");
        }

        $('#seqId').val(industryInfo.seqId);
        $('#ogSeqId').val(industryInfo.ogSeqId);

        $('#pcUpsideImgUrl').val(industryInfo.pcUpsideImgUrl);
        $('#mblUpsideImgUrl').val(industryInfo.mblUpsideImgUrl);

        $('#titleNm').val(industryInfo.titleNm);
        $('#introDesc').val(industryInfo.introDesc);


        $('#iconImgUrl1').val(industryInfo.iconImgUrl1);
        $('#iconImgUrl2').val(industryInfo.iconImgUrl2);
        $('#mainIconImgUrl').val(industryInfo.mainIconImgUrl);
        $('#mainDesc').val(industryInfo.mainDesc);
        $('#bannerPcImgUrl').val(industryInfo.bannerPcImgUrl);
        $('#bannerMblImgUrl').val(industryInfo.bannerMblImgUrl);

        $('#dtlImgUrl').val(industryInfo.dtlImgUrl);

        $('textarea#dtlDesc').val(industryInfo.dtlDesc);

        $('#useYn').val(industryInfo.useYn);


        for(var i in industryInfo.sys){
            var sysNm = industryInfo.sys[i].sysNm;
            var sysDesc = industryInfo.sys[i].sysDesc;
            if(sysDesc == null){
                sysDesc = "";
            }
            $('#indSysTable > tbody:last').append('<tr><td>'+(parseInt(i) + parseInt(1))+'</td>' +
                '<td><input type="text" name="sysNm" class="form-control" value="'+sysNm+'"/></td>' +
                '<td><input type="text" name="sysDesc" class="form-control" value="'+sysDesc+'"/></td>' +
                '<td><button type="button" class="btn btn-primary" onclick="deleteLine(this);">삭제</button></td></tr>');
        }


        if(industryInfo.dtlDesc != null && industryInfo.dtlDesc != ''){
            var text = safeTagToHtmlTag(industryInfo.dtlDesc);
            var replaceBr = text.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');
            $('textarea#dtlDesc').val(replaceBr);
        }


        $('input:radio[name=langCd]').filter('[value=' + industryInfo.langCd + ']').trigger('click');


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
* 산업군 등록
* */
$('#btnRegist').on('click', function () {
    $('#DetailModalLabel').html('산업군 등록');
    $('#seqId').val("");
    document.getElementById('industryForm').reset();
    $('#indSysTable > tbody').empty();
    $('#btn_sunmit').css("display", "");
    //$('input:radio[name=langCd]').filter('[value=KR]').trigger('click');

});

$('#DetailModal').on('hidden.bs.modal', function (e) {

    g_badgeCount = 1;

});

/*
* 산업군 삭제
* */
$('#btnDelete').on('click', function () {

    if ($('input[name=indSelected]:checked').length < 1) {
        alert('삭제할 산업군 대상을 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=indSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    if (confirm('선택된 산업군을 삭제하시겠습니까?')) {
        $.post('/industry/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 산업군이 삭제되었습니다.');
            } else {
                alert('삭제하지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            }
            location.reload();
        }).fail(function(xhr, textStatus, errorThrown) {
            if(xhr.status =='403'){
                alert("해당 기능에 대한 권한이 없습니다.");
            }
        });
    }
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