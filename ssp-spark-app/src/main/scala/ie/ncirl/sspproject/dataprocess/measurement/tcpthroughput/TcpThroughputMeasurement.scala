package ie.ncirl.sspproject.dataprocess.measurement.tcpthroughput

import ie.ncirl.sspproject.dataprocess.measurement.DeviceProperties

case class TcpThroughputMeasurement
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
  duration_period_sec: String,
  data_limit_mb_down: String,
  target: String,
  data_limit_mb_up: String,
  dir_up: String,
  profile_1_freq: String,
  profile_2_freq: String,
  profile_3_freq: String,
  profile_4_freq: String,
  profile_unlimited: String,
  context_interval_sec: String
)

case class Parameters
(
  pkt_size_up_bytes: String,
  count: String,
  key: String,
  parameters: String,
  slow_start_period_ms: String,
  uplink_finish_msg: String,
  start_time: String,
  port_uplink: String,
  interval_sec: String,
  priority: String,
  dir_up: String,
  end_time: String,
  down_data_limit_bytes: String,
  up_data_limit_bytes: String,
  port_downlink: String,
  sample_period_ms: String,
  `type`: String,
  duration_period_ms: String,
  tcp_timeout_ms: String,
  target: String,
  data_limit_bytes_up: String,
  data_limit_bytes_down: String,
  port_config: String,
  tcp_timeout_sec: String,
  data_limit_mb_up: String,
  duration_period_sec: String,
  data_limit_mb_down: String,
  slow_start_period_sec: String,
  sample_period_sec: String,
  context_interval_sec: String
)

case class Values
(
  throught: String,
  speed: String,
  error: String,
  speedAll: String,
  speedDisplay: String,
  duration: String,
  data_limit_exceeded: String,
  server_version: String,
  tcp_speed_results: String,
  total_data_sent_received: String,
  context_results: String,
  mptcp_config: String
)
