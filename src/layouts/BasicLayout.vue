<template>
  <div id="basicLayout">
    <a-layout style="height: 400px">
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
  background: red;
  margin-bottom: 16px;
}

.content {
  background: linear-gradient(to right, #eee, #fff);
  margin-bottom: 16px;
}

.footer {
  background: #efefef;
  padding: 16px;
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>
