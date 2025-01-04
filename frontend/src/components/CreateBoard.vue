<template>
  <div class="bg-white p-6 rounded-md shadow-md mb-6">
    <h2 class="text-xl font-bold mb-4">새 게시판 생성</h2>
    <form @submit.prevent="createBoard" class="flex items-center gap-4">
      <input
          v-model="boardName"
          type="text"
          placeholder="게시판 이름"
          class="border rounded-md px-4 py-2 w-full"
      />
      <button
          type="submit"
          class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600"
      >
        생성
      </button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      boardName: "",
    };
  },
  methods: {
    createBoard() {
      fetch("/api/board", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ board_name: this.boardName }),
      })
          .then((response) => response.json())
          .then(() => {
            alert("게시판이 생성되었습니다.");
            this.boardName = "";
            this.$emit("boardCreated");
          })
          .catch((error) => console.error(error));
    },
  },
};
</script>