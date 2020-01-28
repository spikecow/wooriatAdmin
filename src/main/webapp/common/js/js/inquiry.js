
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
        $('input[name=inquirySelected]').each(function () {
            if (checked)
                $(this).prop('checked', !($(this).prop('checked') === checked));
            else
                $(this).prop('checked', ($(this).prop('checked') === checked));
        });
    });
	
});

/* 영업 문의사항 상세보기 */

$('button[name=btnViewDetail]').on('click', function(){
	
	document.getElementById('iquiryForm').reset();
	
	$.get('/inquiry/detail', {seqId : $(this).attr('data-id')} , function (result){
    	
    	if(result.status == 'fail'){
    		alert('영업문의 정보를 가져오지 못했습니다. \n반복 시 관리자에게 문의해 주세요.');
    		$('#DetailModal button[data-dismiss=modal]').trigger('click');
            return false;
    	}
    	
    	var inquiryInfo = result.data;
    	$('#inquiryDtm').val(inquiryInfo.inquiryDtm);
    	$('#inquiryNm').val(inquiryInfo.inquiryNm);
    	
    	if(inquiryInfo.agrYn1 == 'Y'){
    		$('#agreeYn1').prop('checked', true);
    		$('#agreeYn2').prop('checked', true);
    		$('#agreeYn3').prop('checked', true);
    		$('#agreeYn4').prop('checked', true);
    		$('#agreeYn5').prop('checked', true);
    	}else{
    		$('#agreeYn1').prop('checked', false);
    		$('#agreeYn2').prop('checked', false);
    		$('#agreeYn3').prop('checked', false);
    		$('#agreeYn4').prop('checked', false);
    		$('#agreeYn5').prop('checked', false);
    	}
    	
/*    	if(inquiryInfo.agrYn2 == 'Y'){
    		$('#agreeYn2').prop('checked', true);
    	}else{
    		$('#agreeYn2').prop('checked', false);
    	}
    	
    	if(inquiryInfo.agrYn3 == 'Y'){
    		$('#agreeYn3').prop('checked', true);
    	}else{
    		$('#agreeYn3').prop('checked', false);
    	}
    	
    	if(inquiryInfo.agrYn4 == 'Y'){
    		$('#agreeYn4').prop('checked', true);
    	}else{
    		$('#agreeYn4').prop('checked', false);
    	}
    	
    	if(inquiryInfo.agrYn5 == 'Y'){
    		$('#agreeYn5').prop('checked', true);
    	}else{
    		$('#agreeYn5').prop('checked', false);
    	}
*/    	
    	
    	$('#phone').val(inquiryInfo.phone);
    	$('#email').val(inquiryInfo.email);
    	$('#inquiryCont').val(inquiryInfo.inquiryCont);

    }, 'json').fail(function(xhr, textStatus, errorThrown){
    	if(xhr.status =='403'){
            alert("해당 기능에 대한 권한이 없습니다.");
        }
    });
});

$('#btnDelete').on('click', function () {


    if ($('input[name=inquirySelected]:checked').length < 1) {
        alert('삭제할 영업담당자를 먼저 선택해 주세요.');
        return false;
    }

    var delList = [];
    $('input[name=inquirySelected]:checked').each(function () {
        delList.push($(this).val());
    });
    
    if (confirm('선택된 문의사항을 삭제하시겠습니까?')) {
        $.post('/inquiry/deleteList', {
            delList: delList.join()
        }, function(data) {
            if (data.status == 'success') {
                alert('선택된 문의사항이 삭제되었습니다.');
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

/* CheckBox ReadOnly */
function f_checkbox(checked, name){
	document.getElementById(name).checked = !checked;
}

