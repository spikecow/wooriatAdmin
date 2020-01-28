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
     * 개인정보 처리방침 전체 선택
     * */
     $('input[name=selectAll]').on('click', function () {
         var checked = $(this).prop('checked');
         $('input[name=termsSelected]').each(function () {
             if (checked)
                 $(this).prop('checked', !($(this).prop('checked') === checked));
             else
                 $(this).prop('checked', ($(this).prop('checked') === checked));
         });
     });
	
	/* 개인정보처리방침 등록 */
	$('#termsForm').on('submit', function (event) {
        event.preventDefault();
        
        oEditors.getById["termsCont"].exec("UPDATE_CONTENTS_FIELD",[]);
        oEditors.getById["mblTermsCont"].exec("UPDATE_CONTENTS_FIELD",[]);
        
        
        if($('#termsStartDtm').val().trim() == ''){
        	alert('게시일을 입력해주세요.');
        	$('#termsStartDtm').focus();
        	return false;
        }
        
        
        if($('#termsDtlDesc').val().trim() == ''){
        	alert('Summary를 입력해주세요.');
        	$('#termsDtlDesc').focus();
        	return false;
        }
        
        var termsContent = $('#termsCont').val();
        
        if(termsContent =="" || termsContent == null || termsContent == "<p><br></p>" 
	    		|| termsContent == "&nbsp;" || termsContent == "<p>&nbsp;</p>"){
	    	alert('내용(PC용)을 입력해주세요.');
	    	return false;
        }
        
        var mblTermsContent = $('#mblTermsCont').val();
        
        if(mblTermsContent =="" || mblTermsContent == null || mblTermsContent == "<p><br></p>" 
	    		|| mblTermsContent == "&nbsp;" || mblTermsContent == "<p>&nbsp;</p>"){
	    	alert('내용(Mobile용)을 입력해주세요.');
	    	return false;
        }

        var reqData = new FormData(this);

        $.ajax({
            url : '/terms/insert',
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


/*
* 보도자료 등록
* */
$('#btnRegist').on('click', function () {
    $('#DetailModalLabel').html('개인정보처리방침 등록');
    
    document.getElementById("termsForm").reset();
    
    $('iframe').remove();
    createSE2("regist", null);
    createSE2_Mbl("regist", null);
    
    $('input:radio[name=langCd]').filter('[value=KR]').trigger('click');

});


$( function() {
	
	$.datepicker.setDefaults({
		dateFormat: 'yymmdd'
		,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
	});
	
	//시작일
	$( "#termsStartDtm" ).datepicker({
		onClose: function(selectedDate){
			$('#termsEndDtm').datepicker("option", "minDate", selectedDate);
		}
	});
    
	//종료일
	$( "#termsEndDtm" ).datepicker({
		onClose: function(selectedDate){
			$('#termsStartDtm').datepicker("option", "maxDate", selectedDate);
		}
	});
	
    
});

/*개인정보처리방침 상세보기*/
$('button[name=btnViewDetail]').on('click', function () {
	
    document.getElementById('termsForm').reset();
    
    
	//oEditors.getById["prCont"].exec("SET_IR", [""]);
    $('#DetailModalLabel').html('개인정보처리방침 상세 : ' + $(this).attr('data-id'));

    $.get('/terms/detail', { termsSeqId: $(this).attr('data-id') }, function (result) {

        if (result.status == 'fail') {
            alert('개인정보처리방침 정보를 가져오지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            $('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
        }
        
        var termsInfo = result.data;

        
        $('iframe').remove();
        createSE2("detail", termsInfo);
        createSE2_Mbl("detail", termsInfo);

        $('input:radio[name=langCd]').filter('[value=' + termsInfo.langCd + ']').trigger('click');

    }, 'json').fail(function(xhr, textStatus, errorThrown) {
        if(xhr.status =='403'){
            alert("해당 기능에 대한 권한이 없습니다.");
        }
    });
});

/*
* 개인정보처리방침 삭제
* */
$('#btnDelete').on('click', function () {
    if ($('input[name=termsSelected]:checked').length < 1) {
        alert('삭제할 보도자료 대상을 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=termsSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    if (confirm('선택된 보도자료를 삭제하시겠습니까?')) {
        $.post('/terms/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 보도자료가 삭제되었습니다.');
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
