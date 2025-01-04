<template>
  <div class="bg-white p-6 rounded-md shadow-md">
    <h2 class="text-xl font-bold mb-4">게시판 목록</h2>
    <ul v-if="boards.length > 0">
      <li
          v-for="board in boards"
          :key="board.id"
          class="border-b py-2 flex justify-between items-center"
      >
        <span>{{ board.boardName }}</span>
        <button
            @click="viewBoard(board.id)"
            class="text-blue-500 hover:underline"
        >
          보기
        </button>
      </li>
    </ul>
    <p v-else class="text-gray-500">게시판이 없습니다. 새 게시판을 추가하세요!</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      boards: [], // 게시판 데이터를 저장할 배열
      error: null, // 에러 메시지 저장
    };
  },
  created() {
    this.fetchBoards(); // 컴포넌트 생성 시 데이터 가져오기
  },
  methods: {
    async fetchBoards() {
      try {
        const response = await axios.get("http://localhost:8080/api/board");
        this.boards = response.data;
      } catch (error) {
        this.error = "게시판 데이터를 불러올 수 없습니다.";
        console.error("API 호출 에러:", error);
      }
    },
    viewBoard(id) {
      alert(`게시판 ${id} 보기!`);
      // 상세 페이지로 이동하거나 모달 표시 로직 구현 가능
    },
  },
};
</script>