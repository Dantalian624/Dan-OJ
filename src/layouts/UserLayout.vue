<template>
  <div id="userlayout" class="userLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <a-space>
          <img src="../assets/DanLogo.png" class="logo" />
          <div>Dan OJ</div>
        </a-space>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">Website By Dantalian</a-layout-footer>
    </a-layout>
  </div>
</template>

<script></script>

<style scoped>
.userLayout {
}

.logo {
  width: 146px;
  height: 72px;
}

.header {
  margin-top: 16px;
  margin-bottom: 16px;
  margin-left: 90px;
  /* box-shadow: #eee 1px 1px 5px; */
}

.content {
  /* background: linear-gradient(to right, #eee, #fff); */
  margin-bottom: 16px;
  padding: 20px;
  text-align: center;
}

.footer {
  /* background: #efefef; */
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>
