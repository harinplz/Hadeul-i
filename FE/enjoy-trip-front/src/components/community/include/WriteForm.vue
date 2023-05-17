<template>
  <b-container>
    <b-row>
      <b-col class="text-center">
        <h1>{{ writeFormTitle }}</h1>
      </b-col>
    </b-row>
    <b-row>
      <b-col class="text-left">
        <b-form>
          <b-form-group
            label-cols="12"
            id="title-group"
            label="제목:"
            label-for="title"
            description="제목을 입력하세요.">
            <b-form-input
              id="title"
              ref="title"
              v-model="title"
              type="text"
              required
              placeholder="제목 입력..." />
          </b-form-group>
          <b-form-group
            label-cols="12"
            id="userId-group"
            label="작성자:"
            label-for="userId"
            description="작성자를 입력하세요.">
            <b-form-input
              id="userId"
              ref="userId"
              v-model="userId"
              type="text"
              required
              placeholder="작성자 입력..." />
          </b-form-group>
          <b-form-group
            label-cols="12"
            id="content-group"
            label="내용:"
            label-for="content"
            ref="content">
            <b-form-textarea
              id="content"
              v-model="content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"></b-form-textarea>
          </b-form-group>

          <b-button
            v-if="type == 'create'"
            variant="primary"
            class="m-1"
            @click="validate"
            >등록</b-button
          >
          <b-button v-else variant="success" class="m-1" @click="validate"
            >수정</b-button
          >
          <b-button variant="primary" class="m-1" @click="moveList"
            >목록</b-button
          >
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";

export default {
  props: {
    type: String, // create, modify
  },
  data: function () {
    return {
      boardNo: "",
      title: "",
      userId: "",
      content: "",
    };
  },
  methods: {
    ...mapActions(["createCommunity"]),
    validate() {
      let isValid = true; // 유효하면 true
      let errMsg = "";

      !this.title
        ? ((isValid = false),
          (errMsg = "제목을 입력해주세요."),
          this.$refs.title.focus())
        : !this.userId
        ? ((isValid = false),
          (errMsg = "작성자를 입력해주세요."),
          this.$refs.userId.focus())
        : !this.content
        ? ((isValid = false),
          (errMsg = "내용을 입력해주세요."),
          this.$refs.content.focus())
        : (isValid = true);

      if (!isValid) {
        alert(errMsg);
      } else {
        const payload = {
          community: {
            title: this.title,
            userId: this.userId,
            content: this.content,
          },
          callback: (status) => {
            switch (status) {
              case 200:
                if (this.type == "create") {
                  alert("등록 되었습니다.");
                } else {
                  alert("수정 되었습니다.");
                }
                break;

              case 400:
                alert("잘못된 요청입니다.");
                break;

              case 500:
                alert("서버 오류!!!");
                break;
            }
            this.moveList();
          },
        };
        if (this.type == "create") {
          this.createCommunity(payload);
        } else {
          this.modifyBook(payload);
        }
      }
    },
    moveList() {
      this.$router.push({ name: "CommunityList" });
    },
  },
  created: function () {
    if (this.type == "modify") {
      this.isbn = this.book.isbn;
      this.title = this.book.title;
      this.author = this.book.author;
      this.price = this.book.price;
      this.content = this.book.content;
    }
  },
  computed: {
    // ...mapGetters(["book"]),
    writeFormTitle: function () {
      return this.type == "create" ? "글 등록" : "글 수정";
    },
  },
};
</script>
