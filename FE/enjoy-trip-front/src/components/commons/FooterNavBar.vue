<template>
  <footer>
    <div class="footer-pic">
      <b-icon class="footer-icon" icon="facebook" font-scale="2"></b-icon>
      <b-icon class="footer-icon" icon="instagram" font-scale="2"></b-icon>
      <b-icon class="footer-icon" icon="youtube" font-scale="2"></b-icon>
      <b-icon class="footer-icon" icon="twitter" font-scale="2"></b-icon>
      <a class="nolink" href="https://github.com/seongho-98/enjoy-trip">
        <b-icon class="footer-icon" icon="github" font-scale="2"></b-icon
      ></a>
    </div>
    <div class="footer-1">이용약관 | 개인정보처리방침 | 사이트맵</div>
    <div class="footer-2">
      본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포 등을
      금합니다.
      <br />
      Copyright ⓒ EnjoyTrip All Rights Reserved.
    </div>
  </footer>
</template>

<style scoped>
footer {
  width: 100%;
  height: 150px;
  background-color: #e2e2e2;
  color: #5f5f5f;
  font-weight: bold;
  font-size: 13px;
  /* 나중에 필요할 때 주석 처리 */
  /* bottom: 0px;
  position: absolute; */
}
.footer-1 {
  margin-top: 30px;
  margin-left: 40px;
}
.footer-2 {
  margin-top: 30px;
  margin-left: 40px;
}
.footer-pic {
  margin-top: 30px;
  margin-right: 40px;
  float: right;
}
.footer-icon {
  margin: 3px;
}
.nolink {
  text-decoration: none;
  color: #5f5f5f;
}
</style>
