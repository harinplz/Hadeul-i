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
			<div class="row"></div>
			<div class="col-md-12">
				<div class="alert alert-primary mt-3 text-center fw-bold"
					role="alert">여행 정보 공유</div>
				<div class="text-left">
					<p class="shareTitle">제목 :</p>
					<textarea class="shareTextarea" placeholder="제목 입력"></textarea>
					<p class="shareTitle">내용</p>
					<textarea class="shareTextarea" placeholder="여행 관련 내용 입력"></textarea>
					<div style="text-align: center">
						<a href="information.jsp"><button type="button"
								class="btn btn-success">글등록</button></a>

						<!-- <button type="button" class="btn btn-success">글수정</button> -->
						<button type="button" class="btn btn-outline-success">
							초기화</button>
					</div>
				</div>
				<div class="row"></div>
			</div>
		</div>
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
