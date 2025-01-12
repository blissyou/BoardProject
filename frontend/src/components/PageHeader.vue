<template>
  <header class="page-header">
    <div class="button-container">
      <router-link to="/" class="glow-button">Home</router-link>
      <router-link to="/about" class="glow-button">About</router-link>
      <router-link to="/board" class="glow-button">게시판</router-link>
      <div class="glow-light" :style="glowLightStyle"></div>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      glowLightStyle: {
        opacity: 0,
        transform: "translate(-50%, -50%)",
      },
    };
  },
  methods: {
    handleMouseMove(event) {
      const rect = event.currentTarget.querySelector('.button-container').getBoundingClientRect();
      const x = event.clientX - rect.left; // 컨테이너 내부의 X 좌표
      const y = event.clientY - rect.top;  // 컨테이너 내부의 Y 좌표

      this.glowLightStyle = {
        opacity: 1, // 빛을 표시
        transform: `translate(${x}px, ${y}px) translate(-50%, -50%)`, // 빛 위치를 중앙 정렬
      };
    },
    handleMouseLeave() {
      this.glowLightStyle.opacity = 0; // 마우스가 벗어나면 빛 숨기기
    },
  },
};
</script>
<style scoped>
.page-header {
  position: fixed; /* 헤더를 고정 */
  top: 0;
  left: 0;
  width: 100%;
  background-color: #1e1e1e;
  padding: 10px 20px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  z-index: 1000; /* 헤더를 항상 최상단에 유지 */
}

.button-container {
  display: flex;
  justify-content: center; /* 수평 가운데 정렬 */
  gap: 20px;
  position: relative;
}

.glow-button {
  background-color: #333;
  color: white;
  font-size: 16px;
  border: 2px solid transparent;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
  text-decoration: none;
  text-align: center;
  transition: border-color 0.3s, box-shadow 0.3s, color 0.3s;
}

.glow-button:hover {
  border-color: rgba(0, 150, 255, 0.7);
  box-shadow: 0 0 10px rgba(0, 150, 255, 0.8), 0 0 20px rgba(0, 150, 255, 0.6);
  color: #00aaff;
}

.glow-light {
  position: absolute;
  width: 200px;
  height: 200px;
  background: radial-gradient(ellipse, rgba(0, 150, 255, 0.4), transparent 70%);
  border-radius: 50%;
  filter: blur(20px);
  pointer-events: none;
  opacity: 0;
  transform: translate(-50%, -50%);
  z-index: 1;
  transition: opacity 0.3s, transform 0.1s;
}
</style>