<template>
  <div class="bg-white p-6 rounded-md shadow-md">
    <h2 class="text-xl font-bold mb-4">모든 게시판 목록</h2>

    <ul v-if="boards.length > 0">
      <li
          v-for="(board, index) in boards"
          :key="board.id"
          class="border-b py-4"
      >
        <!-- 클릭 이벤트 추가 -->
        <h3
            class="text-lg font-bold cursor-pointer text-blue-500"
            @click="toggleBoard(index)"
        >
          {{ board.board_name }}
        </h3>

        <!-- 게시물 목록을 조건부 렌더링 -->
        <ul v-show="board.isOpen" class="mt-2 pl-4 border-l">
          <li
              v-for="post in board.postlist"
              :key="post.id"
              class="py-2"
          >
            <strong>{{ post.title }}</strong> - {{ post.userName }}
          </li>
        </ul>
        <ul v-if="board.isOpen" class="mt-2 pl-4 border-l">
          <li
              v-for="post in board.postlist"
              :key="post.id"
              class="py-2"
          >
            <strong>{{ post.title }}</strong> - {{ post.userName }}
          </li>
          <p v-if="board.postlist.length === 0" class="text-sm text-gray-400">
            게시물이 없습니다.
          </p>
        </ul>
      </li>
    </ul>
    <p v-else class="text-gray-500">게시판이 없습니다.</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      boards: [], // 모든 게시판 데이터를 저장할 배열
      loading: true, // 로딩 상태
      error: null, // 에러 메시지
    };
  },
  created() {
    this.fetchAllBoards(); // 컴포넌트 생성 시 모든 게시판 데이터 가져오기
  },
  methods: {
    async fetchAllBoards() {
      try {
        const response = await axios.get("http://localhost:8080/api/board/all");
        console.log("응답 데이터:", response.data);

        // API 응답 데이터를 boards에 저장
        this.boards = response.data.map((board) => ({
          ...board,
          isOpen: false, // 게시판의 열림 상태를 추가
        }));
      } catch (error) {
        console.error("API 호출 에러:", error);
        this.error = "데이터를 불러오는 데 실패했습니다.";
      } finally {
        this.loading = false; // 로딩 상태 종료
      }
    },
    toggleBoard(index) {
      // 클릭된 게시판의 열림 상태를 토글
      this.boards[index].isOpen = !this.boards[index].isOpen;
    },
  },
};
</script>