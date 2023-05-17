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
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-6 col-6">
					<div>
						<a href="${ root }/share.jsp"><button type="button"
								class="btn btn-outline-success">글쓰기</button></a>
					</div>
				</div>
				<!-- <div class="text-left col-sm-12 col-md-12 col-lg-6 col-6">
            <select class="form-select" style="width: 20%">
              <option value="0" selected>검색조건</option>
              <option>제목</option>
              <option>작성자</option>
            </select>
            <input type="text" placeholder="검색어 입력..." />
          </div> -->
			</div>
			<div>
				<table class="table table-striped" style="margin-top: 20px">
					<thead>
						<th>글번호</th>
						<th style="width: 50%">제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</thead>

					<tr>
						<td>10</td>
						<td><a href="content.jsp">여행 가고 싶어요!</a></td>

						<td>이싸피</td>
						<td>2023.03.17</td>
						<td>1</td>
					</tr>
					<tr>
						<td>9</td>
						<td>저도 여행 가고 싶네요..</td>
						<td>김싸피</td>
						<td>2023.03.17</td>
						<td>0</td>
					</tr>
					<tr>
						<td>8</td>
						<td>부산 vs 대구 여행 어디로?</td>
						<td>박싸피</td>
						<td>2023.03.17</td>
						<td>0</td>
					</tr>
					<tr>
						<td>7</td>
						<td>비올까봐 걱정이네요</td>
						<td>정싸피</td>
						<td>2023.03.17</td>
						<td>0</td>
					</tr>
					<tr>
						<td>6</td>
						<td>부산 맛집 추천드려요!</td>
						<td>최싸피</td>
						<td>2023.03.17</td>
						<td>0</td>
					</tr>
					<tr>
						<td>5</td>
						<td>서울 벚꽃 명소~</td>
						<td>황싸피</td>
						<td>2023.03.17</td>
						<td>0</td>
					</tr>
					<tr>
						<td>4</td>
						<td>경주 갈려고 하는데...</td>
						<td>강싸피</td>
						<td>2023.03.17</td>
						<td>0</td>
					</tr>
					<tr>
						<td>3</td>
						<td>강릉 여름 vs 겨울</td>
						<td>임싸피</td>
						<td>2023.03.17</td>
						<td>0</td>
					</tr>
					<tr>
						<td>2</td>
						<td>맛있는거 먹고 싶어요..</td>
						<td>신싸피</td>
						<td>2023.03.17</td>
						<td>0</td>
					</tr>
					<tr>
						<td>1</td>
						<td>첫글이다!</td>
						<td>허싸피</td>
						<td>2023.03.17</td>
						<td>0</td>
					</tr>
				</table>
			</div>
		</div>
		<div style="text-align: center">1</div>
		<!-- 중앙 content end -->

		<%@ include file="/include/footer.jsp" %>

		<!-- Back to Top -->
		<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
			class="bi bi-arrow-up"></i></a>
	</div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cb1d5ee10f75d651a51f84179b9abaf6&libraries=services,clusterer,drawing"></script>
	<script>
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    </script>

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
