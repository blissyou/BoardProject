<template>
  <div class="board-list">
    <table class="w3-table-all">
      <thead>
      <tr>
        <th>No</th>
        <th>게시판 이름</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(row, idx) in list" :key="row.id">
        <td>{{ no - idx }}</td>
        <td>
          <a v-on:click="fnView(row.id)">{{ row.board_name }}</a>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      requestBody: {}, // 데이터 전송
      list: [], // 리스트 데이터
      no: 0, // 게시판 숫자
    };
  },
  mounted() {
    this.fnGetList();
  },
  methods: {
    fnGetList() {
      this.$axios
          .get('http://localhost:8080/api/board/all')
          .then((res) => {
            // 응답 데이터 확인
            console.log('API Response:', res.data);

            // 데이터 할당
            if (res.data) {
              this.list = res.data; // 배열 그대로 할당
              this.no = this.list.length; // 총 게시판 개수 저장
            } else {
              console.error('No data in response:', res.data);
            }
          })
          .catch((err) => {
            if (err.message.includes('Network Error')) {
              alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.');
            } else if (err.response) {
              const { status, data } = err.response;
              alert(`서버 오류: [${status}] ${data?.message || '알 수 없는 오류가 발생했습니다.'}`);
            } else {
              console.error('Unexpected error:', err);
            }
          });
    },
    fnView(id) {
      this.$router.push({
        path: `./post/${id}`,
      });
    },
    fnWrite() {
      this.$router.push({
        path: './write',
      });
    },
  },
};
</script>