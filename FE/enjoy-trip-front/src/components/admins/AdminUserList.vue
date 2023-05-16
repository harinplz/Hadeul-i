<template>
  <b-container>
    <b-list-group>
      <user-list-row v-for="user in users" :key="user.no" :user="user">
      </user-list-row>
    </b-list-group>
    <!-- <b-table
      v-if="users.length"
      class="text-center"
      striped
      hover
      head-variant="dark"
      :items="users"></b-table> -->
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
