<template>
  <div class="shadow-sm p-3 mb-5 bg-white rounded">
    <!-- nav 끝 -->

    <b-navbar toggleable="lg" type="light">
      <b-navbar-brand href="#">EnjoyTrip!</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">HOME</b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'attractions' }">Attraction</router-link>
          </b-nav-item>
          <b-nav-item href="#">HotPlace</b-nav-item>
          <b-nav-item href="#">Community</b-nav-item>
          <b-nav-item href="#">Notice</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <!-- 로그인을 안 했을 시 -->
          <b-nav-item href="#" disabled>로그인이 필요합니다.</b-nav-item>

          <b-nav-item-dropdown right>
            <template #button-content>
              <em><b-icon icon="person" font-scale="1"></b-icon></em>
            </template>
            <b-dropdown-item href="#" @click="showLoginModal"
              >로그인</b-dropdown-item
            >
            <b-dropdown-item href="#">회원가입</b-dropdown-item>
          </b-nav-item-dropdown>

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

          <!-- 관리자로 로그인했을 때 -->
          <!-- <b-nav-item href="#" disabled>관리자</b-nav-item>

          <b-nav-item-dropdown right>
            <template #button-content>
              <em><b-icon icon="person" font-scale="1"></b-icon></em>
            </template>
            <b-dropdown-item href="#">로그아웃</b-dropdown-item>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'admin' }">회원관리</router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown> -->

          <!-- 관리자 외 회원 로그인 -->
          <!-- <b-nav-item href="#" disabled>님 환영합니다.</b-nav-item>

          <b-nav-item-dropdown right>
            <template #button-content>
              <em><b-icon icon="person" font-scale="1"></b-icon></em>
            </template>
            <b-dropdown-item href="#">로그아웃</b-dropdown-item>
            <b-dropdown-item href="#">마이페이지</b-dropdown-item>
          </b-nav-item-dropdown> -->
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data: function () {
    return {
      id: "",
      pw: "",
    };
  },
  methods: {
    ...mapActions(["userLogin"]),
    showLoginModal() {
      this.$refs[`loginModal`].show();
    },
    hideLoginModal() {
      this.$refs[`loginModal`].hide();
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
            this.$bvToast.toast("로그인 성공하였습니다", {
              title: "로그인",
              variant: "success",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 2000,
              solid: true,
            });
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
  },
};
</script>
