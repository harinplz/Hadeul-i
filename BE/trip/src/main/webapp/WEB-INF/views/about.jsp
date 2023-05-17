<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp" %>

<body>
	<div class="container-xxl bg-white p-0">

		<%@ include file="/include/nav.jsp" %>

		<%@ include file="/include/header.jsp" %>

		<!-- 중앙 content start -->
		<div class="container">
			<div style="height: 70px"></div>

			<!-- 중앙 center content end -->
			<div class="col-md-12">
				<div class="alert alert-primary mt-3 text-center fw-bold"
					role="alert">전국 관광지 정보</div>
				<!-- 관광지 검색 start -->
				<form class="d-flex my-5" action="${ root }/attraction?act=search"
					method="get">
					<input type="hidden" name="act" value="search"> <select
						id="search-area" name="search-area" class="form-select me-2">
						<option value="0" selected>검색 할 지역 선택</option>
						<option value="1">서울</option>
						<option value="2">인천</option>
						<option value="3">대전</option>
						<option value="4">대구</option>
						<option value="5">광주</option>
						<option value="6">부산</option>
						<option value="7">울산</option>
						<option value="8">세종특별자치시</option>
						<option value="31">경기도</option>
						<option value="32">강원도</option>
						<option value="33">충청북도</option>
						<option value="34">충청남도</option>
						<option value="35">경상북도</option>
						<option value="36">경상남도</option>
						<option value="37">전라북도</option>
						<option value="38">전라남도</option>
						<option value="39">제주도</option>
					</select> <select id="search-content-id" name="search-content-id"
						class="form-select me-2">
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
					<select id="sort-name" name="sort-name" class="form-select me-2">
						<option value="0" selected>정렬 방법</option>
						<option value="1">이름 순</option>
						<option value="2">역 이름 순</option>
					</select>
					<input id="search-keyword" class="form-control me-2"
						name="search-keyword" type="text" placeholder="검색어"
						aria-label="검색어" />
					<button id="btn-search" class="btn btn-outline-success"
						type="submit" onclick="displayMarker()">검색</button>
				</form>
				<!--  <div class="row">
					<button class="btn btn-outline-success col-6">이름 순 정렬</button>
					<button class="btn btn-outline-success col-6">이름 역순 정렬</button>
				</div> -->
				<!-- kakao map start -->
				<div id="map" class="mt-3" style="width: 100%; height: 600px"></div>
				<!-- kakao map end -->
				<div class="row">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>관광지명</th>
								<th>주소</th>
								<th>위도</th>
								<th>경도</th>
							</tr>
						</thead>
						<tbody id="trip-list">
							<c:forEach var="attraction" items="${ list }">
								<tr>
									<td>${ attraction.title }</td>
									<td>${ attraction.addr1 }</td>
									<td>${ attraction.latitue }</td>
									<td>${ attraction.longitude }</td>
								<tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 관광지 검색 end -->
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
	<script type="text/javascript">
      var positions = [];
      
      <c:forEach var="attraction" items="${list}"  >
      
      var title = "${attraction.title}";
      var latlng = new kakao.maps.LatLng("${ attraction.latitue }", "${ attraction.longitude }");

      positions.push({title: title, latlng: latlng});
      
      </c:forEach>
      
      console.log(positions);

      // 카카오지도
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };

      // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption);

      var control = new kakao.maps.ZoomControl();
      map.addControl(control, kakao.maps.ControlPosition.TOPRIGHT);
      
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
        
        console.log(positions.length);
        
        for (var i = 0; i < positions.length; i++) {
 
          // 마커 이미지의 이미지 크기 입니다
          var imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          });
        }

        // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
        map.setCenter(positions[0].latlng);

      function moveCenter(lat, lng) {
        map.setCenter(new kakao.maps.LatLng(lat, lng));
      }
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
