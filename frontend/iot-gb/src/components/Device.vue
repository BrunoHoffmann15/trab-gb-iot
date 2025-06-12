<script>
const BASE_COLORS = [
  '#b36bcf',
  '#6b6dcf',
  '#6bcfb1',
  '#fd8c0a',
  '#fd0a0a',
  '#ffffff'
]
export default {
  props: {
    device: {
      type: Object,
      required: true,
    },
    lastMeasurement: {
      type: Number,
      default: 22
    }
  },
  computed: {
    radiusBackgroundColor() {
      return this.getBackgroundColor(true)
    },
    deviceBackgroundColor() {
      return this.getBackgroundColor()
    },
    style() {
      return {
        'background-color': this.backgroundColor
      }
    },
  },
  methods: {
    getBackgroundColor(isRadius) {
      let color = BASE_COLORS[5]
      if (this.lastMeasurement < 0) {
        color = BASE_COLORS[0]
      } // roxo

      if (this.lastMeasurement >= 0 && this.lastMeasurement < 22) {
        color = BASE_COLORS[1] // azul
      }

      if (this.lastMeasurement >= 22 && this.lastMeasurement <= 24) {
        color = BASE_COLORS[2] // verde
      }

      if (this.lastMeasurement > 24 && this.lastMeasurement <= 34) {
        color = BASE_COLORS[3] // laranja
      }

      if (this.lastMeasurement > 34) {
        color = BASE_COLORS[5] // vermelho
      }

      return isRadius ? `${color}3b` : color // default
    },
    getRandomAnimationDelay() {
      return `${Math.floor(Math.random() * 5) + 1}s`;
    }
  }
}
</script>

<template>
  <div class="deviceBlock">
    <div class="device" :style="{'background-color': deviceBackgroundColor}">
      <div class="deviceRadius" :style="{'background-color': radiusBackgroundColor, 'animation-delay': getRandomAnimationDelay()}" />
    </div>
  </div>
</template>

<style scoped>
.deviceBlock {
  width: 100px;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #ffffff3b;
  border-style: dashed;
}

.deviceRadius {
  position: absolute;
  width: 50px;
  height: 50px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 100%;
  animation: pulse 5s infinite cubic-bezier(0.18, 0.89, 0.32, 1.28);
}

.device {
  position: relative;
  width: 10px;
  height: 10px;
  border-radius: 100%;
}

@keyframes pulse {
  0%, 100% {
    transform: translate(-50%, -50%) scale(1);
  }
  50% {
    transform: translate(-50%, -50%) scale(1.3);
  }
}
</style>