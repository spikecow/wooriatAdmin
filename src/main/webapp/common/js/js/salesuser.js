/**
 * 
 */

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
    * 담당자 전체 선택
    * */
    $('input[name=selectAll]').on('click', function () {

        var checked = $(this).prop('checked');
        $('input[name=salesUserSelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });
	
});


function refreshSalesUser(operator){
	
	var URL;
	var s_msg;
	var e_msg;
	var confirm_msg;
	
	if(operator == 'insert'){
		URL = '/sales/insert';
		confirm_msg = '등록하시겠습니까?';
		s_msg = '등록되었습니다.';
		e_msg = '등록에 실패하였습니다.';
	}
	else if(operator == 'update'){
		URL = '/sales/update';
		confirm_msg = '수정하시겠습니까?';
		s_msg = '수정되었습니다.';
		e_msg = '수정에 실패하였습니다.';
	}
	
	if($('#enNm').val().trim() == ''){
		alert('영문이름을 입력해주세요.');
		$('#enNm').focus();
		return false;
	}
	
	if($('#krNm').val().trim() == ''){
		alert('한글이름을 입력해주세요.');
		$('#krNm').focus();
		return false;
	}
	
	if($('#enDeptNm').val().trim() == ''){
		alert('영문담당부서명을 입력해주세요.');
		$('#enDeptNm').focus();
		return false;
	}
	
	if($('#krDeptNm').val().trim() == ''){
		alert('한글담당부서명을 입력해주세요.');
		$('#krDeptNm').focus();
		return false;
	}
	
	if($('#email').val().trim() == ''){
		alert('email을 입력해주세요.');
		$('#email').focus();
		return false;
	}
	
	if($('#phone').val().trim() == ''){
		alert('전화번호를 입력해주세요.');
		$('#phone').focus();
		return false;
	}
		
//	if ($('#enNm').val().trim() == '') {
//        alert('영문이름을 입력해주세요.');
//        $('#userId').focus();
//        return false;
//    }
//	
//	if ($('#userPwd').val().trim() == '') {
//        alert('비밀번호를 입력해주세요.');
//        $('#userPwd').focus();
//        return false;
//    }
//	
//	if ($('#userNm').val().trim() == '') {
//        alert('관리자명을 입력해주세요.');
//        $('#userNm').focus();
//        return false;
//    }
	
	var reqData = new FormData(document.getElementById("salesUserForm"));
	
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
* 영업담당자 등록
* */
$('#btnRegist').on('click', function () {
    $('#DetailModalLabel').html('관리자 등록');
    
    $("#div_cretId").hide();
    $("#div_cretDt").hide();
    $("#div_mdfyId").hide();
    $("#div_mdfyDt").hide();
    
    $("#updateSalesUser").hide();
    $("#insertSalesUser").show();
    $("#imgPreview").attr('src', '');
    
    document.getElementById("salesUserForm").reset();

});	

/*
 * 영업담당자 상세조회
 * */
$('button[name=btnViewDetail]').on('click', function(){
	$('#DetailModalLabel').html('관리자 상세조회');
	
    $("#div_cretId").show();
    $("#div_cretDt").show();
    $("#div_mdfyId").show();
    $("#div_mdfyDt").show();
    
	$("#insertSalesUser").hide();
    $("#updateSalesUser").show();
    
    document.getElementById('salesUserForm').reset();
    
    $.get('/sales/detail', {saleUserSeqId : $(this).attr('data-id')} , function (result){
    	
    	if(result.status == 'fail'){
    		alert('관리자 정보를 가져오지 못했습니다. \n반복 시 관리자에게 문의해 주세요.');
    		$('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
    	}
    	
    	var salesUserInfo = result.data;
    	$('#profilImgUrl').val(salesUserInfo.profilImgUrl);
    	$('#saleUserSeqId').val(salesUserInfo.saleUserSeqId);
    	$('#enNm').val(salesUserInfo.enNm);
    	$('#krNm').val(salesUserInfo.krNm);
    	$('#enDeptNm').val(salesUserInfo.enDeptNm);
    	$('#krDeptNm').val(salesUserInfo.krDeptNm);
        $('#email').val(salesUserInfo.email);
        $('#phone').val(salesUserInfo.phone);
        $('#tr_cretId').val(salesUserInfo.cretUserId);
        $('#tr_cretDt').val(salesUserInfo.cretDtm);
        $('#tr_mdfyId').val(salesUserInfo.mdfyUserId);
        $('#tr_mdfyDt').val(salesUserInfo.mdfyDtm);
        
        
        if(salesUserInfo.profilImgUrl == '' || salesUserInfo.profilImgUrl == null){
        	$('#imgPreview').attr('alt', '등록된 사진이 없습니다.');
        }
        
        $('#imgPreview').attr('src', salesUserInfo.profilImgUrl);

        
    }, 'json').fail(function(xhr, textStatus, errorThrown){
    	if(xhr.status =='403'){
            alert("해당 기능에 대한 권한이 없습니다.");
        }
    });
    
});

/*
 * 관리자 삭제 
 * */

$('#btnDelete').on('click', function () {


    if ($('input[name=salesUserSelected]:checked').length < 1) {
        alert('삭제할 영업담당자를 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=salesUserSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    
    
    if (confirm('선택된 영업담당자를 삭제하시겠습니까?')) {
        $.post('/sales/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 영업담당자가가 삭제되었습니다.');
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

//Image 파일 Preview
$(function (){
	$('#profileImgFile').on('change', function(){
		readURL(this);
	});
});


function readURL(input){
	
	if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#imgPreview').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
    }
}
