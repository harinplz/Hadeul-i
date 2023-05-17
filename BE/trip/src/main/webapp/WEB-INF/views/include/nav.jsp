<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>            
                
                <!-- Spinner Start -->
        <div id="spinner"
            class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary"
                style="width: 3rem; height: 3rem" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->

        <!-- Navbar Start -->
        <div class="container-fluid nav-bar bg-transparent">
            <nav class="navbar navbar-expand-lg bg-white navbar-light py-0 px-4">
                <a href="index.jsp"
                    class="navbar-brand d-flex align-items-center text-center">
                    <div class="icon p-2 me-2">
                        <img class="img-fluid" src="img/logo.png" alt="Icon"
                            style="width: 40px; height: 40px" />
                    </div>
                    <h1 class="m-0 text-primary">Trip</h1>
                </a>
                <c:choose>
                <c:when test="${empty user }">
                <a href="${ root }/signUp.jsp">회원가입</a>
                &nbsp;
                <a href="${ root }/login.jsp">로그인</a>
                </c:when>
                <c:otherwise>
                <a class="disabled">${ user.id }님 반갑습니다.</a> 
                &nbsp;&nbsp;
                <a href="${ root }/user?act=logout">로그아웃</a>
                &nbsp;&nbsp;
                <a href="${ root }/user?act=mypage&userid=${user.id}">마이페이지</a>
                </c:otherwise>
                </c:choose>
                &nbsp;&nbsp;
                <c:if test="${user.id eq 'admin'}">
  					<a href="${root}/user?act=memberall">멤버다</a>
				</c:if>
                
                
                <button type="button" class="navbar-toggler"
                    data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto">
                        <a href="index.jsp" class="nav-item nav-link active">Home</a> <a
                            href="about.jsp" class="nav-item nav-link">지역별여행지</a> <a href="#"
                            class="nav-item nav-link">나의여행계획</a> <a href="hotplace.jsp"
                            class="nav-item nav-link">핫플자랑하기</a> <a href="information.jsp"
                            class="nav-item nav-link">여행정보공유</a>
                        <div id="loginX" class="nav-item dropdown">
                        </div>
                        <div id="loginO" class="nav-item dropdown">
                        </div>
                    </div>
                    <!-- <a href="" class="btn btn-primary px-3 d-none d-lg-flex"
              >Add Property</a
            > -->
                </div>
            </nav>
        </div>
        <!-- Navbar End -->