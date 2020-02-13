<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
  <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <title>WOORIAT ADMIN</title>

      <!-- Tell the browser to be responsive to screen width -->
      <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
      <!-- Bootstrap 3.3.5 -->
      <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
      <!-- Bootstrap 3.3.5 -->
      <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
      <!-- Bootstrap custom css -->
      <link rel="stylesheet" href="/bootstrap/css/custom.css">
      <link rel="stylesheet" href="/dist/css/custom.css">
      <!-- Font Awesome -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
      <!-- Ionicons -->
      <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
      <!-- Theme style -->
      <link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
      <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
      <link rel="stylesheet" href="/dist/css/skins/_all-skins.min.css">


      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

      <!-- jQuery 2.1.4 -->
      <script src="/plugins/jQuery/jQuery-2.1.4.min.js"></script>
      <!-- Bootstrap 3.3.5 -->
      <script src="/bootstrap/js/bootstrap.min.js"></script>

  <%--    <!-- iCheck -->--%>
    <link rel="stylesheet" href="/plugins/iCheck/square/blue.css">

  </head>
  <body class="hold-transition login-page">
    <div class="login-box">
	      <div class="login-logo">
	        <a href="#"><b>우리자산 신탁</b></a>
	      </div><!-- /.login-logo -->
    	
	      <div class="login-box-body" style= "padding-bottom: 50px";>
	        <p class="login-box-msg">로그인 해 주세요.</p>
	        <form action="#" method="post" name="loginform">
	        	<input type="hidden" name="returnUrl" value="${ returnUrl }" />
	          <div class="form-group has-feedback">
	            <input type="text" id="userId" name="userId" class="form-control" placeholder="사용자 아이디" value=""/>
	            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	          </div>
	          <div class="form-group has-feedback">
				<input type="password" id="userPwd" name="userPwd" class="form-control" placeholder="비밀번호" value=""/>
	            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	          </div>
	          <div class="row pull-right">
	          
		     
				<input id="btnLogin" type="button" onclick="javascript:login();" class="btn btn-primary" value="Sign In"/>
	            
	          </div>
	        </form>
	      </div><!-- /.login-box-body -->

    </div><!-- /.login-box -->
	

    <!-- iCheck -->
    <script src="/plugins/iCheck/icheck.min.js"></script>

    <script src="/common/js/js/userJS.js"></script>
    <script src="/customJS/cipher.js"></script>
    <script src="/customJS/gibberish-aes.js"></script>

    <script type="text/javascript">
        document.addEventListener('keydown', function(e){
           if(e.keyCode == 13){
               login();
           }
        });

    </script>
  </body>
</html>
