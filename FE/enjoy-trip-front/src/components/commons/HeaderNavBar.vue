<template>
  <div class="shadow-sm p-3 mb-5 bg-white rounded">
    <!-- nav 끝 -->

    <b-navbar toggleable="lg" type="light">
      <b-navbar-brand href="#">EnjoyTrip!</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link :to="{ name: 'home' }">HOME</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'attractions' }">Attraction</router-link>
          </b-nav-item>
          <b-nav-item href="#">HotPlace</b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'communities' }"
              >Community</router-link
            ></b-nav-item
          >
          <b-nav-item href="#">Notice</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <!-- 로그인 안했을 때 -->
          <div v-if="isLogin == null">
            <div style="display: inline">로그인이 필요합니다.&nbsp;</div>
            <a class="login-no-link" href="#"
              ><div style="display: inline" @click="showLoginModal">
                로그인&nbsp;
              </div></a
            >
            <a class="login-no-link">
              <div style="display: inline" @click="showSignupModal">
                회원가입&nbsp;
              </div>
            </a>
          </div>
          <!-- 관리자 로그인 했을 때 -->
          <div v-else-if="isLogin == `admin`">
            <div style="display: inline">관리자&nbsp;</div>
            <a class="login-no-link" href="#">
              <div style="display: inline" @click="logout">로그아웃&nbsp;</div>
            </a>
            <div style="display: inline">
              <router-link :to="{ name: 'admin' }" class="login-no-link"
                >회원관리</router-link
              >
              &nbsp;
            </div>
          </div>
          <!-- 일반 회원 로그인 -->
          <div v-else>
            <div style="display: inline">{{ isLogin }}님 안녕하세요!&nbsp;</div>
            <a class="login-no-link" href="#">
              <div style="display: inline" @click="logout">로그아웃&nbsp;</div>
            </a>
            <div style="display: inline">마이페이지&nbsp;</div>
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
            <div>
              <b-input-group style="width: 240px" prepend="아이디">
                <b-form-input placeholder="아이디 입력 ..." v-model="id">
                </b-form-input>
              </b-input-group>
              <br />
              <b-input-group style="width: 240px" prepend="비밀번호">
                <b-form-input
                  placeholder="비밀번호 입력 ..."
                  v-model="pw"
                  :type="`password`">
                </b-form-input>
              </b-input-group>
            </div>
            <br />
            <div class="text-center">
              <b-button-group>
                <b-button variant="secondary" @click="login">로그인</b-button>
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
                <b-form-input placeholder="아이디 입력 ..." v-model="id">
                </b-form-input>
              </b-input-group>
              <br />
              <b-input-group style="width: 400px" prepend="이름">
                <b-form-input placeholder="이름 입력 ..." v-model="name">
                </b-form-input>
              </b-input-group>
              <br />
              <b-input-group style="width: 400px" prepend="비밀번호">
                <b-form-input
                  placeholder="비밀번호 입력 ..."
                  v-model="pw"
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
import router from "@/router";

import { mapActions } from "vuex";
export default {
  data: function () {
    return {
      id: "",
      pw: "",
      name: "",
      isLogin: "",
    };
  },
  methods: {
    ...mapActions(["userLogin", "createUser"]),
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
      this.$refs[`signupModal`].hide();
    },
    login() {
      console.log(this.id, this.pw);
      const payload = {
        user: {
          id: this.id,
          pw: this.pw,
        },
        callback: (status) => {
          if (status == 200) {
            console.log("콜백함수 실행!");
            this.hideLoginModal(); //모달 창 닫기
            router.go();
          } else if (status == 500) {
            // 서버 오류 Toast 출력
            this.$bvToast.toast("서버 오류 발생!", {
              title: "로그인 오류",
              variant: "danger",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 2000,
              solid: true,
            });
          }
        },
      };
      this.userLogin(payload);
    },
    logout() {
      localStorage.removeItem("login");
      router.go();
    },
    signup() {
      const payload = {
        user: {
          id: this.id,
          name: this.name,
          pw: this.pw,
        },
        callback: (status) => {
          console.log("콜백함수 실행!");
          this.hideSignupModal(); //모달 창 닫기

          if (status == 201) {
            router.go();
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
  created() {
    this.isLogin = localStorage.getItem("login");
    console.log(this.isLogin);
  },
};
</script>

<style scoped>
#login-no {
  /* display: none; */
  color: #7f7f7f;
}
.login-no-link {
  text-decoration: none;
  color: #7f7f7f;
}
#login-admin {
  display: none;
  color: #7f7f7f;
}
#login-user {
  display: none;
  color: #7f7f7f;
}
</style>
