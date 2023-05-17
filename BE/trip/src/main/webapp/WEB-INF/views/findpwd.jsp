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
					role="alert">비밀번호 찾기</div>
				<form action="${ root }/user" method="get">
					<input type="hidden" name="act" value="findpwd">
					<div class="text-left">
						<p class="shareTitle">이름 :</p>
						<input class="shareInput" type="text" name="name"
							placeholder="이름 입력" />
						<p class="shareTitle">아이디 :</p>
						<input class="shareInput" type="text" name="id"
							placeholder="아이디 입력" />
						<div style="text-align: center">
							<button type="submit" class="btn btn-success">비밀번호 찾기</button>
						</div>
					</div>
				</form>

				<div class="row"></div>
			</div>
		</div>
		<!-- 중앙 content end -->

		<%@ include file="/include/footer.jsp"%>

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
