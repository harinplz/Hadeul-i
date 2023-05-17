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
			<div class="col-md-12">
				<div class="alert alert-primary mt-3 text-center fw-bold"
					role="alert">핫 플레이스 정보</div>
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-7 col-7">
						<!-- 카카오 맵 생성 start -->
						<div id="map" style="width: 100%; height: 600px"></div>
						<!-- 카카오 맵 생성 end -->
					</div>
					<div class="text-left col-sm-12 col-md-12 col-lg-5 col-5">
						<p class="hotplaceTitle">핫 플레이스 이름 :</p>
						<input class="hotplaceInput" type="text"
							placeholder="이름을 입력해주세요...." />
						<p class="hotplaceTitle">다녀온 날짜 :</p>
						<input class="hotplaceInput" type="date" />
						<p class="hotplaceTitle">장소유형</p>
						<select class="form-select" style="margin-bottom: 15px">
							<option value="0" selected>관광지 유형</option>
							<option value="12">관광지</option>
							<option value="14">문화시설</option>
							<option value="15">축제공연행사</option>
							<option value="25">여행코스</option>
							<option value="28">레포츠</option>
							<option value="32">숙박</option>
							<option value="38">쇼핑</option>
							<option value="39">음식점</option>
						</select>
						<p class="hotplaceTitle">핫플 소개</p>
						<textarea class="hotplaceTextarea"
							placeholder="핫플레이스 소개를 입력해주세요...."></textarea>
						<button type="button" class="btn btn-success">핫플등록</button>
						<button type="button" class="btn btn-outline-success">목록
						</button>
					</div>
				</div>
			</div>
		</div>
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
>
