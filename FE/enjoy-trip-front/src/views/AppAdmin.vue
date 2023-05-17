<template>
  <admin-user-list></admin-user-list>
</template>

<script>
export default {
  components: {
    AdminUserList: () => import("@/components/admins/AdminUserList.vue"),
  },
};
</script>
