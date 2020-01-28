var optionText;
$(document).ready(function(){
    detail();

    $('button[name=indAdd]').on('click', function () {
        var tr = $(".groupTr");
        tr = tr.length + 1;

        if(tr > 10){
            alert("노출순서는 최대 10개 까지 가능합니다.");
            return;
        }

        if(tr <= 1) {
            $('#trIndAdd').after(
                '<tr class="groupTr"><td class="text-center">노출순서 ' + tr + '</td><td><select name="indSeqId" class="form-control">' +
                optionText+'</select></td>' +
                '<td class="text-right"><button type="button" class="btn btn-primary" onclick="deleteLine(this);">삭제</button></td></td>'
            );
        }else {
            $('.groupTr').eq(tr-2).after(
                '<tr class="groupTr"><td class="text-center">노출순서 ' + tr + '</td><td><select name="indSeqId" class="form-control">' +
                optionText+'</select></td>' +
                '<td class="text-right"><button type="button" class="btn btn-primary" onclick="deleteLine(this);">삭제</button></td></td>'
            );
        }

    });

});

function deleteLine(obj) {
    var tr = $(obj).parent().parent();

    tr.remove();
}

function detail(){
    document.getElementById('mainForm').reset();
    $.get('/main/detail', { mainCd:$('#mainCd').val() }, function (result) {

        if (result.status == 'fail') {
            alert('메인 정보를 가져오지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            return false;
        }

        for(var i in result.indList){
            var seqId = result.indList[i].seqId;
            var titleNm = result.indList[i].titleNm;

            optionText += '<option value="'+seqId+'">'+titleNm+'</option>';
        }

        var mainInfo = result.data;
        if(mainInfo != null){
            $('#mainSeqId').val(mainInfo.mainSeqId);
            $('#mainCd').val(mainInfo.mainCd);
            $('#indDesc').val(mainInfo.indDesc);
            $('#newsDesc').val(mainInfo.newsDesc);
            $('#partnerImgUrl1').val(mainInfo.partnerImgUrl1);
            $('#partnerImgUrl2').val(mainInfo.partnerImgUrl2);
            $('#partnerImgUrl3').val(mainInfo.partnerImgUrl3);
            $('#partnerImgUrl4').val(mainInfo.partnerImgUrl4);
            $('#partnerImgUrl5').val(mainInfo.partnerImgUrl5);

            if(mainInfo.indList != null){
                for(var i in mainInfo.indList){

                    var tr = $(".groupTr");
                    tr = tr.length + 1;

                    if(tr <= 1) {
                        $('#trIndAdd').after(
                            '<tr class="groupTr"><td class="text-center">노출순서 ' + tr + '</td><td><select name="indSeqId" class="form-control">' +
                            optionText+'</select></td>' +
                            '<td class="text-right"><button type="button" class="btn btn-primary" onclick="deleteLine(this);">삭제</button></td></td>'
                        );
                    }else {
                        $('.groupTr').eq(tr-2).after(
                            '<tr class="groupTr"><td class="text-center">노출순서 ' + tr + '</td><td><select name="indSeqId" class="form-control">' +
                            optionText+'</select></td>' +
                            '<td class="text-right"><button type="button" class="btn btn-primary" onclick="deleteLine(this);">삭제</button></td></td>'
                        );
                    }
                    $("select[name='indSeqId'] option[value="+mainInfo.indList[i].seqId+"]").eq(i).attr("selected", true);
                }
            }
        }


    }, 'json').fail(function(xhr, textStatus, errorThrown) {
        if(xhr.status =='403'){
            alert("해당 기능에 대한 권한이 없습니다.");
        }
    });
}


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
* 등록
* */

$('#mainForm').on('submit', function () {

    if(!valCheck('#indDesc', 'Industries 소개를 입력해주세요')){
        return false;
    }

    if(!valCheck('#partnerImgUrl1', '파트너 로고 파일을 첨부해주세요.')){
        return false;
    }

    var reqData = new FormData(this);

    $.ajax({
        url : '/main/insert',
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

        alert('등록 되었습니다.');
        location.reload();
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