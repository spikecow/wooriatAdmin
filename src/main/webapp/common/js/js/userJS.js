function login() {
	var userId = $('#userId').val();
	var password = $('#userPwd').val();
	
	if (userId == '') {
		alert('사용자아이디를 입력하세요.');
	} else if (password == '') {
		alert('비밀번호를 입력하세요.');
	} else {
		var url='login';
		var params = {
				"userId"		: userId
				//,"password"		: password
				,"userPw"		:  AES_Encode(password)
				,"returnUrl"	: $("input[name=returnUrl]").val()
		};
		
		/*asyncJson(params, url, function(data, 
			beforeSend: function(){
		    // Show image container
		    $("#loader").show();
		   },
		   complete:function(data){
			    // Hide image container
			    $("#loader").hide();
			}) 
		{
			loginCallback(data);
		});*/
		
		$.ajax({
			url: url,
			type: 'POST',
			data: params,
			beforeSend: function(){
				$("#btnLogin").attr('disabled', true);
			},
			 complete:function(data){
				 $("#btnLogin").attr('disabled', false);
			}
		}).done(function (result) {

			loginCallback(result);
		}).fail(function(xhr, textStatus, errorThrown) {
			if(xhr.status =='403'){
				alert("해당 기능에 대한 권한이 없습니다.");						
			}
		});
	}	
}

function loginCallback(data) {
	if ('success' == data.status) {
		alert('로그인에 성공했습니다.');
		// if (data.returnUrl.length > 0) {
		// 	window.location.href = data.returnUrl;
		// }

		window.location.href = "/";
		// if (/^cs\-/.test(data.userId)) {
		// 	window.location.href = "/member/memberList";
		// } else if ('onair' == data.userId) {
		// 	window.location.href="/onair/onairChatOnly";
		// } else {
		// 	window.location.href = "/dashboard/dashboard";
		// }
	} else if ('fail' == data.status) {
		alert('로그인에 실패했습니다. \n'+data.msg);
	} else{
		//nothing
	}
}

function enterLogin() {
	if (event.keyCode == 13) {
		login();
	}
}

function chagePasswd(){
	
	
	
	/*if ($('#ChgAccount').val().trim() == '') {
		alert('관리자 ID를 입력해 주세요.');
		$('#ChgAccount').focus();
		return false;
	}*/
	
	var oldPasswd = $('#OldPasswd').val().trim();
	var chgPasswd = $('#ChgPasswd').val().trim();
	var chgPasswdCheck =  $('#ChgPasswdCheck').val().trim();
	
	if (oldPasswd == '' ) {
		alert('기존 비밀번호를 입력해 주세요');
		$('#OldPasswd').focus();
		return false;
	}
	
	if (chgPasswd == '' ) {
		alert('변경할 비밀번호를 입력해 주세요');
		$('#ChgPasswd').focus();
		return false;
	}
	
	if (chgPasswdCheck == '' ) {
		alert('비밀번호를 확인해 주세요');
		$('#ChgPasswdCheck').focus();
		return false;
	}
	

	if(chgPasswd !== chgPasswdCheck){
		alert('입력한 비밀번호가 다릅니다.');
		$('#ChgPasswdCheck').focus();
		return false;
	}
	
	if(chgPasswd.length < 8){
		alert('비밀번호는 최소 8자 이상이여야 합니다.');
		$('#ChgPasswd').focus();
		return false;
	}
	
	var reqData = {
			account: 	AES_Encode($('#ChgAccount').val()) ,
			passWd :	AES_Encode($('#OldPasswd').val())  ,
			chgPassWd:	AES_Encode($('#ChgPasswd').val())  ,
	};
	if(confirm("비밀번호를 변경 하시겠습니까 ?") == true){
		$.post('/admin/changePassWd', reqData, function (result) {

			if (result.STATUS == 'F') {
				// 실패 case : 기존 비빌번호 이력
				alert('비밀번호 변경에  실패하였습니다.\n'+result.REASON+'\n반복 시 관리자에게 문의해 주세요.');
				return false;
			}else if(result.STATUS =='IGNORE'){
				alert('비밀번호 변경에 실패하였습니다.\n ' +result.REASON+'\n반복 시 관리자에게 문의해 주세요.');
			}else{
				alert('비밀번호 변경에 성공하였습니다. ');
				window.location.href ="/login";
			}
		}, 'json').fail(function(xhr, textStatus, errorThrown) {
			if(xhr.status =='403'){
				alert("해당 기능에 대한 권한이 없습니다.");						
			}
		});
		
		return false;

	}else{
		return false;					
	}
}
