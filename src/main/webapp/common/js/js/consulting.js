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
    * 제보자료 전체 선택
    * */
    $('input[name=selectAll]').on('click', function () {

        var checked = $(this).prop('checked');
        $('input[name=consultSelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });
	
});

function refreshConsultInfo(operator){
	
	var URL;
	var s_msg;
	var e_msg;
	var confirm_msg;
	
	if(operator == 'insert'){
		URL = '/consult/insert';
		confirm_msg = '등록하시겠습니까?';
		s_msg = '등록되었습니다.';
		e_msg = '등록에 실패하였습니다.';
	}
	else if(operator == 'update'){
		URL = '/consult/update';
		confirm_msg = '수정하시겠습니까?';
		s_msg = '수정되었습니다.';
		e_msg = '수정에 실패하였습니다.';
	}

	
	var reqData = new FormData(document.getElementById("consultInfoForm"));
	
	if(confirm(confirm_msg)){
		$.ajax({
			url : URL,
			type : 'POST',
			async: true,
	        cache: false,
			data : reqData,
			enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false,
	        success : function(data){
	        }, error : function(error){

	        }
		}).done(function (result){
			if(result.status == 'success'){
				//alert('등록되었습니다.');
				alert(s_msg);
				$('#DetailModal button[data-dismiss=modal]').trigger('click');
			    location.reload();
				return true;
			}
			else if(result.status == 'duplicate'){
				alert('이미 등록된 ID 입니다.');
				return false;
			}
			else if(result.status == 'notexsit'){
				alert('존재하지 않는 ID 입니다.');
				return false;
			}
			else if(result.status == 'fail'){
				//alert('등록에 실패했습니다. [ ' + result.errorMsg + ']\n반복 시 관리자에게 문의 바랍니다.');
				alert(e_msg + '[ ' + result.errorMsg + ']\n반복 시 관리자에게 문의 바랍니다.')
				return false;
			}
		}).fail(function(xhr, textStatus, errorThrown){
			if(xhr.status == '403'){
				alert('해당 기능에 대한 권한이 없습니다.');
			}
		});
	}
	
}

/*
 * 제보자료 상세조회
 * */
$('button[name=btnViewDetail]').on('click', function(){
	$('#DetailModalLabel').html('제보자료 상세조회');
	
    
	$("#insertConsultInfo").hide();
    $("#updateConsultInfo").show();
    
    $("#tr_cretDt").show();
    $("#tr_cretDtDiv").show();
    
    document.getElementById('consultInfoForm').reset();
    
    $.get('/consult/detail', {seqId : $(this).attr('data-id')} , function (result){
    	
    	if(result.status == 'fail'){
    		alert('제보자료를 가져오지 못했습니다. \n반복 시 관리자에게 문의해 주세요.');
    		$('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
    	}
    	
    	var consultInfo = result.data;
    	$('#titl').val(consultInfo.titl);
    	$('#seqId').val(consultInfo.seqId);
    	$('#reportNm').val(consultInfo.reportNm);
    	$('#phone').val(consultInfo.phone);
    	$('#email').val(consultInfo.email);
    	$('#langCd').val(consultInfo.langCd);
        $('#email').val(consultInfo.email);
        $('#phone').val(consultInfo.phone);
        $('#agrYn1').val(consultInfo.agrYn1);
        $('#agrYn2').val(consultInfo.agrYn2);
        $('#agrYn3').val(consultInfo.agrYn3);
        $('#agrYn4').val(consultInfo.agrYn4);

        
        $('#reportCont').val(consultInfo.reportCont);
        $('#attachFileNm').val(consultInfo.attachFileNm);
        $('#attachFileLnkUrl').val(consultInfo.attachFileLnkUrl);
        $('#tr_cretDt').val(consultInfo.reportDtm);
        
        
    }, 'json').fail(function(xhr, textStatus, errorThrown){
    	if(xhr.status =='403'){
            alert("해당 기능에 대한 권한이 없습니다.");
        }
    });
    
});

/*
* 제보자료 등록
* */
$('#btnRegist').on('click', function () {
    $('#DetailModalLabel').html('제보자료 등록');
    
    $("#tr_cretDt").hide();
    $("#tr_cretDtDiv").hide();
    
    $("#updateConsultInfo").hide();
    $("#insertConsultInfo").show();
    
    document.getElementById('consultInfoForm').reset();
});


$("input[type=file]").on('change', function() {
    fileNameAtTarget(this);
});


/*
 * 관리자 삭제 
 * */

$('#btnDelete').on('click', function () {

    if ($('input[name=consultSelected]:checked').length < 1) {
        alert('삭제할 제보자료 대상을 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=consultSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    
    if (confirm('선택된 제보자료를 삭제하시겠습니까?')) {
        $.post('/consult/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 제보자료가 삭제되었습니다.');
                location.reload();
            } else {
                alert('삭제하지 못했습니다.\n반복 시 관리자에게 문의해 주세요.');
            }
        }).fail(function(xhr, textStatus, errorThrown) {
            if(xhr.status =='403'){
                alert("해당 기능에 대한 권한이 없습니다.");
            }
        });
    }
});


/* FileDownload */
$('#fileDownload').on('click', function (e){
    e.preventDefault();
	var readUrl = $('#attachFileLnkUrl').val();
    readUrl.split('/').pop()
	// $('#attachFileDown').attr('href', "data:"+readUrl);
    $('#attachFileDown').attr('href', readUrl);
    $('#attachFileDown').attr('download',readUrl.split('/').pop());
	$('#attachFileDown').get(0).click();
    // window.location.href = readUrl;

});