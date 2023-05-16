<template>
  <b-container>
    <!-- <b-table
      v-if="users.length"
      class="text-center"
      striped
      hover
      head-variant="dark"
      :items="users"></b-table> -->

    <div v-if="users.length > 0">
      <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">번호</th>
            <th scope="col">id</th>
            <th scope="col">이름</th>
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

    <!-- <div v-if="books.length > 0">
      <table id="book-list">
        <colgroup>
          <col style="width: 5%" />
          <col style="width: 20%" />
          <col style="width: 40%" />
          <col style="width: 20%" />
          <col style="width: 15%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>ISBN</th>
            <th>제목</th>
            <th>저자</th>
            <th>가격</th>
          </tr>
        </thead>
        <tbody>
          <list-row
            v-for="(book, index) in books"
            :key="index"
            :no="`${index + 1}`"
            :isbn="book.isbn"
            :title="book.title"
            :author="book.author"
            :price="Number(book.price)"></list-row>
        </tbody>
      </table>
    </div> -->
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  components: {
    "user-list-row": () =>
      import("@/components/admins/include/UserListRow.vue"),
  },
  methods: {
    ...mapActions(["getUsers"]),
  },
  created() {
    this.getUsers();
  },
  computed: {
    ...mapGetters(["users"]),
  },
};
</script>
