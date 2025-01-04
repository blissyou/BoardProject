<template>
  <div class="min-h-screen bg-gray-100">
    <header class="bg-blue-500 text-white py-4 shadow-md">
      <div class="container mx-auto text-center">
        <h1 class="text-3xl font-bold">게시판 관리 시스템</h1>
      </div>
    </header>

    <main class="container mx-auto py-8">
      <CreateBoard />
      <BoardList />
    </main>
  </div>
</template>

<script>
import CreateBoard from './components/CreateBoard.vue';
import BoardList from './components/BoardList.vue';

export default {
  components: {
    CreateBoard,
    BoardList,
  },
};
</script>