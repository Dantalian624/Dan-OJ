<template>
  <div id="app">
    <BasicLayout />
  </div>
</template>

<style></style>

<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import { onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

/**
 * 全局初始化函数，有全局单次调用的代码都可以写到这里
 */
const doInit = () => {
  console.log("hello 欢迎来到我的项目");
};
onMounted(() => {
  doInit();
});

const router = useRouter();
const store = useStore();

router.beforeEach((to, from, next) => {
  //仅管理员可见，判断当前用户是否有权限
  if (to.meta?.access === "canAdmin") {
    if (store.state.user.loginUser?.role !== "admin") {
      next("/noAuth");
      return;
    }
  }
  next();
  // console.log(to);
});
</script>
