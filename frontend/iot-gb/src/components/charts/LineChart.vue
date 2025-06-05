<script>
import { mapState } from 'vuex'
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js'
import { Line } from 'vue-chartjs'

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
)

export default {
  components: {
    Line
  },
  props: {
    data: {
      type: Object,
      required: true,
    },
    options: {
      type: Object,
      required: true
    }
  }
}
</script>

<template>
  <Line :data="data" :options="options" />
</template>
