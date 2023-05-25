<template>
  <div>
    <div class="comments" v-for="comment in comments" :key="comment.id">
      <div>
        <span class="comment_name">{{ comment.userName }}</span>
        <span class="comment_date">{{ formatDate(comment.registDate) }}</span>
      </div>
      <div class="comment_content">{{ comment.comment }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["comments"],

  methods: {
    formatDate(date) {
      return date.split(" ")[0];
    },
  },
};
</script>

<style scoped>
.comments {
  width: 100%;
  height: 120px;
  margin: 10px auto 10px auto;
  border-top: 1px solid #cacaca;
  padding: 10px;
}

.comment_name {
  font-size: 18px;
  font-weight: bold;
}

.comment_date {
  margin-left: 10px;
}
</style>
