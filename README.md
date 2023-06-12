# hadeul-i

## 💡 프로젝트 개요
* 관광지 조회, 여행 계획 설정, 핫플레이스, 커뮤니티 기능을 통해 관광지 정보를 제공하는 서비스

<br>

## 👥 팀원

<table>
    <tr>
    <td align="center">팀장</td>
    <td align="center">팀원</td>
  </tr>
  <tr>
   <td align="center"><a href="https://github.com/seongho-98"><img src="https://avatars.githubusercontent.com/u/73474641?v=4" width="120px;" alt=""/><br /><sub><b>하성호</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/Harinee68"><img src="https://avatars.githubusercontent.com/u/62701446?v=4" width="120px;" alt=""/><br /><sub><b>이하린</b></sub></a><br /></td>
  </tr>
</table>

<br>

## ⏱️ 개발 기간
* 2023.05.18(목) ~ 2023.05.25(목)

<img src="https://github.com/Harinee68/algorithm_study/assets/62701446/47b9039f-2efb-42c6-8eee-401840ead999" width="600px">

<br><br>


## 🛠️ 사용 기술 
* Design

<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">

* Front-End

<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css3-1572b6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-f7df1e?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/bootstrap-7952b3?style=for-the-badge&logo=bootstrap&logoColor=white"> 
<img src="https://img.shields.io/badge/vue.js-4fc08d?style=for-the-badge&logo=vuedotjs&logoColor=white"> <img src="https://img.shields.io/badge/visual studio code-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white"> 

* Back-End

<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/mybatis-181717?style=for-the-badge&logo=mybatis&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">

* API

<img src="https://img.shields.io/badge/Kakao Map-FFCD00?style=for-the-badge&logo=kakao&logoColor=black">

* Team Collaboration Tool

<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">



<br>

## 🐬 데이터베이스 모델링 (ERD) 

<img src="https://github.com/Harinee68/algorithm_study/assets/62701446/3a0b245c-267c-46e5-89ab-4c57f02ccbf6" width="800px">
<br><br>

## 📺 실행 화면

### 1. 메인 페이지
<img src="./assets/1_mainpage.gif">
<br>

### 2. 회원가입 및 로그인
<img src="./assets/2_login.gif"><br>
*  JWT 토큰으로 로그인 처리
<br>

### 3. 커뮤니티
<img src="./assets/3_community.gif"><br>
* 자유롭게 의견 교환이 가능한 익명 커뮤니티 게시판
* 전체 게시물 조회, 게시물 상세 조회, 등록, 수정, 삭제, 댓글 기능
<br>

### 4. 여행 계획
<img src="./assets/4_tripRoute.gif"><br>
* 여행 계획을 세울 수 있고, 다른 사람들의 계획도 볼 수 있는 게시판
* 카카오 맵  API로 관광지 위치 조회 및 여행 경로 확인
* 전체 여행 계획 조회, 상세 조회, 좋아요 기능
<br>

### 5. 핫플레이스
<img src="./assets/5_hotplace.gif"><br>
* 나만 알기 아까운 나만의 핫플레이스를 공유할 수 있는 게시판
* 전체 게시물 조회, 게시물 등록, 상세 조회, 삭제, 검색, 좋아요 기능
<br>

### 6. 마이페이지
<img src="./assets/6_mypage.gif"><br>
* 회원 자신에 대한 활동 및 정보를 볼 수 있는 페이지
* 회원 정보 수정, 삭제, 최근 등록한 게시물 확인 기능
<br>

### 7. 관광지 조회
<img src="./assets/7_attractions.gif"><br>
* <a href="https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15101578">한국관광공사_국문 관광정보 서비스_GW</a> 에서 제공되는 관광지에 대한 정보와 핫플레이스에서 관광지로 선정된 사용자 추천 관광지를 볼 수 있는 페이지
* 전체 관광지 조회, 핫플레이스 관광지 조회, 관광지 상세 조회, 좋아요 기능
<br>

### 8. 관리자
* 회원 관리
  <img src="./assets/8-1_userManage.gif"><br>
  * 관리자로 로그인 시, 회원 관리를 위한 페이지 제공
* 사용자 추천 관광지 등록
  <img src="./assets/8-2_userAttraction.gif"> <br>
  * 관리자로 로그인 시, 핫플레이스 게시물을 사용자 추천 관광지에 등록할 수 있는 기능
