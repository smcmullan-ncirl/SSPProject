package ie.ncirl.sspproject.dataprocess.measurement.udpburst

import ie.ncirl.sspproject.dataprocess.measurement.DeviceProperties

case class UdpBurstMeasurement
(
  task: Task,
  parameters: Parameters,
  success: String,
  timestamp: String,
  device_properties: DeviceProperties,
  values: Values,
  `type`: String,
  id: String
)

case class Task
(
  count: String,
  filter: String,
  parameters: TaskParameters,
  created: String,
  start_time: String,
  interval_sec: String,
  priority: String,
  tag: String,
  end_time: String,
  `type`: String,
  id: String
)

case class TaskParameters
(
  packet_size_byte: String,
  direction: String,
  udp_interval: String,
  target: String,
  packet_burst: String
)

case class Parameters
(
  count: String,
  target: String,
  parameters: String,
  packet_size_byte: String,
  start_time: String,
  interval_sec: String,
  priority: String,
  dir_up: String,
  end_time: String,
  key: String,
  dst_port: String,
  `type`: String,
  udp_burst_count: String,
  udp_interval: String,
  context_interval_sec: String
)

case class Values
(
  target_ip: String,
  PRR: String,
  error: String,
  jitter: String,
  Inversion_Number: String,
  inversion_number: String,
  loss_rate: String,
  loss_ratio: String,
  out_of_order_ratio: String,
  context_results: String
)
