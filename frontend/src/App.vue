<template>
  <div id="app">
    <PageHeader />
    <main class="main-content">
      <router-view />
    </main>
    <PageFooter />
  </div>
</template>

<script>
import PageHeader from "@/components/PageHeader.vue";
import PageFooter from "@/components/PageFooter.vue";
import "@/assets/styles.css";
export default {
  components: {
    PageHeader,
    PageFooter,
  },
};
</script>

