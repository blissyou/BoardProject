<template>
  <div class="board-detail">
    <div class="common-buttons">
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
      <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
    </div>
    <div class="board-contents">
      <input type="text" v-model="title" class="w3-input w3-border" placeholder="제목을 입력해주세요.">
      <input type="text" v-model="user_name" class="w3-input w3-border" placeholder="작성자를 입력해주세요."
             v-if="idx === undefined">
      <input type="text" v-model="email" class="w3-input w3-border" placeholder="이메일을 입력해주세요">
      <input type="text" v-model="password" class="w3-input w3-border" placeholder="비밀번호를 입력해주세요">
    </div>
    <div class="board-contents">
      <textarea cols="30" rows="10" v-model="content" class="w3-input w3-border" style="resize: none;">
      </textarea>
    </div>
    <div class="common-buttons">
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">완료</button>&nbsp;
      <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
    </div>
  </div>
</template>

<script>
export default {
  data() { //변수생성
    return {
      requestBody: this.$route.query,
      idx: this.$route.query.idx,

      title: '',
      user_name: '',
      email: '',
      password: '',
      content: '',
      created_at: ''
    }
  },
  mounted() {
    this.fnGetView()
  },
  methods: {
    fnGetView() {
      if (this.idx !== undefined) {
        this.$axios.get(this.$serverUrl + '/board/' + this.idx, {
          params: this.requestBody
        }).then((res) => {
          this.title = res.data.title
          this.author = res.data.author
          this.content = res.data.content
          this.created_at = res.data.created_at
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    fnList() {
      delete this.requestBody.idx
      this.$router.push({
        path: './list',
        query: this.requestBody
      })
    },
    fnView(idx) {
      this.requestBody.idx = idx
      this.$router.push({
        path: './detail',
        query: this.requestBody
      })
    },
    fnSave() {
      // let apiUrl = "localhost:8081/board"
      this.form = {
        "idx": this.id,
        "title": this.title,
        "user_name" : this.user_name,
        "email": this.email,
        "password": this.password,
        "content": this.content,
      }

      if (this.idx === undefined) {
        //INSERT
        this.$axios.post('http://localhost:8080/api/post', this.form)
            .then((res) => {
              alert('글이 저장되었습니다.')
              this.fnView(res.data.idx)
            }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다. \n 잠시후 시도해주세요 (Insert Error)')
          }
        })
      } else {
        this.$axios.patch(`http://localhost:8080/board/${this.idx}`, this.form)
            .then((res) => {
              alert('글이 저장되었습니다.')
              this.fnView(res.data.idx)
            }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다 \n 잠시후 시도해 주세요 (Update Error)')
          }
        })
      }
    },
  }
}
</script>
<style scoped>

</style>