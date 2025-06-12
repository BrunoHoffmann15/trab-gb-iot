<script setup>
import DevicesTable from '../components/DevicesTable.vue'
import AirConditionerTable from '../components/AirConditionerTable.vue'
import MeasurementsTable from '../components/MeasurementsTable.vue'
import ChartWrapper from '../components/charts/ChartWrapper.vue'
</script>

<template>
  <ChartWrapper />
  <AirConditionerTable />
  <DevicesTable />
  <MeasurementsTable />
</template>
