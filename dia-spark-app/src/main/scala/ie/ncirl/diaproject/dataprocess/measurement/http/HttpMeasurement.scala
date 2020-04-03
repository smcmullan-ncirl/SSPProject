package ie.ncirl.diaproject.dataprocess.measurement.http

case class HttpMeasurement
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
  url: String,
  headers: String,
  method: String,
  profile_1_freq: String,
  profile_2_freq: String,
  profile_3_freq: String,
  profile_4_freq: String,
  profile_unlimited: String
)

case class Parameters
(
  body: String,
  count: String,
  parameters: String,
  url: String,
  start_time: String,
  interval_sec: String,
  priority: String,
  headers: String,
  end_time: String,
  key: String,
  `type`: String,
  method: String,
  context_interval_sec: String,
  sensitive: String,
  sample_byte_interval: String,
  resolved_ip: String
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
  country_code:String,
  wifi_ip_address: String
)

case class DeviceInfo
(
  model: String,
  os: String,
  manufacturer: String,
  tac: String,
  user: String,
  id: String
)

case class Location
(
  latitude: String,
  longitude: String
)

case class Values
(
  body: String,
  code: String,
  MeasurementLongitude: String,
  body_len: String,
  CalledByLocation: String,
  time_ms: String,
  MeasurementLatitude: String,
  headers: String,
  headers_len: String,
  error: String,
  MobilePktRecv: String,
  currentBatteryLevel: String,
  MobileBytesRecv: String,
  contextTimestamp: String,
  MobileBytesSend: String,
  currentRssi: String,
  MobilePktSend: String,
  contextMeasurementInterval: String,
  context_results: String,
  content_length: String,
  status_code: String,
  samples: String
)

