<template>
  <div>
    <div class="mypage_memory wow fadeInUp">
      <div class="memory_title">
        <span class="highlight">하들이와 함께한 추억을 돌아봐요</span>
      </div>
      <div style="text-align: center; padding-top: 5px">
        저희와 함께한 최근의 추억을 보여드릴게요!
      </div>
      <div class="memory_content">
        <div class="row memory_row">
          <div class="col-md-3"><b>여행 경로</b></div>
          <div class="col-md-9">해운대 투어!</div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>핫플레이스</b></div>
          <div class="col-md-9">해운대 암소갈비집</div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>커뮤니티</b></div>
          <div class="col-md-9">서울 맛집 추천해주세요!</div>
        </div>
      </div>
    </div>
    <div class="mypage_userinfo wow fadeInUp">
      <div class="memory_title">
        <span class="highlight_pink">김싸피님의 정보</span>
      </div>
      <div class="memory_content">
        <div class="row memory_row">
          <div class="col-md-3"><b>아이디</b></div>
          <div class="col-md-9">ssafy123</div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>이름</b></div>
          <div class="col-md-9">김싸피</div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>이메일</b></div>
          <div class="col-md-9">ssafy@naver.com</div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>비밀번호</b></div>
          <div class="col-md-9">1234</div>
        </div>
      </div>
      <div class="buttonDiv">
        <button class="btn modifyBtn" @click="showModalModify">수정</button>
        <button class="btn deleteBtn">탈퇴</button>
      </div>
    </div>

    <!-- 수정 모달창 작성 -->
    <b-modal :ref="`user-modify`" title="회원 정보 수정" centered hide-footer>
      <!-- 수정 모달 창 Body 작성 -->
      <div class="text-center">
        <b-input-group style="width: 400px; padding: 10px" prepend="아이디">
          <b-form-input placeholder="아이디 입력 ..."> </b-form-input>
        </b-input-group>
        <br />
        <b-input-group style="width: 400px; padding: 10px" prepend="이름">
          <b-form-input placeholder="이름 입력 ..."> </b-form-input>
        </b-input-group>
        <br />
        <b-input-group style="width: 400px; padding: 10px" prepend="이메일">
          <b-form-input placeholder="이메일 입력 ..."> </b-form-input>
        </b-input-group>
        <br />
        <b-input-group style="width: 400px; padding: 10px" prepend="비밀번호">
          <b-form-input placeholder="비밀번호 입력 ..."> </b-form-input>
        </b-input-group>
      </div>

      <br />

      <!-- 수정 모달 창 Footer 작성 -->
      <div class="text-center">
        <b-button-group>
          <b-button variant="outline-secondary">수정</b-button>
          <b-button variant="secondary">삭제</b-button>
        </b-button-group>
      </div>
    </b-modal>
  </div>
</template>

<script>
export default {
  methods: {
    showModalModify() {
      this.$refs[`user-modify`].show();
    },
  },
};
</script>

<style scoped>
.mypage_memory {
  background-color: rgb(255, 250, 245);
  height: 400px;
  width: 60%;
  border-radius: 70px;
  margin: 0px auto 30px auto;
  font-family: "SUITE-Regular";
}

.mypage_userinfo {
  background-color: rgb(255, 250, 245);
  height: 500px;
  width: 60%;
  border-radius: 70px;
  margin: 50px auto 70px auto;
  font-family: "SUITE-Regular";
}

.memory_title {
  text-align: center;
  padding-top: 30px;
  font-size: 25px;
  font-weight: bold;
}

.memory_content {
  margin: auto;
  padding-top: 40px;
  width: 70%;
  font-size: 20px;
}

.memory_row {
  padding: 20px 0px;
}

.highlight {
  display: inline;
  box-shadow: inset 0 -10px 0 #c3e5ee;
}

.highlight_pink {
  display: inline;
  box-shadow: inset 0 -10px 0 #ffd5e3;
}

.buttonDiv {
  text-align: center;
  margin-top: 20px;
}

.modifyBtn {
  font-size: 20px;
  margin: 0px 10px;
  padding: 10px 20px;
  background-color: #c3e5ee;
  font-weight: bold;
}

.deleteBtn {
  font-size: 20px;
  margin: 0px 10px;
  padding: 10px 20px;
  background-color: #ffd5e3;
  font-weight: bold;
}
</style>
