package ie.ncirl.diaproject.dataprocess.measurement.ping

case class PingMeasurement
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
  location_update_distance: String,
  target: String,
  trigger_location_update: String,
  packet_size_byte: String,
  ping_timeout_sec: String,
  profile_1_freq: String,
  profile_2_freq: String,
  profile_3_freq: String,
  profile_4_freq: String,
  profile_unlimited: String
)

case class Parameters
(
  count: String,
  target: String,
  parameters: String,
  start_time: String,
  interval_sec: String,
  ping_timeout_sec: String,
  priority: String,
  packet_size_byte: String,
  ping_exe: String,
  key: String,
  `type`: String,
  end_time: String,
  myspeedtest: String,
  ping: String,
  options: String,
  context_interval_sec: String,
  ping_time_to_live: String,
  ping_icmp_interval_sec: String,
  sensitive: String,
  ping_method: String,
  use_https: String,
  port: String
)

case class DeviceProperties
(
  battery_level: String,
  cell_info: String,
  timestamp: String,
  network_type: String,
  os_version: String,
  device_info: DeviceInfo,
  carrier: String,
  location: Location,
  rssi: String,
  app_version: String,
  location_type: String,
  is_battery_charging: String,
  host_apps: Array[String],
  registration_id: String,
  mobilyzer_version: String,
  request_app: String,
  ssid: String,
  bssid: String,
  cell_rssi: String,
  wifi_ip_address: String,
  country_code: String
)

case class DeviceInfo
(
  model: String,
  os: String,
  manufacturer: String,
  tac: String,
  id: String,
  user: String
)

case class Location
(
  latitude: String,
  longitude: String
)

case class Values
(
  packets_sent: String,
  packet_loss: String,
  mean_rtt_ms: String,
  CalledByLocation: String,
  MeasurementLatitude: String,
  max_rtt_ms: String,
  target_ip: String,
  stddev_rtt_ms: String,
  min_rtt_ms: String,
  MeasurementLongitude: String,
  filtered_mean_rtt_ms: String,
  error: String,
  ping_method: String,
  battery: String,
  src_ip: String,
  dst_ip: String,
  measure: String,
  time: String,
  context_results: String,
  mptcp_config: String
)
