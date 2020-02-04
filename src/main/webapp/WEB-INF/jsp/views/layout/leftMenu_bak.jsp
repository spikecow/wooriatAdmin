
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
				<li class="tree-view">
					<a href="/user/list">
						<span>회원관리</span>
					</a>
				</li>
				</c:if>
				<li>
					<a href="">
						<span>내 정보 수정</span>
					</a>
				</li>

				<li class="tree-view">
					<a href="#siteMgt" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
						사이트 관리
					</a>
					<ul class="collapse" id="siteMgt">
						<li>
							<a href="/popup/list">
								<span>메인팝업관리</span>
							</a>
						</li>
					</ul>
				</li>
				<li class="tree-view">
					<a href="#company" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
						회사 소개
					</a>
					<ul class="collapse" id="company">
						<li>
							<a href="#">
								<span>경영현황</span>
							</a>
						</li>
						<li>
							<a href="#">
								<span>회사소식</span>
							</a>
						</li>
						<li>
							<a href="#">
								<span>Photo소식</span>
							</a>
						</li>
						<li>
							<a href="#">
								<span>사회공헌</span>
							</a>
						</li>
					</ul>
				</li>
				<li class="tree-view">
					<a href="#item" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
						물건 정보
					</a>
					<ul class="collapse" id="item">
						<li class="tree-view">
							<a href="/SaleItem/list">
								<span>분양물건정보</span>
							</a>
						</li>
						<li class="tree-view">
							<a href="/ShortSell/list">
								<span>공매물건정보</span>
							</a>
						</li>
					</ul>
				</li>
				<li class="tree-view">
					<a href="#support" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
						고객센터
					</a>
					<ul class="collapse" id="support">
						<li>
							<a href="">
								<span>고객문의</span>
							</a>
						</li>
					</ul>
				</li>

			</ul>
		</section>
	</aside>
