$(document).ready(function(){
	$("#DetailModal").modal({
		backdrop : false
		, show : false
	});

	$("#DetailModal").on("hidden.bs.modal", function(e){
		//console.log("close");
		$("#preview").hide();
	});

	$('form').attr('autocomplete', 'off');
});

/*
* 파일 업로드 및 미리보기 관련
*/
function fileNameAtTarget(target) {
	var fileName = target.value.trim().length > 0 ? printFileName(target.value) : '';
	$(target).siblings(".bootstrap-filestyle").children("input[type='text']").val(fileName);
}

/*
사용자가 이미지 선택 시 파일 이름 print
*/
function printFileName(obj) {
	var filePath = obj;
	var strs = filePath.match(/([^\/\\]+)$/);
	var fileName = strs[1];

	return fileName;
}

function previewOnTargetImage(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('.preview-modal-lg .modal-body .preview').each(function () {

				if (this.tagName == 'IMG'){
					$(this).attr('src', e.target.result).show();
				}else{
					$(this).attr('src', '').hide();
				}
			});
		}

		reader.readAsDataURL(input.files[0]);
	}
}


function safeTagToHtmlTag(content) {
	
	if(content == '' || content == null){
		return null;
	}
	
	var result = content.replace(/&gt;/gi, ">");
	result = result.replace(/&lt;/gi, "<");
	result = result.replace(/&quot;/gi, "\"");
	result = result.replace(/&amp;/gi, "&");
	result = result.replace(/&#039;/gi, "'");
	result = result.replace(/&&#40;/gi, "(");
	result = result.replace(/&&#41;/gi, ")");
	result = result.replace(/&#39;/gi, "'");
	/* 개인정보처리방침 termsDescDtl 에서 사용 - 확인 필요 */
	result = result.replace(/&#39;/gi, "'");
	result = result.replace(/&#40;/gi, "(");
	result = result.replace(/&#41;/gi, ")");
	return result;
}

function rtrim(value){
	return value.replace(/^\s+/,"");
}


function logout() {
	var url='/admin/logout';
	var params = {};

	asyncJson(params, url, function(data) {
		window.location.href="/login";
	});
}

function asyncJson(params, url, callBack){

	var options = {
		url         : url
		,contentType :'application/x-www-form-urlencoded;charset=UTF-8'
		,type        :'POST'
		,dataType    :'json'
		,complete    : function (data){
			try {
				var jsonData = JSON.parse(data.responseText);

				if(jsonData.errorType == null || jsonData.errorType == 'undefined' || jsonData.errorType ==''){
					if(typeof callBack=='function'){
						callBack(jsonData);
					}
				}else{
					if(jsonData.errorType=='sessionExpired'){
						alert("세션이 만료되었습니다.");
						window.location.href="../index";
					}

				}
			} catch (err) {
				//alert(err);
			}
		}
		,error       : errorCtl
	};
	$.ajaxSetup(options);
	$.ajax({data:params});
}

function errorCtl(xhr, textStatus, errorThrown){
	//alert("___"+xhr.html + xhr.responseText+"/"+textStatus +'/An error occurred! ' + (errorThrown ? errorThrown : xhr.status));
	if(xhr.status =='403'){
		alert("해당 기능에 대한 권한이 없습니다.");
	}
	console.log("___"+xhr.html + xhr.responseText+"/"+textStatus +'/An error occurred! ' + (errorThrown ? errorThrown : xhr.status));
}