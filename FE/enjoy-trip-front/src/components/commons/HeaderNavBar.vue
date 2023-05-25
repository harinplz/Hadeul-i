<template>
  <div class="shadow-sm p-3 mb-5 bg-white rounded">
    <!-- nav 끝 -->

    <b-navbar toggleable="lg" type="light">
      <b-navbar-brand>
        <router-link class="logo" :to="{ name: 'home' }">
          하들이</router-link
        ></b-navbar-brand
      >

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item class="nav_item" :to="{ name: 'home' }">
            HOME
          </b-nav-item>
          <b-nav-item class="nav_item" :to="{ name: 'attractions' }">
            관광지 조회
          </b-nav-item>
          <b-nav-item class="nav_item" :to="{ name: 'TravelRoute' }"
            >여행 계획</b-nav-item
          >
          <b-nav-item class="nav_item" :to="{ name: 'Hotplace' }">
            핫플레이스</b-nav-item
          >
          <b-nav-item class="nav_item" :to="{ name: 'communities' }">
            커뮤니티</b-nav-item
          >
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="user ml-auto">
          <!-- 로그인 안했을 때 -->
          <div v-if="userInfo == null">
            <div style="display: inline" id="login-no">
              로그인이 필요합니다.&nbsp;
            </div>
            <div class="login-no-link" @click="showLoginModal">
              로그인&nbsp;
            </div>
            <div class="login-no-link" @click="showSignupModal">
              회원가입&nbsp;
            </div>
          </div>
          <!-- 관리자 로그인 했을 때 -->
          <div v-else-if="userInfo.id == 'admin'">
            <div style="display: inline">관리자&nbsp;</div>
            <div class="login-no-link" @click="logout">로그아웃&nbsp;</div>
            <router-link :to="{ name: 'admin' }" class="login-no-link"
              >회원관리</router-link
            >
          </div>
          <!-- 일반 회원 로그인 -->
          <div v-else>
            <div style="display: inline">
              {{ userInfo.name }}님 안녕하세요!&nbsp;
            </div>
            <div class="login-no-link" @click="logout">로그아웃&nbsp;</div>
            <router-link
              class="login-no-link"
              :to="{ name: 'Mypage', params: { userNo: userInfo.no } }"
              >마이페이지&nbsp;</router-link
            >
          </div>

          <!-- 로그인 모달 창 작성 -->
          <b-modal
            size="sm"
            :ref="`loginModal`"
            title="로그인"
            header-bg-variant="dark"
            header-text-variant="light"
            centered
            hide-footer>
            <b-alert show variant="danger" v-if="isLoginError">
              아이디 또는 비밀번호를 확인하세요.
            </b-alert>
            <div>
              <b-input-group style="width: 240px" prepend="아이디">
                <b-form-input placeholder="아이디 입력 ..." v-model="user.id">
                </b-form-input>
              </b-input-group>
              <br />
              <b-input-group style="width: 240px" prepend="비밀번호">
                <b-form-input
                  placeholder="비밀번호 입력 ..."
                  v-model="user.pw"
                  :type="`password`">
                </b-form-input>
              </b-input-group>
            </div>
            <br />
            <div class="text-center">
              <b-button-group>
                <b-button variant="secondary" @click="confirm">로그인</b-button>
              </b-button-group>
            </div>
          </b-modal>

          <!-- 회원 등록 모달창 -->
          <b-modal
            :ref="`signupModal`"
            title="회원 등록"
            header-bg-variant="dark"
            header-text-variant="light"
            centered
            hide-footer>
            <!-- 회원 등록 모달창 Body 작성 -->
            <div class="text-center" style="margin-bottom: 10px">
              <b-input-group style="width: 400px" prepend="아이디">
                <b-form-input placeholder="아이디 입력 ..." v-model="user.id">
                </b-form-input>
              </b-input-group>
              <br />
              <b-input-group style="width: 400px" prepend="이름">
                <b-form-input placeholder="이름 입력 ..." v-model="user.name">
                </b-form-input>
              </b-input-group>
              <br />
              <b-input-group style="width: 400px" prepend="이메일">
                <b-form-input
                  placeholder="이메일 입력 ..."
                  v-model="user.email">
                </b-form-input>
              </b-input-group>
              <br />
              <b-input-group style="width: 400px" prepend="비밀번호">
                <b-form-input
                  placeholder="비밀번호 입력 ..."
                  v-model="user.pw"
                  :type="`password`">
                </b-form-input>
              </b-input-group>
            </div>

            <!-- 회원 등록 모달 창 Footer 작성 -->
            <div class="text-center">
              <b-button-group>
                <b-button variant="secondary" @click="signup"
                  >회원 등록</b-button
                >
              </b-button-group>
            </div>
          </b-modal>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
