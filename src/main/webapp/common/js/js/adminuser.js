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
    * 산업군 전체 선택
    * */
    $('input[name=selectAll]').on('click', function () {

        var checked = $(this).prop('checked');
        $('input[name=admUserSelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });
	
});


function refeshAdmUser(operator){
	
	var URL;
	var s_msg;
	var e_msg;
	var confirm_msg;
	
	if(operator == 'insert'){
		URL = '/manager/insert';
		confirm_msg = '등록하시겠습니까?';
		s_msg = '등록되었습니다.';
		e_msg = '등록에 실패하였습니다.';
	}
	else if(operator == 'update'){
		URL = '/manager/update';
		confirm_msg = '수정하시겠습니까?';
		s_msg = '수정되었습니다.';
		e_msg = '수정에 실패하였습니다.';
	}
	
	if ($('#userId').val().trim() == '') {
        alert('관리자 ID를 입력해주세요.');
        $('#userId').focus();
        return false;
    }
	
	if ($('#userPwd').val().trim() == '') {
        alert('비밀번호를 입력해주세요.');
        $('#userPwd').focus();
        return false;
    }

    var reg2 = /[0-9]/;
    var reg3 = /[!@#$%^&*+\-=]/;
    var reg5 = /[a-z]/;

    var paramCnt = $('#userPwd').val().length;

    if(paramCnt < 6 || paramCnt > 10){
        alert("6~10 자리의 비밀번호를 입력하세요.");
        return false;
    }else{
        if(reg2.test($('#userPwd').val())&&reg3.test($('#userPwd').val())&&reg5.test($('#userPwd').val())){

        }else{

            alert(" 비밀번호는 영문/숫자/허용가능한 특수문자(!@#$%^&*+=-)조합으로 설정해주세요. ");
            return false;
        }
    }

	if ($('#userNm').val().trim() == '') {
        alert('관리자명을 입력해주세요.');
        $('#userNm').focus();
        return false;
    }
	
	if ($('#userDeptNm').val().trim() == '') {
        alert('담당부서명을 입력해주세요.');
        $('#userDeptNm').focus();
        return false;
    }
	
	if ($('#email').val().trim() == '') {
        alert('이메일을 입력해주세요.');
        $('#email').focus();
        return false;
    }
	
	if ($('#phone').val().trim() == '') {
        alert('전화번호를 입력해주세요.');
        $('#phone').focus();
        return false;
    }
	
	if(confirm(confirm_msg)){
		$.ajax({
			//url : '/manager/insert',
			url : URL,
			type : 'POST',
			async: true,
	        cache: false,
			data : $('#adminUserForm').serialize(),
			dataType : 'json'
			
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
* 관리자 등록
* */
$('#btnRegist').on('click', function () {
    $('#DetailModalLabel').html('관리자 등록');
    
    $("#th_cretId").hide();
    $("#th_cretDt").hide();
    $("#th_mdfyId").hide();
    $("#th_mdfyDt").hide();
    
    $("#updateAdmUser").hide();
    $("#insertAdmUser").show();
    
    document.getElementById('adminUserForm').reset();
});	

/*
 * 관리자 상세조회
 * */
$('button[name=btnViewDetail]').on('click', function(){
	$('#DetailModalLabel').html('관리자 상세조회');
	
    $("#th_cretId").show();
    $("#th_cretDt").show();
    $("#th_mdfyId").show();
    $("#th_mdfyDt").show();
    
	$("#insertAdmUser").hide();
    $("#updateAdmUser").show();
    
    document.getElementById('adminUserForm').reset();
    
    $.get('/manager/detail', {userId : $(this).attr('data-id')} , function (result){
    	
    	if(result.status == 'fail'){
    		alert('관리자 정보를 가져오지 못했습니다. \n반복 시 관리자에게 문의해 주세요.');
    		$('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
    	}
    	
    	var admUserInfo = result.data;
    	
    	$('#userId').val(admUserInfo.userId);
        $('#userPwd').val(admUserInfo.userPwd);
        $('#userNm').val(admUserInfo.userNm);
        $('#userDeptNm').val(admUserInfo.userDeptNm);
        $('#email').val(admUserInfo.email);
        $('#phone').val(admUserInfo.phone);
        $('#authDivCd').val(admUserInfo.authDivCd);
        $('#tr_cretId').val(admUserInfo.cretUserId);
        $('#tr_cretDt').val(admUserInfo.cretDtm);
        $('#tr_mdfyId').val(admUserInfo.mdfyUserId);
        $('#tr_mdfyDt').val(admUserInfo.mdfyDtm);
        
    	
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


    if ($('input[name=admUserSelected]:checked').length < 1) {
        alert('삭제할 산업군 대상을 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=admUserSelected]:checked').each(function () {
        delList.push($(this).val());
    });

    
    
    if (confirm('선택된 관리자를 삭제하시겠습니까?')) {
        $.post('/manager/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 관리자가 삭제되었습니다.');
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
