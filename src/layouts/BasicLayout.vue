<template>
  <div id="basicLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader />
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">Website By Dantalian</a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
import GlobalHeader from "../components/GlobalHeader.vue";

export default {
  name: "MainTemplate",
  components: {
    GlobalHeader,
  },
};
</script>

<style scoped>
.header {
  /* margin-bottom: 16px; */
  box-shadow: #eee 1px 1px 5px;
}

.content {
  background: linear-gradient(to right, #fefefe, #fff);
  margin-bottom: 16px;
  padding: 20px;
}

.footer {
  background: #efefef;
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>
