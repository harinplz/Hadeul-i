<template>
  <b-container>
    <div class="text-right">
    <b-button-group style="margin-bottom: 10px">
        <b-button variant="outline-dark" @click="showModalCreate">회원 정보 등록</b-button>
      </b-button-group>
    </div>

    <!-- 회원 등록 모달창 -->
    <b-modal
      :ref="`user-register`"
      title="회원 등록"
      header-bg-variant="dark"
      header-text-variant="light"
      centered
      hide-footer>

      <!-- 회원 등록 모달창 Body 작성 -->
      <div class="text-center" style="margin-bottom: 10px">
        <b-input-group style="width: 400px" prepend="아이디">
          <b-form-input
            placeholder="아이디 입력 ..."
            v-model="id">
          </b-form-input>
        </b-input-group>
        <br/>
        <b-input-group style="width: 400px" prepend="이름">
          <b-form-input
            placeholder="이름 입력 ..."
            v-model="name">
          </b-form-input>
        </b-input-group>
        <br/>        
        <b-input-group style="width: 400px" prepend="이메일">
          <b-form-input
            placeholder="이메일 입력 ..."
            v-model="email">
          </b-form-input>
        </b-input-group>
        <br/>
        <b-input-group style="width: 400px" prepend="비밀번호">
          <b-form-input
            placeholder="비밀번호 입력 ..."
            v-model="pw">
          </b-form-input>
        </b-input-group>
      </div>

      <!-- 회원 등록 모달 창 Footer 작성 -->
      <div class="text-center">
        <b-button-group>
          <b-button variant="secondary" @click="register">회원 등록</b-button>
        </b-button-group>
      </div>
    </b-modal>

    <div v-if="users.length > 0">
      <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">번호</th>
            <th scope="col">id</th>
            <th scope="col">이름</th>
            <th scope="col">이메일</th>
            <th scope="col">비밀번호</th>
            <th scope="col">관리</th>
          </tr>
        </thead>
        <tbody>
          <user-list-row
            v-for="user in users"
            :key="user.no"
            :user="user"></user-list-row>
        </tbody>
      </table>
    </div>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data: function () {
    return {
      id: "",
      name: "",
      email: "",
      pw: "",
    }
  },
  components: {
    "user-list-row": () =>
      import("@/components/admins/include/UserListRow.vue"),
  },
  methods: {
    ...mapActions(["getUsers", "createUser"]),
    showModalCreate() {
      this.$refs[`user-register`].show();
    },
    hideModalCreate() {
      this.$refs[`user-register`].hide();
    },
    register(){
      console.log("회원 등록");

      const payload = {
        user: {
          id: this.id,
          name: this.name,
          email: this.email,
          pw: this.pw,
        },
        callback: (status) => {
          console.log("콜백함수 실행!");
          this.hideModalCreate(); //모달 창 닫기

          if(status == 201) {
            this.getUsers();
            
            this.$bvToast.toast("회원 정보가 등록되었습니다." , {
              title : "회원 관리 알림",
              variant: "success",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 3000,
              solid: true,
          });
          }
          else if(status == 500){
            // 서버 오류 Toast 출력
            this.$bvToast.toast("서버 오류 발생!", {
                title: "회원 관리 알림",
                variant: "danger",
                toaster: "b-toaster-bottom-center",
                autoHideDelay: 3000,
                solid: true,
              });
          }
        }
      }
      this.createUser(payload);
    },
  },
  created() {
    this.getUsers();
  },
  computed: {
    ...mapGetters(["users"]),
  },
};
</script>
