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
    * popup 전체 선택
    * */
    $('input[name=selectAll]').on('click', function () {
        var checked = $(this).prop('checked');
        $('input[name=popupSelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });

    /*popup 등록*/
    $('#popupForm').on('submit', function (event) {
        event.preventDefault();
        $('iframe[src="/se2/SmartEditor2Skin.html"]').contents().find('iframe[src="smart_editor2_inputarea.html"]').contents().find('.se2_inputarea *').css('font-family','');

        oEditors.getById["popupCont"].exec("UPDATE_CONTENTS_FIELD",[]);

        if ($('#popupTitl').val().trim() == '') {
            alert('제목을 입력해주세요.');
            $('#popupTitl').focus();
            return false;
        }

        var popupContent = $('#popupCont').val();
        

        if(popupContent =="" || popupContent == null || popupContent == "<p><br></p>"
        		|| popupContent == "&nbsp;" || popupContent == "<p>&nbsp;</p>"){
        	alert('내용을 입력해주세요.');
        	return false;
        }

        var reqData = new FormData(this);

        $.ajax({
            url : '/popup/insert',
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


/* DatePicker  */

$( function() {
	
	$.datepicker.setDefaults({
		dateFormat: 'yymmdd'
		,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
	});
	
	$( "#startDate" ).datepicker();
    $( "#endDate" ).datepicker();
});

/*팝업 상세보기*/
$('button[name=btnViewDetail]').on('click', function () {
	
    document.getElementById('popupForm').reset();

    $('#DetailModalLabel').html('팝업 상세 ('+$('#deviceCd').val()+'): ' + $(this).attr('data-id'));

    $.get('/popup/detail', { popupSeqId: $(this).attr('data-id') }, function (result) {

        if (result.status == 'fail') {
            alert('ITM CLIP 정보를 가져오지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            $('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
        }
        
        var popupInfo = result.data;

        $('iframe').remove();
        createSE2("detail", popupInfo);

        $('input:radio[name=statCd]').filter('[value=' + popupInfo.statCd + ']').trigger('click');
        $('input:radio[name=popupLnkYn]').filter('[value=' + popupInfo.popupLnkYn + ']').trigger('click');


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
* 팝업 등록
* */
$('#btnRegist').on('click', function () {
    $('#DetailModalLabel').html('팝업 등록 ('+$('#deviceCd').val()+')');
    
    document.getElementById("popupForm").reset();
    
    $('iframe').remove();
    createSE2("regist", null);
    
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
* 팝업 삭제
* */
$('#btnDelete').on('click', function () {
    if ($('input[name=popupSelected]:checked').length < 1) {
        alert('삭제할 ITM CLIP 대상을 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=popupSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    if (confirm('선택된 팝업을 삭제하시겠습니까?')) {
        $.post('/popup/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 팝업이 삭제되었습니다.');
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