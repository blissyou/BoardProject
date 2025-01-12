<template>
  <div id="app">
    <canvas ref="canvas"></canvas>
  </div>
</template>

<script>
export default {
  name: "TextParticleAnimation",
  data() {
    return {
      config: {
        text: "Hover Effect", // 텍스트 내용
        fontSize: 100, // 텍스트 크기
        springStrength: 0.2, // 스프링 힘
        damping: 0.85, // 감쇠율
        mouseRadius: 250, // 마우스 반응 반경
        mouseForce: 1, // 마우스 힘
      },
      particles: [],
      mouse: { x: null, y: null },
      canvas: null,
      ctx: null,
    };
  },
  methods: {
    createParticles() {
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
      this.ctx.font = `bold ${this.config.fontSize}px Arial`;
      this.ctx.fillStyle = "white";
      this.ctx.textAlign = "center";
      this.ctx.textBaseline = "middle";
      this.ctx.fillText(this.config.text, this.canvas.width / 2, this.canvas.height / 2);

      const imageData = this.ctx.getImageData(0, 0, this.canvas.width, this.canvas.height);
      const data = imageData.data;

      for (let y = 0; y < this.canvas.height; y += 5) {
        for (let x = 0; x < this.canvas.width; x += 5) {
          const index = (y * this.canvas.width + x) * 4;
          if (data[index] > 128) {
            const particle = this.createParticle(x, y);
            this.particles.push(particle);
          }
        }
      }
    },
    createParticle(x, y) {
      return {
        x: x + Math.random() * 20 - 10,
        y: y + Math.random() * 20 - 10,
        targetX: x,
        targetY: y,
        vx: 0,
        vy: 0,
        size: Math.random() * 2 + 1,
        hue: Math.random() * 360,
        update(mouse, config) {
          if (mouse.x !== null && mouse.y !== null) {
            const dx = this.x - mouse.x;
            const dy = this.y - mouse.y;
            const distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < config.mouseRadius) {
              const angle = Math.atan2(dy, dx);
              const force = (config.mouseRadius - distance) / config.mouseRadius;
              this.vx += Math.cos(angle) * force * config.mouseForce * 10;
              this.vy += Math.sin(angle) * force * config.mouseForce * 10;
            }
          }

          const springForceX = (this.targetX - this.x) * config.springStrength;
          const springForceY = (this.targetY - this.y) * config.springStrength;
          this.vx += springForceX;
          this.vy += springForceY;

          this.vx *= config.damping;
          this.vy *= config.damping;

          this.x += this.vx;
          this.y += this.vy;

          this.hue += 1;
          if (this.hue >= 360) this.hue = 0;
        },
        draw(ctx) {
          ctx.beginPath();
          ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
          ctx.fillStyle = `hsl(${this.hue}, 50%, 50%)`;
          ctx.fill();
        },
      };
    },
    animate() {
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);

      this.particles.forEach((particle) => {
        particle.update(this.mouse, this.config);
        particle.draw(this.ctx);
      });

      requestAnimationFrame(this.animate);
    },
    resizeCanvas() {
      this.canvas.width = window.innerWidth;
      this.canvas.height = window.innerHeight;
      this.particles = [];
      this.createParticles();
    },
    handleMouseMove(event) {
      const rect = this.canvas.getBoundingClientRect();
      this.mouse.x = event.clientX - rect.left;
      this.mouse.y = event.clientY - rect.top;
    },
    handleMouseOut() {
      this.mouse.x = null;
      this.mouse.y = null;
    },
  },
  mounted() {
    this.canvas = this.$refs.canvas;
    this.ctx = this.canvas.getContext("2d");

    this.resizeCanvas();
    this.createParticles();
    this.animate();

    window.addEventListener("resize", this.resizeCanvas);
    this.canvas.addEventListener("mousemove", this.handleMouseMove);
    this.canvas.addEventListener("mouseout", this.handleMouseOut);
  },
  beforeUnmount() {
    window.removeEventListener("resize", this.resizeCanvas);
    this.canvas.removeEventListener("mousemove", this.handleMouseMove);
    this.canvas.removeEventListener("mouseout", this.handleMouseOut);
  },
};
</script>

<style>
/* HTML과 Body의 기본 스타일 제거 */
html,
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  background-color: #000; /* 검은색 배경 */
  overflow: hidden; /* 스크롤바 제거 */
}

/* Vue 앱의 루트 컨테이너 */
#app {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  position: relative;
}

/* 캔버스가 전체 화면을 덮도록 설정 */
canvas {
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  display: block;
  background-color: #000;
}
</style>