<template>
  <tr>
    <th scope="col">{{ input.no }}</th>
    <td>{{ input.id }}</td>
    <td>{{ input.name }}</td>
    <td>{{ input.email }}</td>
    <td>{{ input.pw }}</td>
    <td>
      <b-button-group>
        <b-button variant="secondary" @click="showModalModify">관리</b-button>
      </b-button-group>

      <!-- 수정 모달창 작성 -->
      <b-modal
        :ref="`user-${input.no}`"
        title="회원 수정"
        header-bg-variant="dark"
        header-text-variant="light"
        centered
        hide-footer
      >
      <!-- 수정 모달 창 Body 작성 -->
      <div class="text-center">
        <b-input-group style="width: 400px" prepend="아이디">
          <b-form-input
            placeholder="아이디 입력 ..."
            v-model="input.id">
          </b-form-input>
        </b-input-group>
        <br/>
        <b-input-group style="width: 400px" prepend="이름">
          <b-form-input
            placeholder="이름 입력 ..."
            v-model="input.name">
          </b-form-input>
        </b-input-group>
        <br/>
        <b-input-group style="width: 400px" prepend="비밀번호">
          <b-form-input
            placeholder="비밀번호 입력 ..."
            v-model="input.pw">
          </b-form-input>
        </b-input-group>
      </div>

      <br/>

      <!-- 수정 모달 창 Footer 작성 -->
      <div class="text-center">
        <b-button-group>
          <b-button variant="outline-secondary" @click="modify">수정</b-button>
          <b-button variant="secondary" @click="showModalDelete">삭제</b-button>
        </b-button-group>
      </div>
      </b-modal>
    </td>
  </tr>

  
</template>

<script>
import {mapActions} from "vuex";
export default {
  props: {
    user: Object,
  },
  data: function() {
    return {
      id: "",
    }
  },
  computed: {
    input() {
      return { ...this.user };
    },
  },
  methods: {
    ...mapActions(["modifyUser", "getUsers", "deleteUser"]),
    showModalModify() {
      this.$refs[`user-${this.input.no}`].show();
    },
    hideModalModify() {
      this.$refs[`user-${this.input.no}`].hide();
    },
    modify() {
      const payload = {
        user: {
          no: this.input.no,
          id: this.input.id,
          name: this.input.name,
          pw: this.input.pw,
        },
        callback: () => {
          //수정한 내용 지우기
          this.input.id = "";
          this.input.name = "";
          this.input.pw = "";

          //모달 창 닫기
          this.hideModalModify();

          // 회원 목록 갱신하기
          this.getUsers({
            no: this.user.no,
          })

          // 수정 완료 Toast 출력
          this.$bvToast.toast("회원 정보가 수정되었습니다." , {
            title : "회원 관리 알림",
            variant: "success",
            toaster: "b-toaster-bottom-center",
            autoHideDelay: 3000,
            solid: true,
          });
        },
      };

      this.modifyUser(payload);
    },
    // 회원 정보 삭제 구현 시작
    showModalDelete() {
      this.hideModalModify(); //수정 모달 창 닫기

      // 삭제 모달 창 띄우기
      this.$bvModal
        .msgBoxConfirm(`회원을 삭제하시겠습니까?`, {
          centered: true,
          size: 'sm',
          buttonSize: 'sm',
          okVariant: 'danger',
          okTitle: '네',
          cancelTitle: '아니오',
        })
        .then((value) => {
          if(value){
            this.delete();
          }
        });
    },
    delete() {
      const payload = {
        id: this.input.id,
        callback: (status) => {
          if(status==200){
            //회원 목록 갱신
            this.getUsers({
              no: this.user.no,
            })

            // 삭제 완료 Toast 출력
            setTimeout(() => {
              this.$bvToast.toast(`회원이 삭제되었습니다.`, {
                title: "회원 관리 알림",
                variant: "success",
                toaster: "b-toaster-bottom-center",
                autoHideDelay: 3000,
                solid: true,
              });
            }, 500);
          }
          else if(status == 500) {
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
      this.deleteUser(payload);
    },
  },
  create() {
    this.id = this.input.id;  
  },
};
</script>

<style scoped>
.table {
  display: table;
}
.tr {
  display: table-row;
}
.td {
  display: table-cell;
}
</style>
