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
          <div class="col-md-9" v-if="this.userMypage.communityTitle">
            {{ userMypage.communityTitle }}
          </div>
          <div class="col-md-9" v-else>
            아직 여행 계획 등록을 하지 않았어요.
          </div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>핫플레이스</b></div>
          <div class="col-md-9" v-if="this.userMypage.hotplaceTitle">
            {{ userMypage.hotplaceTitle }}
          </div>
          <div class="col-md-9" v-else>
            아직 핫플레이스에 글을 작성하지 않았어요.
          </div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>커뮤니티</b></div>
          <div class="col-md-9" v-if="this.userMypage.travelRouteTitle">
            {{ userMypage.travelRouteTitle }}
          </div>
          <div class="col-md-9" v-else>
            아직 커뮤니티에 글을 작성하지 않았어요.
          </div>
        </div>
      </div>
    </div>
    <div class="mypage_userinfo wow fadeInUp">
      <div class="memory_title">
        <span class="highlight_pink">{{ userMypage.name }}님의 정보</span>
      </div>
      <div class="memory_content">
        <div class="row memory_row">
          <div class="col-md-3"><b>아이디</b></div>
          <div class="col-md-9">{{ userMypage.id }}</div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>이름</b></div>
          <div class="col-md-9">{{ userMypage.name }}</div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>이메일</b></div>
          <div class="col-md-9">{{ userMypage.email }}</div>
        </div>
        <div class="row memory_row">
          <div class="col-md-3"><b>비밀번호</b></div>
          <div class="col-md-9">{{ userMypage.pw }}</div>
        </div>
      </div>
      <div class="buttonDiv">
        <button class="btn modifyBtn" @click="showModalModify">수정</button>
        <button class="btn deleteBtn" @click="deletMember">탈퇴</button>
      </div>
    </div>

    <!-- 수정 모달창 작성 -->
    <b-modal :ref="`user-modify`" title="회원 정보 수정" centered hide-footer>
      <!-- 수정 모달 창 Body 작성 -->
      <div class="text-center">
        <b-input-group style="width: 400px; padding: 10px" prepend="아이디">
          <b-form-input
            placeholder="아이디 입력 ..."
            v-model="userMypage.id"></b-form-input>
        </b-input-group>
        <br />
        <b-input-group style="width: 400px; padding: 10px" prepend="이름">
          <b-form-input placeholder="이름 입력 ..." v-model="userMypage.name">
          </b-form-input>
        </b-input-group>
        <br />
        <b-input-group style="width: 400px; padding: 10px" prepend="이메일">
          <b-form-input
            placeholder="이메일 입력 ..."
            v-model="userMypage.email">
          </b-form-input>
        </b-input-group>
        <br />
        <b-input-group style="width: 400px; padding: 10px" prepend="비밀번호">
          <b-form-input placeholder="비밀번호 입력 ..." v-model="userMypage.pw">
          </b-form-input>
        </b-input-group>
      </div>

      <br />

      <!-- 수정 모달 창 Footer 작성 -->
      <div class="text-center">
        <b-button-group>
          <b-button variant="outline-secondary" @click="updateMember"
            >수정</b-button
          >
        </b-button-group>
      </div>
    </b-modal>
  </div>
</template>

<script>
import http from "@/util/http-commons";

export default {
  data() {
    return {
      userMypage: {
        name: "",
      },
      no: "",
    };
  },
  created() {
    const userNo = this.$route.params.userNo;
    this.no = userNo;

    http.get(`/user/mypage/onload/${userNo}`).then((response) => {
      if (response.status == 200) {
        this.userMypage = response.data;
      } else {
        this.userMypage = {};
      }
    });
  },

  methods: {
    showModalModify() {
      this.$refs[`user-modify`].show();
    },
    deletMember() {
      this.$router.push({ name: "home" });

      /* //삭제는 나중에..
      const userId = this.userMypage.id;
      http.delete(`/user/mypage/${userId}`).then((response) => {
        if (response.status == 200) {
          alert("회원 삭제가 완료되었습니다.");
        }
      });*/
    },
    updateMember() {
      const id = this.userMypage.id;
      const pw = this.userMypage.pw;
      const name = this.userMypage.name;
      const email = this.userMypage.email;
      const no = this.no;

      const info = {
        no,
        id,
        pw,
        name,
        email,
      };

      http.put(`/user/mypage/${id}`, info).then((response) => {
        if (response.status == 200) {
          alert("수정이 완료되었습니다.");
          this.$refs[`user-modify`].hide(); //모달 숨김
        }
      });
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
