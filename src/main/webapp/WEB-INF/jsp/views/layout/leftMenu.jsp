
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" href="/bootstrap/css/style.css">

<aside class="main-sidebar">
<section class="sidebar">
<nav id="sidebar">
        <ul class="list-unstyled components">
			<c:if test="${ userLvl eq 'S' }">
			<li>
				<a href="#menuUserList" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">회원관리</a>
				<ul class="collapse list-unstyled" id="menuUserList">
					<li>
						<a href="/user/list">회원관리</a>
					</li>
				</ul>
			</li>
			</c:if>
			<li>
				<a href="#menuMyInfo" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">내 정보 관리</a>
				<ul class="collapse list-unstyled" id="menuMyInfo">
					<li>
						<a href="/myinfo/check">내 정보 수정</a>
					</li>
				</ul>
			</li>
			<c:forEach var="menuId" items="${sessionVo.menuId}" varStatus="status">
				<c:if test="${menuId eq 5}">
					<c:set var="menu5" value="true"/>
				</c:if>
				<c:if test="${menuId eq 6}">
					<c:set var="menu6" value="true"/>
				</c:if>
				<c:if test="${menuId eq 7}">
					<c:set var="menu7" value="true"/>
				</c:if>
				<c:if test="${menuId eq 8}">
					<c:set var="menu8" value="true"/>
				</c:if>
				<c:if test="${menuId eq 9}">
					<c:set var="menu9" value="true"/>
				</c:if>
				<c:if test="${menuId eq 10}">
					<c:set var="menu10" value="true"/>
				</c:if>
				<c:if test="${menuId eq 11}">
					<c:set var="menu11" value="true"/>
				</c:if>
				<c:if test="${menuId eq 12}">
					<c:set var="menu12" value="true"/>
				</c:if>
			</c:forEach>

	<c:if test="${menu5 eq 'true'}">
			<li class="tree-view">
				<a href="#menuSiteMgt" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
					사이트 관리
				</a>
				<ul class="collapse list-unstyled" id="menuSiteMgt">
					<li>
						<a href="/popup/list">
						<span>메인팝업관리</span>
						</a>
					</li>
				</ul>
			</li>
	</c:if>
	<c:if test="${menu6 eq 'true' or menu7 eq 'true' or menu8 eq 'true' or menu9 eq 'true' }">
			<li>
				<a href="#menuCompany" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
					회사 소개
				</a>

				<ul class="collapse list-unstyled" id="menuCompany">
	<c:if test="${menu6 eq 'true'}">
					<li>
						<a href="/notice/list?menuCd=M">
						<span>경영현황</span>
						</a>
					</li>
	</c:if>
	<c:if test="${menu7 eq 'true'}">
					<li>
						<a href="/notice/list?menuCd=C">
						<span>회사소식</span>
						</a>
					</li>
	</c:if>
	<c:if test="${menu8 eq 'true'}">
					<li>
						<a href="/notice/list?menuCd=P">
						<span>Photo소식</span>
						</a>
					</li>
	</c:if>
	<c:if test="${menu9 eq 'true'}">
					<li>
						<a href="/notice/list?menuCd=S">
						<span>사회공헌</span>
						</a>
					</li>
	</c:if>
				</ul>
			</li>
	</c:if>

	<c:if test="${menu10 eq 'true' or menu11 eq 'true'}">
			<li>
				<a href="#menuItem" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
					물건 정보
				</a>
				<ul class="collapse list-unstyled" id="menuItem">
		<c:if test="${menu10 eq 'true'}">
					<li>
						<a href="/SaleItem/list">
						<span>분양물건정보</span>
						</a>
					</li>
		</c:if>
		<c:if test="${menu11 eq 'true'}">
					<li>
						<a href="/ShortSell/list">
						<span>공매물건정보</span>
						</a>
					</li>
		</c:if>
				</ul>
			</li>
	</c:if>
	<c:if test="${menu12 eq 'true'}">
			<li>
				<a href="#menuSupport" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
					고객센터
				</a>
				<ul class="collapse list-unstyled" id="menuSupport">
					<li>
						<a href="/qa/list">
						<span>고객문의</span>
						</a>
					</li>
				</ul>
			</li>
	</c:if>
	</ul>
</nav>
</section>
</aside>