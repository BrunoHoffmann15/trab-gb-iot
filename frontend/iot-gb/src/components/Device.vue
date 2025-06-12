<script>
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
    backgroundColor() {
      if (this.lastMeasurement < 0) return '#b36bcf3b' // roxo

      if (this.lastMeasurement >= 0 && this.lastMeasurement < 22) {
        return '#6b6dcf3b' // azul
      }

      if (this.lastMeasurement >= 22 && this.lastMeasurement <= 24) {
        return '#6bcfb13b' // verde
      }

      if (this.lastMeasurement > 24 && this.lastMeasurement <= 34) {
        return '#fd8c0a3b' // laranja
      }

      if (this.lastMeasurement > 34) {
        return '#fd0a0a3b' // vermelho
      }

      return '#ffffff3b' // default
    },
    style() {
      return {
        'background-color': this.backgroundColor
      }
    }
  },
}
</script>

<template>
  <div class="deviceBlock">
    <div class="device">
      <div class="deviceRadius" :style="{'background-color': backgroundColor}" />
    </div>
  </div>
</template>

<style scoped>
.deviceBlock {
  width: 80px;
  height: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #ffffff3b;
  border-style: dashed;
}

.deviceRadius {
  position: absolute;
  width: 25px;
  height: 25px;
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
  background-color: #000;
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