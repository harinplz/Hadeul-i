<template>
  <b-row>
    <b-col>
      <b-card border-variant="dark" no-body>
        <b-card-header class="text-center">
          <h3>{{ community.title }}</h3>
          <div>
            <h6>{{ community.author }}</h6>
          </div>
        </b-card-header>
        <b-card-body class="text-left">
          <div :inner-html.prop="community.content | enterToBr"></div>
        </b-card-body>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
export default {
  props: {
    community: Object,
  },
  created() {
    console.log(this.community);
  },
};
</script>