// import router from "@/router";

import { mapActions, mapGetters } from "vuex";
export default {
  data: function () {
    return {
      user: {
        user_no: null,
        id: null,
        name: null,
        email: null,
        pw: null,
      },
      isLoginError: false,
    };
  },
  methods: {
    ...mapActions(["login", "logout", "getUserInfo", "createUser"]),
    showLoginModal() {
      this.$refs[`loginModal`].show();
    },
    hideLoginModal() {
      this.$refs[`loginModal`].hide();
    },
    showSignupModal() {
      this.$refs[`signupModal`].show();
    },
    hideSignupModal() {
      this.user.id = "";
      this.user.pw = "";
      this.user.name = "";
      this.user.email = "";
      this.$refs[`signupModal`].hide();
    },
    confirm() {
      this.login({
        user: this.user,
        callback: (status) => {
          if (status == 200) {
            //로그인 성공하면 사용자 정보 얻어오기
            this.getUserInfo({
              accessToken: this.accessToken,
              callback: (status) => {
                if (status == 200) {
                  console.log(status);
                  this.user.id = "";
                  this.user.pw = "";
                  this.hideLoginModal();

                  this.$bvToast.toast("로그인 성공!", {
                    title: "로그인",
                    variant: "dark",
                    toaster: "b-toaster-bottom-center",
                    autoHideDelay: 2000,
                    solid: true,
                  });
                }
              },
            });
          } else if (status == 401) {
            this.isLoginError = true;
          }
        },
      });
    },
    signup() {
      const payload = {
        user: this.user,
        callback: (status) => {
          console.log("콜백함수 실행!");
          this.hideSignupModal(); //모달 창 닫기

          if (status == 201) {
            this.hideSignupModal();
            this.$bvToast.toast("회원가입 완료!", {
              title: "회원가입",
              variant: "dark",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 2000,
              solid: true,
            });
          } else if (status == 500) {
            // 서버 오류 Toast 출력
            this.$bvToast.toast("서버 오류 발생!", {
              title: "회원 관리 알림",
              variant: "danger",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 3000,
              solid: true,
            });
          }
        },
      };
      this.createUser(payload);
    },
  },
  computed: {
    ...mapGetters(["accessToken", "userInfo"]),
  },
};
</script>

<style scoped>
#login-no {
  /* display: none; */
  color: #7c7877;
}

.nav_item {
  text-decoration: none;
  color: #7c7877;
  font-size: 18px;
  font-weight: bold;
  padding: 0px 7px;
}

.login-no-link {
  text-decoration: none;
  color: #7c7877;
  font-weight: bold;
  display: inline;
  cursor: pointer;
}
#login-admin {
  display: none;
  color: #7c7877;
  font-weight: bold;
}
#login-user {
  display: none;
  color: #7c7877;
  font-weight: bold;
}

.logo {
  font-size: 25px;
  font-weight: bold;
  text-decoration: none;
  color: #61c4df;
  padding-left: 30px;
}

.user {
  padding-right: 30px;
  font-size: 18px;
}
</style>
