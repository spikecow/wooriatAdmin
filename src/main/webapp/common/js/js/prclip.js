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

    /*
    * ITM CLIP 전체 선택
    * */
    $('input[name=selectAll]').on('click', function () {
        var checked = $(this).prop('checked');
        $('input[name=prClipSelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });

    /*ITM CLIP 등록*/
    $('#prClipForm').on('submit', function (event) {
        event.preventDefault();
        $('iframe[src="/se2/SmartEditor2Skin.html"]').contents().find('iframe[src="smart_editor2_inputarea.html"]').contents().find('.se2_inputarea *').css('font-family','');

        oEditors.getById["prClipCont"].exec("UPDATE_CONTENTS_FIELD",[]);

        if ($('#prClipTitl').val().trim() == '') {
            alert('ITM CLIP 타이틀을 입력해주세요.');
            $('#prClipTitl').focus();
            return false;
        }

        if ($('#postDt').val().trim() == '') {
            alert('ITM CLIP 게시일을 입력해주세요.');
            $('#postDt').focus();
            return false;
        }
        
        if($('#summary').val().trim() == ''){
        	alert('ITM CLIP 요약 내용을 입력해주세요.');
        	$('#summary').focus();
        	return false;
        }


        var prClipContent = $('#prClipCont').val();
        

        if(prClipContent =="" || prClipContent == null || prClipContent == "<p><br></p>"
        		|| prClipContent == "&nbsp;" || prClipContent == "<p>&nbsp;</p>"){
        	alert('ITM CLIP 내용을 입력해주세요.');
        	return false;
        }

        var reqData = new FormData(this);

        $.ajax({
            url : '/prclip/insert',
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

        $.post('/prclip/updateUseYn', {
            useYn:$(this).attr('data-value')
            ,prClipSeqId:$(this).attr('data-id')
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


/* DatePicker  */

$( function() {
	
	$.datepicker.setDefaults({
		dateFormat: 'yymmdd'
		,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
	});
	
	$( "#postDt" ).datepicker();
    
    
});

/*ITM CLIP 상세보기*/
$('button[name=btnViewDetail]').on('click', function () {
	
    document.getElementById('prClipForm').reset();

    $('#DetailModalLabel').html('ITM CLIP 상세 : ' + $(this).attr('data-id'));

    $.get('/prclip/detail', { prClipSeqId: $(this).attr('data-id') }, function (result) {

        if (result.status == 'fail') {
            alert('ITM CLIP 정보를 가져오지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            $('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
        }
        
        var prClipInfo = result.data;

        $('iframe').remove();
        createSE2("detail", prClipInfo);

        $('input:radio[name=langCd]').filter('[value=' + prClipInfo.langCd + ']').trigger('click');



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
$("label[id^='btnImagePreview1']").on("click", function() {
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
* ITM CLIP 등록
* */
$('#btnRegist').on('click', function () {
    $('#DetailModalLabel').html('ITM CLIP 등록');
    
    document.getElementById("prClipForm").reset();
    
    $('iframe').remove();
    createSE2("regist", null);
    
    $('input:radio[name=langCd]').filter('[value=KR]').trigger('click');

});

/*$('#DetailModal').on('show.bs.modal', function (e) {
	  
	if(isSECreate == false){
		createSE2();
	}
	  
});*/

$('#DetailModal').on('hidden.bs.modal', function (e) {
	  // do something...
	  
});

/*
* ITM CLIP 삭제
* */
$('#btnDelete').on('click', function () {
    if ($('input[name=prClipSelected]:checked').length < 1) {
        alert('삭제할 ITM CLIP 대상을 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=prClipSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    if (confirm('선택된 ITM CLIP을 삭제하시겠습니까?')) {
        $.post('/prclip/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 ITM CLIP가 삭제되었습니다.');
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