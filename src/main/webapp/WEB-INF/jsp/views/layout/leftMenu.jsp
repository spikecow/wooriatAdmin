
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
						<a href="">내 정보 수정</a>
					</li>
				</ul>
			</li>

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

			<li>
				<a href="#menuCompany" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
					회사 소개
				</a>

				<ul class="collapse list-unstyled" id="menuCompany">
					<li>
						<a href="/notice/list?menuCd=M">
						<span>경영현황</span>
						</a>
					</li>
					<li>
						<a href="/notice/list?menuCd=C">
						<span>회사소식</span>
						</a>
					</li>
					<li>
						<a href="/notice/list?menuCd=P">
						<span>Photo소식</span>
						</a>
					</li>
					<li>
						<a href="/notice/list?menuCd=S">
						<span>사회공헌</span>
						</a>
					</li>
				</ul>
			</li>

			<li>
				<a href="#menuItem" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
					물건 정보
				</a>
				<ul class="collapse list-unstyled" id="menuItem">
					<li>
						<a href="/SaleItem/list">
						<span>분양물건정보</span>
						</a>
					</li>
					<li>
						<a href="/ShortSell/list">
						<span>공매물건정보</span>
						</a>
					</li>
				</ul>
			</li>
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
	</ul>
</nav>
</section>
</aside>