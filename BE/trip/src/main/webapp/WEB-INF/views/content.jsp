<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp" %>

<body>
	<div class="container-xxl bg-white p-0">
		<%@ include file="/include/nav.jsp"%>

		<%@ include file="/include/header.jsp"%>

		<!-- 중앙 content start -->
		<div class="container">
			<div style="height: 70px"></div>

			<!-- 중앙 center content end -->
			<!-- 글 내용 조회 start -->
			<div class="contentDiv">
				<table class="contentTable">
					<tr height="100px">
						<td colspan="2" style="text-align: center; font-weight: bolder;">여행
							가고 싶어요! [0]</td>
					</tr>
					<tr>
						<td style="text-align: left; padding-left: 30px;"><i
							class="bi bi-person-circle"></i>&nbsp;이싸피</td>
						<td style="text-align: right; padding-right: 30px;">
							2023.03.17</td>
					</tr>
					<tr height="300px">
						<td colspan="2" style="padding-left: 30px;">요즘 날씨가 너무 좋아서 여행이
							너무 가고싶네요 ... 여행지 추천 부탁드려요!!</td>

					</tr>
				</table>
			</div>
			<div style="text-align: center; margin-top: 30px;">
				<a href="information.jsp">
					<button type="button" class="btn btn-success">글목록</button>
				</a> <a href="update.jsp">
					<button type="button" class="btn btn-success">글수정</button>
				</a>
				<button type="button" class="btn btn-success">글삭제</button>
				<!-- <button type="button" class="btn btn-success">글수정</button> -->
			</div>
			<!-- 글 내용 조회 end -->


			<!-- 중앙 content end -->

			<%@ include file="/include/footer.jsp" %>

			<!-- Back to Top -->
			<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
				class="bi bi-arrow-up"></i></a>
		</div>

		<!-- JavaScript Libraries -->
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
		<script src="lib/wow/wow.min.js"></script>
		<script src="lib/easing/easing.min.js"></script>
		<script src="lib/waypoints/waypoints.min.js"></script>
		<script src="lib/owlcarousel/owl.carousel.min.js"></script>

		<!-- Template Javascript -->
		<script src="js/main.js"></script>
</body>
</html>
