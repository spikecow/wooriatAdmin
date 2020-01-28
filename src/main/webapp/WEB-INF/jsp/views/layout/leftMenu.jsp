
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


	<aside class="main-sidebar">
		<section class="sidebar">
			<ul class="sidebar-menu active">
				<li class="header">MAIN NAVIGATION</li>

				<li class="treeview" data-uri="/main">
					<a href="">
						<i class="fa fa-anchor"></i> <span>메인관리</span>
					</a>
				</li>
				<li class="treeview" data-uri="/main" style="margin-left: 10px;">
					<a href="">
						<span class="icon expand-icon glyphicon glyphicon-plus-sign"></span>
						<span>PC</span>
					</a>
				</li>
				<li class="treeview" data-uri="/main" style="margin-left: 20px;">
					<a href="/main/setup?mainCd=PC">
						<span class="icon expand-icon glyphicon glyphicon-plus"></span>
						<span>국문</span>
					</a>
				</li>
				<li class="treeview" data-uri="/main" style="margin-left: 20px;">
					<a href="/main/setup?mainCd=PCEN">
						<span class="icon expand-icon glyphicon glyphicon-plus"></span>
						<span>영문</span>
					</a>
				</li>
				<li class="treeview" data-uri="/main" style="margin-left: 10px;">
					<a href="">
					<span class="icon expand-icon glyphicon glyphicon-plus-sign"></span>
					<span>MOBILE</span>
					</a>
				</li>
				<li class="treeview" data-uri="/main" style="margin-left: 20px;">
					<a href="/main/setup?mainCd=MOBILE">
					<span class="icon expand-icon glyphicon glyphicon-plus"></span>
					<span>국문</span>
					</a>
				</li>
				<li class="treeview" data-uri="/main" style="margin-left: 20px;">
					<a href="/main/setup?mainCd=MOBILEEN">
					<span class="icon expand-icon glyphicon glyphicon-plus"></span>
					<span>영문</span>
					</a>
				</li>

				<li class="treeview" data-uri="/service">
					<a href="/service/list">
						<i class="fa fa-cube"></i> <span>서비스</span>
					</a>
				</li>
				<li class="treeview" data-uri="/industry">
						<a href="/industry/list">
						<i class="fa fa-industry"></i> <span>산업군</span>
						</a>
				</li>
				<li class="treeview" data-uri="/case">
					<a href="/case/list">
						<i class="fa fa-archive"></i> <span>구축사례</span>
					</a>
				</li>
				<li class="treeview" data-uri="/dist">
					<a href="#">
						<i class="fa fa-film"></i> <span>IT Distribution</span>
					</a>
				</li>
				<li class="treeview" data-uri="/dist" style="margin-left: 10px;">
					<a href="/dist/list?menuCd=P">
					<span class="icon expand-icon glyphicon glyphicon-plus"></span>
					<span>Partner</span>
					</a>
				</li>
				<li class="treeview" data-uri="/dist" style="margin-left: 10px;">
					<a href="/dist/list?menuCd=S">
					<span class="icon expand-icon glyphicon glyphicon-plus"></span>
					<span>Solution</span>
					</a>
				</li>
				<li class="treeview" data-uri="/">
					<a href="/pr/list">
						<i class="fa fa-newspaper-o"></i> <span>보도자료</span>
					</a>
				</li>
				<li class="treeview" data-uri="/">
					<a href="/prclip/list">
						<i class="fa fa-newspaper-o"></i> <span>ITM CLIP</span>
					</a>
				</li>
				<li class="treeview" data-uri="/">
					<a href="/prdoc/list">
						<i class="fa fa-file-pdf-o"></i> <span>홍보자료</span>
					</a>
				</li>
				<li class="treeview" data-uri="/sales">
					<a href="/sales/list">
						<i class="fa fa-user"></i> <span>영업담당자</span>
					</a>
				</li>
				<li class="treeview" data-uri="/consult">
					<a href="/consult/list">
						<i class="fa fa-warning"></i> <span>상담제보</span>
					</a>
				</li>
				<li class="treeview" data-uri="/">
					<a href="/inquiry/list">
						<i class="fa fa-question-circle"></i> <span>영업문의</span>
					</a>
				</li>
				<li class="treeview" data-uri="/">
					<a href="/terms/list">
						<i class="fa fa-street-view"></i> <span>개인정보 처리방침</span>
					</a>
				</li>

				<c:if test="${ userLvl eq 'S' }">
				<li class="treeview" data-uri="/admin">
					<a href="/manager/list">
						<i class="fa fa-users"></i> <span>관리자</span>
					</a>
				</li>
				</c:if>
 				<li class="treeview" data-uri="/popup">
					<a href="/popup/list">
						<i class="fa fa-dashboard"></i> <span>팝업</span>
					</a>
				</li>
				
			</ul>
		</section>
	</aside>
