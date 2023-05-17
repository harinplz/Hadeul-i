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
                    role="alert">회원 관리</div>
            </div>
            
            <div>
                <table class="table table-striped" style="margin-top: 20px">
                    <thead>
                        <th>회원 번호</th>
                        <th style="width: 50%">회원 아이디</th>
                        <th>비밀번호</th>
                        <th>이름</th>
                    </thead>
                    
                    <c:forEach var="user" items="${users}">
                    
                     <tr>
                        <td>${user.no}</td>
                        <td>${user.id}</td>
                        <td>${user.pw}</td>
                        <td>${user.name}</td>
                    </tr>
                    
                    </c:forEach>
                    
                </table>
            </div>
            <div style="text-align:center">
                <button>가입</button> &nbsp 
                <button>삭제</button> &nbsp 
                <button>수정</button>
            </div> 
        </div>
        
        
        <!-- 중앙 content end -->

        <%@ include file="/include/footer.jsp"%>

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
            class="bi bi-arrow-up"></i></a>
    </div>

    <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cb1d5ee10f75d651a51f84179b9abaf6&libraries=services,clusterer,drawing"></script>
    <script>
        var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
            center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level : 3, // 지도의 확대 레벨
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
