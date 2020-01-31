
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<aside class="main-sidebar">
		<section class="sidebar">
			<ul class="sidebar-menu active">
				<li class="header">MAIN NAVIGATION</li>

				<c:if test="${ userLvl eq 'S' }">
				<li class="treeview" data-uri="/admin">
					<a href="/user/list">
						<i class="fa fa-users"></i> <span>회원관리</span>
					</a>
				</li>
				</c:if>
				<li class="treeview" data-uri="/admin">
					<a href="/SaleItem/list">
						<i class="fa fa-users"></i> <span>분양물건정보</span>
					</a>
				</li>
				<li class="treeview" data-uri="/admin">
					<a href="/ShortSell/list">
						<i class="fa fa-users"></i> <span>공매물건정보</span>
					</a>
				</li>
			</ul>
		</section>
	</aside>
