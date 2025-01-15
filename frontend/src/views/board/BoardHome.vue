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
        text: "킹 받지~", // 텍스트 내용
        fontSize: 100, // 텍스트 크기
        textPosition: { x: 0.5, y: 0.2 }, // 텍스트 위치 비율 (가로, 세로)
        springStrength: 0.2, // 스프링 힘
        damping: 0.85, // 감쇠율
        mouseRadius: 250, // 마우스 반응 반경
        mouseForce: 1, // 마우스 힘
      },
      customWidth: 800, // 사용자 정의 너비
      customHeight: 600, // 사용자 정의 높이
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

      const textX = this.canvas.width * this.config.textPosition.x;
      const textY = this.canvas.height * this.config.textPosition.y;
      this.ctx.fillText(this.config.text, textX, textY);

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
      const aspectRatio = 16 / 9; // 원하는 비율 (16:9)
      const width = window.innerWidth;
      const height = window.innerHeight;

      if (width / height > aspectRatio) {
        this.canvas.width = height * aspectRatio;
        this.canvas.height = height;
      } else {
        this.canvas.width = width;
        this.canvas.height = width / aspectRatio;
      }

      this.particles = [];
      this.createParticles();
    },
    debounce(func, wait) {
      let timeout;
      return function (...args) {
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(this, args), wait);
      };
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

    const debounceResize = this.debounce(this.resizeCanvas, 100);
    window.addEventListener("resize", debounceResize);

    this.resizeCanvas();
    this.createParticles();
    this.animate();

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
html,
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  background-color: #000;
  overflow: hidden;
}

#app {
  width: 100%;
  height: 100%;
  position: relative;
}

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