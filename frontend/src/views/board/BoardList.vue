<template>
  <div class="board-list">
    <div class="common-buttons">
      <button type="button"  class="w3-button w3-round w3-blue-gray" v-on:click="fnWrite">등록</button>
    </div>
    <table class="w3-table-all">
      <thead>
      <tr>
        <th>No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일시</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(row, idx) in list" :key="row.id">
        <td>{{ no - idx }}</td>
        <td>
          <a v-on:click="fnView(row.id)">{{ row.title }}</a>
        </td>
        <td>{{ row.user_name }}</td>
        <td>{{ row.posted_at }}</td>
      </tr>
      </tbody>
    </table>
    <div class="pagination w3-bar w3-padding-16 w3-small" v-if="paging.totalElement > 0">
      <span class="pg">
        <a href="javascript:" @click="fnPage(1)" class="first w3-button w3-bar-item w3-border">&lt;&lt;</a>
        <a
            href="javascript:"
            v-if="paging.page > 0"
            @click="fnPage(paging.page)"
            class="prev w3-button w3-bar-item w3-border"
        >&lt;</a
        >
        <template v-for="n in paginavigation()">
          <template v-if="paging.page === n - 1">
            <strong class="w3-button w3-bar-item w3-border w3-green" :key="n">{{ n }}</strong>
          </template>
          <template v-else>
            <a
                class="w3-button w3-bar-item w3-border"
                href="javascript:"
                @click="fnPage(n)"
                :key="n"
            >{{ n }}</a
            >
          </template>
        </template>
        <a
            href="javascript:"
            v-if="paging.page < paging.totalPages - 1"
            @click="fnPage(paging.page + 2)"
            class="next w3-button w3-bar-item w3-border"
        >&gt;</a
        >
        <a
            href="javascript:"
            @click="fnPage(paging.totalPages)"
            class="last w3-button w3-bar-item w3-border"
        >&gt;&gt;</a
        >
      </span>
    </div>
    <div>
      <select v-model="search_key">
        <option value="">- 선택 -</option>
        <option value="user_name">작성자</option>
        <option value="title">제목</option>
        <option value="content">내용</option>
      </select>
      &nbsp;
      <input type="text" v-model="search_value" @keyup.enter="fnPage(1)" />
      &nbsp;
      <button @click="fnPage(1)">검색</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      requestBody: {}, // 데이터 전송
      list: [], // 리스트 데이터
      no: 0, // 게시판 숫자
      paging: {
        page: 0,
        size: 10,
        totalPages: 1,
        totalElement: 0,
      }, // 페이징 데이터
      page: this.$route.query.page ? parseInt(this.$route.query.page) : 1,
      size: this.$route.query.size ? parseInt(this.$route.query.size) : 10,
      search_key: this.$route.query.sk || '',
      search_value: this.$route.query.sv || '',
    };
  },
  mounted() {
    this.fnGetList();
  },
  methods: {
    //TODO: 검색기능 API 구현
    fnGetList() {
      this.requestBody = {
        sk: this.search_key,
        sv: this.search_value,
        page: this.page - 1,
        size: this.size,
      };

      this.$axios
          .get('http://localhost:8080/api/post/all', {
            params: this.requestBody,
          })
          .then((res) => {
            if (res.data) {
              this.list = res.data.body; // 게시물 데이터
              this.paging = res.data.pagination; // 페이징 데이터
              this.no = this.paging.totalElement - this.paging.page * this.paging.size;
            } else {
              console.error('Error retrieving data:', res.data.message);
            }
          })
          .catch((err) => {
            if (err.message.includes('Network Error')) {
              alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.');
            }
          });
    },
    fnView(id) {
      this.$router.push({
        path: './detail',
        query: { id },
      });
    },
    fnWrite() {
      this.$router.push({
        path: './write',
      });
    },
    fnPage(n) {
      if (this.page !== n) {
        this.page = n;
        this.fnGetList();
      }
    },
    paginavigation() {
      const start = Math.max(1, this.paging.page - 4);
      const end = Math.min(this.paging.totalPages, this.paging.page + 5);
      const pages = [];
      for (let i = start; i <= end; i++) {
        pages.push(i);
      }
      return pages;
    },
  },
};
</script>