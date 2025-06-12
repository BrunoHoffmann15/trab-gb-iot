export const chartData = (temperatureMeasurementsByDevice) => {
  const keys = Object.keys(temperatureMeasurementsByDevice)
  const datasets = Object.values(temperatureMeasurementsByDevice)?.map((measurements, index) => {
    const data = measurements?.map(({ temperature }) => temperature)
    const color = '#' + Math.floor(Math.random() * 16777215).toString(16).padStart(6, '0')
    return {
      label: `Device ${keys[index]}`,
      data,
      borderColor: color,        // Cor da linha
      pointBackgroundColor: color, // Cor dos pontos (opcional)
      fill: false,
      tension: 0.4
    }
  })
  return {
    labels: temperatureMeasurementsByDevice[keys[0]].map(measurement => new Date(measurement.createdAt).toISOString().split('T')[1].split('.')[0]),
    datasets
  }
}

export const options = {
  responsive: true,
  maintainAspectRatio: true
}
